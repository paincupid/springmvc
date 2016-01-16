<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
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

<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/> 

	
<script type="text/javascript">

$(function(){
	var options={
		    //target : '#output',    // 把服务器返回的内容放入id为output的元素中
		    beforeSubmit : showRequest,    // 提交前的回调函数，做表单校验用
		    beforeSerialize: modifySubmitData,	//提交到Action/Controller时，可以自己对某些值进行处理。
		    success : showResponse,    // 提交后的回调函数
		    //contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		    url :'${pageContext.request.contextPath}/jqueryFormPlugin/getForm',    //默认是form的action，如果申明，则会覆盖
		    type : "POST",    // 默认值是form的method("GET" or "POST")，如果声明，则会覆盖
		    dataType : "json"    // html（默认）、xml、script、json接受服务器端返回的类型
		    // clearForm : true,    // 成功提交后，清除所有表单元素的值
		    // resetForm : true,    // 成功提交后，重置所有表单元素的值
		    // timeout : 3000    // 限制请求的时间，当请求大于3秒后，跳出请求
		}

	 // bind form using 'ajaxForm' 
	$('#myForm1').ajaxForm(options); 
	
	$("#search_btn1").click(function () {
		 $("#searchForm1").ajaxSubmit(options);
	});
	
});


function showRequest(formData, jqForm, options){
	//仅用于校验，即使改变了表单的某些值，在前台会有显示，但不改变提交到Action/Controller的值。
	
    // formData: 数组对象，提交表单时，form插件会以ajax方式自动提交这些数据，格式如[{name:user,value:val},{name:pwd,value:pwd}]
    // jqForm: jQuery对象，封装了表单的元素
    // options: options对象
    //var queryString=$.param(formData); // name=1&address=2
    //formData: $('#searchForm1').serialize();
    //var queryCondition = $('#searchForm1').serialize();
    var t1 = jqForm;
    var t2 = formData;
    var t3 = options;
    var queryCondition =$.param(formData);
    var formElement=jqForm[0]; // 将jqForm转换为DOM对象
    var name=formElement.name.value; // 访问jqForm的DOM元素
    $("#name").val("lele李姓名showRequest");
    $("#comment").val("呵呵ll123");
    
    return true; // 只要不返回false，表单都会提交，在这里可以对表单元素进行验证
}

function modifySubmitData(){
	//组装发送的表单值之前修改某些字段的值。在Action/Controller中真正接收的值。
	$("#id").val("lele李");
    $("#name").val("lele李姓名modifySubmitData");
    $("#comment").val("呵呵ll123444");
    
}
function showResponse(responseText,statusText){
    var data = responseText;
    
    if (data.success == true) {
        var retlist = data.result;
        //alert(retlist.length);
      }else{
        alert("error:"+data.responseText);
     } 

}

</script>
</head>
<body>
	<P>The time on the server is ${serverTime}.</P>
	<h2>jquery.form.js用法</h2>
	<form name="searchForm1" id="searchForm1" enctype="multipart/form-data">
		<input type="hidden" name="comment" id="comment" value="123" />
		<table>
			<tr>
				<td><label>pid</label></td>
				<td><input id = "id"   name="id"  type = "text" value="18600001234"/></td>
			</tr>
			<tr>
				<td><label>姓名</label></td>
				<td><input id = "name"  name="name" type = "text" value="用jqueryFormPlugin的提交"/></td>
			</tr>
			<tr>
				<td><button id="search_btn1"  type="button">查询提交表单1</button></td>
			</tr>
		</table>
	</form>
	
</body>
</html>