package user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import checkboard.db.CheckboardParticipantDAO;
import checkboard.vo.CheckBoardParticipantsVO;
import checkboard.vo.CheckboardVO;
import common.Utils;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

@WebServlet("/user/FriendList.do")
public class FriendListSev extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int i_checkboard = Utils.parseStringToInt(request.getParameter("i_checkboard"), 0);
		request.setAttribute("i_checkboard", i_checkboard);

		CheckboardVO param = new CheckboardVO();
		param.setI_checkboard(i_checkboard);

		// DB로부터 리스트를 가져온다.
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false); // 트랜잭션을 시작
			List<CheckBoardParticipantsVO> list = CheckboardParticipantDAO.getCheckboardList_participants(conn, param);
			request.setAttribute("list", list);
			
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/user/friend.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
