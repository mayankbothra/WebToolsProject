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

<div style="background-color: #dbe2f3; height: 450pt;">
<br />
<div style="font-size: 21pt; color: maroon; position: relative; left: 30pt; font-family: fantasy;"  class="bodyMargin">Depot wise Stock</div><br /><br /><br />
<div style="position: relative; left: 130pt;width: 70%">
<!-- <div id="validateMessage" style="color: red"></div> -->

<c:if test="${size == 0}">
<br /><br />
<span style="font-size: 20pt;">No Depot</span><br /><br />
<form action="stockManagerHome.htm" method="get">
<input type="submit" value="Back" class="button1" style="font-size: 11pt;" >
</form>
</c:if>

<div id="customAccordion">
<c:forEach var="stock" items="${stockList}">
<h3>Depot ID: ${stock.key.id}<span style="float: right; position: relative;right: 35pt;">Location: ${stock.key.location}</span></h3>
<div>
<table style="width: 100%; background-color: #eeeeee; border: 1px solid white;padding: 3px">
<tr>
<th style="background-color: #eeeeee" class="form1">Product ID</th>
<th style="background-color: #eeeeee" class="form1">Product Name</th>
<th style="background-color: #eeeeee" class="form1">Quantity</th>
</tr>
<c:forEach var="stockItem" items="${stock.value}">
<c:choose>
<c:when test="${stockItem.quantity <= 10}">
<tr rowspan="2"  class="form" style="border: 1px solid white; color: red">
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;">${stockItem.product.id}</span></td>
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;">${stockItem.product.productName}</span></td>
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;">${stockItem.quantity}</span></td></tr>
</c:when>
<c:otherwise>
<tr rowspan="2"  class="form" style="border: 1px solid white;">
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;">${stockItem.product.id}</span></td>
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;">${stockItem.product.productName}</span></td>
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;">${stockItem.quantity}</span></td></tr>
</c:otherwise>
</c:choose>
</c:forEach>
</table>
<%-- <input type="hidden" name="orderId" value="${order.key.id}" />
<input type="submit" value="Process Order" class="button1" style="font-size: 11pt;width: 100pt;height:30pt; float: right;position: relative;top:10pt;" >
 --%>
</div>
</c:forEach>
</div>
<form method="get" action="stockManagerHome.htm" id="form1">
<input type="submit" value="Back" class="button1" style="font-size: 11pt; position: relative; top:25pt;float: right;" >
 </form>
</div>
</div>
<br /><br />





 
       
