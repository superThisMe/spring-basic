package com.springboard.mapper;

import java.util.List;

import com.springboard.vo.BoardVO;

// interface의 package와 name은 board-mapper.xml의 namespace와 일치하도록 설정
public interface BoardMapper {

	// interface의 method는 board-mapper.xml의 select, insert 등의 요소 정의와 일치하도록 작성
	List<BoardVO> selectBoard();
	void insertBoard(BoardVO board);
	BoardVO selectBoardByBno(int bno);
	
}