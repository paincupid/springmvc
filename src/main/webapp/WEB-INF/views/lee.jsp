<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>My JSP 'index.jsp' starting page</title>
<script type="text/javascript">
	//添加用户  
	function addUser1() {
		var form = document.forms['form1'];
		form.action = "${pageContext.request.contextPath}/leeController/addUser1";
		//form.action = "${pageContext.request.contextPath}/user/addUser2";  
		//form.action = "${pageContext.request.contextPath}/user/addUser3";
		form.method = "post";
		form.submit();
	}
	function addUser2() {
		var form = document.forms['form1'];
		form.action = "${pageContext.request.contextPath}/leeController/addUser2";
		//form.action = "${pageContext.request.contextPath}/user/addUser2";  
		//form.action = "${pageContext.request.contextPath}/user/addUser3";
		form.method = "post";
		form.submit();
	}
	function addUser3() {
		var form = document.forms['form1'];
		form.action = "${pageContext.request.contextPath}/leeController/addUser3";
		//form.action = "${pageContext.request.contextPath}/user/addUser2";  
		//form.action = "${pageContext.request.contextPath}/user/addUser3";
		form.method = "post";
		form.submit();
	}
	function queryUser() {
		var form2 = document.forms['form1'];
		form2.action = "${pageContext.request.contextPath}/leeController/getList";
		//form.action = "${pageContext.request.contextPath}/user/addUser2";  
		//form.action = "${pageContext.request.contextPath}/user/addUser3";  
		form2.method = "Get";
		form2.submit();
	}
</script>
</head>
<body>
	<P>The time on the server is ${serverTime}.</P>

	<h2>用户列表</h2>
	<form name="form1"  id="form1">
		<tr>
			<td>账号</td>
			<td><input type="text" name="userName" value="123"></td>
		</tr>
		<tr>
			<td>密码</td>
			<td><input type="text" name="password"></td>
		</tr>
		<tr>
			<td><input type="button" value="提交" onclick="queryUser()">
			</td>
			<td>
			<td><input type="button" value="add1" onclick="addUser1()"></td>
			<td><input type="button" value="add2" onclick="addUser2()"></td>
			<td><input type="button" value="add3" onclick="addUser3()"></td>
		</tr>


		<table border="1">
			<tr>
				<td>id</td>
				<td>name</td>
				<td>操作</td>
			</tr>

			<c:forEach items="${userList}" var="row">
				<td align="center">${row.userName}</td>
				<td align="center">${row.password}</td>
				<td><a
					href="${pageContext.request.contextPath}/person/toupdate.action?id=${p.id}">修改</a>
					<a
					href="${pageContext.request.contextPath}/person/delete.action?delId=${p.id}">删除</a>
				</td>
				</tr>
			</c:forEach>

		</table>
	</form>
</body>
</html>