<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/style/menu.css"/>

<div class="menu">
<ul>
<li><a href="myHome.htm" >Home</a></li>
<li><a href="#" id="current">Shop By Product Type</a>
<ul>
<li><a href="shopByProductType.htm?type=TV">TV & Video</a></li>
<li><a href="shopByProductType.htm?type=AUDIO">Home Audio & Theatre</a></li>
<li><a href="shopByProductType.htm?type=CAMERA">Camera, Photo & Video</a></li>
<li><a href="shopByProductType.htm?type=CELLPHONES">Cell Phones & Accessories</a></li>
<li><a href="shopByProductType.htm?type=LAPTOP">Laptops, Tablets & Netbooks</a></li>
</ul>
</li>
<li><a href="/#">Shop By Manufacturer</a>
<ul>
<c:forEach var="manufacturer" items="${sessionScope.manufacturerList}">
<li><a href="shopByManufacturer.htm?mid=${manufacturer.id}">${manufacturer.companyName}</a></li>
</c:forEach>
</ul>
</li>
<li><a href="logout.htm">Logout</a></li>
<li><a href="#">Hello, &nbsp;<span style="color: #FFED53">${sessionScope.person.fName}</span></a>
<ul>
<li><a href="myAccount.htm">Your Account</a></li>
<li><a href="myOrders.htm">Your Orders</a></li>
<li><a href="myCart.htm">Your Cart</a></li>
</ul>
</li>
<!-- <li><a href="loginPageLink.htm" style="color: maroon; text-decoration: none; font-size: 14pt;position: relative;float: right;right: 20pt;top:2pt;">Login</a></li> -->
<li style="float: right; position: relative;left: -25pt;"><input type="text" name="search" placeholder="Enter type or manufacturer" style="width: 160pt; height: 20pt">
&nbsp;&nbsp;&nbsp;<input type="submit" value="Search" class="button10"></li>
</ul>
</div>