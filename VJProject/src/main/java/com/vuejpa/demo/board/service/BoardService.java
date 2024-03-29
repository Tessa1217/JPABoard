package com.vuejpa.demo.board.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.vuejpa.demo.board.entity.Board;
import com.vuejpa.demo.board.entity.BoardRequestDTO;
import com.vuejpa.demo.board.entity.BoardResponseDTO;
import com.vuejpa.demo.board.repository.BoardRepository;
import com.vuejpa.demo.user.entity.User;
import com.vuejpa.demo.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	
	/** 게시판 Repository */
	private final BoardRepository boardRepository;
	
	/** 회원 Repository */
	private final UserRepository userRepository;
	
	// 게시물 목록 조회 (페이징)
	public Page<Board> selectBoardList(Pageable pageable) {
		// 삭제 게시물은 제외
		return boardRepository.getBoardList(pageable);
	}
	
	// 게시물 정보 조회
	public BoardResponseDTO selectBoard(Long id) {
		Board entity = boardRepository.findById(id).get();
		return new BoardResponseDTO(entity);
	}
	
	// 게시물 상세조회
	@Transactional
	public BoardResponseDTO selectBoardDetail(final Long id) {
		Board entity = boardRepository.getBoardDetail(id);
		entity.updateViewCnt(entity.getViewCnt() + 1); // 게시물 조회수 변경
		return new BoardResponseDTO(entity);
	}
	
	// 게시물 저장
	@Transactional
	public Long insertBoard(final Authentication authentication, final BoardRequestDTO requestDTO) {
		User user = userRepository.findByName(authentication.getName()).get();
		requestDTO.setUser(user);
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
