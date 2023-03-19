package com.vuejpa.demo.jwt;

import java.security.Key;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.vuejpa.demo.jwt.entity.TokenResponseDTO;
import com.vuejpa.demo.jwt.service.RefreshTokenService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenProvider implements InitializingBean {
	
	// InitializingBean을 implement
	private static final String AUTHORITIES_KEY = "auth";
	
	private final String secret;
	private final long accessTokenValidityInMilliseconds;
	private final long refreshTokenValidityInMilliseconds;
	
	private final RefreshTokenService refreshTokenService;
	
	private Key key;
	
	public TokenProvider(
			@Value("${jwt.secret}") String secret,
			@Value("${jwt.access-token-validity-in-seconds}") long accessTokenValidityInSeconds,
			@Value("${jwt.refresh-token-validity-in-seconds}") long refreshTokenValidityInSeconds,
			RefreshTokenService refreshTokenService) {
				this.secret = secret;
				this.accessTokenValidityInMilliseconds = accessTokenValidityInSeconds * 1000;
				this.refreshTokenValidityInMilliseconds = refreshTokenValidityInSeconds * 1000;
				this.refreshTokenService = refreshTokenService;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// 빈 생성 후 주입 받은 secret 값을 Base64에 Decode해서 key에 할당
		byte[] keyBytes = Base64.getDecoder().decode(secret);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}
	
	public TokenResponseDTO createToken(Authentication authentication) {
		// Authorization 객체의 권한 정보를 이용해서 토큰 생성 
		String authorities = authentication.getAuthorities().stream()
				                           .map(GrantedAuthority::getAuthority)
				                           .collect(Collectors.joining(","));
		String userName = authentication.getName();
		String accessToken = createAccessToken(userName, authorities);
		String refreshToken = createRefreshToken();
		return new TokenResponseDTO(userName, accessToken, refreshToken);
	}
	
	private String createAccessToken(String userName, String authorities) {
		long now = (new Date()).getTime();
		Date validity = new Date(now + this.accessTokenValidityInMilliseconds);
		String accessToken = Jwts.builder()
                .setSubject(userName)
                .claim(AUTHORITIES_KEY, authorities)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
		return accessToken;
	}
	
	private String createRefreshToken() {
		long now = (new Date()).getTime();
		Date validity = new Date(now + this.refreshTokenValidityInMilliseconds);
		String refreshToken = Jwts.builder()
				                  .setExpiration(validity)
				                  .signWith(key, SignatureAlgorithm.HS256)
				                  .compact();
		return refreshToken;
	}
	
	public String getUserNameFromJwtToken(String token) {
		return Jwts.parserBuilder()
				   .setSigningKey(key)
				   .build()
				   .parseClaimsJws(token)
				   .getBody()
				   .getSubject();
	}
	
	public Authentication getAuthentication(String token) {
		Claims claims = Jwts
				        .parserBuilder()
				        .setSigningKey(key)
				        .build()
				        .parseClaimsJws(token)
				        .getBody();
		Collection<? extends GrantedAuthority> authorities = 
				Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
				      .map(SimpleGrantedAuthority::new)
				      .collect(Collectors.toList());
		User principal = new User(claims.getSubject(), "", authorities);
		return new UsernamePasswordAuthenticationToken(principal, token, authorities);
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			e.printStackTrace();
		} catch(ExpiredJwtException e) {
			e.printStackTrace();
		} catch (UnsupportedJwtException e) {
			e.printStackTrace();
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	

}
