<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML>
<html>
<head>
<script>
    function confirmSubmit() {
    	  if (confirm("Are you sure you want to logout?")) {
    	    document.getElementById("logout").submit();
    	  }
    	  else{}
    	  return false;
    	}
    </script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/style/css1.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/style/custom.css"/>
<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>
<div style="height:5%;margin-bottom: 20pt;" class="bodyMargin">
<tiles:insertAttribute name="header" />
</div>
<%-- <div style="background-color: #eeeeee; height: 17pt; border: 1pt solid; border-radius: 10pt;margin-top: 12pt;margin-bottom:12pt; border-color: #eeeeee";>
<!-- <hr color="#999999" size="15pt;"/> -->
<span style="float: left;position: relative;left: 25pt;top:3pt;font-size: 13pt;"><b>${sessionScope.manufacturer.companyName}</b></span>
<span style="float: right;position: relative;right: 25pt;top:2pt;">${sessionScope.person.fName} ${sessionScope.person.lName} &#40;${sessionScope.person.userAccount.roleString}&#41;&nbsp;&nbsp;&nbsp;<a href="logout.htm" style="color: maroon; text-decoration: none; font-size: 14pt;" onclick="return confirmSubmit()" id="logout">Logout</a></span>
</div> --%>
<div style="height: 5%;margin-bottom: 5pt;">
<tiles:insertAttribute name="menu" />
</div>
<div >
<div style="height: 95%;">
<tiles:insertAttribute name="body" />
</div>
<div style="height: 5%;clear: both;" class="bodyMargin" >
<tiles:insertAttribute name="footer" />
</div>
</div>
</body>
</html>