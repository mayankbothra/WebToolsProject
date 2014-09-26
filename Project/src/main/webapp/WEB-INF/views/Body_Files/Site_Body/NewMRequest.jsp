<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    function confirmProcess() {
    	  if (confirm("Are you sure you want to process the request?")) {
    	    document.getElementById("process").submit();
    	  }
    	  else{}
    	  return false;
    	}
    function validate() {
    	errorMessages = "";

    	if(!document.getElementById("select").checked){
    		errorMessages += "Please select a manufacturer<br />";
    	}
    	if(!document.getElementById("phone").checked){
    		errorMessages += "Please perform phone verification<br />";
    	}
    	if(!document.getElementById("document").checked){
    		errorMessages += "Please verify documents<br />";
    	}
    	var m = document.getElementById("status");
    	var value = m.options[m.selectedIndex].value;
    	
    	if(value == "default"){
    		errorMessages += "Please select status<br />";
    	}
    	
    	if(errorMessages!= ""){
    		document.getElementById("validateMessage").innerHTML = errorMessages;
    		return false;
    	}
    	else{
    		form1.submit();
    		confirmProcess();
    		return true;
    	}
    }
    
    </script>
<div style="background-color: #eeeeee; height: 350pt;">
<br /><br />
<div style="font-size: 21pt; color: maroon; position: relative; left: 30pt; font-family: fantasy;"  class="bodyMargin">New Manufacturer Requests</div><br /><br /><br />
<div style="position: relative; left: 130pt;width: 70%">
<div id="validateMessage" style="color: red"></div>
<form method="post" id="form1" onsubmit="return validate()" action="processRequest.htm">	
<table style="width: 100%; background-color: #bbbbbb; color: black; border: 1px solid white;padding: 3px" id="table1">
<tr style="color: white">
<th style="background-color: #333333;" class="form1">Select</th>
<th style="background-color: #333333;" class="form1">Manufacturer ID</th>
<th style="background-color: #333333;" class="form1">Manufacturer Name</th>
<th style="background-color: #333333;" class="form1">Admin Name</th>
<th style="background-color: #333333;" class="form1">Location</th>
<th style="background-color: #333333;" class="form1">Verification</th>
<th style="background-color: #333333;" class="form1">Status</th>
</tr>
<c:forEach var="manu" items="${mList}">
<tr rowspan="2"  class="form" style="border: 1px solid white;">
<td style="border: 1px solid white;" align="center"><input type="radio" name="select" value="${manu.key.id}" id="select" style="width: 2em; height: 2em;"></td>
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;color:black">${manu.key.id}</span></td>
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;color:black">${manu.key.companyName}</span></td>
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;color:black">${manu.value.fName}</span></td>
<td style="border: 1px solid white;"><span style="position:relative;left: 10pt;color:black">${manu.value.address.city}</span></td>
<td style="border: 1px solid white;">
<input type="checkbox" name="verification" value="phone" id="phone">&nbsp;Phone<br />
<input type="checkbox" name="verification" value="document" id="document">&nbsp;Documents</td>
<td style="border: 1px solid white;" align="center"><select name="status" style="width: 60pt;" id="status">
<option value="default">-Select-</option>
<option value="active">Approve Request</option>
<option value="rejected">Reject Request</option>
</select></td>
</tr>
</c:forEach>
</table>
<table style="width: 100%; background-color: #eeeeee; color: black; padding: 3px">
<tr align="center" class="form" style="height: 70pt;">
<td colspan="7"><input type="submit" value="Process Request" id="process" class="button2" style="width: 150pt;"></td>
</tr>
</table>
</form>
</div>
</div>
<br /><br />
