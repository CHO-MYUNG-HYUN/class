<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>Todo 수정 ( ${loginInfo.uid } [${loginInfo.uname }])</h1>

	<form method="post">

		번호 : <input type="text" name="no" value="${todo.tno }" readonly> <br>	
		할일 : <input type="text" name="todo" value="${todo.todo }"> <br>
		날짜 : <input type="date" name="duedate" value="${todo.duedate }"> <br>
		완료 : <input type="checkbox" name="complete" ${todo.finished ? 'checked' : '' }> <br>
		<!-- checkbox value => "on" -->
		<input type="reset"> <input type="submit" value="Modify">
		
	</form>
</body>
</html>