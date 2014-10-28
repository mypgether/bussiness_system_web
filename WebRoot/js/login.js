$(function() {
	// 检查密码长度
	$.extend($.fn.validatebox.defaults.rules, {
		minLength : {
			validator : function(value, param) {
				return value.length >= param[0];
			},
			message : '密码长度至少为6位！'
		}
	});

	// 检查密码和确认密码是否相同。
	$.extend($.fn.validatebox.defaults.rules, {
		equals : {
			validator : function(value, param) {
				return value == $(param[0]).val();
			},
			message : '两次输入的密码不一致！'
		}
	});

	$("#loginDialog").dialog({
		width : 300,
		height : 200,
		modal : true,
		closable : false,
		buttons : [ {
			text : '登录',
			handler : function() {
				if ($("#loginForm").form('validate')) {
					$.ajax({
						type : "post",
						url : "login",
						data : $("#loginForm").serialize(),
						dataType : "json",
						success : function(data) {
							$("#loginDialog").dialog('close');
							$.messager.alert("登录", "登陆成功！", "info", function() {
								window.location = 'index';
							});
						},
						error : function(e) {
							$.messager.alert("登录", "用户名和密码不匹配！", "info");
						}
					});
				}
			}
		}, {
			text : '取消',
			handler : function() {
				$("#loginDialog").dialog('close');
			}
		} ]
	});
});