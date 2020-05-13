package board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.db.BoardDAO;
import board.vo.BoardVO;
import user.vo.UserVO;

@WebServlet("/boardRegmod")
public class BoardRegModSev extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	//화면 띄우는 용도
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션에 값세팅
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute("loginUser");
		if(loginUser == null) {
			response.sendRedirect("/login");
			return;
		}
				
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/boardRegMod.jsp");
		rd.forward(request, response);
	}

	//작업 용도(insert, update)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute("loginUser");		
		int i_user = loginUser.getI_user();
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		System.out.println("i_user : " + i_user);
		System.out.println("title : " + title);
		System.out.println("content : " + content);
		
		BoardVO bVO = new BoardVO();
		bVO.setTitle(title);
		bVO.setContent(content);
		bVO.setI_user(i_user);
		
		BoardDAO.insertBoard(bVO);
		
		response.sendRedirect("/boardList");
	}

}
