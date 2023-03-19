package com.vuejpa.demo.board.entity;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class BoardResponseDTO {
	
	private Long id;
	
	private String title;
	
	private String content;
	
	private Integer viewCnt;
	
	private String delYn;
	
	private LocalDateTime regDate;
	
	private LocalDateTime modDate;
	
	private String userName;
	
	private String writer;
	
	public BoardResponseDTO(Board entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.content = entity.getContent();
		this.viewCnt = entity.getViewCnt();
		this.delYn = entity.getDelYn();
		this.regDate = entity.getRegDate();
		this.modDate = entity.getModDate();
		this.userName = entity.getUser().getName();
		this.writer = entity.getUser().getNickname();
	}

}
