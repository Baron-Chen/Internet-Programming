package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.QueueInfoBean;
import bean.TableBean;
import database.TableDao;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<TableBean> tables = new ArrayList<TableBean>();
		int waitCount = 0;
		int waitTime = 0;
		try {
			tables = TableDao.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (tables != null) {
			for (TableBean table : tables) {
				int remain = table.getRemainCount();
				if (remain < 0)
					remain = 0;
				request.setAttribute("remainNum"+table.getTableType(), remain);
				waitCount = table.getWaitCount();
				if(waitCount<0)
					waitCount=0;
				waitTime = 5 * waitCount;
				System.out.println(table.getTableType() + " wait " + waitCount + ", remain " + remain + ", time " + waitTime);
				request.setAttribute("waitNum"+table.getTableType(), waitCount);
				request.setAttribute("waitTime"+table.getTableType(), waitTime);
				
			}
		} else
			System.out.println("tables is null");
		System.out.println("==================");
		//request.getRequestispatcher("index.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
