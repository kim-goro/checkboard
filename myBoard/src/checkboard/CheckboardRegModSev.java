package checkboard;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
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
import checkboard.db.CheckboardDAO;
import checkboard.vo.CheckBoardGoalVO;
import checkboard.vo.CheckBoardParticipantsVO;
import checkboard.vo.CheckboardVO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import user.vo.UserVO;

@WebServlet("/checkboard/regmod.do")
public class CheckboardRegModSev extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 화면 띄우는 용도
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/checkboard/checkboardRegMod.jsp");
		rd.forward(request, response);
	}

	// 작업 용도(insert, update)
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession hs = request.getSession();
		UserVO authUser = (UserVO) hs.getAttribute("authUser");
		int i_user = authUser.getI_user();

		String checkboard_title = request.getParameter("checkboard_title");
		String checkboard_decription = request.getParameter("checkboard_decription");
		String due_dt = request.getParameter("due_dt");
		String[] goals = request.getParameterValues("goals");
		String[] friends = request.getParameterValues("friends");

		

		// DB로부터 리스트를 가져온다.
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false); // 트랜잭션을 시작
			
			CheckboardVO vo = new CheckboardVO();
			vo.setCheckboard_i_user(i_user);
			vo.setCheckboard_title(checkboard_title);
			vo.setCheckboard_decription(checkboard_decription);
			vo.setDue_dt(due_dt);
			CheckboardDAO.CreateCheckboard(conn, vo);
			for (String goalName : goals) {
				CheckBoardGoalVO vo2 = new CheckBoardGoalVO();
				vo2.setGoalName(goalName);
				vo2.setI_checkboard(CheckboardDAO.getCheckboardPk(conn, i_user));
				CheckboardGoalDAO.CreateCheckboard_goal(conn, vo2);
			}
			for (String f_i_user : friends) {
				CheckBoardParticipantsVO vo3 = new CheckBoardParticipantsVO();
				vo3.setI_user(Integer.parseInt(f_i_user));
				vo3.setI_checkboard(CheckboardDAO.getCheckboardPk(conn, i_user));
				CheckboardParticipantDAO.InsertCheckboard_participants(conn, vo3);
			}
			
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
