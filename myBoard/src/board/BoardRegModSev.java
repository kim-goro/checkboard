package board;

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
import checkboard.db.CheckboardReportDAO;
import checkboard.db.CheckboardDAO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import user.vo.UserVO;

@WebServlet("/board/regmod.do")
public class BoardRegModSev extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	//화면 띄우는 용도
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션에 값세팅
		HttpSession hs = request.getSession();
		UserVO authUser = (UserVO)hs.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect("/user/login.do");
			return;
		}
				
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/board/boardRegMod.jsp");
		rd.forward(request, response);
	}

	//작업 용도(insert, update)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		UserVO authUser = (UserVO)hs.getAttribute("authUser");		
		int i_user = authUser.getI_user();
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		System.out.println("i_user : " + i_user);
		System.out.println("title : " + title);
		System.out.println("content : " + content);
		
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setI_user(i_user);

		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			BoardDAO.insertBoard(conn, vo);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		
		
		response.sendRedirect("/board/list.do");
	}

}
