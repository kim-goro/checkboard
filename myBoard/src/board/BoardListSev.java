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

@WebServlet("/board/list.do")
public class BoardListSev extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 체크
		HttpSession hs = request.getSession();
		UserVO authUser = (UserVO)hs.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect("/user/login.do");
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
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false); // 트랜잭션을 시작

			request.setAttribute("page", page);
			request.setAttribute("totalPageCnt", BoardDAO.getTotalPageCnt(conn, param));
			request.setAttribute("list", BoardDAO.getBoardList(conn, param));

			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}

		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/board/boardList.jsp");
		rd.forward(request, response);
	}


}





