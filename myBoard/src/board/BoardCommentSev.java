package board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.db.BoardCommentDAO;
import board.vo.BoardCommentVO;
import user.vo.UserVO;


@WebServlet("/boardComment")
public class BoardCommentSev extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//화면단이 없는 서블릿 (업무처리용)
	//삭제
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute("loginUser");
		if(loginUser == null) {
			response.sendRedirect("/login");
			return;
		}
		
		int i_comment = Integer.parseInt(request.getParameter("i_comment"));
		// i_comment = Utils.parseStringToInt(str, def) 직접 생성한 메서드로
		int i_user = Integer.parseInt(request.getParameter("i_user"));
		if(i_user == loginUser.getI_user()) {
			BoardCommentDAO.deleteComment(i_comment);
		}
		response.sendRedirect("/boardDetail?i_board="+request.getParameter("i_board"));
	}

	//등록
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int i_board = Integer.parseInt(request.getParameter("i_board"));
		String content = request.getParameter("content");
		
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute("loginUser");
		if(loginUser == null) {
			response.sendRedirect("/login");
			return;
		}
		
		BoardCommentVO bcvo = new BoardCommentVO();
		bcvo.setI_board(i_board);
		bcvo.setContent(content);
		bcvo.setI_user(loginUser.getI_user());
		int result = BoardCommentDAO.insertComment(bcvo);
		response.sendRedirect("/boardDetail?i_board="+i_board+"&commentResult"+result);
	}

}
