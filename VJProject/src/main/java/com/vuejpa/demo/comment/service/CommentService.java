package com.vuejpa.demo.comment.service;

import org.springframework.stereotype.Service;

import com.vuejpa.demo.comment.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {
	
	private final CommentRepository commentRepository;
	


}
