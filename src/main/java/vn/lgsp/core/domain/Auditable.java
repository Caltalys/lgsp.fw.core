package vn.lgsp.core.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U >{

	@CreatedDate
	@Column(name = "\"ngayTao\"")
	private LocalDateTime ngayTao;
	
	@LastModifiedDate
	@Column(name = "\"ngaySua\"")
	private LocalDateTime ngaySua;
	
	@CreatedBy
	@Column(name = "\"nguoiTao\"")
	private U nguoiTao;
	
	@LastModifiedBy
	@Column(name = "\"nguoiSua\"")
	private U nguoiSua;
	
}
