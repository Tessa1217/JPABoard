package com.vuejpa.demo.user.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
	
	// 권한 변경 (2023.03.19)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "TBL_COMM_USER_ROLES",
	           joinColumns = @JoinColumn(name = "USER_ID"),
	           inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	private Set<Role> roles = new HashSet<>();
	
	public void encodedPassword(String password) {
		this.password = password;
	}
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
