package vn.lgsp.core.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
@Transactional(readOnly=true)
public interface CustomJpaRepository <T,ID extends Serializable> extends JpaRepository<T, ID>{

	T findByIdAndDeletedFalse(@Param("id") ID id);

	boolean existsByIdAndDeletedFalse(@Param("id")ID id);
	
}
