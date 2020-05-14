package board;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.db.BoardCommentDAO;
import board.vo.BoardCommentVO;
import checkboard.db.CheckboardGoalDAO;
import checkboard.db.CheckboardParticipantDAO;
import checkboard.db.CheckboardReportDAO;
import checkboard.db.CheckboardDAO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import user.vo.UserVO;

@WebServlet("/board/comment.do")
public class BoardCommentSev extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 삭제
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO) hs.getAttribute("loginUser");
		if (loginUser == null) {
			return;
		}

		int i_comment = Integer.parseInt(request.getParameter("i_comment"));
		int i_user = Integer.parseInt(request.getParameter("i_user"));

		// DB로부터 리스트를 가져온다.
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();

			if (i_user == loginUser.getI_user()) {
				BoardCommentDAO.deleteComment(conn, i_comment);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}

		response.sendRedirect("/board/detail?i_board=" + request.getParameter("i_board"));
	}

	// 등록
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int i_board = Integer.parseInt(request.getParameter("i_board"));
		String content = request.getParameter("content");

		HttpSession hs = request.getSession();
		UserVO authUser = (UserVO) hs.getAttribute("authUser");
		if (authUser == null) {
			response.sendRedirect("/user/login.do");
			return;
		}

		BoardCommentVO vo = new BoardCommentVO();
		vo.setI_board(i_board);
		vo.setContent(content);
		vo.setI_user(authUser.getI_user());

		// DB로부터 리스트를 가져온다.
		Connection conn = null;
		int cmd = 0;
		try {
			conn = ConnectionProvider.getConnection();
			cmd = BoardCommentDAO.insertComment(conn, vo);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}

		response.sendRedirect("/boardDetail?i_board=" + i_board + "&commentResult" + cmd);
	}

}
