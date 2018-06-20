package vn.lgsp.fw.core;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;

public interface BaseService<T extends BaseEntity<T>> {
	
	Page<T> findPage(Predicate predicate, Pageable pageable, OrderSpecifier<?>... orders);
	
	List<T> findAll(Predicate predicate, Pageable pageable, OrderSpecifier<?>... orders);
	
	T findOneById(Long id);

	boolean existsById(Long id);
	
	T save(T entity);

	T update(Long id, T entity) ;

	void delete(Long id);
	
}
