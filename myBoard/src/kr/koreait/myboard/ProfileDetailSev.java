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
import kr.koreait.myboard.vo.UserImgVO;
import kr.koreait.myboard.vo.UserVO;

@WebServlet("/profileDetail")
public class ProfileDetailSev extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 체크
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute("loginUser");
		if(loginUser == null) {
			response.sendRedirect("/login");
			return;
		}
		
		String img = UserDAO.getProfileImg(loginUser.getI_user());
		request.setAttribute("img", img);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/profileDetail.jsp");
		rd.forward(request, response);
	}

	//프로필 이미지 업로드
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute("loginUser");
				
		String filePath = String.valueOf(loginUser.getI_user());		
		String fileNm = Utils.uploadFile(request, filePath);
		
		UserImgVO param = new UserImgVO();
		param.setI_user(loginUser.getI_user());
		param.setImg(fileNm);
		
		UserDAO.updUserImgAddSeq(param);		
		int result = UserDAO.regUserImg(param);
		doGet(request, response);
		
	}

}






