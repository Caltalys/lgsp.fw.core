package vn.lgsp.fw.core.repository;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.QSort;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.data.repository.support.PageableExecutionUtils.TotalSupplier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.SystemPropertyUtils;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;

@NoRepositoryBean
@Transactional(readOnly=true)
public class BaseRepositoryImpl<T, ID extends Serializable> 
	extends QueryDslJpaRepository<T, ID> implements BaseRepository<T, ID>{

	private static final Logger log = LoggerFactory.getLogger(BaseRepositoryImpl.class);
	
	private EntityManager em;
	private final EntityPath<T> path;
	
    private final Querydsl querydsl;
    private final PathBuilder<T> builder;
    private static final EntityPathResolver DEFAULT_ENTITY_PATH_RESOLVER = SimpleEntityPathResolver.INSTANCE;

    /**
	 * Creates a new {@link BaseRepository} from the given domain class and {@link EntityManager}. This will use
	 * the {@link SimpleEntityPathResolver} to translate the given domain class into an {@link EntityPath}.
	 * 
	 * @param entityInformation must not be {@literal null}.
	 * @param entityManager must not be {@literal null}.
	 */
	public BaseRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.em = entityManager;
        this.path = DEFAULT_ENTITY_PATH_RESOLVER.createPath(entityInformation.getJavaType());
        this.builder = new PathBuilder<>(path.getType(), path.getMetadata());
        this.querydsl = new Querydsl(entityManager, builder);
	}

	@Override
	public Class<T> getDomainClass() {
		return super.getDomainClass();
	}

	@Override
	public Page<T> findPage(Predicate predicate, Pageable pageable, OrderSpecifier<?>... orders) {
		final JPQLQuery<?> countQuery = createCountQuery(predicate);
		JPQLQuery<T> query = querydsl.applyPagination(pageable, createQuery(predicate).select(path));
		
		Sort sort = new QSort(orders);
		query = (JPQLQuery<T>) querydsl.applySorting(sort, query);

		return PageableExecutionUtils.getPage(query.fetch(), pageable, new TotalSupplier() {

			@Override
			public long get() {
				return countQuery.fetchCount();
			}
		});
	}

	@Override
	public List<T> findAll(Predicate predicate, Pageable pageable, OrderSpecifier<?>... orders) {
		JPQLQuery<T> query = querydsl.applyPagination(pageable, createQuery(predicate).select(path));
		
		Sort sort = new QSort(orders);
		query = (JPQLQuery<T>) querydsl.applySorting(sort, query);

        List<T> entities = query.fetch();

        return entities;
	}

	@Override
	public T findOneById(ID id) {
		EntityPath<?> ePath = getEntityPath();
		final JPAQuery<T> query = getQuery(ePath);
		query.where(Expressions.numberPath(Long.class, ePath, "id").eq((Long)id));
		return query.fetchFirst();
	}

	@Override
	public boolean existsById(ID id) {
		EntityPath<?> ePath = getEntityPath();
		final JPAQuery<T> query = getQuery(ePath);
		query.where(Expressions.numberPath(Long.class, ePath, "id").eq((Long)id));
		return query.fetchCount()==1L;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public EntityPath<T> getEntityPath() {
		final String path = StringUtils.uncapitalize(getDomainClass().getSimpleName());
		EntityPath<T> ePath = null;
		try {
			final Class<?> qclass = Class.forName(getDomainClass().getPackage().getName() + ".Q" + getDomainClass().getSimpleName());
			Field field = FieldUtils.getDeclaredField(qclass, path + "1");
			if (field == null) {
				field = FieldUtils.getDeclaredField(qclass, path);
			}
			if (field != null) {
				ePath = (EntityPath<T>) field.get(null);
			}
		} catch (final IllegalAccessException e) {
			log.debug(e.getMessage());
		} catch (ClassNotFoundException e) {
			log.debug(e.getMessage());
		}
		if (ePath == null) {
			ePath = new EntityPathBase<T>(getDomainClass(), path);
		}
		log.info(ePath.getClass().getName());
		return ePath;
	}

	private JPAQuery<T> getQuery(EntityPath<?> path) {
		final JPAQuery<T> query = new JPAQuery<T>(em)
				.setHint("org.hibernate.cacheable", SystemPropertyUtils.resolvePlaceholders("${conf.default.cacheable:true}"))
				.from(path);
		if (MethodUtils.getAccessibleMethod(getDomainClass(), "isDeleted", new Class<?>[0]) != null) {
			query.where(Expressions.booleanPath(path, "deleted").isFalse());
		}
		return query;
	}


	
}
