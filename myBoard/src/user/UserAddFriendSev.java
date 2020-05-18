package user;

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

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import user.db.UserDAO;
import user.db.UserRelationshipDAO;
import user.vo.UserRelationshipVO;
import user.vo.UserVO;


@WebServlet("/user/AddFriend.do")
public class UserAddFriendSev extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession hs = request.getSession();
		UserVO authUser = (UserVO)hs.getAttribute("authUser");
		String[] user_ids = request.getParameterValues("user_id");
		
		for( String user_id : user_ids) {
			UserRelationshipVO param = new UserRelationshipVO();
			param.setI_user(authUser.getI_user());
			param.setI_userf(Integer.parseInt(user_id));
			
			Connection conn = null;
			try {
				conn = ConnectionProvider.getConnection();
				UserRelationshipDAO.InsertFriend(conn, param);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				JdbcUtil.close(conn);
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher("/user/team.do");
		rd.forward(request, response);
	}
}
