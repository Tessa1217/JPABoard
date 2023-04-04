package com.vuejpa.demo.comment.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vuejpa.demo.comment.entity.CommentRequestDTO;
import com.vuejpa.demo.comment.service.CommentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {
	
	private final CommentService commentService;
	
	@RequestMapping(value="/insertComment.do", method=RequestMethod.POST)
	public Map<String, Object> insertComment(Authentication authentication, 
			              					 @RequestBody CommentRequestDTO requestDTO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Long commentId = commentService.insertComment(authentication, requestDTO);
		if(commentId == null) {
			resultMap.put("result", "failed");
		} else {
			resultMap.put("result", "success");
		}
		return resultMap;
	}

}