package com.vuejpa.demo.jwt.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenResponseDTO {

	private String accessToken;

	private String refreshToken;

	private String tokenType = "Bearer ";
	
	private String userName;

	public TokenResponseDTO(String userName, String accessToken, String refreshToken) {
		this.userName = userName;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

}
