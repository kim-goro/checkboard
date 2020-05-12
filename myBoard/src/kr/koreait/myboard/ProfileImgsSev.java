package kr.koreait.myboard;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.koreait.myboard.db.UserDAO;
import kr.koreait.myboard.vo.UserVO;

@WebServlet("/profileImgs")
public class ProfileImgsSev extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// View
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 체크
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute("loginUser");
		if(loginUser == null) {
			response.sendRedirect("/login");
			return;
		}
		
		request.setAttribute("imgList", UserDAO.getProfileImgList(loginUser.getI_user()));
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/profileImgs.jsp");
		rd.forward(request, response);
	}

	// 삭제
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
