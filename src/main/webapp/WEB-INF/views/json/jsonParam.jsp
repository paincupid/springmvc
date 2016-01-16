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

<script type="text/javascript" src="<%=basePath%>/resources/js/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/jqueryForm/jquery.form.js"></script>
	
<script type="text/javascript">


$(function(){
		//var form = $("form[name=searchForm1]"); 
		//var t = $('#searchForm1').serialize();
		/* var options = {
				target: '#output1',
				url : '${pageContext.request.contextPath}/jsonparam/getNotForm?pid2='+pid2+'&pname2='+pname2,
	 	        dataType : 'post',
	 	        resetForm: false,
	 	        beforeSubmit: checkData,
	 	       success: function(data){
	 	            alert("成功了");
	 	        }
		};
		form.ajaxSubmit(options); */
	
	function checkData(formData, jqForm, options) {
		/* var formElement = jqForm[0]; 
		if ($(formElement).find("#pname").val() == "") {
			
		} */
	}
	
	
	$("#search_btn1").click(function(){
			var submitData = $('#searchForm1').serialize();
			alert(submitData);
	        $.ajax({
	            type: "POST",
	            dataType: "json",
	            //contentType: "application/x-www-form-urlencoded;charset=UTF-8",
	            url: '${pageContext.request.contextPath}/jsonparam/getForm',
	            data: $('#searchForm1').serialize(),
	            success: function (data) {
	            	var f = data.success;
	            	if (data.success == true) {
	     	            var retlist = data.result;
	     	            alert(retlist.length);
	     	          }
	            },
	            error: function(data) {
	                alert("error:"+data.responseText);
	             }
	
	        });
	});
	
	
	//ajax提交form表单的方式
	$("#search_btn2").click(function(){
			var submitData = $('#searchForm2').serialize();
            $.ajax({
                type: "POST",
                dataType: "json",
                url: '${pageContext.request.contextPath}/jsonparam/getNotForm',
                data: $('#searchForm2').serialize(),
                success: function (data) {
                	var f = data.success;
                	if (data.success == true) {
         	            var retlist = data.result;
         	            //alert(retlist.length);
         	          }
                },
                error: function(data) {
                    alert("error:"+data.responseText);
                 }

            });
	});
	
	
});

</script>
</head>
<body>
	<P>The time on the server is ${serverTime}.</P>
	<h2>json传参</h2>
	<form name="searchForm1" id="searchForm1" >
		<table>
			<tr>
				<td><label>pid</label></td>
				<td><input id = "id"   name="id"  type = "text" value="abcd李"/></td>
			</tr>
			<tr>
				<td><label>姓名</label></td>
				<td><input id = "name"  name="name" type = "text" value="1234啦一a"/></td>
			</tr>
			<tr>
				<td><button id="search_btn1"  type="button">查询提交表单</button></td>
			</tr>
		</table>
	</form>
	
	
	<form name="searchForm2" id="searchForm2">
		<table>
			<tr>
				<td><label>pid1</label></td>
				<td><input id = "pid2"  name = "pid2" type = "text" value="123地"/></td>
			</tr>
			<tr>
				<td><label>姓名2</label></td>
				<td><input id = "pname2"  name="pname2" type = "text"  value="abc"/></td>
			</tr>
			<tr>
				<td><button id="search_btn2" type="button">查询提交表单</button></td>
			</tr>
		<table>
	</form>
</body>
</html>