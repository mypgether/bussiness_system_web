/** * 更换主题 */
changeTheme = function(type) {
	var $easyuiTheme = $('#easyuiTheme');
	var url = $easyuiTheme.attr('href');
	console.info(url);
	var href = url.substring(0, url.indexOf('themes') + 7) + type
			+ '/easyui.css';
	// console.info(href);
	$easyuiTheme.attr('href', href);
	/*
	 * 如果使用了iframe. 则要添加下面这段代码、否则的话iframe中的内容不会出现样式的改变
	 */
	var $iframe = $('iframe');
	if ($iframe.length > 0) {
		for (var i = 0; i < $iframe.length; i++) {
			var ifr = $iframe[i];
			$(ifr).contents.find('#easyuiTheme').attr('href', href);
		}
	}
	// $.cookie('easyuiThemeName', type, {
	// expires : 7
	// });
};