package checkboard;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import checkboard.db.CheckboardDAO;
import checkboard.db.CheckboardGoalDAO;
import checkboard.vo.CheckBoardGoalVO;
import checkboard.vo.CheckboardVO;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import user.vo.UserVO;

@WebServlet("/checkboard/list.do")
public class CheckboardListSev extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 체크
		HttpSession hs = request.getSession();
		UserVO authUser = (UserVO)hs.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect("/user/login.do");
			return;
		}
		
		//페이지 설정
		int page = 1;
		String strPage = request.getParameter("page");
		if(strPage != null) {
			page = Integer.parseInt(strPage);
		}		
		int RowCnt = 10; //LIMIT 뒷값
		int sIdx = (page - 1) * RowCnt; //LIMIT 앞값
		
		
		
		
		
		CheckboardVO param = new CheckboardVO();
		param.setCheckboard_i_user(authUser.getI_user());
		
		//DB로부터 리스트를 가져온다.
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false); // 트랜잭션을 시작
			request.setAttribute("page", page);
			request.setAttribute("totalPageCnt", CheckboardDAO.getTotalPageCnt(conn, RowCnt));
			List<CheckboardVO> checkboardList = CheckboardDAO.getCheckboardList(conn, param, sIdx, RowCnt);
			// 맵 < 체크보드VO : 체크보드골list>
			HashMap<CheckboardVO, List<CheckBoardGoalVO>> map = new HashMap<>();
			for(CheckboardVO index : checkboardList) {
				map.put(index,CheckboardGoalDAO.getCheckboardList_goal(conn, index));
			}
			request.setAttribute("checkboardMap", map);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/checkboard/checkboardList.jsp");
		rd.forward(request, response);
	}


}





