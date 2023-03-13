package com.vuejpa.demo.jwt.service;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vuejpa.demo.jwt.TokenRefreshException;
import com.vuejpa.demo.jwt.entity.RefreshToken;
import com.vuejpa.demo.jwt.repository.RefreshTokenRepository;
import com.vuejpa.demo.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {

	@Value("${jwt.refresh-token-validity-in-seconds}")
	private Long refreshTokenDurationMx;

	private final RefreshTokenRepository refreshTokenRepository;

	private final UserRepository userRepository;

	public Optional<RefreshToken> findByToken(String token) {
		return refreshTokenRepository.findByToken(token);
	}

	public RefreshToken verifyExpiration(RefreshToken token) {
		if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.delete(token);
			throw new TokenRefreshException(token.getToken(),
					"Refresh token was expired. Please make a new signin request");
		}

		return token;
	}

}
