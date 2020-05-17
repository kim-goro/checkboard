package checkboard;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import checkboard.db.CheckboardDAO;
import checkboard.db.CheckboardGoalDAO;
import checkboard.db.CheckboardParticipantDAO;
import checkboard.db.CheckboardReportDAO;
import checkboard.vo.CheckBoardParticipantsVO;
import checkboard.vo.CheckboardVO;
import common.Utils;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

@WebServlet("/checkboard/AddParticipant.do")
public class CheckboardAddParticipantSev extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int i_checkboard = Utils.parseStringToInt(request.getParameter("i_checkboard"), 0);
		if (i_checkboard == 0) { // 문제 발생!!
			return;
		}
		if (request.getParameterValues("i_user") == null) { // 문제 발생!!
			return;
		}
		for(String users : request.getParameterValues("i_user")) {
			int i_user = Utils.parseStringToInt(users,0);
			if(i_user == 0) break;

			
			CheckBoardParticipantsVO param = new CheckBoardParticipantsVO();
			param.setI_checkboard(i_checkboard);
			param.setI_user(i_user);

			// DB로부터 리스트를 가져온다.
			Connection conn = null;
			try {
				conn = ConnectionProvider.getConnection();
				conn.setAutoCommit(false); // 트랜잭션을 시작
				CheckboardParticipantDAO.InsertCheckboard_participants(conn, param);

				conn.commit();
			} catch (SQLException e) {
				JdbcUtil.rollback(conn);
				throw new RuntimeException(e);
			} finally {
				JdbcUtil.close(conn);
			}
			
		}

		response.sendRedirect("/checkboard/detail.do?i_checkboard="+i_checkboard);

	}

}
