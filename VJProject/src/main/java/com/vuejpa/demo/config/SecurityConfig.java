package com.vuejpa.demo.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import com.vuejpa.demo.jwt.JwtAccessDeniedHandler;
import com.vuejpa.demo.jwt.JwtAuthenticationEntryPoint;
import com.vuejpa.demo.jwt.JwtSecurityConfig;
import com.vuejpa.demo.jwt.TokenProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final TokenProvider tokenProvider;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		    .csrf().disable()
		    .exceptionHandling()
		    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
		    .accessDeniedHandler(jwtAccessDeniedHandler)
		    .and()
		    .authorizeHttpRequests()
		    .requestMatchers("/").permitAll()
		    .requestMatchers("/css/**").permitAll()
		    .requestMatchers("/js/**").permitAll()
		    .requestMatchers("/favicon/**").permitAll()
		    .requestMatchers("/users/**").permitAll()
		    .requestMatchers("/board").permitAll()
		    .requestMatchers("/board/select**").permitAll()
		    .anyRequest().authenticated()
		    .and()
			.apply(new JwtSecurityConfig(tokenProvider));
		return http.build();
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080", "http://localhost:9000"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
		configuration.addAllowedHeader("*");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
	}
	
}
