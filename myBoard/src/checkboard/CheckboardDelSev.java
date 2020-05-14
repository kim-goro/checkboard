package checkboard;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.db.BoardDAO;
import board.vo.BoardVO;
import checkboard.db.CheckboardGoalDAO;
import checkboard.db.CheckboardParticipantDAO;
import checkboard.db.CheckboardReportDAO;
import checkboard.db.CheckboardDAO;
import checkboard.vo.CheckboardVO;
import common.Utils;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import user.vo.UserVO;

@WebServlet("/checkboard/del.do")
public class CheckboardDelSev extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession hs = request.getSession();
		UserVO authUser = (UserVO) hs.getAttribute("authUser");
		if (authUser == null) {
			response.sendRedirect("/user/login.do");
			return;
		}
		int i_checkboard = Utils.parseStringToInt(request.getParameter("i_checkboard"), 0);
		if (i_checkboard == 0) { // 문제 발생!!
			return;
		}

		CheckboardVO param = new CheckboardVO();
		param.setI_checkboard(i_checkboard);
		param.setCheckboard_i_user(authUser.getI_user());

		// DB로부터 리스트를 가져온다.
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false); // 트랜잭션을 시작
			
			CheckboardDAO.DeleteCheckboard(conn, param);
			CheckboardGoalDAO.DeleteAllCheckboard_goal(conn, param);
			CheckboardParticipantDAO.DeleteAllCheckboard_goal(conn, param);
			CheckboardReportDAO.DeleteAllCheckboard_report(conn, param);

			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}

		response.sendRedirect("/checkboard/list.do");

	}

}
