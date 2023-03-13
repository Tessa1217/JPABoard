package com.vuejpa.demo.config;

import java.util.List;

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
	private String[] requestWhiteList = {"/",
			                             "/css/**",
			                             "/js/**",
			                             "/favicon/**",
			                             "/users/**",
			                             };
	
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.httpBasic().disable();
		
		http.csrf().disable();
		
		http.cors().configurationSource(corsConfigurationSource());
		
		http.sessionManagement()
		    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.authorizeHttpRequests()
		    .requestMatchers(requestWhiteList).permitAll()
		    .anyRequest().authenticated();
		
		http.apply(new JwtSecurityConfig(tokenProvider))
		    .and()
		    .exceptionHandling()
		    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
		    .accessDeniedHandler(jwtAccessDeniedHandler);
		   
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
