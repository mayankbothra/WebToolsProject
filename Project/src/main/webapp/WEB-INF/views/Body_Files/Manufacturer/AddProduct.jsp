 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
    function confirmRegister() {
    	  if (confirm("Are you sure you want to create the product?")) {
    	    document.getElementById("register").submit();
    	  }
    	  else{}
    	  return false;
    	}
    </script>
 <div style="background-color: #dbe2f3; height: 400pt;">
 <br /><br />
<div style="font-size: 21pt; color: maroon; position: relative; left: 30pt; font-family: fantasy;"  class="bodyMargin">Depot Creation Form</div><br /><br />
<div style="position: relative; left: 50pt;width: 60%">
<br /><br />
<form:form method="POST" commandName="product" action="productAdded.htm" enctype="multipart/form-data">
<table style="width: 100%; background-color: #eeeeee">
<tr>
<th colspan="3" align="left" style="background-color: #aaaaaa;" class="form">&nbsp;&nbsp;&nbsp;Product Details</th>
</tr>
<tr class="form">
<td align="right">Product Name:</td>
<td>&nbsp;&nbsp;&nbsp;<form:input path="productName" size="30" value="" maxlength="100" placeholder="Product Name" /></td>
<td><form:errors path="productName" cssClass="error" /></td>
</tr>
<tr class="form">
<td align="right">Product Type:</td>
<td>&nbsp;&nbsp;&nbsp;<form:select path="productType" id="type" cssStyle="height:16pt;"><option value="default"> --Select-- </option>
<option value="TV">TV & Video</option>
<option value="AUDIO">Home Audio & Theatre</option>
<option value="CAMERA">Camera, Photo & Video</option>
<option value="CELLPHONES">Cell Phones & Accessories</option>
<option value="LAPTOP">Laptops, Tablets & Netbooks</option></form:select></td>
<td><form:errors path="productType" cssClass="error" /></td>
</tr>
<tr class="form">
<td align="right">Product Price:</td>
<td>&nbsp;&nbsp;&nbsp;<form:input path="price" size="30" value="" maxlength="" placeholder="Product Price" /></td>
<td><form:errors path="price" cssClass="error" /></td>
</tr>
<tr class="form">
<td align="right">Product Description:</td>
<td>&nbsp;&nbsp;&nbsp;<form:textarea path="description" rows="5" cols="26"  value="" maxlength="500" placeholder="Product Description" /></td>
<td><form:errors path="description" cssClass="error" /></td>
</tr>
<tr class="form">
<td align="right">Product Image:</td>
<td>&nbsp;&nbsp;&nbsp;<input type="file" name="fileUpload" size="50" /> (220px * 180px)</td>
</tr>
</table>
<br /><br /><br />
<input type="submit" value="Add" style="position: relative;left: 240pt;" class="button1" onclick="return confirmRegister()" id="register"/>
</form:form><br /><br />
</div>
</div>
<br /><br />


 
 
 
 
 
 
 
 
 