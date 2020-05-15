package user.db;

import java.net.http.HttpRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jdbc.JdbcUtil;
import user.vo.UserImgVO;
import user.vo.UserVO;

public class UserDAO {

	// --------------------------------------------------------- CRUD
	// ---------------------------------------------------------//

	// 유저 생성하기 joinUser()
	// 유저 조회(검색)하기 getById()

	// 유저 생성하기
	public static int joinUser(Connection conn, UserVO param) throws SQLException {
		int cmd = 0;
		System.out.println(param.getUser_id() + "님이 가입을 시도하였습니다.");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = " INSERT INTO t_user "
				+ " (user_id, user_password, user_email, user_gender, user_hobby, user_birth) "
				+ " VALUES(?, ?, ?, ?, ?, ?) ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, param.getUser_id());
			pstmt.setString(2, param.getUser_password());
			pstmt.setString(3, param.getUser_email());
			pstmt.setString(4, param.getUser_gender());
			pstmt.setString(5, param.getUser_hobby());
			pstmt.setString(6, param.getUser_birth());
			pstmt.executeUpdate();
			System.out.println(param.getUser_id() + "님의 가입을 완료하였습니다.");
			cmd = 1;

		} catch (Exception e) {
			System.out.println(param.getUser_id() + "님의 가입이 실패하였습니다.");
			cmd = 2;
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

		return cmd;
	}

	// 유저 조회(검색)하기
	public static List<UserVO> getById(Connection conn, UserVO param) {
		List<UserVO> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM t_user " + " WHERE user_id = ? OR user_email = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, param.getUser_id());
			pstmt.setString(2, param.getUser_email());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				UserVO vo = new UserVO();
				vo.setI_user(rs.getInt("i_user"));
				vo.setUser_id(rs.getString("user_id"));
				vo.setUser_email(rs.getString("user_email"));
				vo.setUser_gender(rs.getString("user_gender"));
				vo.setUser_hobby(rs.getString("user_hobby"));
				vo.setUser_birth(rs.getString("user_birth"));
				vo.setUser_birth(rs.getString("user_birth"));
				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return list;
	}

	// --------------------------------------------------------- CRUD
	// ---------------------------------------------------------//

	// 로그인 하기 doLogin()
	// 로그아웃 하기 doLogout()
	// 유령회원 설정하기

	// 로그인하기
	public static int doLogin(Connection conn, UserVO param) {
		int cmd = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = " SELECT * FROM t_user " + "WHERE user_id = ? ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, param.getUser_id());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				String user_password = rs.getString("user_password");
				if (user_password.equals(param.getUser_password())) {
					cmd = 1; // 로그인 성공
					param.setUser_password(null);
					param.setUser_id(rs.getString("user_id"));
					param.setI_user(rs.getInt("i_user"));
				} else {
					cmd = 3; // 비밀번호 틀림
				}
			} else {
				cmd = 2; // 아이디 없음
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		return cmd;
	}

	// 로그아웃 하기
	public static void doLogout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		session.invalidate(); // 세션 종료
	}

}
