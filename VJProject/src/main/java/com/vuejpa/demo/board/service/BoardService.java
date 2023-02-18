package com.vuejpa.demo.board.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.vuejpa.demo.board.entity.Board;
import com.vuejpa.demo.board.entity.BoardRequestDTO;
import com.vuejpa.demo.board.entity.BoardResponseDTO;
import com.vuejpa.demo.board.repository.BoardRepository;

import jakarta.transaction.Transactional;

@Service
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	// 생성자 주입
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	// 게시물 목록 조회
	public List<BoardResponseDTO> selectBoardList() {
		Sort sort = Sort.by(Direction.DESC, "id");
		List<Board> boardList = boardRepository.findAll(sort);
		return boardList.stream().map(BoardResponseDTO::new).collect(Collectors.toList());
	}
	
	// 게시물 정보 조회
	public BoardResponseDTO selectBoard(Long id) {
		Board entity = boardRepository.findById(id).get();
		return new BoardResponseDTO(entity);
	}
	
	// 게시물 상세조회
	@Transactional
	public BoardResponseDTO selectBoardDetail(final Long id) {
		Board entity = boardRepository.findById(id).get();
		entity.updateViewCnt(entity.getViewCnt() + 1); // 게시물 조회수 변경
		return new BoardResponseDTO(entity);
	}
	
	// 게시물 저장
	@Transactional
	public Long insertBoard(final BoardRequestDTO requestDTO) {
		Board entity = boardRepository.save(requestDTO.toEntity());
		return entity.getId();
	}
	
	// 게시물 수정
	@Transactional
	public Long updateBoard(final BoardRequestDTO requestDTO, final Long id) {
		Board entity = boardRepository.findById(id).get();
		entity.updateBoard(requestDTO.getTitle(), requestDTO.getContent());
		return entity.getId();
	}
	
	// 게시물 삭제
	@Transactional
	public void deleteBoard(final Long id) {
		Board entity = boardRepository.findById(id).get();
		String delYn = "Y";
		entity.deleteBoard(delYn);
	}
	



}
