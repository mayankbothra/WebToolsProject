<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="background-color: #eeeeee; height: 350pt;">
<br /><br />
<div style="font-size: 21pt; color: maroon; position: relative; left: 30pt; font-family: fantasy;"  class="bodyMargin">Welcome &nbsp;${sessionScope.person.fName}&nbsp; ${sessionScope.person.lName}</div><br /><br />
<div style="position: relative; left: 160pt;width: 40%">	
<span style="font-size: 20pt; color: olive;">Grow Our Business !!!</span><br /><br />
<table style="font-size: 15pt;position: relative;left: 40pt;">
<tr style="height: 60pt">
<td style="width: 260pt;"><center>New Request<br /><span style="font-size: 11pt;">&#40;View new manufacturer requests and process them&#41;</span></td>
<td><form method="post" action="newManufacturerRequest.htm">
<input type="submit" value="View" class="button2">
</form></td>
</tr>
<tr style="height: 60pt">
<td style="width: 260pt;"><center>View Manufacturers<br /><span style="font-size: 11pt;">&#40;View complete list of manufacturers&#41;</span></td>
<td><form method="post" action="viewAllManufacturers.htm">
<input type="submit" value="View" class="button2">
</form></td>
</tr>
</table>
</div>
</div>
<br /><br />
