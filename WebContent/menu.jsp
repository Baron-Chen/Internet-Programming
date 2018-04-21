<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="bean.QueueInfoBean"%>
<%@page import="bean.MealBean"%>

<!DOCTYPE html>
<jsp:include page="/OrderServlet" flush="true" />
<html>
<head>
<meta charset="UTF-8">
<title>Hello World!</title>
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="js/index_control.js"></script>
<script type="text/javascript" src="js/jquery.spinner.js"></script>
<link rel="stylesheet" type="text/css" href="css/menu.css">
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
		<div class="left_detail">
			<div class='btn_dish' id='btn_salad'></div>
			<div class='text_dish'>Salad</div>
		</div>
		<div class="left_detail">
			<div class='btn_dish' id='btn_lobster'></div>
			<div class='text_dish'>Meat</div>
		</div>
		<div class="left_detail">
			<div class='btn_dish' id='btn_ice'></div>
			<div class='text_dish'>Dessert</div>
		</div>
		<div class="left_detail" style="border-bottom: none">
			<div class='btn_dish' id='btn_wine'></div>
			<div class='text_dish'>Beverage</div>
		</div>
	</div>

	<div class="rightupper_part">
		<div id='subtitle_text'>Meat</div>
		<div class='btn_dish2' id='btn_lobster'></div>
		<div class='btn_order' id='btn_bill'></div>
		<c:if test="${queueNumber!=null}" >
		<form id=form  action=OrderServlet  method="POST">
			<div
				style="width: 10%; height: 50%; float: right; font-weight: bold; margin-top: 1%; margin-right: 2%; color: #ffffff; font-size: 200%; cursor: pointer"
				id="confirm">Confirm</div>
				<input type="text" name="dish_list" id="dish_list" value="text" hidden>
		</form>
		</c:if>
	</div>

	<div class="right_part">
		<c:forEach items="${mealList}" var="meal">
			<div class="rightdown_part">
				<div class='dish_detail' id='dish2'>
					<div
						style="width: 100%; margin-left: 95%; color: #ffffff; font-size: 200%; text-align: left;">
						Name:
						<div style="display: inline-block;" id="name_2" class="dish_name">${meal.getMealName()}</div>
					</div>
					<div
						style="width: 100%; color: #ffff00; font-size: 200%; margin: 23% 0% 0% 95%; text-align: left;">
						Price: S$
						<div style="display: inline-block;" id="price_2"
							class="dish_price">${meal.getPrice()}</div>
					</div>
					<div class="number_change" id="2">
						<div class="minus" id="m_2"></div>
						<input type="text" class="counter" id="c_2" />
						<div class="plus" id="p_2"></div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			var i = 0
			$(".rightdown_part").each(function() {
				$(this).find(".dish_detail").attr("id", "dish_" + i);
				$(this).find(".number_change").attr("id", i);
				$(this).find(".minus").attr("id", "m_" + i);
				$(this).find(".counter").attr("id", "c_" + i);
				$(this).find(".plus").attr("id", "p_" + i);
				$(this).find(".dish_name").attr("id", "name_" + i);
				$(this).find(".dish_price").attr("id", "price_" + i);
				i++;
			})
			$('.number_change').spinner();
			$("#confirm").on("click", function() {
				var dish_list = '';
				$(".dish_detail").each(function() {
					var name = $(this).find(".dish_name").text();
					var price = $(this).find(".dish_price").text();
					var num = $(this).find(".counter").val();
					dish_list += (name + '&' + price + '&' + num + ',');
					
				});
				//console.log(dish_list);
				$("#dish_list").val(dish_list);
				$("#form").submit(); 

			});
		})
	</script>
</body>
</html>