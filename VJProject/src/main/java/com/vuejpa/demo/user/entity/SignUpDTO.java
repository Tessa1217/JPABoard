package com.vuejpa.demo.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpDTO {
	
	private String name;
	
	private String password;
	
	private String nickname;
	
	public User toEntity() {
		return User.builder()
		    .name(this.name)
		    .password(this.password)
		    .nickname(this.nickname)
		    .build();
	}
	

}
