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

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import user.db.UserDAO;
import user.vo.UserVO;

@WebServlet("/user/join.do")
public class JoinSev extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/join.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		String user_password = request.getParameter("user_password");
		String user_email = request.getParameter("user_email");
		String user_gender = request.getParameter("user_gender");
		String[] user_hobbys = request.getParameterValues("user_hobby");
		String user_hobby = "";
		for (int i = 0; i < user_hobbys.length; i++) {
			user_hobby += user_hobbys[i] + ";";
        }
		String user_birth = request.getParameter("user_birth");
		System.out.println(request.getParameterMap().toString());
		
		UserVO vo = new UserVO();
		vo.setUser_id(user_id);
		vo.setUser_password(user_password);
		vo.setUser_email(user_email);
		vo.setUser_gender(user_gender);
		vo.setUser_hobby(user_hobby);
		vo.setUser_birth(user_birth);
		vo.setUser_birth(user_birth);

		int cmd = 0;
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false); // 트랜잭션을 시작

			List<UserVO> searchUser = UserDAO.getById(conn, vo);
			if (!searchUser.isEmpty()) { // ID에 해당하는 데이터가 이미 존재하면 에러발생
				JdbcUtil.rollback(conn);
				//throw new DuplicateIdException();
			}
			cmd = UserDAO.joinUser(conn,vo);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	
		
		switch(cmd) {
		case 0:
			System.out.println("회원 가입에 실패하였습니다.");
			response.sendRedirect("/join.do");
			break;
		case 1:
			System.out.println("회원 가입에 성공하였습니다.");
			response.sendRedirect("/login.do");
			break;
		case 2:
			System.out.println("회원 가입에 실패하였습니다.");
			response.sendRedirect("/join.do");
			break;
		}
	}
}
