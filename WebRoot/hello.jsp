<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<script type="text/javascript" src="main/EasyUI/jquery-1.9.0.js"></script>
<script type="text/javascript" src="main/EasyUI/jquery.easyui.min.js"></script>
<script type="text/javascript" src="main/EasyUI/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css"
	href="main/EasyUI/custom/lastnews.js">
<link rel="stylesheet" type="text/css"
	href="main/EasyUI/themes/icon.css">
<link id="easyuiTheme" rel="stylesheet" type="text/css"
	href="main/EasyUI/themes/default/easyui.css">
<title>国土资源文档查询系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>
<body onload="javascript:window.location='login';">
</html>
