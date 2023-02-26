package com.vuejpa.demo.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vuejpa.demo.board.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {
	
	// 삭제 여부 조건으로 페이징하여 게시물 목록 조회
	public Page<Board> findByDelYn(String delYn, Pageable pageable);
	
}
