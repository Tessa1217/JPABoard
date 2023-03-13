package com.vuejpa.demo.jwt.entity;

import java.time.Instant;

import com.vuejpa.demo.user.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Entity
@Table(name="TBL_REFRESH_TOKEN")
@SequenceGenerator(
		name="SEQ_GENERATOR",
		sequenceName = "TBL_REFRESH_TOKEN_ID_SEQ",
		allocationSize = 1
	)
public class RefreshToken {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GENERATOR")
	private Long id;
	
	@OneToOne
	@JoinColumn(name="USER_ID")
	private User user;
	
	@Column(nullable=false, unique=true)
	private String token;
	
	@Column(nullable = false)
	private Instant expiryDate;
}
