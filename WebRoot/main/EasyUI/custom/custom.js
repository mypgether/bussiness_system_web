/****************************************************一、easyUI框架功能begin*************************************************************/
/**
 * 更换EasyUI主题的方法
 * 
 * @param themeName
 *            主题名称
 */
function changeTheme(themeName) {
	// 更换link地址
	var $easyuiTheme = $('#easyuiTheme');
	var url = $easyuiTheme.attr('href');
	var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName
			+ '/easyui.css';
	$easyuiTheme.attr('href', href);
	// 更换框架
	var $iframe = $('iframe');
	if ($iframe.length > 0) {
		for ( var i = 0; i < $iframe.length; i++) {
			var ifr = $iframe[i];
			$(ifr).contents().find('#easyuiTheme').attr('href', href);
		}
	}
}

/**
 * 控制面板更新用户信息 ？
 */
function userInfoFun() {
	$('<div/>').dialog({
		href : '/userController/userInfo.action',
		width : 490,
		height : 285,
		modal : true,
		title : '用户信息',
		buttons : [ {
			text : '修改密码',
			iconCls : 'icon-edit',
			handler : function() {
				var d = $(this).closest('.window-body');
				$('#user_userInfo_form').form('submit', {
					url : '/userController/modifyCurrentUserPwd.action',
					success : function(result) {
						try {
							var r = $.parseJSON(result);
							if (r.success) {
								d.dialog('destroy');
							}
							$.messager.show({
								title : '提示',
								msg : r.msg
							});
						} catch (e) {
							$.messager.alert('提示', result);
						}
					}
				});
			}
		} ],
		onClose : function() {
			$(this).dialog('destroy');
		},
		onLoad : function() {
		}
	});
};

/**
 * 更新选中tab中的List页面
 */
function updateTab(classId, url, subtitle) {
	$('#tabs_' + classId).tabs('select', subtitle);
	var tab = $('#tabs_' + classId).tabs('getSelected');
	tab.panel('refresh', url);
}

/**
 * 动态点击west中的a标签
 * 
 */
function dynamicClick() {
	$('.sider li a').click(function() {
		var classId = 'index';
		var subtitle = $(this).text();
		var url = $(this).attr('href_custom');
		// 更新内容到右侧的tabs内容区
		if (!$('#tabs_' + classId).tabs('exists', subtitle)) {
			$('#tabs_' + classId).tabs('add', {
				title : subtitle,
				closable : true,
				href : url,
				tools : [ {
					iconCls : 'icon-mini-refresh',
					handler : function() {
						updateTab(classId, url, subtitle);
					}
				} ]
			});
			return false;
		} else {
			$('#tabs_' + classId).tabs('select', subtitle);
			return false;
		}
	});
}
/** **************************************************easyUI框架功能end************************************************************ */

/** **************************************************二、center中的具体业务逻辑begin*************************************************** */

/** ********************************************1、用户管理begin*************************************************** */

/** *********************************(1)用户信息begin******************************************** */
/**
 * 弹出添加用户页面
 * 
 */
function user_add() {
	var titleadd = '添加学生';
	var hrefadd = 'page/background/admincenter/userAdd_back.jsp';
	$('<div></div>').dialog({
		title : titleadd,
		href : hrefadd,
		width : 600,
		height : 400,
		modal : true,
		resizable : true,
		collapsible : true,
		maximizable : true,
		onClose : function() {
			$('#adminstuList').datagrid('reload');
			$(this).dialog('destroy');
		}
	});
}
// ///EXCEL导出
function view2() {
	var columns = $('#adminstuList').datagrid('options').columns[0];
	var outstr = "";
	for ( var i = 0; i < columns.length; i++) {// 标题
		outstr = outstr + columns[i].title + "\t";
		// $.messager.alert('提示',columns[i].title , 'info');
	}
	outstr = outstr + "\r\n";
	var row = $('#adminstuList').datagrid('getRows');
	for ( var i = 0; i < row.length; i++) {// 标题
		//$.messager.alert('提示', row[i].stuId + '  ' + row[i].username, 'info');
		outstr = outstr + row[i].stuId + " \t" + row[i].username + " \t"
				+ row[i].college + " \t" + "\r\n";
	}// body
	doFileExport("t.xls", outstr);
}
function doFileExport(inName, inStr) {
	var xlsWin = null;
	if (!!document.all("glbHideFrm")) {
		xlsWin = glbHideFrm;
	} else {
		var width = 6;
		var height = 4;
		var openPara = "left=" + (window.screen.width / 2 - width / 2)
				+ ",top=" + (window.screen.height / 2 - height / 2)
				+ ",scrollbars=no,width=" + width + ",height=" + height;
		xlsWin = window.open("", "_blank", openPara);
	}
	xlsWin.document.write(inStr);
	//xlsWin.document.close();
	xlsWin.document.execCommand('Saveas', true, inName);
	//xlsWin.close();
}

/**
 * 添加用户
 * 
 */
function userAdd() {
	var uname = document.getElementById("uname").value;
	var upassword = document.getElementById("upassword").value;
	$.ajax({
			url : 'savestu.action',
			data : {
				userid : uname,
				password : upassword
			},
			dataType : 'html',
			success : function(request) {
			   //var action = eval('('+request.responseText+')');  
			//window.load(result);
			    //console.info(request);
				alert(request);
				//var murl=result.par
				//href:'result'
			}
	})
	//psuserServiceImpl.addUser(uname, upassword, ubirthday, userAdd_cb);
}
function userAdd_cb(data) {
	if (data == 1) {
		alert("添加成功");
	} else {
		alert("添加失败");
	}
}

/**
 * 删除用户
 * 
 */
function userDelete(userID) {
	if (window.confirm("确定要删除此用户？")) {
		psuserServiceImpl.deleteUser(userID, userDelete_cb);
	}
}
function userDelete_cb(data) {
	if (data == 1) {
		alert("删除成功");

	} else {
		alert("删除失败");
	}
	$('#userList').datagrid('reload');
}

/**
 * 弹出修改用户页面
 * 
 */
function user_edit(index) {

	var titleedit = '编辑用户';
	var hrefedit = 'main/page/background/center/userEdit_back.jsp';

	if (index != undefined) {
		$('#userList').datagrid('selectRow', index);
	}
	var node = $('#userList').datagrid('getSelected');

	$('<div></div>').dialog({
		title : titleedit,
		href : hrefedit,
		width : 600,
		height : 400,
		modal : true,
		resizable : true,
		collapsible : true,
		maximizable : true,
		onClose : function() {
			$('#userList').datagrid('reload');
			$(this).dialog('destroy');
		},
		onLoad : function() {
			$('#userListForm').form('load', node);// 自动载入节点信息
		}
	});
}

/**
 * 修改用户
 * 
 */
function userEdit() {
	var useriseNum = document.getElementById("useriseNum").value;
	var uname = document.getElementById("uname").value;
	var upassword = document.getElementById("upassword").value;
	var ubirthday = document.getElementById("ubirthday").value;

	psuserServiceImpl.updateUser(useriseNum, uname, upassword, ubirthday,
			userEdit_cb);
}
function userEdit_cb(data) {
	if (data == 1) {
		alert("修改成功");
	} else {
		alert("修改失败");
	}
}
/** *********************************用户信息end******************************************** */

/** ********************************************用户管理end*************************************************** */

/** **************************************************center中的具体业务逻辑end*************************************************** */
