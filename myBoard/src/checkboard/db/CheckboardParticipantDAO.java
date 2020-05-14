package checkboard.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import checkboard.vo.CheckBoardGoalVO;
import checkboard.vo.CheckBoardParticipantsVO;
import checkboard.vo.CheckboardVO;
import jdbc.JdbcUtil;

public class CheckboardParticipantDAO {

	// --------------------------------------------------------- CRUD
	// ---------------------------------------------------------//

	// 체크보드 팀원 추가히기 InsertCheckboard_participants()
	// 체크보드 팀원 조회하기 getCheckboardList_participants()
	// 체크보드 팀원 삭제하기 DeleteCheckboard_goal()

	// 체크보드 팀원 추가히기
	public static int InsertCheckboard_participants(Connection conn, CheckBoardParticipantsVO param)
			throws SQLException {
		int cmd = 0;
		System.out.println(param.getI_user() + "등록을 시도하였습니다.");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = " INSERT INTO t_checkboardparticipants " + " (i_checkboard, i_user) " + " VALUES(?, ?) ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getI_checkboard());
			pstmt.setInt(2, param.getI_user());
			pstmt.executeUpdate();
			System.out.println(param.getI_user() + "등록을 완료하였습니다.");
			cmd = 1;

		} catch (Exception e) {
			System.out.println(param.getI_user() + "등록에 실패하였습니다.");
			cmd = 2;
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

		return cmd;
	}

	// 체크보드 팀원 조회하기
	public static List<CheckBoardParticipantsVO> getCheckboardList_participants(Connection conn,
			CheckboardVO param) {
		List<CheckBoardParticipantsVO> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM t_checkboardparticipants " + " WHERE i_checkboard = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getI_checkboard());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CheckBoardParticipantsVO vo = new CheckBoardParticipantsVO();
				vo.setI_checkboardparticipants(rs.getInt("i_checkboardparticipants"));
				vo.setI_checkboard(rs.getInt("i_checkboard"));
				vo.setI_user(rs.getInt("i_user"));
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
	
	// 체크보드 팀원 삭제하기
		public static int DeleteAllCheckboard_goal(Connection conn, CheckboardVO param) {
			int count = 0;
			PreparedStatement pstmt = null;

			String sql = " DELETE FROM t_checkboardparticipants WHERE i_checkboard = ? ";

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

	// 해당 체크보드 팀원 삭제하기
	public static int DeleteCheckboard_goal(Connection conn, CheckBoardParticipantsVO param) {
		int count = 0;
		PreparedStatement pstmt = null;

		String sql = " DELETE FROM t_checkboardparticipants WHERE i_checkboardparticipants = ? ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getI_checkboardparticipants());
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
