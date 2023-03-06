package com.vuejpa.demo.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="TBL_COMM_AUTHORITY")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Authority {
	
	@Id
	@Column(name="AUTHORITY_CODE")
	private String code;
	
	@Column(name="AUTHORITY_DESCRIPTION")
	private String description;
}
