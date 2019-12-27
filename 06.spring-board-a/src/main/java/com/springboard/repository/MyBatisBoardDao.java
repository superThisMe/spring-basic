package com.springboard.repository;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.springboard.vo.BoardVO;

import lombok.Setter;

public class MyBatisBoardDao implements BoardDao {

	private final String MAPPER = "com.springboard.mapper.BoardMapper.";
	
	@Setter
	private SqlSessionTemplate sessionTemplate;

	@Override
	public int insertBoard(BoardVO board) {

		sessionTemplate.insert(/*"com.springboard.mapper.BoardMapper.insertBoard", */MAPPER + "insertBoard", board);
		
		return board.getBno();
	}

//	@Override
//	public List<BoardVO> selectBoard() {
//
//		List<BoardVO> boards = sessionTemplate.selectList(/* "com.springboard.mapper.BoardMapper.selectBoard" */MAPPER + "selectBoard");
//		
//		return boards;
//	}

	@Override
	public List<BoardVO> selectBoardWithPaging(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

}
