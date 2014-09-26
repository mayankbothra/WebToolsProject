<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<br /><br />
<div style="background-color: #dbe2f3; height: 350pt;">
<br /><br />
<div style="font-size: 21pt; color: maroon; position: relative; left: 30pt; font-family: fantasy;"  class="bodyMargin">Welcome &nbsp;${sessionScope.person.fName}&nbsp; ${sessionScope.person.lName}<span style="float: right;position: relative;right: 40pt;">Location : ${person.address.city}</span></div><br /><br />
<div style="position: relative; left: 160pt;width: 40%">	
<span style="font-size: 20pt; color: olive;">Product Delivery is the priority !!!</span><br /><br />
<table style="font-size: 15pt;position: relative;left: 40pt;">
<tr style="height: 60pt">
<td style="width: 260pt;"><center>Process Order<br /><span style="font-size: 11pt;">&#40;Check Stock and Approve and Process the customer orders&#41;</span></td>
<td><form method="post" action="processCustomerOrder.htm">
<input type="submit" value="Process" class="button1">
</form></td>
</tr>
<tr style="height: 60pt">
<td style="width: 260pt;"><center>View Stock<br /><span style="font-size: 11pt;">&#40;View a detailed list of stock in the depot&#41;</span></td>
<td><form method="post" action="viewDepotStock.htm">
<input type="submit" value="View" class="button1">
</form></td>
</tr>
</table>
</div>
</div>
<br /><br />
