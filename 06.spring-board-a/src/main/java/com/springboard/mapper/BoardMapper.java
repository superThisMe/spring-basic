package com.springboard.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springboard.vo.BoardVO;

// 인터페이스의 패키지와 이름은 
// board-mapper.xml의 namespace와 일치하도록 작성
public interface BoardMapper {
	
	// 인터페이스의 메서드는 board-mapper.xml의 
	// select, insert 등의 요소 정의와 일치하도록 작성
	
	void insertBoard(BoardVO board);
	List<BoardVO> selectBoard();
	List<BoardVO> selectBoardWithPaging(HashMap<String, Object> params);	
	BoardVO selectBoardByBno(int bno);
	void deleteBoard(int bno);
	void updateBoard(BoardVO board);
	void updateReadCount(int bno);
	int selectBoardCount(HashMap<String, Object> params);
	

}








