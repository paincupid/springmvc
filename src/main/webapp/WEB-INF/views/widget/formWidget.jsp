<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path;
%>
<html>
<head>
<title>jquery.form.js用法- author: paincupid</title>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>

<script type="text/javascript" src="<%=basePath%>/resources/js/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/jqueryForm/jquery.form.js"></script>
<!-- selene theme -->
<script type="text/javascript" src="<%=basePath%>/resources/js/seleneJs/html5.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/seleneJs/jquery-ui.min-1.8.16.js"></script>
<link type="text/css" href="<%=basePath%>/resources/css/seleneTheme/css/ui-selene/jquery-ui-1.8.17.custom.css" rel="stylesheet" />	
<link rel="stylesheet" href="<%=basePath%>/resources/css/seleneTheme/site/css/master.css" type="text/css" media="screen" title="no title" charset="utf-8">
<!-- jqPaginator -->
<script type="text/javascript" src="<%=basePath%>/resources/js/jqPaginator/jqPaginator.js"></script>
<!-- <link type="text/css" rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.1.1/css/bootstrap.min.css"/> -->

<script type="text/javascript" src="<%=basePath%>/resources/js/common/common.js"></script>

<script type="text/javascript">


$(function(){
	// Buttons
	$(".buttons").button();
	serach(1);
	getPersonDropDownTips();
	$("#search_btn1").click(function () {
		//var id = $( '#userRoleSelect option:selected' ).data( 'id' );
		var id = $( '#userRoleList option:selected' ).data( 'id' );
		alert(id);
		//TODO what should I do? 
		
		
		return;
		serach(1);
	});
	
	$("#personSelect").change(function(){
		if($("#personSelect").val()!=-1){
			getUserRoleDropDownTips();
		}
	});
	
	$("#userRoleList").change(function(){
		//do not work
		//alert($("#userRoleSelect").val());
	});
	
	
	
});

function lookup(){
	alert("f");
	// do not work.
}
function getPersonDropDownTips(){
	var requestUrl = "<%=basePath%>/formWidget/getPersonDropDownTips.json"
	$.ajax({
		url: requestUrl,
		type: 'GET',
		dataType: 'json',
		success: function(data){
			var html = [];
			if(data.success){
				var retlist = data.result;
				html.push(' <option value=-1 >请选择</option>' );
				for(var i=0;i<retlist.length;i++){
					var vo = retlist[i];
					html.push(' <option value=' + vo.id + '>' + vo.name + '</option>' );
				}
				$("#personSelect").append(html.join(""));
			}
		}
	});
}
function getUserRoleDropDownTips(){
	var personId = $("#personSelect").val();
	var requestUrl = '${pageContext.request.contextPath}'+"/formWidget/getUserRoleDropDownTips?personId="+personId;
	$.ajax({
		url: requestUrl,
		type: 'post',
		dataType: 'json',
		success: function(data){
			if(data.success){
				var retlist = data.result;
				var html = [];
				for(var i=0;i<retlist.length;i++){
					var vo = retlist[i];
					html.push(' <option  data-id= "'+vo.id+'" value="' + vo.name + '" class="userRoleSelect"></option>' );
				}
				$("#userRoleSelect").append(html.join(""));
			}
		}
	});
}
function serach(currentPage){
	$("#currentPage").val(currentPage);
	$("#searchForm1").ajaxSubmit({
		url :'${pageContext.request.contextPath}/formWidget/getForm',    //默认是form的action，如果申明，则会覆盖
		type : "POST",    // 默认值是form的method("GET" or "POST")，如果声明，则会覆盖
		dataType : "json",    // html（默认）、xml、script、json接受服务器端返回的类型
		beforeSubmit : function(){
		    // 提交前的回调函数，做表单校验用
			/* $("#name").val("lee李 - beforeSubmit");
		    $("#comment").val("呵呵ll123"); */
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
	    		buildTableData(data);
	    		initPageWidget(data);
	          }else{
	            alert("error:"+data.responseText);
	         } 
	    }
	    
 	});
}
function buildTableData(data){
	var retlist = data.result;
    $(".retListTr").remove();
    for (var i = 0; i < retlist.length; i++) {  
    	var vo = retlist[i];
    	var tbodyString = "<tr class = 'retListTr' data-id=" + vo.id +">";  
    	tbodyString = tbodyString + '<td>' + (i + 1) + '</td>'   
	                            + '<td>' + vo.id + '</td>'
	                            + '<td>' + vo.name + '</td>'   
	                            + '<td>' + vo.age + '</td>'   
	                            + '<td>' + vo.tel + '</td>'   
	                            + '<td>' + vo.prov + '</td>'   
	                            + '<td>' + vo.city + '</td>'   
	                            + '<td>' + vo.town + '</td>'   
	                            + '<td>' + vo.sex + '</td>'   
	                            + '<td>' + vo.location + '</td>'   
	                            + '<td>' + vo.company + '</td>'   
	                            + '<td>' + vo.comment + '</td>';
    	tbodyString = tbodyString + "</tr>";  
    
    	$("#retListBody").append($(tbodyString));
    }
}

function initPageWidget(data){
	//jqPaginator初始化
	$.jqPaginator('#pagination', {
        visiblePages: 10,
        currentPage: data.currentPage,
        pageSize: data.pageSize,
        totalCounts: data.totalCounts,
        onPageChange: function (num, type) {
        	if(type=="init") return;
        	serach(num);
        }
    });
}
</script>
</head>
<body>
	<div class="container">
		<div class="content" >
				<div  style="padding-left:2%">
				<h2>组件用法：百度下拉提示（在点击完姓名下拉后，ajax动态给角色赋值。使用html5的datalist）</h2>
				</div>
				<form name="searchForm1" id="searchForm1" enctype="multipart/form-data">
					<input type="hidden" name="comment" id="comment" value="123"  style="width: 800px; height: 400px"/>
					<input type="hidden" name="currentPage" id="currentPage"/>
					<div class="component" >
							<label style="width:24%;">pid</label>
							<input  id = "id"   name="id"  class="text"  type = "text" value="18600001234"/>
							<label style="width:24%;">姓名</label>
		             		<select id="personSelect" name="personSelect" ></select>
							<label style="width:24%;">角色</label>
							<input id = "userRole"  name="userRole"  class="text"  type = "text"  list="userRoleList"/>
							<datalist id="userRoleList">
								<select name="userRoleSelect" id="userRoleSelect" onchange="lookup()">
								
								</select>
							</datalist>
							

							&nbsp&nbsp	
							<button id="search_btn1"  type="button" class="buttons ui-button-blue">submit</button>
						</div>
				</form>
				<div class="space"></div>
				<table class="listTable" id = "retListTable" >
						<thead>  
							<tr>
					        	<th scope="col">序号</th>
					            <th scope="col">id</th>
					            <th scope="col">姓名</th>
					            <th scope="col">年龄</th>
					            <th scope="col">电话</th>
					            <th scope="col">省</th>
					            <th scope="col">市</th>
					            <th scope="col">区</th>
					            <th scope="col">性别</th>
					            <th scope="col">地点</th>
					            <th scope="col">公司</th>
					            <th scope="col">备注</th>
					            
					        </tr>
			            </thead>
			            <tbody id = "retListBody" >  
			            </tbody>
				</table>
				<div style="float:right; padding-right:2%">
	    		<ul class="pagination" id="pagination"></ul>
				</div>
		</div><!--  content   end -->
	</div><!--  container end -->
</body>
</html>