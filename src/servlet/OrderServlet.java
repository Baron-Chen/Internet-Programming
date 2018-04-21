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
import bean.OrderBean;
import database.MealDao;
import database.OrderDao;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderServlet() {
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
		// 从数据库中查询到所有的meal信息,获得一个list或者cursor
		List<MealBean> mealList = new ArrayList<MealBean>();
		// 给前端的meal列表传值
		try {
			mealList = MealDao.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (MealBean mealBean : mealList) {
			System.out.println(mealBean.getMealName() + "==" + mealBean.getMealType() + "==" + mealBean.getPrice());
		}
		request.setAttribute("mealList", mealList);
		//request.getRequestDispatcher("menu.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<MealBean> orderList=new ArrayList<>();
		String dish_list = request.getParameter("dish_list");
		String[] lists = dish_list.split(",");
		for (int i=0;i<lists.length;i++) {
			String[] contains = lists[i].split("&");
			String name = contains[0];
			int price = Integer.parseInt(contains[1]);
			int num = Integer.parseInt(contains[2]);;
			if(num != 0) {
				orderList.add(new MealBean(name, "chuancai", num, price));
			}
		}
		HttpSession session = request.getSession();
		String queueNumber=(String) session.getAttribute("queueNumber");
				//request.getParameter("orderList");
		
		/*orderList.add(new MealBean("yuxiangrousi", "chuancai", 20,2));
		orderList.add(new MealBean("koushuiji", "chuancai", 15,1));
		orderList.add(new MealBean("shuizhuroupian", "chuancai", 25,2));*/
		int totalPrice=0;
		for (MealBean mealBean : orderList) {
			totalPrice+=mealBean.getPrice()*mealBean.getCount();
		}
		OrderBean order=new OrderBean(queueNumber, orderList, totalPrice);
		try {
			if(order.getTotalPrice() > 0) {
				OrderDao.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("totalPrice:"+totalPrice);
		
		session.setAttribute("isOrder", "true");
		session.setAttribute("orderList", orderList);
		session.setAttribute("totlePrice", totalPrice);
		request.getRequestDispatcher("mine.jsp").forward(request, response);
		return;
	}

}
