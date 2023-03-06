package com.vuejpa.demo.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
	USER("ROLE_USER"),
	ADMIN("ROLE_USER,ROLE_ADMIN"),
	SUPERADMIN("ROLE_USER,ROLE_ADMIN,ROLE_SUPERADMIN");
	
	private String value;
	
}
