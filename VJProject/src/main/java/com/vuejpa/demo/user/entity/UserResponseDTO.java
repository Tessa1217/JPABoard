package com.vuejpa.demo.user.entity;

import lombok.Getter;

@Getter
public class UserResponseDTO {
	
	private Long no;
	
	private String id;
	
	private String name;
	
	public UserResponseDTO(User entity) {
		this.no = entity.getNo();
		this.id = entity.getId();
		this.name = entity.getName();
	}
	
	

}
