<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ninja Gold Game</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

</head>
<body>
	<div class="container">
		<h1>Ninja Gold Game</h1>
		<h2>Your Gold: ${total_gold}</h2>

		<table>
			<col width="300">
			<col width="300">
			<col width="300">
			<col width="300">
			<tr>
				<td>
					<form action="/process_money" method="post">
						<h2>Farm</h2>
						<p>(earns 10-20 golds)</p>
						<input type="hidden" name="which_form" value="farm" /> <input
							type="submit" class="btn btn-success" value="Find Gold!" />
					</form>
				</td>
				<td>
					<form action="/process_money" method="post">
						<h2>Cave</h2>
						<p>(earns 5-10 golds)</p>
						<input type="hidden" name="which_form" value="cave" /> <input
							type="submit" class="btn btn-success" value="Find Gold!" />
					</form>
				</td>
				<td>
					<form action="/process_money" method="post">
						<h2>House</h2>
						<p>(earns 2-5 golds)</p>
						<input type="hidden" name="which_form" value="house" /> <input
							type="submit" class="btn btn-success" value="Find Gold!" />
					</form>
				</td>
				<td>
					<form action="/process_money" method="post">
						<h2>Casino</h2>
						<p>(earns/takes 0-50 golds)</p>
						<input type="hidden" name="which_form" value="casino" /> <input
							type="submit" class="btn btn-success" value="Find Gold!" />
					</form>
				</td>
			</tr>
		</table>

		<p>Activities:</p>
		<select multiple size="10" style="width: 80%;">
			<c:forEach items="${activities}" var="activity">
			${activity}
			</c:forEach>
		</select>

		<form action="/reset">
			<input type="submit" class="btn btn-warning" value="Play Again">
		</form>

	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</body>
</html>