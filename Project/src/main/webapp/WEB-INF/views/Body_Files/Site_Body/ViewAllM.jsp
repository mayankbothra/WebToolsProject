<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
function validate() {
	errorMessages = "";
	
	if(!document.getElementById("select").checked){
		errorMessages += "Please select a manufacturer<br />";
		document.getElementById("validateMessage").innerHTML = errorMessages;
		return false;
	}
	else{
		form1.submit();
		return true;
	}
}
</script>
<div style="background-color: #eeeeee; height: 350pt;">
<br />
<div style="font-size: 21pt; color: maroon; position: relative; left: 30pt; font-family: fantasy;"  class="bodyMargin">List of All Manufacturers</div><br /><br /><br />
<div style="position: relative; left: 130pt;width: 70%">
<div id="validateMessage" style="color: red"></div>
<form method="post" id="form1" onsubmit="return validate()" action="viewManufacturerDetails.htm">	
<table style="width: 100%; background-color: #bbbbbb; color: white; border: 1px solid white;padding: 3px">
<tr>
<th style="background-color: #333333;" class="form1">Select</th>
<th style="background-color: #333333;" class="form1">Manufacturer ID</th>
<th style="background-color: #333333;" class="form1">Manufacturer Name</th>
<th style="background-color: #333333;" class="form1">Admin Name</th>
<th style="background-color: #333333;" class="form1">Location</th>
<th style="background-color: #333333;" class="form1">Status</th>
</tr>
<c:forEach var="manu" items="${mList}">
<tr rowspan="2"  class="form" style="border: 1px solid white;">
<td style="border: 1px solid white;" align="center"><input type="radio" name="select" id="select" value="${manu.key.id}" style="width: 2em; height: 2em;"></td>
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;color:black">${manu.key.id}</span></td>
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;color:black">${manu.key.companyName}</span></td>
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;color:black">${manu.value.fName}</span></td>
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;color:black">${manu.value.address.city}</span></td>
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;color:black">${manu.value.userAccount.status}</span></td>
</tr>
</c:forEach>
</table>
<table style="width: 100%; background-color: #eeeeee; color: black; padding: 3px">
<tr align="center" class="form" style="height: 70pt;">
<td colspan="7"><input type="submit" value="View Details" id="process" class="button2" style="width: 150pt;"></td>
</tr>
</table>
</form>
</div>
</div>
<br /><br />
