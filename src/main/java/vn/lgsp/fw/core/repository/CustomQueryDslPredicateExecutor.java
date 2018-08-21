package vn.lgsp.fw.core.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;

@NoRepositoryBean
@Transactional(readOnly=true)
public interface CustomQueryDslPredicateExecutor<T, ID> extends QueryDslPredicateExecutor<T>{

	//Class<T> getDomainClass();	
	
	EntityPath<T> getPath();
	
	Page<T> findPage(@Param("predicate") Predicate predicate, Pageable pageable, OrderSpecifier<?>... orders);
	
	List<T> findAll(@Param("predicate") Predicate predicate, Pageable pageable, OrderSpecifier<?>... orders);
	
}
