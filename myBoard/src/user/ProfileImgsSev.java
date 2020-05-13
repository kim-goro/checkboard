package user;

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

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import user.db.UserDAO;
import user.db.UserImgDAO;
import user.vo.UserVO;

@WebServlet("/profileImgs")
public class ProfileImgsSev extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// View
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 체크
		HttpSession hs = request.getSession();
		UserVO authUser = (UserVO)hs.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect("/login.do");
			return;
		}
		
		int cmd = 0;
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			request.setAttribute("imgList", UserImgDAO.getProfileImg(conn, authUser.getI_user()));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/profileImgs.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
