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

import common.Utils;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import user.db.UserDAO;
import user.db.UserImgDAO;
import user.vo.UserImgVO;
import user.vo.UserVO;

@WebServlet("/user/profile.do")
public class ProfileImgsSev extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// View
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 체크
		HttpSession hs = request.getSession();
		UserVO authUser = (UserVO)hs.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect("/user/login.do");
			return;
		}
		
		int cmd = 0;
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			request.setAttribute("authUser",authUser);
			request.setAttribute("img", UserImgDAO.getProfileImg(conn, authUser.getI_user()));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/user/profileDetail.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		UserVO authUser = (UserVO)hs.getAttribute("authUser");
				
		String filePath = String.valueOf(authUser.getI_user());		
		String fileNm = Utils.uploadFile(request, filePath);
		
		UserImgVO param = new UserImgVO();
		param.setI_user(authUser.getI_user());
		param.setImg(fileNm);
		
		int cmd = 0;
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			cmd = UserImgDAO.regUserImg(conn, param);		
			doGet(request, response);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

}
