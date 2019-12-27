package com.springboard.repository;

import java.util.HashMap;
import java.util.List;

import com.springboard.vo.BoardVO;

public interface BoardDao {

	int insertBoard(BoardVO board);
	
//	List<BoardVO> selectBoard();
	List<BoardVO> selectBoardWithPaging(HashMap<String, Object> params);
	
}
