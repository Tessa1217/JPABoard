package com.vuejpa.demo.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vuejpa.demo.board.entity.BoardRequestDTO;
import com.vuejpa.demo.board.entity.BoardResponseDTO;
import com.vuejpa.demo.board.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	private final BoardService boardService;
	
	// 생성자 주입
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	// 게시글 목록 조회
	@RequestMapping(value="/selectBoardList.do", method=RequestMethod.POST)
	@ResponseBody
	public List<BoardResponseDTO> selectBoardList() {
		return boardService.selectBoardList();
	}

	// 게시물 상세조회
	@RequestMapping(value="/selectBoardDetail.do")
	@ResponseBody
	public BoardResponseDTO selectBoardDetail(@RequestParam("id") Long id) {
		return boardService.selectBoardDetail(id);
	}
	
	// 게시물 정보 조회
	@RequestMapping(value="/selectBoard.do")
	@ResponseBody
	public BoardResponseDTO selectBoard(@RequestParam("id") Long id) {
		return boardService.selectBoard(id);
	}
	
	// 게시물 등록
	@RequestMapping(value="/insertBoard.do", method=RequestMethod.POST)
	@ResponseBody
	public Long insertBoard(@RequestBody BoardRequestDTO request) {
		return boardService.insertBoard(request);
	}
	
	// 게시물 수정 
	@RequestMapping(value="/updateBoard.do/{id}", method=RequestMethod.POST)
	@ResponseBody
	public Long updateBoard(@PathVariable("id")Long id, @RequestBody BoardRequestDTO request) {
		return boardService.updateBoard(request, id);
	}
	
	// 게시물 삭제
	@RequestMapping(value="/deleteBoard.do/{id}", method=RequestMethod.POST)
	@ResponseBody
	public String deleteBoard(@PathVariable("id")Long id) {
		boardService.deleteBoard(id);
		return "success";
	}

}
