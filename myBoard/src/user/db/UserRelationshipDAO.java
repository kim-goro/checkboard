package user.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import user.vo.UserRelationshipVO;
import user.vo.UserVO;

public class UserRelationshipDAO {

	//--------------------------------------------------------- CRUD ---------------------------------------------------------//

	// 친구 추가 InsertFriend()
	// 모든 친구 삭제하기  DeleteAllFriend()
	// 친구 삭제 DeleteFriend()
	// 친구 조회하기 getFriendList()

	// 체크보드 팀원 추가히기
	public static int InsertFriend(Connection conn, UserRelationshipVO param)
			throws SQLException {
		int cmd = 0;
		PreparedStatement pstmt = null;

		String sql = " INSERT INTO t_userRelationship(i_user, i_userf) " + " VALUES(?, ?) ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getI_user());
			pstmt.setInt(2, param.getI_userf());
			pstmt.executeUpdate();
			cmd = 1;

		} catch (Exception e) {
			cmd = 2;
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}

		return cmd;
	}

	// 체크보드 팀원 조회하기
	public static List<UserRelationshipVO> getFriendList(Connection conn,
			UserVO param) {
		List<UserRelationshipVO> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM t_userRelationship WHERE i_user = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getI_user());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				UserRelationshipVO vo = new UserRelationshipVO();
				vo.setI_userRelationship(rs.getInt("i_userRelationship"));
				vo.setI_user(rs.getInt("i_user"));
				vo.setI_userf(rs.getInt("i_userf"));
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
		public static int DeleteAllFriend(Connection conn, UserRelationshipVO param) {
			int count = 0;
			PreparedStatement pstmt = null;

			String sql = " DELETE FROM t_userRelationship WHERE i_user = ? ";

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, param.getI_user());
				count = pstmt.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JdbcUtil.close(pstmt);
			}
			return count;
		}

	// 해당 체크보드 팀원 삭제하기
	public static int DeleteFriend(Connection conn, UserRelationshipVO param) {
		int count = 0;
		PreparedStatement pstmt = null;

		String sql = " DELETE FROM t_userRelationship WHERE i_checkboardparticipants = ? ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getI_userRelationship());
			count = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return count;
	}
	
	//--------------------------------------------------------- CRUD ---------------------------------------------------------//
}
