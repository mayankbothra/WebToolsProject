<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="background-color: #dbe2f3; height: 350pt;">
<br />
<div style="font-size: 21pt; color: maroon; position: relative; left: 30pt; font-family: fantasy;"  class="bodyMargin">List of All Products</div><br /><br /><br />
<div style="position: relative; left: 130pt;width: 70%">
<div id="validateMessage" style="color: red"></div>
<form method="post" action="mAdminHome.htm" id="form1">	
<table style="width: 100%; background-color: #eeeeee; border: 1px solid white;padding: 3px">
<tr>
<th style="background-color: #aaaaaa" class="form1">Product ID</th>
<th style="background-color: #aaaaaa;" class="form1">Product Name</th>
<th style="background-color: #aaaaaa;" class="form1">Product Type</th>
<th style="background-color: #aaaaaa;" class="form1">Product Price</th>
<th style="background-color: #aaaaaa;" class="form1">Product Description</th>
</tr>
<c:forEach var="product" items="${productList}">
<tr rowspan="2"  class="form" style="border: 1px solid white;">
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;">${product.id}</span></td>
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;">${product.productName}</span></td>
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;">${product.productType}</span></td>
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;">${product.price}</span></td>
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;">${product.description}</span></td>
</tr>
</c:forEach>
</table>
<table style="width: 100%; background-color: #dbe2f3; padding: 3px">
<tr align="center" class="form" style="height: 70pt;">
<td colspan="7"><input type="submit" value="Back" id="delete" class="button1" style="width: 100pt;"></td>
</tr>
</table>
</form>
</div>
</div>
<br /><br />
