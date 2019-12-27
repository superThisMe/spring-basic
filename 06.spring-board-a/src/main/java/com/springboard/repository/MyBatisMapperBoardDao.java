package com.springboard.repository;

import java.util.HashMap;
import java.util.List;

import com.springboard.mapper.BoardMapper;
import com.springboard.vo.BoardVO;

import lombok.Setter;

public class MyBatisMapperBoardDao implements BoardDao {
	
	@Setter
	private BoardMapper boardMapper;

	@Override
	public int insertBoard(BoardVO board) {
		
		boardMapper.insertBoard(board);
		
		return board.getBno();
	}

//	@Override
//	public List<BoardVO> selectBoard() {
//
//		List<BoardVO> boards = boardMapper.selectBoard();
//		
//		return boards;
//	}
	
	@Override
	public List<BoardVO> selectBoardWithPaging(HashMap<String, Object> params) {
		
		List<BoardVO> boards = boardMapper.selectBoardWithPaging(params);
		
		return boards;
	}

}
