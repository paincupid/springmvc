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

<script type="text/javascript">

var colArray = Array.prototype.slice.call(columns);
$(function(){
	serach(1);
	$("#search_btn1").click(function () {
		serach(1);
	});
	
	var colArray = Array.prototype.slice.call(columns);
	buildTableTitle(colArray);
	
	
	
});
function buildTableTitle(colArray){
	var thead=[];
	thead.push("<thead id='columnTitles'><tr>");
	for(var i=0;i<colArray.length;i++){
		var col = colArray[i];
		thead.push("<th>" + col.title + "</th>");
	}
	thead.push("</tr></thead>");
	$("#retListTable").append(thead.join(""));
}
var detailUrl = "www.baidu.com";
var id = "007";
var columns = [
    {title: 'id', width: 120, sortable: false, dataIndex: 'id'},
    {title: '姓名', width: 100, sortable: false, dataIndex: 'name'},
    {title: '年龄', width: 120, sortable: false, dataIndex: 'age'},
    {title: '电话', width: 80, sortable: false, dataIndex: 'tel'},
    
    {
        title: '操作', width: 100, dataIndex: 'g', renderer: function (value, obj) {
        var id = obj.id;
        return '<span data-title="查看" data-href="' + detailUrl + '?id=' + id + '" data-id="' +
                id + '" class="page-action grid-command">查看</span>';
        }
    }
];

function serach(currentPage){
	$("#currentPage").val(currentPage);
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
				<h2>jquery.form.js用法</h2>
				</div>
				<form name="searchForm1" id="searchForm1" enctype="multipart/form-data">
					<input type="hidden" name="comment" id="comment" value="123"  style="width: 800px; height: 400px"/>
					<input type="hidden" name="currentPage" id="currentPage"/>
					<div class="component" >
							<label style="width:24%;">pid</label>
							<input  id = "id"   name="id"  class="text"  type = "text" value="18600001234"/>
							<label style="width:24%;">姓名</label>
							<input id = "name"  name="name"  class="text"  type = "text" value="用jqueryFormPlugin的提交"/>
							&nbsp&nbsp	
							<button id="search_btn1"  type="button" class="buttons ui-button-blue ui-button ui-widget ui-state-default ui-corner-all">查询提交表单1</button>
						</div>
				</form>
				<div class="space"></div>
				<table class="listTable" id = "retListTable" >
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