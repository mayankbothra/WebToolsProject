
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
.error {
	color: #ff0000;
}
 
.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
<script>
    function confirmProcess() {
    	  if (confirm("Are you sure you want to process the request?")) {
    	    document.getElementById("register").submit();
    	  }
    	  else{}
    	  return false;
    	}
    function validate() {
    	errorMessages = "";
    	
    	var pro = document.getElementById("product");
    	var product = pro.options[pro.selectedIndex].value;
    	
    	if(product == "default"){
    		errorMessages += "Please select a product<br />";
    	}
    	var dep = document.getElementById("depot");
    	var depot = dep.options[pro.selectedIndex].value;
    	
    	if(depot == "default"){
    		errorMessages += "Please select the depot<br />";
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
<div style="background-color: #dbe2f3; height: 370pt;">
<br /><br />
<div style="font-size: 21pt; color: maroon; position: relative; left: 30pt; font-family: fantasy;"  class="bodyMargin">Stock Adding Form</div><br /><br />
<div style="position: relative; left: 50pt;width: 60%">
<br /><br />
<div id="validateMessage" style="color: red"></div>
<form method="POST" action="stockAdded.htm" id="form1" onsubmit="return validate()">
<table style="width: 100%; background-color: #eeeeee">
<tr>
<th colspan="3" align="left" style="background-color: #aaaaaa;" class="form">&nbsp;&nbsp;&nbsp;Stock Addition Details</th>
</tr>
<tr class="form">
<td align="right">Select Product:</td>
<td>&nbsp;&nbsp;&nbsp;<select name="product" id="product" style="height:16pt;"><option value="default"> --Select-- </option>
<c:forEach var="product" items="${productList}">
<option value="${product.id}">${product.productName}</option>
</c:forEach>
</select></td>
</tr>
<tr class="form">
<td align="right">Select Depot:</td>
<td>&nbsp;&nbsp;&nbsp;<select name="depot" id="depot" style="height:16pt;"><option value="default"> --Select-- </option>
<c:forEach var="depot" items="${depotList}">
<option value="${depot.id}">${depot.location}</option>
</c:forEach>
</select></td>
</tr>
<tr class="form">
<td align="right">Quantity:</td>
<td>&nbsp;&nbsp;&nbsp;<input type="number" name="quantity"  min="1" max="50" id="quantity" /></td>
</tr>
</table>
<br /><br /><br />
<input type="submit" value="Add" style="position: relative;left: 240pt;" class="button1" id="register"/>
</form><br /><br />
</div>
</div>
<br /><br />


 
 
 
 
 
 
 
 
 