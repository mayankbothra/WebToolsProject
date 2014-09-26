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
<div style="font-size: 21pt; color: maroon; position: relative; left: 30pt; font-family: fantasy;"  class="bodyMargin">Stock List<span style="float: right;position: relative;right: 40pt;">Location : ${person.address.city}</span></div><br /><br /><br />
<div style="position: relative; left: 130pt;width: 70%">
<!-- <div id="validateMessage" style="color: red"></div> -->

<div id="customAccordion">
<h3>Stock less than threshold</h3>
<div>
<form method="post" action="quickOrderDepot.htm" id="form1">
<table style="width: 100%; background-color: #eeeeee; border: 1px solid white;padding: 3px">
<tr>
<th style="background-color: #eeeeee" class="form1">Product ID</th>
<th style="background-color: #eeeeee" class="form1">Product Name</th>
<th style="background-color: #eeeeee" class="form1">Product Type</th>
<th style="background-color: #eeeeee;" class="form1">Product Price</th>
<th style="background-color: #eeeeee" class="form1">Quantity in Stock</th>
</tr>
<c:forEach var="shortList" items="${shortList}">
<tr rowspan="2"  class="form" style="border: 1px solid white;">
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;">${shortList.product.id}</span></td>
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;">${shortList.product.productName}</span></td>
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;">${shortList.product.productType}</span></td>
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;">${shortList.product.price}</span></td>
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;">${shortList.quantity}</span></td>
</tr>
</c:forEach>
</table>
<c:if test="${size > 0}">
<input type="number" name="quantity"  min="1" max="20" id="quantity" style="width: 85pt;height:30pt; float: right;position: relative;top:10pt;left: -120pt;"/>
<input type="submit" value="Place Order" class="button1" style="font-size: 11pt;float: right;top:10pt;right: -90pt;position: relative;" >
</c:if>
</form>
</div>


<h3>Total Stock</h3>
<div>

<table style="width: 100%; background-color: #eeeeee; border: 1px solid white;padding: 3px">
<tr>
<th style="background-color: #eeeeee" class="form1">Product ID</th>
<th style="background-color: #eeeeee;" class="form1">Product Name</th>
<th style="background-color: #eeeeee;" class="form1">Product Type</th>
<th style="background-color: #eeeeee;" class="form1">Product Price</th>
<th style="background-color: #eeeeee;" class="form1">Quantity in Stock</th>
</tr>
<c:forEach var="stockItemList" items="${stockItemList}">
<tr rowspan="2"  class="form" style="border: 1px solid white;">
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;">${stockItemList.product.id}</span></td>
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;">${stockItemList.product.productName}</span></td>
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;">${stockItemList.product.productType}</span></td>
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;">${stockItemList.product.price}</span></td>
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;">${stockItemList.quantity}</span></td>
</tr>
</c:forEach>
</table>
</div>
</div>
</div>
</div>
<br /><br />





 
       
