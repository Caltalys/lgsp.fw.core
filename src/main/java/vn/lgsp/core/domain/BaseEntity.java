package vn.lgsp.core.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.domain.Persistable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * This class is an abstract superclass for all Entity classes in the
 * application. This class defines variables which are common for all entity
 * classes.
 */

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
@MappedSuperclass
public class BaseEntity<T extends BaseEntity<T>> extends Auditable<String> implements Persistable<Long>{

	private static final long serialVersionUID = 9197809217082471305L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "\"daXoa\"")
	private boolean daXoa;

	@Override
	public boolean isNew() {
		return id == null || id == 0;
	}
	
}
