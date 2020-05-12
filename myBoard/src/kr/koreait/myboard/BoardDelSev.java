package kr.koreait.myboard;

import java.io.IOException;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.koreait.myboard.db.BoardDAO;
import kr.koreait.myboard.vo.*;

@WebServlet("/boardDel")
public class BoardDelSev extends HttpServlet {
	private static final long serialVersionUID = 1L;       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute("loginUser");
		if(loginUser == null) {
			response.sendRedirect("/login");
			return;
		}
		
		BoardVO param = new BoardVO();
		
		String i_board = request.getParameter("i_board");
		int intBoard = Utils.parseStringToInt(i_board, 0);
		System.out.println("intBoard: " + intBoard);
		if(intBoard == 0) { //문제 발생!!
			return;
		}
		
		param.setI_board(intBoard);
		param.setI_user(loginUser.getI_user());		
		int result = BoardDAO.delBoard(param);	
		System.out.println("result: " + result);
		if(result == 0) { //문제 발생!!
			return;
		}
		
		response.sendRedirect("/boardList");
		
	}

}







