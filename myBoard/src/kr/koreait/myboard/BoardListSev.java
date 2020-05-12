package kr.koreait.myboard;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.koreait.myboard.db.BoardDAO;
import kr.koreait.myboard.vo.BoardVO;
import kr.koreait.myboard.vo.UserVO;

@WebServlet("/boardList")
public class BoardListSev extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 체크
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO)hs.getAttribute("loginUser");
		if(loginUser == null) {
			response.sendRedirect("/login");
			return;
		}
		String search = request.getParameter("search");				
		int page = 1;
		
		String strPage = request.getParameter("page");
		if(strPage != null) {
			page = Integer.parseInt(strPage);
		}		
		int cnt = 10; //content view count
		int sIdx = (page - 1) * cnt;
		
		BoardVO param = new BoardVO();
		param.setsIdx(sIdx);
		param.setRowCnt(cnt);
		param.setSearch(search);
		
		//DB로부터 리스트를 가져온다.
		request.setAttribute("page", page);
		request.setAttribute("totalPageCnt", BoardDAO.getTotalPageCnt(param));
		request.setAttribute("list", BoardDAO.getBoardList(param));
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/boardList.jsp");
		rd.forward(request, response);
	}


}





