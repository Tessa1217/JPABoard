package com.vuejpa.demo.comment.entity;

import com.vuejpa.demo.board.entity.Board;
import com.vuejpa.demo.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CommentRequestDTO {
	
	private Board board;
	
	private String content;
	
	private User user;
	
	public Comment toEntity() {
		return Comment.builder()
				      .board(board)
				      .content(content)
				      .delYn("N")
				      .user(user)
				      .build();
	}

}
