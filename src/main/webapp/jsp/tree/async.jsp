<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html>
<HTML>
<HEAD>
	<TITLE> ZTREE DEMO - Async</TITLE>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=basePath%>/resources/css/demo.css" type="text/css">
	<link rel="stylesheet" href="<%=basePath%>/resources/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="<%=basePath%>/resources/js/jquery/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/resources/js/jquery/jquery.ztree.core-3.5.min.js"></script>
	
	<!--  <script type="text/javascript" src="<<%=basePath%>/resources/js/jquery.ztree.excheck-3.4.js"></script>
	  <script type="text/javascript" src="<<%=basePath%>/resources/js/jquery.ztree.exedit-3.4.js"></script>-->
	<SCRIPT type="text/javascript">
		var setting = {
			async: {
				enable: true,
				url:"getNodes.php",
				autoParam:["id", "name=n", "level=lv"],
				otherParam:{"otherParam":"zTreeAsyncTest"},
				dataFilter: filter
			}
		};

		function filter(treeId, parentNode, childNodes) {
			if (!childNodes) return null;
			for (var i=0, l=childNodes.length; i<l; i++) {
				childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			}
			return childNodes;
		}

		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting);
		});
	</SCRIPT>
</HEAD>

<BODY>
<h1>异步加载节点数据的树</h1>
<h6>[ 文件路径: core/async.html ]</h6>
<div class="content_wrap">
	<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
	<div class="right">
		<ul class="info">
			<li class="title"><h2>1、setting 配置信息说明</h2>
				<ul class="list">
				<li class="highlight_red">使用异步加载，必须设置 setting.async 中的各个属性，详细请参见 API 文档中的相关内容</li>
				</ul>
			</li>
			<li class="title"><h2>2、treeNode 节点数据说明</h2>
				<ul class="list">
				<li>异步加载功能对于 treeNode 节点数据没有特别要求，如果采用简单 JSON 数据，请设置 setting.data.simple 中的属性</li>
				<li>如果异步加载每次都只返回单层的节点数据，那么可以不设置简单 JSON 数据模式</li>
				</ul>
			</li>
			<li class="title"><h2>3、其他说明</h2>
				<ul class="list">
				<li class="highlight_red">观察 autoParam 和 otherParam 请使用 firebug 或 浏览器的开发人员工具</li>
				<li class="highlight_red">此 Demo 只能加载到第 4 级节点（level=3）</li>
				<li class="highlight_red">此 Demo 利用 dataFilter 对节点的 name 进行了修改</li>
				</ul>
			</li>
		</ul>
	</div>
</div>
</BODY>
</HTML>