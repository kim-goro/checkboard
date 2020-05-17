package user.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jdbc.JdbcUtil;
import user.vo.UserImgVO;

public class UserImgDAO {

	// --------------------------------------------------------- CRUD
	// ---------------------------------------------------------//

	// 유저 이미지 등록하기 regUserImg()
	// 유저 이미지 조회하기 getProfileImg()

	// 유저 이미지 등록하기
	public static int regUserImg(Connection conn, UserImgVO param) {
		int cmd = 0;
		System.out.println("" + "님의 프로필 이미지를 변경합니다.");
		PreparedStatement pstmt = null;
		String sql = " INSERT INTO t_user_img " + " (i_user, img) " + " VALUES (?, ?) ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getI_user());
			pstmt.setString(2, param.getImg());
			pstmt.executeUpdate();
			System.out.println("" + "님의 프로필 이미지를 변경하였습니다.");
			cmd = 1;

		} catch (Exception e) {
			System.out.println("" + "님의 프로필 이미지 변경을 실패하였습니다.");
			cmd = 2;
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}

		return cmd;
	}


	// 유저 이미지 조회하기
	public static String getProfileImg(Connection conn, int i_user) {
		String img = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = " SELECT img FROM t_user_img WHERE i_user = ? ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, i_user);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				img = rs.getString("img");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return img;
	}	
	

	// --------------------------------------------------------- CRUD
	// ---------------------------------------------------------//

}
