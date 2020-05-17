package checkboard;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import checkboard.db.CheckboardReportDAO;
import checkboard.vo.CheckBoardReportVO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import user.db.UserDAO;
import user.vo.UserVO;

/**
 * Servlet implementation class CheckBoardReportSev
 */
@WebServlet("/checkboard/report.do")
public class CheckBoardReportSev extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		UserVO authUser = (UserVO)hs.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect("/user/login.do");
			return;
		}
		int i_checkboard = Integer.parseInt(request.getParameter("i_checkboard"));
		String[] i_checkboardgoals = request.getParameterValues("i_checkboardgoal");
		for (String i_checkboardgoal : i_checkboardgoals) {
			CheckBoardReportVO param = new CheckBoardReportVO();
			param.setI_checkboard(i_checkboard);
			param.setI_user(authUser.getI_user());
			param.setI_checkboardgoal(Integer.parseInt(i_checkboardgoal));

			int cmd = 0;
			Connection conn = null;
			try {
				conn = ConnectionProvider.getConnection();
				CheckboardReportDAO.InsertCheckboard_report(conn, param);
				
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
