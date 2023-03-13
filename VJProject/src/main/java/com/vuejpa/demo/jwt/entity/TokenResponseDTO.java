package com.vuejpa.demo.jwt.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenResponseDTO {

	private String accessToken;

	private String refreshToken;

	private String tokenType = "Bearer ";

	public TokenResponseDTO(String accessToken, String refreshToken) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

}
