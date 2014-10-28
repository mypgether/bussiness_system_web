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
<script type="text/javascript" src="js/navigation.js"></script>
<script type="text/javascript" src="js/changeTheme.js"></script>
<script type="text/javascript">
	//滚动信息
	window.onload = function() {
		marquee('roll_box', 'roll_content', 'roll_temp', '40');
		showIndex();
	};

	function marquee(id, id1, id2, speed) {
		var obj = document.getElementById(id);
		var obj1 = document.getElementById(id1);
		var obj2 = document.getElementById(id2);
		obj2.innerHTML = obj1.innerHTML;
		function scrolly() {
			if (obj.scrollTop >= obj1.offsetHeight) {
				obj.scrollTop = 0;
			} else {
				obj.scrollTop++;
			}
			//console.info(obj.scrollTop);
		}
		//function scrolly(){if(obj.scrollTop>=obj1.offsetHeight){obj1.offsetHeight-=obj.scrollTop;}else{obj.scrollTop++;}}

		var rollTime = setInterval(scrolly, speed);
		obj.onmouseover = function() {
			clearInterval(rollTime);
		}
		obj.onmouseout = function() {
			rollTime = setInterval(scrolly, speed);
		}
	};

	function showIndex() {
		$("#center-tabs").tabs('add', {
			title : "首页",
			content : "<h1 align='center'>欢迎进入上海市国土资源局管理系统</h1>",
		});
	};
