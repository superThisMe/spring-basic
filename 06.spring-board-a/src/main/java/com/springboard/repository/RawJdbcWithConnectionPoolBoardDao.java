package com.springboard.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import com.springboard.vo.BoardVO;

import lombok.Setter;

public class RawJdbcWithConnectionPoolBoardDao implements BoardDao {
	
//	private DataSource dataSource = new org.apache.commons.dbcp2.BasicDataSource();
	
	@Setter
	private DataSource dataSource;

	@Override
	public int insertBoard(BoardVO board) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// 1. get connection from connection pool
			conn = dataSource.getConnection();

			// 2. create command
			String sql = 
					"INSERT INTO tbl_board (bno, title, writer, content) " + 
					"VALUES (seq_board.nextval, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			
			// 3. execute command
			pstmt.executeUpdate(); // for INSERT, UPDATE, DELETE
			
			// 4. process result (if 'SELECT')
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5. close connection
			try { pstmt.close(); } catch (Exception e) {}
			try { conn.close(); } catch (Exception e) {}
		}

		return 0;
	}

//	@Override
//	public List<BoardVO> selectBoard() {
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		ArrayList<BoardVO> boards = new ArrayList<>();
//		
//		try {
//			// 1. get connection from connection pool
//			conn = dataSource.getConnection();
//
//			// 2. create command
//			String sql = 
//					"SELECT bno, title, writer, regdate, updatedate, deleted, readcount " + 
//					"FROM tbl_board " +
////					"WHERE deleted = '0' " +
//					"ORDER BY bno DESC";
//			pstmt = conn.prepareStatement(sql);
//			
//			// 3. execute command
////			pstmt.executeUpdate(); // for INSERT, UPDATE, DELETE
//			rs = pstmt.executeQuery();
//			 
//			// 4. process result (if 'SELECT')
//			while (rs.next()) {
//				BoardVO board = new BoardVO();
//				board.setBno(rs.getInt(1));
//				board.setTitle(rs.getString(2));
//				board.setWriter(rs.getString(3));
//				board.setRegDate(rs.getDate(4));
//				board.setUpdateDate(rs.getDate(5));
//				board.setDeleted(rs.getBoolean(6));
//				board.setReadCount(rs.getInt(7));
//				
//				boards.add(board);
//			}			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			// 5. close connection
//			try { rs.close(); } catch (Exception e) {}
//			try { pstmt.close(); } catch (Exception e) {}
//			try { conn.close(); } catch (Exception e) {}
//		}
//		
//		return boards;
//	}

	@Override
	public List<BoardVO> selectBoardWithPaging(HashMap<String, Object> params) {
		
		return null;
	}

}
