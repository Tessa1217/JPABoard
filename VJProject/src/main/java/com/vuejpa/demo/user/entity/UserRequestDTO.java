package com.vuejpa.demo.user.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequestDTO {
	
	private String id;
	
	private String password;
	
	private String name;
	
	@Builder
	public UserRequestDTO(String id, String password, String name) {
		this.id = id;
		this.password = password;
		this.name = name;
	}
	
	public User toEntity() {
		return User.builder()
		    .id(this.id)
		    .password(this.password)
		    .name(this.name)
		    .build();
	}
	

}
