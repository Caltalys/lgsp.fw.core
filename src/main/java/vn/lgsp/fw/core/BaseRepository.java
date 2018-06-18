package vn.lgsp.fw.core;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;

/**
 * Super repository for all resource. 
 * 
 * @author caltalys
 *
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
@Transactional(readOnly=true)
public interface BaseRepository<T,ID extends Serializable> extends JpaRepository<T, ID>, QueryDslPredicateExecutor<T>{

	Class<T> getDomainClass();	
	
	Page<T> findPage(@Param("predicate")Predicate predicate, Pageable pageable, OrderSpecifier<?>... orders);
	
	List<T> findAll(@Param("predicate")Predicate predicate, Pageable pageable, OrderSpecifier<?>... orders);
	
	T findOneById(@Param("id") ID id);

	boolean existsById(ID id);
	
}
