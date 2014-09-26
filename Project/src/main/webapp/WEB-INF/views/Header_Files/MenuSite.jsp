<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/style/menu.css"/>
<div class="menu">
<ul>
<li><a href="myHome.htm" >Home</a></li>
<li><a href="myAccount.htm?pid=${sessionScope.person.id}" id="current">My Account</a></li>
<li><a href="logout.htm">Logout</a></li>
<li style="float: right;position: relative;right: 25pt;top:2pt;color: white;">${sessionScope.person.fName} ${sessionScope.person.lName} &#40;${sessionScope.person.userAccount.roleString}&#41;</li>
</ul>
</div>