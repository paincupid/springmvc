<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

System.out.println("addree path: "+path);
%>

<html>
<head>
	<title>addressu</title>
	<base href="<%=basePath%>">
	
	<script type="text/javascript" src="<%=basePath%>/resources/js/jquery/jquery-1.4.4.min.js"></script>
</head>

</script>
<script type="text/javascript">
   function testjquery(){
	    var user_name=$("#test").attr("value");
	    alert(user_name);
    }
  </script>
<body>
	<input id="test" value="jquery">
	<input type="button" value="click me!" onclick="testjquery();">
</body>
</html>