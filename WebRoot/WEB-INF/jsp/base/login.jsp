<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
<script type="text/javascript" src="js/login.js"></script>
</head>
<body>
	<div id="loginDialog" class="easyui-dialog" title="登录" algin="center">
		<form id="loginForm" method="post">
			<table>
				<tbody>
					<tr>
						<td><lable>用户ID：</lable></td>
						<td><input class="easyui-validatebox" name="userId"
							required="true"></input></td>
					</tr>
					<tr>
						<td><lable>密码：</lable></td>
						<td><input class="easyui-validatebox" name="password"
							type="password" id="password" required="true"></input></td>
					</tr>
					<tr>
						<td><lable>重复密码：</lable></td>
						<td><input class="easyui-validatebox" name="repassword"
							type="password" id="repassword" required=true
							validType="equals['#password']"></input></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>
