package com.springboard.service;

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

	@Override
	public List<BoardVO> findBoard() {

//		List<BoardVO> boards = boardDao.selectBoard();
		
		// Dao를 거치지 않고 Service에서 직접 Mapper 호출
		List<BoardVO> boards = boardMapper.selectBoard();

		return boards;
	}

	@Override
	public BoardVO fintBoardByBno(int bno) {
		
		return boardMapper.selectBoardByBno(bno);
	}

}
