<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style=" margin-top: 2%;">
<br/><br />
<form action="viewOrderPdf.htm" method="get">
<span style="color: teal; font-size: 20pt;position: relative; left:30pt; ">My Orders
<input type="submit" name="" value="View PDF" class="button1" style="width: 100pt;float: right; position: relative; right: 40pt;"><br /><br /></span></form>
<hr style="color: #dddddd" />
<c:forEach var="order" items="${orderDetails}">
<c:forEach var="orderItem" items="${order.value}">
<div style="clear: both; height:160pt;">
<table style="margin-left: 15%; margin-right: 15%;margin-top: 25pt;">
<tr>
<td width="400pt;"><span style="font-size: 23pt;"><b>${orderItem.product.productName}</b></span><br /></td>
<td rowspan ="5"><span style="position: relative; left: 100pt;top:-20pt; font-size: 15pt;">Quantity: ${orderItem.quantity}</span></td>
</tr>
<tr height="15pt;">
<td><span style="font-size: 15pt;">
Category: ${orderItem.product.productType}<br /></span></td></tr>
<tr height="15pt;">
<td><span style="font-size: 15pt;">
By: ${orderItem.product.manufacturer.companyName}<br /></span></td></tr>
<tr height="15pt;">
<td><span style="font-size: 15pt;">Price: ${orderItem.product.price}<br /></span></td>
<td colspan="2"><span style="font-size: 15pt;position: relative;left: 30pt;">Status : ${orderItem.itemStatus}</span></td></tr>
<tr height="15pt;">
<td><span style="font-size: 15pt;">Description: ${orderItem.product.description}<br /></span></td></tr>
</table>
</div>
<hr style="color: #dddddd" />
</c:forEach>
</c:forEach>
</div>

<br /><br /><br /><br />