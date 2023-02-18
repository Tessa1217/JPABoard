package com.vuejpa.demo.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vuejpa.demo.board.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{
	
}
