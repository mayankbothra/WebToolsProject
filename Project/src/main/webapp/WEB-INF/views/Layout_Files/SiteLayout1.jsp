<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
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
<div style="height:5%;margin-bottom: 20pt" class="bodyMargin">
<tiles:insertAttribute name="header" />
</div>
<div style="height: 5%;margin-bottom: 15pt;">
<tiles:insertAttribute name="menu" />
</div>

<div>
<div style="height: 90%;">
<tiles:insertAttribute name="body" />
</div>
<div style="height: 5%;clear: both;" class="bodyMargin">
<tiles:insertAttribute name="footer" />
</div>
</div>
</body>
</html>