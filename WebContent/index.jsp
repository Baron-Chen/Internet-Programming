<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<jsp:include page="/HomeServlet" flush="true"/>
<html>
<head>
<meta charset="UTF-8">
<title>Hello Word!</title>
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="js/index_control.js"></script>
<link rel="stylesheet" type="text/css" href="css/index.css">
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

	<div class="left_part">
		<table class="detail" cellspacing="55">
			<tr>
				<th>Table Type</th>
				<th>
					Number of Table
					<table cellspacing="20">
						<tr>
							<th>Remaining</th>
							<th>Waiting</th>
						</tr>
					</table>
				</th>
				<th>Estimated Waiting Time</th>
			</tr>
			<tr>
				<td>
					<h1>A</h1>
					<p>(1-2)</p>
				</td>
				<td>
					<div class="num"><h2>${remainNumA}</h2></div>
					<div class="num"><h2>${waitNumA}</h2></div>
				</td>
				<td><h2>${waitTimeA } min</h2></td>
			</tr>
			<tr>
				<td>
					<h1>B</h1>
					<p>(3-4)</p>
				</td>
				<td>
					<div class="num"><h2>${remainNumB}</h2></div>
					<div class="num"><h2>${waitNumB}</h2></div>
				</td>
				<td><h2>${waitTimeB } min</h2></td>
			</tr>
			<tr>
				<td>
					<h1>C</h1>
					<p>(>5)</p>
				</td>
				<td>
					<div class="num"><h2>${remainNumC}</h2></div>
					<div class="num"><h2>${waitNumC}</h2></div>
				</td>
				<td><h2>${waitTimeC}min</h2></td>
			</tr>
		</table>
	</div>

	<div class="right_part">
		<div class="reserve">
			<div id="reserve"></div><h2>Reserve Now!</h2>
		</div>
	</div>
</body>
</html>