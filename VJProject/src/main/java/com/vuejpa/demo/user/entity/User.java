package com.vuejpa.demo.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
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
	@Column(name="USER_NO")
	private Long no;
	
	@Column(name="USER_ID")
	private String id;
	
	@Convert(converter = PasswordConverter.class)
	@Column(name="USER_PASSWORD")
	private String password;
	
	@Column(name="USER_NAME")
	private String name;
	
	@Builder
	public User(String id, String password, String name) {
		this.id = id;
		this.password = password;
		this.name = name;
	}

}
