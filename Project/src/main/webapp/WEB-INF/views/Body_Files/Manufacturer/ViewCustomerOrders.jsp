<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- <script>
function checkAvailability(str)
{
if (str.length==0)
  { 
  document.getElementById("availability").innerHTML="";
  return;
  }
xmlHttp=GetXmlHttpObject();
if (xmlHttp==null)
  {
  alert ("Your browser does not support AJAX!");
  return;
  } 
var url="checkAvail.htm";
url=url+"?id="+str;
xmlHttp.onreadystatechange=stateChanged;
xmlHttp.open("GET",url,true);
xmlHttp.send(null);
} 

function stateChanged() 
{ 
if (xmlHttp.readyState==4)
{ 
document.getElementById("availability").innerHTML=xmlHttp.responseText;
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
</script> -->



<div style="background-color: #eeeeee; height: 350pt;">
<br /><br />
<div style="font-size: 21pt; color: maroon; position: relative; left: 30pt; font-family: fantasy;"  class="bodyMargin">Order Details</div><br /><br /><br />
<div style="position: relative; left: 130pt;width: 70%">
<form method="post" id="form1" action="shipOrder.htm">
<div id="availability" style="color:red"></div>	
<table style="width: 100%; background-color: #bbbbbb; color: black; border: 1px solid white;padding: 3px" id="table1">
<tr style="color: white">
<th style="background-color: #333333;" class="form1">Select</th>
<th style="background-color: #333333;" class="form1">Order ID</th>
<th style="background-color: #333333;" class="form1">Product Name</th>
<th style="background-color: #333333;" class="form1">Quantity Ordered</th>
<th style="background-color: #333333;" class="form1">Customer Name</th>
</tr>
<c:forEach var="order" items="${orderList}">
<c:forEach var="orderItem" items="${order.value}">
<tr rowspan="2"  class="form" style="border: 1px solid white;">
<td style="border: 1px solid white;" align="center"><input type="radio" name="select" value="${order.key.id}" id="select" style="width: 2em; height: 2em;"></td>
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;color:black">${order.key.id}</span></td>
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;color:black">${orderItem.product.productName}</span></td>
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;color:black">${orderItem.quantity}</span></td>
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;color:black">${orderItem.person.fName}</span></td>
</tr>
</c:forEach>
</c:forEach>
</table>
<table style="width: 100%; background-color: #eeeeee; color: black; padding: 3px">
<tr align="center" class="form" style="height: 70pt;">
<td colspan="5"><input type="submit" value="Process Order" id="process" class="button2" style="width: 150pt;">(Check Stock and Ship Order)</td>
</tr>
</table>
</form>
</div>
</div>
<br /><br />
