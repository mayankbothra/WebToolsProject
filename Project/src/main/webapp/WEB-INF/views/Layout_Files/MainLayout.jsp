<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/style/css1.css"/>
<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>
<div style="height:5%;" class="bodyMargin">
<tiles:insertAttribute name="logo" />
</div>
<div style="background-color: #eeeeee; height: 17pt; border: 1pt solid; border-radius: 10pt;margin-top: 12pt;margin-bottom:12pt; border-color: #eeeeee";>
<a href="loginPageLink.htm" style="color: maroon; text-decoration: none; font-size: 14pt;position: relative;float: right;right: 20pt;top:2pt;">Login</a>
<!-- <hr color="#999999" size="15pt;"/> -->
</div>
<!-- <div>
<hr color="#eeeeee" size="17pt;"/>
</div> -->
<div style="height: 90%;">
<tiles:insertAttribute name="body" />
</div>
<div style="height: 5%;clear: both;" class="bodyMargin">
<tiles:insertAttribute name="footer" />
</div>
</body>
</html>
