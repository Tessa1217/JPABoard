package com.vuejpa.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.vuejpa.demo.common.util.Aes256Utils;
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
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.cors()
			.and()
		    .csrf().disable()
		    .sessionManagement()
		    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		    .and()
		    .authorizeHttpRequests()
			.requestMatchers("/", "/**").permitAll()
			.requestMatchers("/css/**").permitAll()
			.requestMatchers("/js/**").permitAll()
			.requestMatchers("/favicon/**").permitAll()
			.requestMatchers("/users/**").permitAll()
			.requestMatchers("/board").permitAll()
			.requestMatchers("/board/select**").permitAll()
		    .anyRequest().authenticated()
		    .and()
		    .exceptionHandling()
		    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
		    .accessDeniedHandler(jwtAccessDeniedHandler)
		    .and()
			.apply(new JwtSecurityConfig(tokenProvider));
		return http.build();
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");
		configuration.addAllowedOriginPattern("*");
		configuration.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", configuration);
        return source;
	}
	
}