</script>
</head>
<body class="easyui-layout">
	<!-- north begin -->
	<div data-options="region:'north'"
		style="height:130px;background-image:url(bg2.jpg);background-size:cover">
		<div class="site_title">
			<font size=6></font>
		</div>
		<div id="sessionInfoDiv"
			style="position: absolute;right: 5px;top:10px;"></div>
		<div style="position: absolute; right: 0px; bottom: 0px; ">
			<a href="javascript:void(0);" class="easyui-menubutton"
				data-options="menu:'#layout_north_pfMenu',iconCls:'icon-ok'">更换皮肤</a>
			<a href="javascript:void(0);" class="easyui-menubutton"
				data-options="menu:'#layout_north_sysInfoMenu',iconCls:'icon-tip'">系统信息</a>
		</div>
		<div id="layout_north_pfMenu" style="width: 120px; display: none;">
			<div onclick="changeTheme('default');">默认风格</div>
			<div onclick="changeTheme('gray');">灰色风格</div>
			<div onclick="changeTheme('black');">黑色风格</div>
			<div onclick="changeTheme('metro');">metro风格</div>
			<div onclick="changeTheme('bootstrap');">bootstrap风格</div>
		</div>
		<div id="layout_north_sysInfoMenu"
			style="width: 100px; display: none;">
			<div onclick="">版本信息</div>
			<div onclick="">使用说明</div>
		</div>
	</div>
	<!-- north end -->
	<!-- west begin -->
	<div data-options="region:'west',border:false,split:false,title:'导航菜单'"
		style="width:230px;">
		<div class="easyui-tabs" fit="true" border="false" target="west">
			<div title="管理中心" style="padding:10px">
				<ul id="tree"></ul>
			</div>
			<div title="最新新闻" style="padding:10px">
				<li>&nbsp&nbsp<font size=2>
						轨道交通15号线是上海市轨道交通网络中一条南北向线路。线路起自城市西北部的蕰藻浜，途经宝山、普陀、长宁、徐汇、闵行等5个行政区，止于城市西南部的紫竹高新技术产业开发区，对加强城市西部内外环线之间的轨道服务、带动沿线区域发展、汇集并转换沿线相交轨道线路客流、均衡网络客流有着较强的作用。</font>
				</li> <br> <br>
				<li>&nbsp&nbsp <font size=2>根据《中华人民共和国土地管理法》、《中华人民共和国土地管理法实施条例》、《征用土地公告办法》和《上海市实施〈中华人民共和国土地管理法〉办法》等有关规定，依据上海市人民政府
						沪府土[2014]68号批文批准的征地方案，现将拟定的《征地补偿安置方案》公告如下。</font></li>

			</div>
			<div title="网上中心" style="padding:10px">
				<ul class="easyui-tree"
					data-options="url:'tree_data1.json',method:'get',animate:true">
					<ul class="information_c">
						<font size=3>
							<li><a href="javascript:goXxgkml('xxgkgd')">信息公开</a></li>
							<li><a href="javascript:goXxgkml('xxgkzn')">新闻发布</a></li>
							<li><a href="javascript:goXxgkml('xxgkml')">网上办事</a></li>
							<li><a href="javascript:goXxgkml('ysqgk')">互动平台</a></li>
							<li><a href="javascript:goXxgkml('xxgkyjx')">便民服务</a></li>
							<li><a href="javascript:goXxgkml('xxgknb')">政策法规</a></li>
							<li><a href="javascript:goDownload()">网上公告</a></li>
							<li><a href="javascript:ysqgkjgcx()">规划管理</a></li>
						</font>
						<li><a href="javascript:ysqgkjgcx()">土地管理</a></li>
						</font>
						<li><a href="javascript:ysqgkjgcx()">地矿管理</a></li>
						</font>
						<li><a href="javascript:ysqgkjgcx()">科技教育</a></li>
						</font>
					</ul>
				</ul>
			</div>
		</div>
	</div>
	<!-- west end -->

	<!-- center begin -->
	<div data-options="region:'center',title:'首页'">
		<div id="center-tabs" class="easyui-tabs" border="false" fit="true"></div>
	</div>
	<!-- center end -->

	<!-- east begin -->
	<div data-options="region:'east'" style="width:180px;"
		class="indexcenter" title="公告栏">
		<div id="roll_box" style="height:250px;overflow:hidden;margin:10px;">
			<div id="roll_content">
				<!--滚动内容start-->
				<li><a href="" target="_blank">关于《江北区中心城区土地利用总体规划（2006-2020年）》2014年第1次规划落实方案的批复</a><span></span>
				</li>
				<li><a href="" target="_blank">宁波市国土资源局江北分局行政权力清单征求意见公告</a><span></span>
				</li>
				<li><a href="" target="_blank">宁波市国土资源局江北分局房屋拆迁公开选取评估单位公告（甬北征拆评〔2014〕05号）</a><span></span>
				</li>
				<li><a href="" target="_blank">宁波市江北区征地区片综合价调整听证会公告（宁北国土资听公字〔2014〕第1号）</a>
				</li>
				<li><a href="" target="_blank">《江北区中心城区土地利用总体规划（2006-2020年）》2014年第1次规划修改方案听证会公告(201407)</a>
				</li>
				<li><a href="" target="_blank">关于《江北区中心城区土地利用总体规划（2006-2020年）》2014年第1次规划落实方案的批复</a>
				</li>
				<li><a href="" target="_blank">土地注销登记公告</a></li>
				<li><a href="" target="_blank">宁波市国土资源局江北分局行政权力清单征求意见公告</a></li>
				<li><a href="" target="_blank">《江北区中心城区土地利用总体规划（2006-2020年）》2014年第1次规划修改方案听证会公告(201407)</a>
				</li>
				<li><a href="" target="_blank">济宁波市国土资源局江北分局行政权力清单征求意见公告</a></li>
				<!--滚动内容end-->
			</div>
			<div id="roll_temp"></div>
		</div>
		<br /> <br />
		<div class="easyui-panel" border="false">
			<ul>
				<font size=3>推&nbsp广</font>
				<li class="list">
					<div class="tdjy_title_2_td1_2 line_right">
						<a href="www.baidu.com">松江区中山街道SJC...</a>
					</div>
				</li>
				<br />
				<li class="list">
					<div class="tdjy_title_2_td1_2 line_right">
						<a href="www.baidu.com">闵行区颛桥镇闵行...</a>
					</div>
				</li>
				<br />
				<li class="list">
					<div class="tdjy_title_2_td1_2 line_right">
						<a href="www.baidu.com">金山区亭林镇CB_20...</a>
					</div>
				</li>
				<br />
				<li class="list">
					<div class="tdjy_title_2_td1_2 line_right">
						<a href="www.baidu.com">金山工业园区金山...</a>
					</div>
				</li>
			</ul>
		</div>
	</div>
	<!-- east end -->
</body>
</html>
