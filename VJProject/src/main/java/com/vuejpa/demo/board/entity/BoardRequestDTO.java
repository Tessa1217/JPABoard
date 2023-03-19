package com.vuejpa.demo.board.entity;

import com.vuejpa.demo.user.entity.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardRequestDTO {
	
	private String title;
	
	private String content;
	
	private Integer viewCnt;
	
	private String delYn;
	
	private User user;
	
	@Builder
	public BoardRequestDTO(String title, String content, Integer viewCnt, String delYn) {
		this.title = title;
		this.content = content;
		this.viewCnt = viewCnt;
		this.delYn = delYn;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Board toEntity() {
		return Board.builder()
				    .title(title)
				    .content(content)
				    .viewCnt(viewCnt)
				    .delYn(delYn)
				    .user(user)
				    .build();
	}

}
