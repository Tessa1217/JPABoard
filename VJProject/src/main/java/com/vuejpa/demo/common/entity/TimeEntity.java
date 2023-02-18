package com.vuejpa.demo.common.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;

@Getter
/** JPA Entity 클래스들이 해당 
 * entity 상속 시 필드도 칼럼으로 인식하도록 */
@MappedSuperclass
/** 해당 클래스에 Auditing 기능 포함시킴 */
@EntityListeners(AuditingEntityListener.class)
public abstract class TimeEntity {
	
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime regDate;
	
	@LastModifiedDate
	private LocalDateTime modDate;
	
	@PrePersist
	public void prePersist() {
		regDate = LocalDateTime.now();
	}
	
	@PreUpdate
	public void preUpdate() {
		modDate = LocalDateTime.now();
	}

}
