 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div style="background-color: #eeeeee; height: 600pt;">
<br /><br />
<div style="font-size: 21pt; color: maroon; position: relative; left: 30pt; font-family: fantasy;"  class="bodyMargin">Manufacturer Details</div><br /><br />
<div style="position: relative; left: 50pt;width: 60%">

<form method="POST" action="viewAllManufacturers.htm">
<table style="width: 100%; background-color: #eeeeee">
<tr>
<th colspan="2" align="left" style="background-color: #aaaaaa;" class="form">&nbsp;&nbsp;&nbsp;Company Details</th>
</tr>
<tr class="form">
<td align="right" class="rowWidth">Business Name:</td>
<td>&nbsp;&nbsp;&nbsp;<label for="companyName">${manufacturer.companyName}</label></td>
</tr>
<tr class="form">
<td align="right">Contact:</td>
<td>&nbsp;&nbsp;&nbsp;<label for="contact">${person.contact}</label></td>
</tr>
<tr class="form">
<td align="right">Street address 1:</td>
<td>&nbsp;&nbsp;&nbsp;<label for="street1">${person.address.street1}</label></td>
</tr>
<tr class="form">
<td align="right">Street address 2:</td>
<td>&nbsp;&nbsp;&nbsp;<label for="street2">${person.address.street2}</label></td>
</tr>
<tr class="form">
<td align="right">City:</td>
<td>&nbsp;&nbsp;&nbsp;<label for="city">${person.address.city}</label></td>
</tr>
<tr class="form">
<td align="right">State / Province:</td>
<td>&nbsp;&nbsp;&nbsp;<label for="state">${person.address.state}</label></td>
</tr>
<tr class="form">
<td align="right">Zip:</td>
<td>&nbsp;&nbsp;&nbsp;<label for="zip">${person.address.zip}</label></td>
</tr>
</table>
<br /><br /><br />
<table style="width: 100%; background-color: #eeeeee">
<tr>
<th colspan="2" align="left" style="background-color: #aaaaaa;" class="form">&nbsp;&nbsp;&nbsp;Personal Details</th>
</tr>
<tr class="form">
<td align="right" class="rowWidth">First Name:</td>
<td>&nbsp;&nbsp;&nbsp;<label for="fName">${person.fName}</label></td>
</tr>
<tr class="form">
<td align="right">Last Name:</td>
<td>&nbsp;&nbsp;&nbsp;<label for="lName">${person.lName}</label></td>
</tr>
<tr class="form">
<td align="right">Email address:</td>
<td>&nbsp;&nbsp;&nbsp;<label for="email">${person.email}</label></td>
</tr>
</table>
<br /><br /><br/><br/>
<input type="submit" value="Back" style="position: relative;left: 240pt;" class="button2" id="register"/>
</form>
</div>
</div>
<br /><br /> 