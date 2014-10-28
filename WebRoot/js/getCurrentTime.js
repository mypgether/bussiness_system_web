getCurrentTime = function(type) {
	var currTime = new Date();
	var strDate = currTime.getFullYear() + "-";
	strDate += currTime.getMonth() + 1 + "-";
	strDate += currTime.getDate();
	return strDate;
};