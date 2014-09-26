<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="background-color: #eeeeee; height: 350pt;">
<br /><br />
<div style="font-size: 21pt; color: maroon; position: relative; left: 30pt; font-family: fantasy;"  class="bodyMargin">Order Details
<span style="position: relative;float: right;right: 30pt;">Order ID: ${oId}</span></div><br /><br /><br />
<div style="position: relative; left: 130pt;width: 70%">
<div id="validateMessage" style="color: red"></div>
<form method="post" id="form1" onsubmit="return validate()" action="processOrder.htm">	
<table style="width: 100%; background-color: #bbbbbb; color: black; border: 1px solid white;padding: 3px" id="table1">
<tr style="color: white">
<th style="background-color: #333333;" class="form1">Product Name</th>
<th style="background-color: #333333;" class="form1">Product Manufacturer</th>
<th style="background-color: #333333;" class="form1">Product Price</th>
<th style="background-color: #333333;" class="form1">Quantity Ordered</th>
</tr>
<c:forEach var="order" items="${orderItemList}">
<tr rowspan="2"  class="form" style="border: 1px solid white;">
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;color:black">${order.product.productName}</span></td>
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;color:black">${order.product.manufacturer.companyName}</span></td>
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;color:black">${order.product.price}</span></td>
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;color:black">${order.quantity}</span></td>
</tr>
</c:forEach>
</table>
<input type="hidden" name="oId" value="${oId}">
<table style="width: 100%; background-color: #eeeeee; color: black; padding: 3px">
<tr align="center" class="form" style="height: 70pt;">
<td colspan="4"><input type="submit" value="Process Order" id="process" class="button2" style="width: 150pt;"></td>
</tr>
</table>
</form>
</div>
</div>
<br /><br />
