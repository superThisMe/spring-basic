package com.springboard.service;

import java.util.HashMap;
import java.util.List;

import com.springboard.mapper.BoardMapper;
import com.springboard.repository.BoardDao;
import com.springboard.vo.BoardVO;

import lombok.Setter;

public class BoardServiceImpl implements BoardService {
	
	// setter 메서드 이용한 의존성 주입 (root-context.xml -> property)
	@Setter // lombok 이 setter 메서드 만드는 어노테이션
	private BoardDao boardDao;
	
	@Setter
	private BoardMapper boardMapper;
	
	@Override
	public int writeBoard(BoardVO board) {
		
		// int newBno = boardDao.insertBoard(board);
		// return newBno;
		
		boardMapper.insertBoard(board);
		return board.getBno();
		
	}

	@Override
	public List<BoardVO> findBoard() {

		//return boardDao.selectBoard();
		return boardMapper.selectBoard();
	}
	
	@Override
	public List<BoardVO> findBoardWithPaging(HashMap<String, Object> params) {
		
		return boardMapper.selectBoardWithPaging(params);
		
	}


	@Override
	public BoardVO findBoardByBno(int bno) {
		
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
	public int findBoardCount(HashMap<String, Object> params) {

		return boardMapper.selectBoardCount(params);

	}
	
}














