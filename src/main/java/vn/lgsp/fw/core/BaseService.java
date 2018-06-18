package vn.lgsp.fw.core;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<T extends BaseEntity<T>> {
	
	Page<T> findAll(Pageable pageable);
	
	T findOneById(Long id);

	T save(T ethnicity);

	T update(Long id, T ethnicity) ;

	void delete(Long id);
	
}
