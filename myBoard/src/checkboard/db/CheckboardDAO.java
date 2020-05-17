package checkboard.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.vo.BoardVO;
import checkboard.vo.CheckBoardGoalVO;
import checkboard.vo.CheckboardVO;
import jdbc.JdbcUtil;
import user.vo.UserVO;

public class CheckboardDAO {

	// --------------------------------------------------------- CRUD
	// ---------------------------------------------------------//

	// 체크보드 등록하기 CreateCheckboard()
	// 해당 체크보드 조회하기 getCheckboard()
	// 유저의 모든 체크보드 조회하기 getCheckboardList()
	// 체크보드 수정하기 UpdateCheckboard()
	// 체크보드 삭제하기 DeleteCheckboard()

	// 체크보드 등록하기
	public static int CreateCheckboard(Connection conn, CheckboardVO param) throws SQLException {
		int cmd = 0;
		System.out.println(param.getCheckboard_title() + "등록을 시도하였습니다.");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = " INSERT INTO t_checkboard "
				+ " (checkboard_i_user, checkboard_title, checkboard_decription, due_dt) "
				+ " VALUES(?, ?, ?, ?) ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getCheckboard_i_user());
			pstmt.setString(2, param.getCheckboard_title());
			pstmt.setString(3, param.getCheckboard_decription());
			pstmt.setString(4, param.getDue_dt());
			pstmt.executeUpdate();
			System.out.println(param.getCheckboard_title() + "등록을 완료하였습니다.");
			cmd = 1;

		} catch (Exception e) {
			System.out.println(param.getCheckboard_title() + "등록에 실패하였습니다.");
			cmd = 2;
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

		return cmd;
	}

	// 해당 체크보드 조회하기
	public static CheckboardVO getCheckboard(Connection conn, CheckboardVO param) {
		CheckboardVO vo = new CheckboardVO();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM t_checkboard " + " WHERE checkboard_i_user = ? OR i_checkboard = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getCheckboard_i_user());
			pstmt.setInt(2, param.getI_checkboard());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo.setI_checkboard(rs.getInt("i_checkboard"));
				vo.setCheckboard_i_user(rs.getInt("checkboard_i_user"));
				vo.setCheckboard_title(rs.getString("checkboard_title"));
				vo.setCheckboard_decription(rs.getString("checkboard_decription"));
				vo.setR_dt(rs.getString("r_dt"));
				vo.setDue_dt(rs.getString("due_dt"));
				vo.setCheckboard_state(rs.getString("checkboard_state"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return vo;
	}

	// 유저의 모든 체크보드 조회하기
	public static List<CheckboardVO> getCheckboardList(Connection conn, CheckboardVO param, int sIdx, int RowCnt) {
		List<CheckboardVO> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql ="SELECT A.i_checkboard, A.checkboard_title, A.r_dt, A.due_dt, B.user_id, ifnull(C.img, '') as img" + 
				"				FROM t_checkboard A" + 
				"					INNER JOIN t_user B" + 
				"					ON A.checkboard_i_user = B.i_user" + 
				"					LEFT JOIN t_user_img C" + 
				"					ON A.checkboard_i_user = C.i_user" + 
				"				WHERE A.checkboard_i_user = ?" + 
				"				ORDER BY r_dt DESC" + 
				"				LIMIT ?, ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getCheckboard_i_user());
			pstmt.setInt(2, sIdx);
			pstmt.setInt(3, RowCnt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CheckboardVO vo = new CheckboardVO();
				vo.setI_checkboard(rs.getInt("i_checkboard"));
				vo.setCheckboard_title(rs.getString("checkboard_title"));
				vo.setR_dt(rs.getString("r_dt"));
				vo.setDue_dt(rs.getString("due_dt"));
				vo.setUser_id(rs.getString("user_id"));
				vo.setUser_img(rs.getString("img"));
				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return list;
	}

	// 체크보드 수정하기
	public static int UpdateCheckboard(Connection conn, CheckboardVO param) {
		int cmd = 0;
		CheckboardVO vo = new CheckboardVO();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "UPDATE INTO t_checkboard(checkboard_title, checkboard_decription, due_dt, checkboard_state)"
				+ " VALUES (? ,?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, param.getCheckboard_title());
			pstmt.setString(2, param.getCheckboard_decription());
			pstmt.setString(3, param.getDue_dt());
			pstmt.setString(4, param.getCheckboard_state());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				cmd = 1;
			}

		} catch (Exception e) {
			cmd = 2;
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return cmd;
	}

	// 체크보드 삭제하기
	public static int DeleteCheckboard(Connection conn, CheckboardVO param) {
		int count = 0;
		PreparedStatement pstmt = null;

		String sql = " DELETE FROM t_checkboard WHERE i_checkboard = ? ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getI_checkboard());
			count = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return count;
	}

	// --------------------------------------------------------- CRUD
	// ---------------------------------------------------------//
	// 모든 체크보드 갯수 가져오기 getTotalPageCnt()
	// 마지막 체크보드 pk 값 가져오기

	// 모든 체크보드 갯수 가져오기
	public static int getTotalPageCnt(Connection conn, int RowCnt) {
		int totalPageCnt = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT CEIL(COUNT(i_board) / ?) AS RowCnt " + " FROM t_board ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, RowCnt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				totalPageCnt = rs.getInt("RowCnt");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return totalPageCnt;
	}
	
	public static int getCheckboardPk(Connection conn, int i_user) {
		int pk = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT i_checkboard FROM t_checkboard  WHERE checkboard_i_user = ? ORDER BY i_checkboard DESC";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, i_user);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pk = rs.getInt("i_checkboard");
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return pk;
	}
}
