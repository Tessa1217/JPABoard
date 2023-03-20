package com.vuejpa.demo.comment.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.vuejpa.demo.board.entity.QBoard;
import com.vuejpa.demo.board.repository.BoardRepository;
import com.vuejpa.demo.comment.entity.QComment;
import com.vuejpa.demo.user.entity.QUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom {
	
	private final JPAQueryFactory queryFactory;
	
	private final BoardRepository boardRepository;
	
	private final CommentRepository commentRepository;
	
	QComment comment = QComment.comment;
	
	QBoard board = QBoard.board;
	
	QUser user = QUser.user;
	
}
