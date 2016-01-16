<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;

%>

<HTML>
 <HEAD>
  <TITLE> ZTREE DEMO </TITLE>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  
  <link rel="stylesheet" href="<%=basePath%>/resources/css/zTreeStyle/zTreeStyle.css" type="text/css">
  <style>
	body {background-color: white; margin:0; padding:0; text-align: center;}
	div, p, table, th, td {list-style:none; margin:0; padding:0; color:#333; font-size:12px;
		font-family:dotum, Verdana, Arial, Helvetica, AppleGothic, sans-serif;}
	#testIframe {margin-left: 10px;}
  </style>
<script type="text/javascript" src="<%=basePath%>/resources/js/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/resources/js/jquery/jquery.ztree.core-3.5.min.js"></script>
  <SCRIPT type="text/javascript" >
  
  var zTree;
	var demoIframe;

	var setting = {
		view: {
			dblClickExpand: false,
			showLine: true,
			selectedMulti: false,
			expandSpeed: ($.browser.msie && parseInt($.browser.version)<=6)?"":"fast"
		},
		data: {
			simpleData: {
				enable:true,
				idKey: "id",
				pIdKey: "pId",
				rootPId: ""
			}
		},
		callback: {
			beforeClick: function(treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj("tree");
				if (treeNode.isParent) {
					zTree.expandNode(treeNode);
					return false;
				} else {
					var src = '<%=basePath%>/'+treeNode.file;
					demoIframe.attr("src",src);
					return true;
				}
			}
		}
	};

	/* var zNodes =[
		{id:1, pId:0, name:"[core] 基本功能 演示", open:true},
		{id:108, pId:1, name:"异步加载 节点数据", file:"/async.jsp"}
	]; */
	var zNodes;
	
	$(document).ready(function(){
		getTreeData();
		var t = $("#tree");
		t = $.fn.zTree.init(t, setting, zNodes);
		demoIframe = $("#testIframe");
		demoIframe.bind("load", loadReady);
		var zTree = $.fn.zTree.getZTreeObj("tree");
		zTree.selectNode(zTree.getNodeByParam("id", 101));

	});

	function loadReady() {
		var bodyH = demoIframe.contents().find("body").get(0).scrollHeight,
		htmlH = demoIframe.contents().find("html").get(0).scrollHeight,
		maxH = Math.max(bodyH, htmlH), minH = Math.min(bodyH, htmlH),
		h = demoIframe.height() >= maxH ? minH:maxH ;
		if (h < 530) h = 530;
		demoIframe.height(h);
	}

	function getTreeData(){
 		$.ajax( {
 	        type : 'GET',
 	        contentType : 'application/json',
 	        url : '<%=basePath%>/systemTree/initTree',
 	        dataType : 'json',
 	        async: false,
 	        success : function(data) {
 	          if (data.success == true) {
 	            zNodes = data.result;
 	            
 	          }
 	        },
 	        error : function() {
 	          alert("error: can't get tree data")
 	        }
 	      });
 	}
</SCRIPT>
</HEAD>

<BODY>
	<TABLE border=0 height=98% align=left>
		<TR>
			<TD width="12%" align=left valign=top style="BORDER-RIGHT: #999999 1px dashed">
				<ul id="tree" class="ztree" style="width:260px; overflow:auto;"></ul>
			</TD>
			<TD width="100%" align=left valign=top>
				<IFRAME ID="testIframe" Name="testIframe" FRAMEBORDER=0  SCROLLING=AUTO 
				width=100%  height="100%" SRC="${pageContext.request.contextPath}/jqueryFormPluginSimple/list" onload="loadReady();" >
				</IFRAME>
		</TD>
		</TR>
	</TABLE>

</BODY>
</HTML>