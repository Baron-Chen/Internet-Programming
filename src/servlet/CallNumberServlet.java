package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.QueueInfoBean;
import bean.TableBean;
import database.QueueInfoDao;
import database.TableDao;

/**
 * Servlet implementation class CallNumber
 */
@WebServlet("/CallNumber")
public class CallNumberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CallNumberServlet() {
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
		String nextQA = "none";
		String nextQB = "none";
		String nextQC = "none";
		ArrayList<QueueInfoBean> queueA = null;
		ArrayList<QueueInfoBean> queueB = null;
		ArrayList<QueueInfoBean> queueC = null;

		try {
			queueA = (ArrayList<QueueInfoBean>) QueueInfoDao.findAll("A");
			queueB = (ArrayList<QueueInfoBean>) QueueInfoDao.findAll("B");
			queueC = (ArrayList<QueueInfoBean>) QueueInfoDao.findAll("C");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		QueueInfoBean current = new QueueInfoBean();
		if (queueA != null && queueA.size() > 0) {
			current = queueA.get(0);
			nextQA = current.getQueueNumber();
		}
		if (queueB != null && queueB.size() > 0) {
			current = queueB.get(0);
			nextQB = current.getQueueNumber();
		}
		if (queueC != null && queueC.size() > 0) {
			current = queueC.get(0);
			nextQC = current.getQueueNumber();
		}
		request.setAttribute("nextQA", nextQA);
		request.setAttribute("nextQB", nextQB);
		request.setAttribute("nextQC", nextQC);

	}

	private String updateQInfo(ArrayList<QueueInfoBean> queue, String type) {
		// TODO Auto-generated method stub
		String currentQNum = "none";
		QueueInfoBean delete = new QueueInfoBean();
		if (queue != null && queue.size() > 0) {
			delete = queue.remove(0);
			try {
				QueueInfoDao.delete(delete);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (queue != null && queue.size() > 0) {
				for (QueueInfoBean queueInfo : queue) {
					int count = queueInfo.getWaittingCount();
					count--;
					int time = count * 5;
					queueInfo.setWaittingCount(count);
					queueInfo.setWaittingTime(time);
					try {
						QueueInfoDao.update(queueInfo);

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				QueueInfoBean currentQ = queue.get(0);
				currentQNum = currentQ.getQueueNumber();
				System.out.println(currentQ.getTableType() + ":  nextQNumber:" + currentQ.getQueueNumber()
						+ " currentCount:" + queue.size());

			}
			try {
				TableBean table = TableDao.findByType(type);
				int count = table.getRemainCount();
				int wait = table.getWaitCount();
				if (count < table.getTotalCount()) {
					count++;
				} else
					count = table.getTotalCount();
				wait--;
				table.setRemainCount(count);
				table.setWaitCount(wait);
				TableDao.update(table);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return currentQNum;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tableType = request.getParameter("tableType");
		String status = request.getParameter("status");
		String current = request.getParameter("current");
		String nextQ = "none";
		ArrayList<QueueInfoBean> queue = new ArrayList<>();
		if (tableType != null) {
			try {
				queue = (ArrayList<QueueInfoBean>) QueueInfoDao.findAll(tableType);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			nextQ = updateQInfo(queue, tableType);
		}

		/*
		 * if (tableType.equals("A")) { nextQ = updateQInfo(queue, tableType);
		 * request.setAttribute("nextQA", nextQ); } else if (tableType.equals("B")) {
		 * nextQ = updateQInfo(queue, tableType); request.setAttribute("nextQB", nextQ);
		 * } else if (tableType.equals("C")) { nextQ = updateQInfo(queue, tableType);
		 * request.setAttribute("nextQC", nextQ); }
		 */

		if (status != null) {
			System.out.println(status);
			if (status.equals("missed")) {
				if (!current.equals("none")) {
					try {
						QueueInfoBean currentQ = QueueInfoDao.findByNum(current, tableType);
						if (currentQ != null) {
							currentQ.setIsMissed("true");
							QueueInfoDao.update(currentQ);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}

		}
		if (tableType == null) {
			doGet(request, response);
		}
		request.setAttribute("nextQ" + tableType, nextQ);
		response.getWriter().write(nextQ);
		System.out.println("nextQ" + tableType + ":" + nextQ);
		// request.getRequestDispatcher("table_control.jsp").forward(request, response);
	}

}
