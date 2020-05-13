package user.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import user.vo.UserImgVO;
import user.vo.UserVO;

public class UserDAO {
	
	//--------------------------------------------------------- CRUD ---------------------------------------------------------//
	
	// 유저 생성하기 joinUser()
	// 유저 이미지 등록하기 regUserImg()
	// 유저 조회(검색)하기
	// 유저 이미지 조회하기 getProfileImg()
	
		
	// CREATE 유저 생성
	// return 0:에러 발생, 1:등록이 잘 됐음
	public static int joinUser(UserVO param) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = " insert into t_user " + " (u_id, u_pw, u_nickname, email, ph, addr, sex, birth) " + " values "
				+ " (?, ?, ?, ?, ?, ?, ?, ?) ";

		try {
			con = DbBridge.getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, param.getU_id());
			ps.setString(2, param.getU_pw());
			ps.setString(3, param.getU_nickname());
			ps.setString(4, param.getEmail());
			ps.setString(5, param.getPh());
			ps.setString(6, param.getAddr());
			ps.setInt(7, param.getSex());
			ps.setString(8, param.getBirth());

			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbBridge.close(con, ps);
		}

		return result;
	}
	
	// CREATE 유저 이미지 등록
	public static int regUserImg(UserImgVO param) {
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		String sql = " INSERT INTO t_user_img " + 
				" (i_user, seq, img) " + 
				" VALUES " + 
				" (?, 1, ?) ";		
		try {
			con = DbBridge.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getI_user());
			ps.setString(2, param.getImg());			
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			DbBridge.close(con, ps);
		}
		
		return result;
	}
	
	// SELECT 유저의 최근 이미지 조회
	public static String getProfileImg(int i_user) {
		String img = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT img FROM t_user_img "
				+ " WHERE i_user = ? "
				+ " AND seq = 1 ";
		
		try {
			con = DbBridge.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, i_user);			
			
			rs = ps.executeQuery();
			while(rs.next()) {
				img = rs.getString("img");
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			DbBridge.close(con, ps, rs);
		}
		
		
		return img;
	}
	
	//--------------------------------------------------------- CRUD ---------------------------------------------------------//
	
	// 로그인 하기 doLogin()
	// 로그아웃 하기
	// 유령회원 설정하기
	
	
	// 0:알수없는 에러발생, 1:로긴 성공, 2:아이디 없음, 3:비밀번호 틀림
	public static int doLogin(UserVO param) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = " SELECT * FROM t_user WHERE u_id = ? ";

		try {
			con = DbBridge.getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, param.getU_id());

			rs = ps.executeQuery();
			if (rs.next()) {
				String u_pw = rs.getString("u_pw");
				if (u_pw.equals(param.getU_pw())) { // 로그인 성공
					result = 1;

					String nickNm = rs.getString("u_nickname");
					int i_user = rs.getInt("i_user");

					param.setU_pw(null);
					param.setU_nickname(nickNm);
					param.setI_user(i_user);

				} else {
					result = 3;
				}
			} else {
				result = 2;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbBridge.close(con, ps, rs);
		}
		return result;
	}
	
}


