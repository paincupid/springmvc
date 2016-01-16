<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path;
%>
<html>
<head>
<title>My JSP 'index.jsp' starting page</title>

<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/> 

<script type="text/javascript" src="<%=basePath%>/resources/js/seleneJs/jquery_1.7.1_jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/jqueryForm/jquery.form.js"></script>

<script type="text/javascript" src="<%=basePath%>/resources/js/seleneJs/html5.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/seleneJs/jquery-ui-1.8.16.min.js"></script>

<link type="text/css" href="<%=basePath%>/resources/css/seleneTheme/css/ui-selene/jquery-ui-1.8.17.custom.css" rel="stylesheet" />	
<link rel="stylesheet" href="<%=basePath%>/resources/css/seleneTheme/site/css/master.css" type="text/css" media="screen" title="no title">
		


	
<script type="text/javascript">

$(function(){
	
	$("#search_btn1").click(function () {
		$("#searchForm1").ajaxSubmit({
				url :'${pageContext.request.contextPath}/jqueryFormPluginSimple/getForm',    //默认是form的action，如果申明，则会覆盖
				type : "POST",    // 默认值是form的method("GET" or "POST")，如果声明，则会覆盖
				dataType : "json",    // html（默认）、xml、script、json接受服务器端返回的类型
				beforeSubmit : function(){
				    // 提交前的回调函数，做表单校验用
					$("#name").val("lee李 - beforeSubmit");
				    $("#comment").val("呵呵ll123");
				    return true;
				},
			    beforeSerialize: function(){
			    	//提交到Action/Controller时，可以自己对某些值进行处理。
			    	$("#id").val("lele李");
			        $("#name").val("lee李 - beforeSerialize");
			        $("#comment").val("呵呵ll123444");
			    },
			    success: function(data){
			    	if (data.success == true) {
			            var retlist = data.result;
			            $(".retListTr").remove();
			            for (var i = 0; i < retlist.length; i++) {  
			            	var vo = retlist[i];
			            	var tbodyString = "<tr class = 'retListTr' data-id=" + vo.id +">";  
			            	tbodyString = tbodyString + '<td>' + (i + 1) + '</td>'   
						                            + '<td>' + vo.id + '</td>'   
						                            + '<td>' + vo.name + '</td>';
			            	tbodyString = tbodyString + "</tr>";  
			            	
			            	$("#retListBody").append($(tbodyString));  
			            }
			          }else{
			            alert("error:"+data.responseText);
			         } 
			    }
			    
		 });
	});
	
});


</script>

</head>
<body>
	<div class="container">
		<div class="content" >
			<div class="component">
				<P>The time on the server is ${serverTime}.</P>
				<h2>jquery.form.js用法</h2>
			</div>
			<div class="component">
			
				<form name="searchForm1" id="searchForm1" enctype="multipart/form-data">
					<input type="hidden" name="comment" id="comment" value="123" />
					<table>
						<tr>
							<td><label>pid</label></td>
							<td><input id = "id"   name="id"  class="text" type = "text" value="18600001234"/></td>
						</tr>
						<tr>
							<td><label>姓名</label></td>
							<td><input id = "name"  name="name"  class="text" type = "text" value="用jqueryFormPlugin的提交"/></td>
						</tr>
						<div class="component">
							<p><button id="search_btn1"  type="button" class="bluebtn">查询提交表单1</button>
							<input class="buttons  ui-button  ui-state-default ui-corner-all" type="button" id="inputButton" value="Input Button" />
							<input class="buttons ui-button-blue ui-button ui-widget ui-state-default ui-corner-all" type="button" id="inputButton2" value="Input Button2" />
							</p>
						</div>
					</table>
					
					<div class="component">
						<!-- Button -->
						<h2 class="demoHeaders">UI Button</h2>	
						<p>
							<a   class="buttons"id="linkButton" href="#">&lt;A href="#"&gt; Button</a>		
							<input class="buttons" type="button" id="inputButton" value="Input Button" />
							<input class="buttons" type="submit" id="submitButton" value="Submit Button" />
						</p>
	
						<p>
							<a     class="buttons ui-button-blue"id="linkButton" href="#">&lt;A href="#"&gt; Button</a>		
							<input class="buttons ui-button-blue" type="button" id="inputButton" value="Input Button" />
							<input class="buttons ui-button-blue" type="submit" id="submitButton" value="Submit Button" />
						</p>
	
						<p>
							<a     class="buttons ui-button-green"id="linkButton" href="#">&lt;A href="#"&gt; Button</a>		
							<input class="buttons ui-button-green" type="button" id="inputButton" value="Input Button" />
							<input class="buttons ui-button-green" type="submit" id="submitButton" value="Submit Button" />
						</p>
					</div>
					
				</form>
				</div><!-- form end -->
				<div class="component">
				<table class="listtable" id = "retListTable" border="1">
						<thead>  
				             <tr  class="con-table-hd">  
					             <th>序号</th>
					             <th>id</th>
					             <th>描述</th>  
				             </tr> 
			            </thead>
			            <tbody id = "retListBody" >  
			            </tbody>
				</table>
				</div>
		</div>
	</div>
</body>
</html>