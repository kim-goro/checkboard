package kr.koreait.myboard.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.koreait.myboard.vo.BoardVO;

public class BoardDAO {
	
	//------------------------------------------------- Create (insert)
	public static int insertBoard(BoardVO param) {
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		String sql = " INSERT INTO t_board "
				+ " (title, content, i_user) "
				+ " VALUES "
				+ " (?, ?, ?) ";
		
		try {
			con = DbBridge.getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, param.getTitle());
			ps.setString(2, param.getContent());
			ps.setInt(3, param.getI_user());			
			
			result = ps.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DbBridge.close(con,  ps);			
		}
		
		return result;
	}
	
	
	//------------------------------------------------- Read (select)
	public static BoardVO getBoard(BoardVO param) {
		BoardVO vo = null;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " SELECT "
				+ " A.title, A.hits, A.r_dt, A.m_dt "
				+ " , A.content, A.i_user, B.u_nickname "
				+ " FROM t_board A "
				+ " INNER JOIN t_user B "
				+ " ON A.i_user = B.i_user "
				+ " WHERE A.i_board = ? "
				+ " ORDER BY r_dt DESC ";
		
		try {
			con = DbBridge.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getI_board());			
			rs = ps.executeQuery();
			
			while(rs.next()) {				
				String title = rs.getString("title");
				String content = rs.getString("content");
				int i_user = rs.getInt("i_user");
				int hits = rs.getInt("hits");
				String r_dt = rs.getString("r_dt");
				String m_dt = rs.getString("m_dt");
				String u_nickname = rs.getString("u_nickname");
								
				vo = new BoardVO();
				vo.setI_board(param.getI_board());
				vo.setTitle(title);
				vo.setContent(content);
				vo.setI_user(i_user);
				vo.setHits(hits);
				vo.setR_dt(r_dt);
				vo.setM_dt(m_dt);
				vo.setU_nickname(u_nickname);				
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbBridge.close(con, ps, rs);
		}
		
		return vo;
	}
	
	public static int getTotalPageCnt(BoardVO param) {
		int totalPageCnt = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " SELECT CEIL(COUNT(i_board) / ?) AS cnt "
				+ " FROM t_board ";
		
		if(param.getSearch() != null) {
			sql += " WHERE title LIKE '%" + param.getSearch() + "%' ";
		}		
		
		try {
			con = DbBridge.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getRowCnt());
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				totalPageCnt = rs.getInt("cnt");
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbBridge.close(con, ps, rs);
		}
		
		return totalPageCnt;
	}
	
	public static List<BoardVO> getBoardList(BoardVO param) {
		List<BoardVO> list = new ArrayList();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " SELECT A.i_board, A.title, A.hits, A.r_dt "
				+ " , B.u_nickname , C.i_user, ifnull(C.img, '') as img "
				+ " FROM t_board A "
				+ " INNER JOIN t_user B "
				+ " ON A.i_user = B.i_user "
				+ " LEFT JOIN t_user_img C " 
 				+ " ON A.i_user = C.i_user "
				+ " AND C.seq = 1 ";
			
		if(param.getSearch() != null) {
			sql += " WHERE title LIKE '%" + param.getSearch() + "%' ";
		}		
		sql += " ORDER BY r_dt DESC LIMIT ?, ? ";
		
		try {
			con = DbBridge.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getsIdx());
			ps.setInt(2, param.getRowCnt());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int i_board = rs.getInt("i_board");
				String title = rs.getString("title");
				int hits = rs.getInt("hits");
				String r_dt = rs.getString("r_dt");
				int i_user = rs.getInt("i_user");
				String u_nickname = rs.getString("u_nickname");
				String img = rs.getString("img");
				
				BoardVO vo = new BoardVO();
				vo.setI_board(i_board);
				vo.setTitle(title);
				vo.setHits(hits);
				vo.setR_dt(r_dt);
				vo.setI_user(i_user);
				vo.setU_nickname(u_nickname);
				vo.setImg(img);
				
				list.add(vo);
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbBridge.close(con, ps, rs);
		}
		
		return list;
	}
	
	
	
	//------------------------------------------------- Update (update)
	//조회수 수정
	public static int updateBoardHits(BoardVO param) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " UPDATE t_board "
				+ " SET hits = hits + 1"
				+ " WHERE i_board = ? ";		
		
		try {
			con = DbBridge.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getI_board());	
		
			result = ps.executeUpdate();
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			DbBridge.close(con, ps);
		}
		
		return result;
	}
	
	
	//------------------------------------------------- Delete (delete)
	
	public static int delBoard(BoardVO param) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;		
		
		String sql = " DELETE FROM t_board "
				+ " WHERE i_board = ? "
				+ " AND i_user = ? ";	
		
		try {
			con = DbBridge.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getI_board());
			ps.setInt(2, param.getI_user());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			DbBridge.close(con, ps);
		}
		
		return result;
	}
}














