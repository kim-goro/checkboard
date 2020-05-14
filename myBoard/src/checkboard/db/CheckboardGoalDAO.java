package checkboard.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import checkboard.vo.CheckBoardGoalVO;
import checkboard.vo.CheckboardVO;
import jdbc.JdbcUtil;

public class CheckboardGoalDAO {

	// --------------------------------------------------------- CRUD
	// ---------------------------------------------------------//

	// Goal 추가하기 CreateCheckboard_goal()
	// Goal 조회하기 getCheckboardList_goal()
	// Goal 상태 변경하기 UpdateCheckboard_goal()
	// Goal 삭제하기 DeleteCheckboard_goal()

	public static int CreateCheckboard_goal(Connection conn, CheckBoardGoalVO param) throws SQLException {
		int cmd = 0;
		System.out.println(param.getGoalName() + "등록을 시도하였습니다.");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = " INSERT INTO t_checkboardgoal " + " (i_checkboard, goalName) " + " VALUES(?, ?) ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getI_checkboardgoal());
			pstmt.setString(2, param.getGoalName());
			pstmt.executeUpdate();
			System.out.println(param.getGoalName() + "등록을 완료하였습니다.");
			cmd = 1;

		} catch (Exception e) {
			System.out.println(param.getGoalName() + "등록에 실패하였습니다.");
			cmd = 2;
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

		return cmd;
	}

	// 유저의 모든 체크보드 조회하기
	public static List<CheckBoardGoalVO> getCheckboardList_goal(Connection conn, CheckboardVO param) {
		List<CheckBoardGoalVO> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM t_checkboardgoal " + " WHERE i_checkboard = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getI_checkboard());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CheckBoardGoalVO vo = new CheckBoardGoalVO();
				vo.setI_checkboardgoal(rs.getInt("i_checkboardgoal"));
				vo.setI_checkboard(rs.getInt("i_checkboard"));
				vo.setGoalName(rs.getString("goalName"));
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

	// 체크보드 수정하기
	public static int UpdateCheckboard_goal(Connection conn, CheckBoardGoalVO param) {
		int cmd = 0;
		CheckboardVO vo = new CheckboardVO();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "UPDATE t_checkboardgoal SET goalName = ?" + " WHERE i_checkboardgoal = ? ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, param.getGoalName());
			pstmt.setInt(2, param.getI_checkboardgoal());
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
	public static int DeleteAllCheckboard_goal(Connection conn, CheckboardVO param) {
		int count = 0;
		PreparedStatement pstmt = null;

		String sql = " DELETE FROM t_checkboardgoal WHERE i_checkboard = ? ";

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

	// 해당 체크보드 삭제하기
	public static int DeleteCheckboard_goal(Connection conn, CheckBoardGoalVO param) {
		int count = 0;
		PreparedStatement pstmt = null;

		String sql = " DELETE FROM t_checkboardgoal WHERE i_checkboardgoal = ? ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getI_checkboardgoal());
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
