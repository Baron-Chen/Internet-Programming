<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<jsp:include page="/CallNumber" flush="true" />
<html>
<head>
<meta charset="UTF-8">
<title>Hello Word!</title>
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="js/index_control.js"></script>
<link rel="stylesheet" type="text/css" href="css/table_control.css">
</head>
<body>
	<div class="top_bar">
		<div class="top_text">Ordering</div>
	</div>
	<div class="table_info">
		<table class="table">
			<tr>
				<th style="width: 20%">Table Type</th>
				<th style="width: 20%">Current No.</th>
				<th style="width: 60%">Order</th>
			</tr>
			<tr>
				<td>
					<h1>A</h1>
					<p>(1-2)</p>
				</td>
				<td><hh id="nextQA">${nextQA}</hh></td>
				<td>
					<div class="arrived" id="arrivedA"></div>
					<div class="missed" id="missA"></div>
				</td>
			</tr>
			<tr>
				<td>
					<h1>B</h1>
					<p>(3-4)</p>
				</td>
				<td><hh id="nextQB">${nextQB}</hh></td>
				<td>
					<div class="arrived" id="arrivedB"></div>
					<div class="missed" id="missB"></div>
				</td>
			</tr>
			<tr style="border: none;">
				<td>
					<h1>C</h1>
					<p>(>5)</p>
				</td>
				<td><hh id="nextQC">${nextQC}</hh></td>
				<td>
					<div class="arrived" id="arrivedC"></div>
					<div class="missed" id="missC"></div>
				</td>
			</tr>
		</table>
	</div>
	<script type="text/javascript">
		$("#arrivedA").click(function() {
           $.post("CallNumber",{
        	   tableType: "A",
        	   status: "arrived",
        	   current: $("#nextQA").text()
           }
           , function(data) {
				$("#nextQA").text(data)
				console.log(data)
			});
		});
		$("#arrivedB").click(function() {
			$.post("CallNumber",{
	        	   tableType: "B",
	        	   status: "arrived",
	        	   current: $("#nextQB").text()
	           }
			, function(data) {
				$("#nextQB").text(data)
			});
		});
		$("#arrivedC").click(function() {
			$.post("CallNumber",{
	        	   tableType: "C",
	        	   status: "arrived",
	        	   current: $("#nextQC").text()
	           }
			, function(data) {
				$("#nextQC").text(data)
			});
		});
		$("#missA").click(function() {
			$.post("CallNumber",{
	        	   tableType: "A",
	        	   status: "missed",
	        	   current: $("#nextQA").text()
	           }
			, function(data) {
				$("#nextQA").text(data)
			});
		});
		$("#missB").click(function() {
			$.post("CallNumber",{
	        	   tableType: "B",
	        	   status: "missed",
	        	   current: $("#nextQB").text()
	           }
			, function(data) {
				$("#nextQB").text(data)
			}
			);
		});
		$("#missC").click(function() {
			$.post("CallNumber",{
	        	   tableType: "C",
	        	   status: "missed",
	        	   current: $("#nextQC").text()
			}
	           
		, function(data) {
				$("#nextQC").text(data)
			});
		});
	</script>
</body>
</html>