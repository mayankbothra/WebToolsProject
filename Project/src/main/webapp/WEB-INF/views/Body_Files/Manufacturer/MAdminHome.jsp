<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<br /><br />
<div style="background-color: #dbe2f3; height: 350pt;">
<br /><br />
<div style="font-size: 21pt; color: maroon; position: relative; left: 30pt; font-family: fantasy;"  class="bodyMargin">Welcome &nbsp;${sessionScope.person.fName}&nbsp; ${sessionScope.person.lName}</div><br /><br />
<div style="position: relative; left: 160pt;width: 40%">	
<span style="font-size: 20pt; color: olive;">A Platform to Market your Products !!!</span><br /><br />
<table style="font-size: 15pt;position: relative;left: 40pt;">
<tr style="height: 60pt">
<td style="width: 260pt;"><center>Add Depot<br /><span style="font-size: 11pt;">&#40;Add details of your depots&#41;</span></td>
<td><form method="post" action="addDepot.htm">
<input type="submit" value="Add" class="button1">
</form></td>
</tr>
<tr style="height: 60pt">
<td style="width: 260pt;"><center>Add Employees<br /><span style="font-size: 11pt;">&#40;Add details of your product and stock manager&#41;</span></td>
<td><form method="post" action="addEmployees.htm">
<input type="submit" value="Add" class="button1">
</form></td>
</tr>
<tr style="height: 60pt">
<td style="width: 260pt;"><center>View Products<br /><span style="font-size: 11pt;">&#40;View a complete list of all your products&#41;</span></td>
<td><form method="post" action="viewAllProducts.htm">
<input type="submit" value="View" class="button1">
</form></td>
</tr>
</table>
</div>
</div>
<br /><br />
