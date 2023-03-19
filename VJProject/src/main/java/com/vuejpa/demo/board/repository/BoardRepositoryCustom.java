package com.vuejpa.demo.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vuejpa.demo.board.entity.Board;

public interface BoardRepositoryCustom {

	public Page<Board> getBoardList(Pageable pageable);
	
	public Board getBoardDetail(Long id);

}
