package checkboard.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import checkboard.vo.CheckBoardGoalVO;
import checkboard.vo.CheckBoardReportVO;
import checkboard.vo.CheckboardVO;
import jdbc.JdbcUtil;

public class CheckboardReportDAO {

	// --------------------------------------------------------- CRUD
	// ---------------------------------------------------------//

	// 보고서 제출하기 InsertCheckboard_report()
	// 보고서 가져오기 getCheckboardList_report()
	// 보고서 수정하기 UpdateCheckboard_report()
	// 보고서 삭제하기 DeleteCheckboard_report()

	// 보고서 제출하기
	public static int InsertCheckboard_report(Connection conn, CheckBoardReportVO param) throws SQLException {
		int cmd = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = " INSERT INTO t_checkboardreport(i_checkboard, i_user, i_checkboardgoal, goalState)"
				+ " VALUES(?, ?, ?, 'Y') ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getI_checkboard());
			pstmt.setInt(2, param.getI_user());
			pstmt.setInt(3, param.getI_checkboardgoal());
			pstmt.executeUpdate();
			cmd = 1;

		} catch (Exception e) {
			cmd = 2;
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

		return cmd;
	}

	// 보고서 가져오기
	public static List<CheckBoardReportVO> getCheckboardList_report(Connection conn, CheckboardVO param) {
		// 오늘 날짜라 가장 가까운 3개의 날짜만 내림차순으로, i_user별로 가져오기
		// i_checkboard의 연결된 
		// i_checkboardgoal 중에서 날짜가 가장 최신순으로 3개의 날짜 저장하기
		// 3개의 날짜에 해당되는 i_checkboard의 연결된 i_checkboardgoal의 i_user 저장하기
		// 저장된 i_user별로 CheckBoardReportVO 반환하기
		List<CheckBoardReportVO> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM t_checkboardreport " + " WHERE i_checkboard = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getI_checkboard());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CheckBoardReportVO vo = new CheckBoardReportVO();
				vo.setI_checkboardreport(rs.getInt("i_checkboardreport"));
				vo.setI_checkboard(rs.getInt("i_checkboard"));
				vo.setI_user(rs.getInt("i_user"));
				vo.setI_checkboardgoal(rs.getInt("i_checkboardgoal"));
				vo.setGoalState(rs.getString("goalState"));
				vo.setR_dt(rs.getString("r_dt"));
				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return list;
	}

	// 보고서 수정하기
	public static int UpdateCheckboard_report(Connection conn, CheckBoardReportVO param) {
		int cmd = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "UPDATE t_checkboardreport SET goalState = ?" + " WHERE i_checkboardreport = ? ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, param.getGoalState());
			pstmt.setInt(2, param.getI_checkboardreport());
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
	
	// 보고서 삭제하기
		public static int DeleteAllCheckboard_report(Connection conn, CheckboardVO param) {
			int count = 0;
			PreparedStatement pstmt = null;

			String sql = " DELETE FROM t_checkboardreport WHERE i_checkboard = ? ";

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

	// 해당 보고서 삭제하기
	public static int DeleteCheckboard_report(Connection conn, CheckBoardReportVO param) {
		int count = 0;
		PreparedStatement pstmt = null;

		String sql = " DELETE FROM t_checkboardreport WHERE i_checkboardreport = ? ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getI_checkboardreport());
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

}
