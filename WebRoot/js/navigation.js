//导航树的显示
$(function() {
	var treeData = [ {
		"text" : "个人中心",
		"iconCls" : "icon-save",
		"state" : "closed",
		"children" : [ {
			"text" : "个人中心",
			"attributes" : {
				"url" : "user/users"
			}
		} ]
	}, {
		"text" : "系统管理",
		"iconCls" : "icon-save",
		"state" : "closed",
		"children" : [ {
			"text" : "员工管理",
			"attributes" : {
				"url" : "user/users"
			}
		}, {
			"text" : "部门管理",
			"attributes" : {
				"url" : "dept/depts"
			}
		} ]
	} ];
	$("#tree").tree({
		fit : true,
		data : treeData,
		lines : true,
		animate : true,
		onClick : function(node) {
			if (node.attributes) {
				openTab(node.text, node.attributes.url);
			}
		}
	});
});

function openTab(text, url) {
	if ($("#center-tabs").tabs('exists', text)) {
		$("#center-tabs").tabs('select', text);
	} else {
		$("#center-tabs")
				.tabs(
						'add',
						{
							title : text,
							// href:url,
							content : "<iframe id='iframe'scrolling='auto' align='middle' frameborder='0' width='100%' height='100%' src="
									+ url + "></iframe> ",
							closable : true,
						});
	}
}