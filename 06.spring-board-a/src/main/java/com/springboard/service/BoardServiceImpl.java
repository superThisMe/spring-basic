package com.springboard.service;

import java.util.HashMap;
import java.util.List;

import com.springboard.mapper.BoardMapper;
import com.springboard.repository.BoardDao;
import com.springboard.vo.BoardVO;

import lombok.Setter;

public class BoardServiceImpl implements BoardService {

	@Setter
	private BoardDao boardDao;
	
	@Setter
	private BoardMapper boardMapper;

	@Override
	public int writeBoard(BoardVO board) {

//		int newBno = boardDao.insertBoard(board);
//		return newBno;
		
		// Dao를 거치지 않고 Service에서 직접 Mapper 호출
		boardMapper.insertBoard(board);
		return board.getBno();
	}

//	@Override
//	public List<BoardVO> findBoard() {
//
////		List<BoardVO> boards = boardDao.selectBoard();
//		
//		// Dao를 거치지 않고 Service에서 직접 Mapper 호출
//		List<BoardVO> boards = boardMapper.selectBoard();
//
//		return boards;
//	}

	@Override
	public List<BoardVO> findBoardWithPaging(HashMap<String, Object> params) {
		
		return boardMapper.selectBoardWithPaging(params);
	}

	@Override
	public BoardVO fintBoardByBno(int bno) {
		
		return boardMapper.selectBoardByBno(bno);
	}

	@Override
	public void deleteBoard(int bno) {
		
		boardMapper.deleteBoard(bno);		
	}

	@Override
	public void updateBoard(BoardVO board) {
		
		boardMapper.updateBoard(board);
	}

	@Override
	public void increaseReadCount(int bno) {
		
		boardMapper.updateReadCount(bno);
	}

	@Override
	public int findBoardCount() {
		
		return boardMapper.selectBoardCount();
	}

}
