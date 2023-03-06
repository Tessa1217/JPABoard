package com.vuejpa.demo.user.entity;

import lombok.Getter;

@Getter
public class UserResponseDTO {
	
	private Long id;
	
	private String name;
	
	private String nickname;
	
	public UserResponseDTO(User entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.nickname = entity.getNickname();
	}
	
	

}
