<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<html>
<head>
<title>My JSP 'index.jsp' starting page</title>

<script type="text/javascript"
	src="<%=basePath%>/resources/js/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#return_list_btn").click(function(){
			jQuery.ajax( {
     	        type : 'GET',
     	        contentType : 'application/json',
     	        url : '${pageContext.request.contextPath}/json/getList',
     	        dataType : 'json',
     	        async: false,
     	        success : function(data) {
     	          if (data.success == true) {
     	            var retlist = data.result;
     	            alert(retlist.length);
     	          }
     	        },
     	        error : function() {
     	          //alert("error: can't get  data")
     	        }
     	      });
		});
		$("#return_map_btn").click(function(){
			jQuery.ajax( {
     	        type : 'GET',
     	        contentType : 'application/json',
     	        url : '${pageContext.request.contextPath}/json/getClass',
     	        dataType : 'json',
     	        async: false,
     	        success : function(data) {
     	          if (data.success == true) {
     	            var person = data.result;
     	            alert(person.id);
     	          }
     	        },
     	        error : function() {
     	          //alert("error: can't get  data")
     	        }
     	      });
		});
	});
</script>
</head>
<body>
	<P>The time on the server is ${serverTime}.</P>

	<h2>用户列表</h2>
	<form name="form1" id="form1">
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
	<form name="form2" id="form2">
		<button id="return_list_btn" type="button">查询一个list</button>
	</form>
	<form name="form3" id="form3">
		<button id="return_map_btn" type="button">得到类</button>
	</form>
</body>
</html>