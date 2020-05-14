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

import board.db.BoardCommentDAO;
import board.db.BoardDAO;
import board.vo.BoardVO;
import checkboard.db.CheckboardGoalDAO;
import checkboard.db.CheckboardParticipantDAO;
import checkboard.db.CheckboardReportDAO;
import checkboard.db.CheckboardDAO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

@WebServlet("/board/detail.do")
public class BoardDetailSev extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String i_board = request.getParameter("i_board");		
		int intI_board = Integer.parseInt(i_board);
		
		
		// DB로부터 리스트를 가져온다.
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false); // 트랜잭션을 시작
			
			BoardVO param = new BoardVO();
			param.setI_board(intI_board);		
			
			BoardDAO.updateBoardHits(conn, param);			
			request.setAttribute("detail", BoardDAO.getBoard(conn, param));
			
			// 댓글 GET
			String commentResult = request.getParameter("commentResult");
			if("0".equals(commentResult)) { // NULL을 예방하는 방법
				request.setAttribute("msg", "댓글 등록에 실패하였습니다.");
			}
			request.setAttribute("commentList", BoardCommentDAO.getBoardCommenList(conn, intI_board));

			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/boardDetail.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
