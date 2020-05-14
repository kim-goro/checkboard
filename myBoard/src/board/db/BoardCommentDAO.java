package board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import board.vo.BoardCommentVO;
import board.vo.BoardVO;
import jdbc.JdbcUtil;

public class BoardCommentDAO {

	//--------------------------------------------------------- CRUD ---------------------------------------------------------//
	
	// 댓글 달기 insertComment()
	// 댓글 삭제하기 deleteComment()
	// 댓글 수정하기
	// 댓글 조회하기 getBoardCommenList()
	
	public static int insertComment(Connection conn, BoardCommentVO param) {
		int cmd = 0;
		PreparedStatement pstmt = null;
		String sql = " INSERT INTO t_board_comment "
				+ " (i_board, content, i_user) "
				+ " VALUES "
				+ " (?, ?, ?) ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getI_board());
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
	
	public static int deleteComment(Connection conn, int i_comment) {
		int cmd = 0;
		PreparedStatement pstmt = null;
		String sql = " DELETE FROM t_board_comment "
				+ " WHERE "
				+ " i_comment= ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, i_comment);
			
			cmd = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);		
		}
		
		return cmd;
	}

	public static List<BoardCommentVO> getBoardCommenList(Connection conn, int i_board) {
		List<BoardCommentVO> list = new ArrayList();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT "
				+ " A.i_comment, A.content, A.r_dt "
				+ " , B.i_user, B.u_nickname as user_nm, ifnull(C.img, '') as user_img "
				+ " FROM t_board_comment A "
				+ " INNER JOIN t_user B "
				+ " ON A.i_user = B.i_user "
				+ " LEFT JOIN t_user_img C "
				+ " ON B.i_user = C.i_user "
				+ " AND C.seq = 1 "
				+ " WHERE A.i_board = ? ";
		try {;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, i_board);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int i_comment = rs.getInt("i_comment");
				int i_user = rs.getInt("i_user");
				String content = rs.getString("content");
				String r_dt = rs.getString("r_dt");
				String user_nm = rs.getString("user_nm");
				String user_img = rs.getString("user_img");
				
				BoardCommentVO vo = new BoardCommentVO();
				vo.setI_comment(i_comment);
				vo.setI_user(i_user);
				vo.setContent(content);
				vo.setR_dt(r_dt);
				vo.setUser_nm(user_nm);
				vo.setUser_img(user_img);
				
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

	//--------------------------------------------------------- CRUD ---------------------------------------------------------//
	
}

