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

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="js/getCurrentTime.js"></script>
</head>

<body>
	<div id="addDept" class="easyui-dialog" title="添加部门" closed="true">
		<form id="addDeptForm">
			<table>
				<tr>
					<td>部门名称:</td>
					<td><input class="easyui-validatebox" name="deptName"
						required=true style="width:150px"></input></td>
					<td>创建者:</td>
					<td><input class="easyui-combobox" name="createrId"
						required=true style="width:150px" valueField="value"
						textField="lable" url="user/findUsersName" panelHeight="auto"></input></td>
				</tr>
				<tr>
					<td>创建时间:</td>
					<td><input class="easyui-datebox" name="createTime"
						required=true style="width:150px"></input></td>
					<td>部门描述:</td>
					<td><textarea rows="3" cols="15" name="description"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dg" class="easyui-datagrid"></div>
	<script>
		$(function() {
			var urlJson = 'dept/findDeptWithPage';
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
												$("#addDept")
														.dialog(
																{
																	modal : true,
																	closed : false,
																	buttons : [
																			{
																				text : '提交',
																				handler : function() {
																					if ($(
																							"#addDeptForm")
																							.form(
																									'validate')) {
																						$
																								.ajax({
																									url : 'dept/update',
																									data : $(
																											"#addDeptForm")
																											.serialize(),
																									type : 'post',
																									success : function(
																											e) { //成功后回调
																										showMessageBox(
																												'保存记录',
																												'成功保存记录！');
																										$(
																												"#addDeptForm")
																												.form(
																														'clear');
																										$(
																												"#addDept")
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
																												"#addDeptForm")
																												.form(
																														'clear');
																										$(
																												"#addDept")
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
																							"#addDeptForm")
																							.form(
																									'clear');
																				}
																			},
																			{
																				text : '关闭',
																				handler : function() {
																					$(
																							"#addDept")
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
									field : 'deptName',
									title : '部门名称',
									width : 20,
									editor : 'text',
									align : 'center'
								}, {
									field : 'createrId',
									title : '创建者',
									width : 20,
									editor : {
										type : 'combobox',
										options : {
											valueField : 'value',
											textField : 'lable',
											url : 'user/findUsersName',
											panelHeight : 'auto'
										}
									},
									align : 'center',
								}, {
									field : 'createTime',
									title : '创建时间',
									width : 25,
									editor : 'datebox',
									align : 'center'
								}, {
									field : 'description',
									title : '部门描述',
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
						url : 'dept/delete',
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
				url : 'dept/update',
				data : {
					id : selected.id,
					deptName : selected.deptName,
					createrId : selected.createrId,
					createTime : selected.createTime,
					description : selected.description,
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

				$.post("dept/update", effectRow, function(rsp) {
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