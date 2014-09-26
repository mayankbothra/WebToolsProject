<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style=" margin-top: 2%;">
<br/><br />
<span style="color: teal; font-size: 20pt;">Product List By Manufacturer: ${manufacturer.companyName}<br /><br /></span>
<hr style="color: #dddddd" />
<c:forEach var="product" items="${pList}">
<form action="addToCart.htm" method="post">
<div style="clear: both; height:160pt;">
<table style="margin-left: 15%; margin-right: 15%;margin-top: 25pt;">
<tr height="20pt;"><td rowspan ="5" width="200pt;">
<img src="${ordeItem.product.fileName}" height= "170pt" width="140pt" style="border: 1px solid black;"/></td>
<td width="400pt;"><span style="font-size: 23pt;"><b>${product.productName}</b></span><br /></td>
<td rowspan ="3"><select name="quantity" style="position: relative;left: 15pt;">
<option value="default">--Select Quantity--</option>
<option value="1">1</option><option value="2">2</option><option value="3">3</option>
<option value="4">4</option><option value="5">5</option><option value="6">6</option>
<option value="7">7</option><option value="8">8</option><option value="9">9</option>
</select></td>
</tr>
<tr height="15pt;">
<td><span style="font-size: 15pt;">
Category: ${product.productType}<br /></span></td></tr>
<tr height="15pt;">
<td><span style="font-size: 15pt;">
By: ${product.manufacturer.companyName}<br /></span></td></tr>
<tr height="15pt;">
<td><span style="font-size: 15pt;">Price: ${product.price}<br /></span></td>
<td colspan="2"><input type="submit" name="" value="Add to Cart" class="button1" style="width: 150pt;"></td></tr>
<tr height="15pt;">
<td><span style="font-size: 15pt;">Description: ${product.description}<br /></span></td></tr>
</table>
</div>
<hr style="color: #dddddd" />
<input type="hidden" name="productId" value="${product.id}">
</form>
</c:forEach>
</div>
<br /><br /><br /><br />