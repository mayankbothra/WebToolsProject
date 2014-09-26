<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/style/menu.css"/>

<script>
var xmlHttp

function showHint(str)
{
if (str.length==0)
  { 
  document.getElementById("searchBox").innerHTML="";
  return;
  }
xmlHttp=GetXmlHttpObject();
if (xmlHttp==null)
  {
  alert ("Your browser does not support AJAX!");
  return;
  } 
var url="search.htm";
url=url+"?q="+str;
url=url+"&sid="+Math.random();
xmlHttp.onreadystatechange=stateChanged;
xmlHttp.open("GET",url,true);
xmlHttp.send(null);
} 

function stateChanged() 
{ 
if (xmlHttp.readyState==4)
{ 
document.getElementById("searchBox").innerHTML=xmlHttp.responseText;
}
}

function GetXmlHttpObject()
{
var xmlHttp=null;
try
  {
  // Firefox, Opera 8.0+, Safari
  xmlHttp=new XMLHttpRequest();
  }
catch (e)
  {
  // Internet Explorer
  try
    {
    xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
    }
  catch (e)
    {
    xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
  }
return xmlHttp;
}
</script>


<div class="menu">
<ul>
<li><a href="home.htm" >Home</a></li>
<li><a href="#" id="current">Shop By Product Type</a>
<ul>
<li><a href="shopByProductType.htm?type=TV">TV & Video</a></li>
<li><a href="shopByProductType.htm?type=AUDIO">Home Audio & Theatre</a></li>
<li><a href="shopByProductType.htm?type=CAMERA">Camera, Photo & Video</a></li>
<li><a href="shopByProductType.htm?type=CELLPHONES">Cell Phones & Accessories</a></li>
<li><a href="shopByProductType.htm?type=LAPTOP">Laptops, Tablets & Netbooks</a></li>
</ul>
</li>
<li><a href="#">Shop By Manufacturer</a>
<ul>
<c:forEach var="manufacturer" items="${manufacturerList}">
<li><a href="shopByManufacturer.htm?mid=${manufacturer.id}">${manufacturer.companyName}</a></li>
</c:forEach>
</ul>
</li>
<li><a href="loginPageLink.htm">Login</a></li>
<li><a href="#">Register</a>
<ul>
<li><a href="customerReg.htm">Customer Registration</a></li>
<li><a href="manuReg1.htm">Manufacturer Registration</a></li>
</ul>
</li>
<li style="float: right; position: relative;left: -25pt;">
<div id="searchBox" style="height: 60px; width: 160pt">
<input type="text" name="search" placeholder="Enter type or manufacturer" style="width: 160pt; height: 20pt" onkeyup="showHint(this.value)" id="search"></div>
&nbsp;&nbsp;&nbsp;<input type="submit" value="Search" class="button10"></li>
</ul>
</div>