package com.vuejpa.demo.comment.entity;

import com.vuejpa.demo.board.entity.Board;
import com.vuejpa.demo.common.entity.BaseEntity;
import com.vuejpa.demo.user.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBL_COMMENT")
@SequenceGenerator(
		name="SEQ_GENERATOR",
		sequenceName = "TBL_COMMENT_ID_SEQ",
		allocationSize = 1
	)
public class Comment extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GENERATOR")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="BOARD_ID")
	private Board board;
	
	private String content;
	
	private String delYn;
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;
	
	public void updateComment(String content) {
		this.content = content;
	}
	
	public void deleteComment() {
		this.delYn = "Y";
	}

}
