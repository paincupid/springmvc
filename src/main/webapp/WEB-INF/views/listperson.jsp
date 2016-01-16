<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>My JSP 'index.jsp' starting page</title>
<script type="text/javascript">
	
</script>
</head>
<body>
	<P>The time on the server is ${serverTime}.</P>

	<h2>用户列表</h2>
	<form name="form1"  id="form1">
		
		<table border="1">
			<tr>
				<td>id</td>
				<td>name</td>
			</tr>
			
			<c:forEach items="${personList}" var="row">
			<tr>
				<td align="center">${row.id}</td>
				<td align="center">${row.name}</td>
			</tr>
			</c:forEach>
			
		</table>
	</form>
</body>
</html>