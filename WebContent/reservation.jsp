<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello Word!</title>
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="js/index_control.js"></script>
<link rel="stylesheet" type="text/css" href="css/reservation.css">
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

	<div class="left_part"></div>

	<div class="right_part">
		<div class="content">
			<h3>Reservation</h3>
			</br>
			</br>
			<div style="height: 90%">
				<form style="height: 100%; position: relative;"
					action=ReserveServlet method="post">
					<div style="width: 100%; float: left;">Your Name:</div>
					<input type="text" name="name" id="name" class="input">
					<div style="height: 15%; float: left; width: 30%;">
						<label class="check" for="mr"> <input type="radio"
							value="1" name="gender" id="mr" class="gender" checked="checked">
							<span class="icon"><div class="span_icon" id="mr_icon"></div></span>
						</label> <label class="check" for="miss"> <input type="radio"
							value="2" name="gender" id="miss" class="gender"> <span
							class="icon"><div class="span_icon" id="miss_icon"></div></span>
						</label>
					</div>
					<div style="width: 100%; float: left;">Your Contact Number:</div>
					</br>
					</br>
					<input type="tel" name="phone" id="phone" class="input">
					<div style="width: 100%; float: left;">Number of Seats:</div>
					<input type="number" name="member" id="member" class="input">
					<c:if test="${queueNumber==null}">
						<label
							style="width: 20%; right: 1%; bottom: 1%; position: absolute; cursor: pointer;"
							for="submit"> Submit<input type="submit" value="&nbsp;"
							id="submit"></label>
					</c:if>
				</form>
			</div>
		</div>
	</div>
</body>
</html>