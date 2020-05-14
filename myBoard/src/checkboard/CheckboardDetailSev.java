package checkboard;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.db.BoardCommentDAO;
import board.db.BoardDAO;
import board.vo.BoardVO;
import checkboard.db.CheckboardGoalDAO;
import checkboard.db.CheckboardParticipantDAO;
import checkboard.db.CheckboardReportDAO;
import checkboard.db.CheckboardDAO;
import checkboard.vo.CheckBoardParticipantsVO;
import checkboard.vo.CheckboardVO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import user.db.UserDAO;
import user.db.UserImgDAO;
import user.vo.UserImgVO;
import user.vo.UserVO;

@WebServlet("/checkboard/detail.do")
public class CheckboardDetailSev extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		UserVO authUser = (UserVO)hs.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect("/user/login.do");
			return;
		}
		
		int i_checkboard = Integer.parseInt(request.getParameter("i_checkboard"));
		CheckboardVO param = new CheckboardVO();
		param.setI_checkboard(i_checkboard);
		
		//DB로부터 리스트를 가져온다.
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false); // 트랜잭션을 시작
			request.setAttribute("checkboard", CheckboardDAO.getCheckboard(conn, param));
			request.setAttribute("checkboard_goal", CheckboardGoalDAO.getCheckboardList_goal(conn, param));
			request.setAttribute("checkboard_participant", CheckboardParticipantDAO.getCheckboardList_participants(conn, param));
			request.setAttribute("checkboard_report", CheckboardReportDAO.getCheckboardList_report(conn, param));
			request.setAttribute("participants", CheckboardParticipantDAO.getCheckboardList_participants(conn, param));
			
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/checkboard/checkboardDetail.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
