package com.vuejpa.demo.comment.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.vuejpa.demo.comment.entity.Comment;
import com.vuejpa.demo.comment.entity.CommentRequestDTO;
import com.vuejpa.demo.comment.repository.CommentRepository;
import com.vuejpa.demo.user.entity.User;
import com.vuejpa.demo.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {
	
	private final UserRepository userRepository;
	
	private final CommentRepository commentRepository;
	
	@Transactional
	public Long insertComment(final Authentication authentication, final CommentRequestDTO commentRequestDTO) {
		User user = userRepository.findByName(authentication.getName()).get();
		commentRequestDTO.setUser(user);
		Comment comment = commentRepository.save(commentRequestDTO.toEntity());
		return comment.getId();
	}
	


}
