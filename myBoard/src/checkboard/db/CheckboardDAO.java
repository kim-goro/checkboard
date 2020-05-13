package checkboard.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import checkboard.vo.CheckBoardGoalVO;
import checkboard.vo.CheckboardVO;
import jdbc.JdbcUtil;
import user.vo.UserVO;

public class CheckboardDAO {
	
	//--------------------------------------------------------- CRUD ---------------------------------------------------------//

	// 체크보드 등록하기 CreateCheckboard()
	// 해당 체크보드 조회하기 getCheckboard()
	// 유저의 모든 체크보드 조회하기 getCheckboardList()
	// 체크보드 수정하기 UpdateCheckboard()
	// 체크보드 삭제하기 DeleteCheckboard()
	
	// 체크보드 등록하기
	public static int CreateCheckboard(Connection conn, CheckboardVO param) throws SQLException {
		int cmd = 0;
		System.out.println(param.getUser_id() + "님이 가입을 시도하였습니다.");
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = " INSERT INTO t_checkboard "
				+ " (checkboard_i_user, checkboard_title, checkboard_decription, due_dt) "
				+ " VALUES(?, ?, ?, ?, ?, ?, ?) ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, param.getUser_id());
			pstmt.setString(2, param.getUser_password());
			pstmt.setString(4, param.getUser_email());
			pstmt.setString(3, param.getUser_gender());
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
	
	// 해당 체크보드 조회하기
	public static CheckboardVO getCheckboard() {
		
	}
	
	// 유저의 모든 체크보드 조회하기
	public static List<CheckboardVO> getCheckboardList(){
		
	}
	
	// 체크보드 수정하기
	public static int UpdateCheckboard() {
		
	}
	
	// 체크보드 삭제하기
	public static int DeleteCheckboard() {
		
	}
	
	
	//--------------------------------------------------------- CRUD ---------------------------------------------------------//

	// 체크보드 골 등록하기
	// 체크보드 친구 등록하기

}
