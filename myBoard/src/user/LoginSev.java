package user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import common.cmdType;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import user.db.UserDAO;
import user.vo.UserVO;


@WebServlet("/user/login.do")
public class LoginSev extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Msg = request.getParameter("Msg");
		if(Msg != null) {
			switch(Msg) {
			case "0":
				request.setAttribute("Msg", "알 수 없는 에러 발생");
				break;
			case "1":
				request.setAttribute("Msg", "아이디를 확인해 주세요");
				break;
			case "2":
				request.setAttribute("Msg", "비밀번호를 확인해 주세요");
				break;
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/user/login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		String user_password = request.getParameter("user_password");
		
		System.out.println("user_id : " + user_id);
		System.out.println("user_password : " + user_password);
		
		UserVO param = new UserVO();
		param.setUser_id(user_id);
		param.setUser_password(user_password);
		
		int cmd = 0;
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			cmd = UserDAO.doLogin(conn,param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		
		if(cmd == 1) {			
			HttpSession hs = request.getSession();
			hs.setAttribute("authUser", param);
			response.sendRedirect("/checkboard/list.do");
			return;
		}

		response.sendRedirect("/user/login.do?Msg=" + cmd);		
	}

}





