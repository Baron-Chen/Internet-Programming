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
import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import bean.CustomerBean;
import bean.QueueInfoBean;
import bean.TableBean;
import database.CustomerDao;
import database.QueueInfoDao;
import database.TableDao;

/**
 * Servlet implementation class ReserveServlet
 */
@WebServlet("/ReserveServlet")
public class ReserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReserveServlet() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String customName = request.getParameter("name");
		String customContNum = request.getParameter("phone");
		String number = request.getParameter("member");
		int member = Integer.valueOf(number);
		String gender = request.getParameter("gender");
		// �ж��û��Ƿ���ڣ������ھ�����
		CustomerBean newCustomer = new CustomerBean(customContNum, gender);
		try {
			if (!CustomerDao.IsUserExit(customContNum)) {
				CustomerDao.add(newCustomer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String tableType = "A";
		QueueInfoBean queueInfo = new QueueInfoBean();
		List<QueueInfoBean> queue=new ArrayList<QueueInfoBean>();
		if (member == 1 || member == 2) {
			tableType = "A";
			int currentCount = QueueInfoBean.currentCountA;
			String queueNum = tableType + currentCount;
			QueueInfoBean.currentCountA++;
			try {
				queue=QueueInfoDao.findAll(tableType);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int waittingCount = queue.size();
			int waittingTime = waittingCount * 5;
			queueInfo = new QueueInfoBean(queueNum, tableType, waittingTime, member, waittingCount);

			//queue.add(queueInfo);
		} else if (member == 3 || member == 4) {
			tableType = "B";
			int currentCount = QueueInfoBean.currentCountB;
			String queueNum = tableType + currentCount;
			QueueInfoBean.currentCountB++;
			try {
				queue=QueueInfoDao.findAll(tableType);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int waittingCount = queue.size();
			int waittingTime = waittingCount * 5;
			queueInfo = new QueueInfoBean(queueNum, tableType, waittingTime, member, waittingCount);

			//QueueInfoBean.queueB.add(queueInfo);

		} else if (member >= 5) {
			tableType = "C";
			int currentCount = QueueInfoBean.currentCountC;
			String queueNum = tableType + currentCount;
			QueueInfoBean.currentCountC++;
			try {
				queue=QueueInfoDao.findAll(tableType);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int waittingCount = queue.size();
			int waittingTime = waittingCount * 5;
			queueInfo = new QueueInfoBean(queueNum, tableType, waittingTime, member, waittingCount);

			//QueueInfoBean.queueC.add(queueInfo);

		}

		// ����һ���ǽ���queue��Ϣ���뵽���ݿ���
		try {
			QueueInfoDao.add(queueInfo);
			TableBean table = TableDao.findByType(queueInfo.getTableType());
			int count = table.getRemainCount();			
			int wait=table.getWaitCount();
			System.out.println("count"+wait);
			wait++;
			System.out.println("wait"+wait);
			count--;
			table.setRemainCount(count);
			table.setWaitCount(wait);
			TableDao.update(table);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(queueInfo.getQueueNumber() + " " + queueInfo.getMembers() + " "
				+ queueInfo.getWaittingCount() + " " + queueInfo.getTableType() + " " + queueInfo.getWaittingTime());
		// ��ת�����ҵġ�ҳ��
		HttpSession session = request.getSession();
		session.setAttribute("queueNumber", queueInfo.getQueueNumber());
		session.setAttribute("tableType", queueInfo.getTableType());
		// request.setAttribute("queueNumber", queueInfo.getQueueNumber());
		// request.setAttribute("tableType", queueInfo.getTableType());
		request.getRequestDispatcher("mine.jsp").forward(request, response);
	}

}
