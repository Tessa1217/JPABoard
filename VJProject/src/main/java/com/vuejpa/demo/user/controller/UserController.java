package com.vuejpa.demo.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vuejpa.demo.jwt.JwtFilter;
import com.vuejpa.demo.jwt.TokenProvider;
import com.vuejpa.demo.jwt.entity.TokenResponseDTO;
import com.vuejpa.demo.jwt.service.RefreshTokenService;
import com.vuejpa.demo.user.entity.LoginDTO;
import com.vuejpa.demo.user.entity.SignUpDTO;
import com.vuejpa.demo.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(name = "/users", method = RequestMethod.POST)
public class UserController {
	
	private final TokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final UserService userService;
	private final RefreshTokenService refreshTokenService;
	
	@RequestMapping(value="/users/signUp.do")
	public Map<String, Object> signUp(@RequestBody SignUpDTO signUpDTO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Long id = userService.signUp(signUpDTO);
		if(id == null) {
			resultMap.put("result", "failed");
			resultMap.put("msg", "회원가입에 실패하셨습니다.");
		} else {
			resultMap.put("result", "success");
			resultMap.put("msg", "회원가입에 성공하셨습니다.");
		}
		return resultMap;
	}
	
	@RequestMapping(value="/users/login.do")
	public ResponseEntity<TokenResponseDTO> login(@RequestBody LoginDTO loginDTO) {

		UsernamePasswordAuthenticationToken authenticationToken =
				             new UsernamePasswordAuthenticationToken(loginDTO.getName(), loginDTO.getPassword());
		// AuthenticationToken을 이용하여 authenticate가 실행될 때 UserService의 loadUserByUsername 실행
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		TokenResponseDTO tokenResponseDTO = tokenProvider.createToken(authentication);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, tokenResponseDTO.getTokenType() + tokenResponseDTO.getAccessToken());
		return new ResponseEntity<>(tokenResponseDTO, httpHeaders, HttpStatus.OK);
	}
	

}
