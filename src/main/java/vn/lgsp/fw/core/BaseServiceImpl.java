package vn.lgsp.fw.core;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BaseServiceImpl<T extends BaseEntity<T>> implements BaseService<T>{

	@Override
	public Page<T> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public T findOneById(Long id) {
		return null;
	}

	@Override
	public T save(T entity) {
		return null;
	}

	@Override
	public T update(Long id, T entity) {
		return null;
	}

	@Override
	public void delete(Long id) {
	}

	

}
