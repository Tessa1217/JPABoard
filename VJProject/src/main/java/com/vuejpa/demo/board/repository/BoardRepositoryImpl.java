package com.vuejpa.demo.board.repository;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.vuejpa.demo.board.entity.Board;
import com.vuejpa.demo.board.entity.QBoard;
import com.vuejpa.demo.comment.entity.QComment;
import com.vuejpa.demo.user.entity.QUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {

	private final JPAQueryFactory queryFactory;
	
	// 게시판
	QBoard board = QBoard.board;
	
	// 사용자
	QUser user = QUser.user;
	
	// 댓글
	QComment comment = QComment.comment;
	

	@Override
	public PageImpl<Board> getBoardList(Pageable pageable) {
		// 게시판 목록
		List<Board> boardList = queryFactory.selectFrom(board)
				                            .where(board.delYn.eq("N"))
				                            .leftJoin(board.user, user)
				                            .fetchJoin()
				                            .offset(pageable.getOffset())
				                            .limit(pageable.getPageSize())
				                            .orderBy(board.id.desc())
				                            .fetch();
		
		// 게시판 목록 수 
		Long count = queryFactory.select(board.count())
				                 .from(board)
				                 .where(board.delYn.eq("N"))
				                 .fetchOne();
		
		return new PageImpl<>(boardList, pageable, count);
	}
	
	@Override
	public Board getBoardDetail(Long id) {
		Board resultBoard = queryFactory.selectFrom(board)
										.where(board.id.eq(id))
					                    .leftJoin(board.user, user)
					                    .fetchJoin()
					                    .leftJoin(board.commentList, comment)
				                        .fetchOne();
		return resultBoard;
	}
	
	
}
