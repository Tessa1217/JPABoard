package com.vuejpa.demo.board.entity;

import com.vuejpa.demo.common.entity.BaseEntity;
import com.vuejpa.demo.user.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name="TBL_BOARD")
@SequenceGenerator(
	name="SEQ_GENERATOR",
	sequenceName = "TBL_BOARD_ID_SEQ",
	allocationSize = 1
)
public class Board extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GENERATOR")
	@Column(name="BOARD_ID")
	private Long id;
	
	@Column(name="BOARD_TITLE", nullable=false)
	private String title;
	
	@Lob
	@Column(name="BOARD_CONTENT")
	private String content;
	
	@Column(name="BOARD_VIEW_CNT")
	private Integer viewCnt;
	
	@Column(name="BOARD_DEL_YN")
	private String delYn;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private User user;
	
	@Builder
	public Board(String title, String content, Integer viewCnt, String delYn) {
		this.title = title;
		this.content = content;
		this.viewCnt = viewCnt;
		this.delYn = delYn;
	}
	
	public void updateBoard(String title, String content) {
		this.title = title;
		this.content = content;
	}
	
	public void updateViewCnt(Integer viewCnt) {
		this.viewCnt = viewCnt;
	}
	
	public void deleteBoard(String delYn) {
		this.delYn = delYn;
	}
	
}
