package com.vuejpa.demo.board.repository;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.vuejpa.demo.board.entity.Board;
import com.vuejpa.demo.board.entity.QBoard;

public class BoardRepositoryImpl implements BoardRepositoryCustom {

	private final JPAQueryFactory queryFactory;
	
	public BoardRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	QBoard board = QBoard.board;
	

	@Override
	public PageImpl<Board> getBoardList(Pageable pageable) {
		List<Board> boardList = queryFactory.select(board)
				                            .from(board)
				                            .where(board.delYn.eq("N"))
				                            .offset(pageable.getOffset())
				                            .limit(pageable.getPageSize())
				                            .fetch();
		Long count = queryFactory.select(board.count())
				                 .from(board)
				                 .fetchOne();
		return new PageImpl<>(boardList, pageable, count);
	}
	
	
}
