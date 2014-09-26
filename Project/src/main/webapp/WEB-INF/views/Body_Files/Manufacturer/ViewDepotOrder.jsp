<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css" />
  <script>
  $(function() {
    $( "#customAccordion" ).accordion();
  });  
  </script>

<div style="background-color: #dbe2f3; height: 400pt;">
<br />
<div style="font-size: 21pt; color: maroon; position: relative; left: 30pt; font-family: fantasy;"  class="bodyMargin">View Orders from Depots</span></div><br /><br /><br />
<div style="position: relative; left: 130pt;width: 70%">
<!-- <div id="validateMessage" style="color: red"></div> -->

<c:if test="${size == 0}">
<br /><br />
<span style="font-size: 20pt;">No new orders</span><br /><br />
<form action="stockManagerHome.htm" method="get">
<input type="submit" value="Back" class="button1" style="font-size: 11pt;" >
</form>
</c:if>

<div id="customAccordion">
<c:forEach var="order" items="${orderList}">
<h3>Depot ID: ${order.key.depot.id}<span style="float: right; position: relative;right: 35pt;">Location: ${order.key.depot.location}</span></h3>
<div>
<form method="post" action="processDepotOrder.htm" id="form1">
<table style="width: 100%; background-color: #eeeeee; border: 1px solid white;padding: 3px">
<tr>
<th style="background-color: #eeeeee" class="form1">Product ID</th>
<th style="background-color: #eeeeee" class="form1">Product Name</th>
<th style="background-color: #eeeeee" class="form1">Quantity Ordered</th>
</tr>
<c:forEach var="orderItem" items="${order.value}">
<tr rowspan="2"  class="form" style="border: 1px solid white;">
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;">${orderItem.product.id}</span></td>
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;">${orderItem.product.productName}</span></td>
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;">${orderItem.quantity}</span></td></tr>
</c:forEach>
</table>
<input type="hidden" name="orderId" value="${order.key.id}" />
<input type="submit" value="Process Order" class="button1" style="font-size: 11pt;width: 100pt;height:30pt; float: right;position: relative;top:10pt;" >
</form>
</div>
</c:forEach>
</div>
</div>
</div>
<br /><br />





 
       
