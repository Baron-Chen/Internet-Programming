<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="bean.QueueInfoBean"%>
<%@page import="bean.MealBean"%>
<!DOCTYPE html>
<jsp:include page="/MineServlet" flush="true" />
<html>
<head>
<meta charset="UTF-8">
<title>Hello Word!</title>
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="js/index_control.js"></script>
<script type="text/javascript" src="js/my_condition.js"></script>
<link rel="stylesheet" type="text/css" href="css/mine.css">
</head>
<body>
	<div class="top_bar">
		<div class="top_button" id='home'>
			<div class='btn_pic' id='btn_home'></div>
			<div class='btn_text'>HOME</div>
		</div>
		<div class="top_button" id='reservation'>
			<div class='btn_pic' id='btn_reservation'></div>
			<div class='btn_text'>RESERVATION</div>
		</div>
		<div class="top_button" id='menu'>
			<div class='btn_pic' id='btn_menu'></div>
			<div class='btn_text'>MENU</div>
		</div>
		<div class="top_button" id='mine'>
			<div class='btn_pic' id='btn_mine'></div>
			<div class='btn_text'>MINE</div>
		</div>
	</div>
	<c:if test="${isMissed=='false' }">
		<div class="my_info">
			<div class="show_info">
				<div class="num_tag">
					No.
					<div class="num">${queueInfo.getQueueNumber()}</div>
				</div>
				<div class="table_info">
					<div>Waiting tables ahead:</div>
					</br>
					<div id="wait_table" class="table_detail">${queueInfo.getWaittingCount()}</div>
					<div class="line"></div>
					</br>
					<div>Estimated Waiting Time:</div>
					</br>
					<div id="wait_time" class="table_detail">${queueInfo.getWaittingTime()}</div>
					<div class="line"></div>
				</div>
				<form id="form" action="menu.jsp" method="GET">
					<div class="order" id="online">
						<div class="order_icon" id="online_icon"></div>
						Online Order
					</div>
				</form>
				<div class="order" id="inside">
					Order at Restaurant
					<div class="order_icon" id="inside_icon"></div>
				</div>
			</div>
			<c:if test="${isOrder=='true' }">
				<div class="my_order">
					My Order
					<ul class="order_list">
						<li><p>Name</p>
							<p>Number</p>
							<p>Price</p></li>
						<div class="line"></div>
						<c:forEach items="${order_list}" var="eachOrder">
							<li><p>${eachOrder.getMealName()}</p>
								<p>${eachOrder.getCount()}</p>
								<p>S$ ${eachOrder.getPrice()}</p>
								</li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
		</div>
	</c:if>
	<c:if test="${isMissed=='true'}">
		<div class="my_condition">
			Sorry!Missed your queue</br> You May Want:</br>
			<div class="condition_icon" id="re_reserve"></div>
			<div class="condition_icon" id="cancel"></div>
		</div>
	</c:if>
	<script type="text/javascript">
		$("#online").click(function() {
			$("#form").submit();
		});
	</script>
</body>
</html>