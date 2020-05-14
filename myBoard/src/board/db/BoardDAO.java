package board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import board.vo.BoardVO;
import jdbc.JdbcUtil;

public class BoardDAO {
	
	//--------------------------------------------------------- CRUD ---------------------------------------------------------//
	
	// 게시글 등록 insertBoard()
	// 게시글 수정
	// 게시글 삭제 delBoard()
	// 해당 게시물 가져오기 getBoard()
	// 모든 게시물리스트 가져오기 getBoardList()
	
	// CREATE 게시글 등록
	public static int insertBoard(Connection conn, BoardVO param) {
		int cmd = 0;
		PreparedStatement pstmt = null;
		String sql = " INSERT INTO t_board "
				+ " (title, content, i_user) "
				+ " VALUES "
				+ " (?, ?, ?) ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, param.getTitle());
			pstmt.setString(2, param.getContent());
			pstmt.setInt(3, param.getI_user());			
			
			cmd = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);	
		}
		
		return cmd;
	}
	
	
	// SELECT 해당 게시글 조회하기
	public static BoardVO getBoard(Connection conn, BoardVO param) {
		BoardVO vo = null;
		PreparedStatement pstmt = null;
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
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getI_board());			
			rs = pstmt.executeQuery();
			
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
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return vo;
	}
	
	
	
	// SELECT 모든 게시글 리스트 조회하기
	public static List<BoardVO> getBoardList(Connection conn, BoardVO param) {
		List<BoardVO> list = new ArrayList();
		PreparedStatement pstmt = null;
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
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getsIdx());
			pstmt.setInt(2, param.getRowCnt());
			
			rs = pstmt.executeQuery();
			
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
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return list;
	}
	
	
	// DELETE 게시글 삭제하기 
	public static int delBoard(Connection conn, BoardVO param) {
		int cmd = 0;
		PreparedStatement pstmt = null;		
		
		String sql = " DELETE FROM t_board "
				+ " WHERE i_board = ? "
				+ " AND i_user = ? ";	
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getI_board());
			pstmt.setInt(2, param.getI_user());
			
			cmd = pstmt.executeUpdate();
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return cmd;
	}
	//--------------------------------------------------------- CRUD ---------------------------------------------------------//
	
	//모든 게시글 갯수 가져오기 getTotalPageCnt()
	//조회수 변경(증가) 하기 updateBoardHits()
	
	// SELECT 모든 게시글 갯수 조회하기
		public static int getTotalPageCnt(Connection conn, BoardVO param) {
			int totalPageCnt = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = " SELECT CEIL(COUNT(i_board) / ?) AS cnt "
					+ " FROM t_board ";
			
			if(param.getSearch() != null) {
				sql += " WHERE title LIKE '%" + param.getSearch() + "%' ";
			}		
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, param.getRowCnt());
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					totalPageCnt = rs.getInt("cnt");
				}	
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			
			return totalPageCnt;
		}
		
		// UPDATE 조회수 변경(증가) 하기
		public static int updateBoardHits(Connection conn, BoardVO param) {
			int cmd = 0;
			PreparedStatement pstmt = null;
			
			String sql = " UPDATE t_board "
					+ " SET hits = hits + 1"
					+ " WHERE i_board = ? ";		
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, param.getI_board());	
			
				cmd = pstmt.executeUpdate();
				
			} catch (Exception e) {			
				e.printStackTrace();
			} finally {
				JdbcUtil.close(pstmt);
			}
			
			return cmd;
		}
}














