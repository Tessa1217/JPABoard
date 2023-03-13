package com.vuejpa.demo.user.entity;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="TBL_COMM_USERS")
@SequenceGenerator(
	name="SEQ_GENERATOR",
	sequenceName = "TBL_USER_NO_SEQ",
	allocationSize = 1
)
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GENERATOR")
	@Column(name="USER_ID")
	private Long id;
	
	@Column(name="USER_NAME", unique = true)
	private String name;
	
	@Column(name="USER_PASSWORD")
	private String password;
	
	@Column(name="USER_NICKNAME")
	private String nickname;
	
	@Enumerated(value = EnumType.STRING)
	@Convert(converter = UserRoleConverter.class)
	@Column(name="USER_ROLE")
	private Role role;
	
	public void encodedPassword(String password) {
		this.password = password;
	}
	
	public void addRole(Role role) {
		this.role = role;
	}

}
