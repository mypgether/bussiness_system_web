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
<script type="text/javascript" src="main/EasyUI/custom/custom.js"></script>
<script type="text/javascript" src="main/EasyUI/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css"
	href="main/EasyUI/custom/lastnews.js">
<link rel="stylesheet" type="text/css"
	href="main/EasyUI/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="main/EasyUI/custom/custom.css">
<link id="easyuiTheme" rel="stylesheet" type="text/css"
	href="main/EasyUI/themes/default/easyui.css">
<script type="text/javascript">
	$(function() {
		$("#panel").panel({
			fit : true,
		});
	});
</script>
<title>查询条件 文书档案类</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>
<body>
	<div id="panel" class="easyui-panel">
		<form id="ff" action="user/users" method="post">
			<table cellpadding="5">
				<tr>
					<td>题名(标题):</td>
					<td><input class="easyui-textbox" type="text" name="Bt"
						style="width:200px"></input></td>
				</tr>
				<tr>
					<td>年&nbsp&nbsp&nbsp&nbsp&nbsp度:</td>
					<td><select class="easyui-combobox" name="nd"
						style="width:200px"><option value="2014">2014</option>
							<option value="2013">2013</option>
							<option value="2012">2012</option>
							<option value="2011">2011</option>
							<option value="2010">2010</option>
							<option value="2010">2009</option>
							<option value="2008">2008</option>
							<option value="2007">2007</option>
							<option value="2006">2006</option>
							<option value="2005">2005</option>
							<option value="2004">2004</option>
					</select></td>
					<td>所在地:</td>
					<td><select class="easyui-combobox" name="position"
						style="width:200px"><option value="不限">不限</option>
							<option value="hz">杭州</option>
							<option value="nb">宁波</option>
							<option value="sx">绍兴</option>
							<option value="tz">台州</option>
							<option value="qz">衢州</option>
							<option value="wz">温州</option>
					</select></td>
				</tr>
				<tr>
					<td>文&nbsp&nbsp&nbsp&nbsp&nbsp号:</td>
					<td><input class="easyui-textbox" type="text" name="wh"
						style="width:200px"></input></td>
					<td>密&nbsp&nbsp级:</td>
					<td><input class="easyui-textbox" type="text" name="mj"
						style="width:200px"></input></td>
				</tr>
				<tr>
					<td>分&nbsp类&nbsp号:</td>
					<td><select class="easyui-combobox" name="flh"
						style="width:200px"><option value="A">综合类(A)</option>
							<option value="B">计划财务类(B)</option>
							<option value="C">地籍管理类(C)</option>
							<option value="D">国土资源利用规划类(D)</option>
							<option value="E">建设用地类(E)</option>
							<option value="F">国土资源检查类(F)</option>
							<option value="G">国土资源啊宣教、科技、信息类(G)</option>
							<option value="H">电子、声像材料类(H)</option>
							<option value="I">地质、矿产管理类(I)</option>
					</select></td>
					<td>保管期限:</td>
					<td><select class="easyui-combobox" name="Bgqx"
						style="width:200px"><option value="不限">不限</option>
							<option value="1">一年</option>
							<option value="2">两年</option>
							<option value="3">三年</option>
							<option value="4">四年</option>
							<option value="5">五年</option>
							<option value="6">六年</option>
							<option value="7">七年</option>
							<option value="8">八年</option>
							<option value="9">九年</option>
							<option value="10">十年</option>
					</select></td>
				</tr>
				<tr>
					<td>案&nbsp卷&nbsp号:</td>
					<td><input class="easyui-textbox" type="text" name="ajh"
						style="width:200px"></input></td>
					<td>件&nbsp&nbsp号:</td>
					<td><input class="easyui-textbox" type="text" name="jh"
						style="width:200px"></input></td>
				</tr>
				<tr>
					<td>录入日期:</td>
					<td><input class="easyui-datebox" type="text" name="startTime"
						style="width:200px"></input></td>
					<td>至:</td>
					<td><input class="easyui-datebox" type="text" name="endTime"
						style="width:200px"></input></td>
				</tr>
			</table>
			<div style="text-align:center;padding:5px">
				<input type="submit" value="提交" /> <input type="button" value="清空"
					onclick="clearForm()" />
			</div>
		</form>

	</div>
	<script>
		function clearForm() {
			$('#ff').form('clear');
		}
	</script>
</body>

</html>
