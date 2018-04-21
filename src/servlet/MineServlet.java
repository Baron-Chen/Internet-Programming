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
import javax.servlet.http.HttpSession;

import bean.MealBean;
import bean.QueueInfoBean;
import database.OrderDao;
import database.QueueInfoDao;

/**
 * Servlet implementation class MineServlet
 */
@WebServlet("/MineServlet")
public class MineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MineServlet() {
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

		// request.setAttribute("queueInfo", queueInfo);
		HttpSession session = request.getSession();
		String queueNum = (String) session.getAttribute("queueNumber");
		String tableType = (String) session.getAttribute("tableType");
		String isOrder = (String) session.getAttribute("isOrder");
		QueueInfoBean queueInfo = new QueueInfoBean();
		request.setAttribute("isMissed", "false");
		// 根据queueNum查找出对应的queue信息
		if (tableType != null) {
			System.out.println(tableType);
			try {
				queueInfo = QueueInfoDao.findByNum(queueNum, tableType);
				if (queueInfo == null) {
					request.setAttribute("isMissed", "true");
				} else {
					System.out.println("No. " + queueInfo.getQueueNumber() + " number:" + queueInfo.getMembers()
					+ " waittingCount:" + queueInfo.getWaittingCount() + "  tableType:" + queueInfo.getTableType()
					+ " waittingTime" + queueInfo.getWaittingTime());
					request.setAttribute("queueInfo", queueInfo);
					if (isOrder != null) {
						if (isOrder.equals("true")) {
							ArrayList<MealBean> orderList = new ArrayList<>();
							if (session.getAttribute("orderList") != null) {
								orderList = (ArrayList<MealBean>) session.getAttribute("orderList");
								for (MealBean mealBean : orderList) {
									System.out.println(mealBean.getMealName() + "++" + mealBean.getMealType() + "++"
											+ mealBean.getPrice());
								}
								request.setAttribute("order_list", orderList);
							}
						}
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			

		}
		/*
		 * String orderId=request.getParameter("orderId"); if(orderId!=null) { try {
		 * ArrayList<MealBean> meals = OrderDao.findById(orderId).getOrderDetails();
		 * request.setAttribute("order_list", meals); } catch (SQLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } }
		 */

		// request.getRequestDispatcher("mine.jsp").forward(request, response);

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
