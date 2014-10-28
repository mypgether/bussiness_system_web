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
<title>查询结果 文书档案类</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="js/getCurrentTime.js"></script>
<script type="text/javascript">
	function formatterDeptId(user) {
		if (user.dept == "1") {
			return "技术部";
		} else if (user.dept == "2") {
			return "市场部";
		} else if (user.dept == "3") {
			return "销售部";
		} else if (user.dept == "4") {
			return "售后部";
		} else {
			return "运营部";
		}
	};
</script>
</head>

<body>
	<div id="addUser" class="easyui-dialog" title="添加用户" closed="true"
		draggable="true">
		<form id="addUserForm">
			<table>
				<tr>
					<td>用户ID:</td>
					<td><input class="easyui-validatebox" name="userId"
						required=true style="width:150px"></input></td>
					<td>用户名:</td>
					<td><input class="easyui-validatebox" name="userName"
						required=true style="width:150px"></input></td>
				</tr>
				<tr>
					<td>密码:</td>
					<td><input class="easyui-validatebox" name="password"
						required=true style="width:150px"></input></td>
					<td>用户昵称:</td>
					<td><input class="easyui-validatebox" name="nikeName"
						required=true style="width:150px"></input></td>
				</tr>
				<tr>
					<td>所属部门:</td>
					<td><select id="apart_combobox" class="easyui-combobox"
						name="dept" required=true style="width:150px" valueField="value"
						textField="lable" url="dept/findDeptsName" panelHeight="auto"}>
					</select></td>
					<td>加入时间:</td>
					<td><input class="easyui-datebox" name="joinTime"
						required=true style="width:150px"></input></td>
				</tr>
				<tr>
					<td>个人描述:</td>
					<td><textarea rows="4" cols="15" name="description"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dg" class="easyui-datagrid"></div>
	<script>
		$(function() {
			var urlJson = 'user/findUserWithPage';
			$('#dg')
					.datagrid(
							{
								nowrap : false,
								fit : true,
								border : false,
								autoRowHeight : true,
								rownumbers : true,
								fitColumns : true,
								striped : true,
								singleSelect : true,
								method : 'get',
								url : urlJson,
								idField : 'bt',
								toolbar : [
										{
											text : '刷新',
											iconCls : 'icon-reload',
											handler : function() {
												$('#dg').datagrid('reload');
											}
										},
										{
											text : '删除',
											iconCls : 'icon-remove',
											handler : function() {
												var selected = $("#dg")
														.datagrid('getSelected');
												if (selected) {
													deleterow(selected);
												} else {
													showMessageBox('删除记录',
															'当前没有选中行！');
												}
											}
										},
										{
											text : '编辑',
											iconCls : 'icon-edit',
											handler : function() {
												var selected = $("#dg")
														.datagrid('getSelected');
												var index = $("#dg")
														.datagrid(
																'getRowIndex',
																selected);
												if (selected) {
													cancelAllEdit();
													$("#dg").datagrid(
															"beginEdit", index);
												} else {
													showMessageBox('编辑记录',
															'当前没有选中行！');
												}
											}
										},
										{
											text : '取消',
											iconCls : 'icon-cancel',
											handler : function() {
												cancelAllEdit();
											}
										},
										{
											text : '添加',
											iconCls : 'icon-add',
											handler : function() {
												$("#addUser")
														.dialog(
																{
																	modal : true,
																	closed : false,
																	buttons : [
																			{
																				text : '提交',
																				handler : function() {
																					if ($(
																							"#addUserForm")
																							.form(
																									'validate')) {
																						$
																								.ajax({
																									url : 'user/update',
																									data : $(
																											"#addUserForm")
																											.serialize(),
																									type : 'post',
																									success : function(
																											e) { //成功后回调
																										showMessageBox(
																												'保存记录',
																												'成功保存记录！');
																										$(
																												"#addUserForm")
																												.form(
																														'clear');
																										$(
																												"#addUser")
																												.dialog(
																														'close');
																										$(
																												'#dg')
																												.datagrid(
																														'reload');
																									},
																									error : function(
																											e) { //失败后回调
																										showMessageBox(
																												'保存记录',
																												'保存记录失败！');
																										$(
																												"#addUserForm")
																												.form(
																														'clear');
																										$(
																												"#addUser")
																												.dialog(
																														'close');
																									}
																								})
																					}
																				}
																			},
																			{
																				text : '重置',
																				handler : function() {
																					$(
																							"#addUserForm")
																							.form(
																									'clear');
																				}
																			},
																			{
																				text : '关闭',
																				handler : function() {
																					$(
																							"#addUser")
																							.dialog(
																									'close');
																				}
																			} ]
																});
											}
										},
										{
											text : "保存",
											iconCls : "icon-save",
											handler : function() {
												var selected = $("#dg")
														.datagrid('getSelected');
												if (selected) {
													saverow();
												} else {
													showMessageBox("保存记录",
															"当前没有选中行!");
												}
											}
										} ],
								columns : [ [ {
									field : 'userId',
									title : '用户ID',
									width : 25,
									editor : 'text',
									align : 'center',
									sortable : true,
								}, {
									field : 'userName',
									title : '用户名',
									width : 30,
									editor : 'text',
									align : 'center'
								}, {
									field : 'password',
									title : '密码',
									width : 20,
									editor : 'text',
									align : 'center'
								}, {
									field : 'nikeName',
									title : '用户昵称',
									width : 20,
									editor : 'text',
									align : 'center'
								}, {
									field : 'dept',
									title : '所属部门',
									width : 20,
									editor : {
										type : 'combobox',
										options : {
											valueField : 'value',
											textField : 'lable',
											url : 'dept/findDeptsName',
											panelHeight : 'auto'
										}
									},
									formatter : function(value, row, index) {
										return formatterDeptId(row);
									},
									align : 'center'
								}, {
									field : 'joinTime',
									title : '加入时间',
									width : 25,
									editor : 'datebox',
									align : 'center'
								}, {
									field : 'description',
									title : '描述',
									width : 30,
									editor : 'textarea',
									align : 'center'
								} ] ],
								pagination : true,
								onBeforeEdit : function(index, row) {
									row.editing = true;
									$('#dg').datagrid('refreshRow', index);
								},

								onAfterEdit : function(index, row) {
									row.editing = false;
									$('#dg').datagrid('refreshRow', index);
								},

								onCancelEdit : function(index, row) {
									row.editing = false;
									$('#dg').datagrid('refreshRow', index);
								}
							});
		});

		function deleterow(selected) {
			$.messager.confirm('确认', '你确定删除该条记录吗？', function(r) {
				if (r) {
					$.ajax({
						url : 'user/delete',
						data : {
							id : selected.id
						},
						type : 'post',
						success : function(e) { //成功后回调
							showMessageBox('删除', '成功删除记录！');
							$('#dg').datagrid('reload');
						},
						error : function(e) { //失败后回调
							showMessageBox('删除', '删除记录失败！');
						}
					})
				}
			});
		}

		function saverow() {
			var selected = $("#dg").datagrid('getSelected');
			var index = $('#dg').datagrid('getRowIndex', selected);
			$('#dg').datagrid('endEdit', index);
			$.ajax({
				url : 'user/update',
				data : {
					id : selected.id,
					userId : selected.userId,
					userName : selected.userName,
					password : selected.password,
					nikeName : selected.nikeName,
					dept : selected.dept,
					joinTime : selected.joinTime,
					description : selected.description
				},
				type : 'post',
				success : function(e) { //成功后回调
					showMessageBox('保存记录', '成功保存记录！');
					$('#dg').datagrid('reload');
				},
				error : function(e) { //失败后回调
					showMessageBox('保存记录', '保存记录失败！');
				}
			})
		}

		function cancelAllEdit() {
			var rows = $("#dg").datagrid('getRows');
			for (var i = 0; i < rows.length; i++) {
				$("#dg").datagrid('cancelEdit', i);
			}
		}

		function endAllEdit() {
			var rows = $("#dg").datagrid('getRows');
			for (var i = 0; i < rows.length; i++) {
				$("#dg").datagrid('endEdit', i);
			}
		}

		function showMessageBox(title, msg) {
			$.messager.show({
				title : title,
				msg : msg,
				timeout : 2000,
			});
		}

		function saveAll() {
			if ($("#dg").datagrid('getChanges').length) {
				var inserted = $("#dg").datagrid('getChanges', "inserted");
				var deleted = $("#dg").datagrid('getChanges', "deleted");
				var updated = $("#dg").datagrid('getChanges', "updated");

				var effectRow = new Object();
				if (inserted.length) {
					effectRow["inserted"] = JSON.stringify(inserted);
				}
				if (deleted.length) {
					effectRow["deleted"] = JSON.stringify(deleted);
				}
				if (updated.length) {
					effectRow["updated"] = JSON.stringify(updated);
				}

				$.post("user/update", effectRow, function(rsp) {
					if (rsp.status) {
						showMessageBox("保存", "保存成功！");
						$("#dg").datagrid('acceptChanges');
					}
				}, "JSON").error(function() {
					showMessageBox("保存", "保存失败！");
				});
			}
		}
	</script>
</body>
</html>