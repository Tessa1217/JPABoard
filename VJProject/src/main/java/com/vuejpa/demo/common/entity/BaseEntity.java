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
/** 공통 속성을 상속할 수 있도록 구현한 추상 클래스 */
@MappedSuperclass
/** 해당 클래스에 Auditing 기능 포함시킴 */
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
	
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime regDate;
	
	@LastModifiedDate
	@Column(insertable = false)
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
