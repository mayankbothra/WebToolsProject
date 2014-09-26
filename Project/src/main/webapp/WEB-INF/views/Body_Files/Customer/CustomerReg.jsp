 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
 <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
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
    function confirmSubmit() {
    	  if (confirm("Are you sure you want to submit the form?")) {
    	    document.getElementById("register").submit();
    	  }
    	  else{}
    	  return false;
    	}
  $(function() {
    $( document ).tooltip();
  });
  </script>
<br />
<div style="font-size: 21pt; color: maroon; position: relative; left: 30pt; font-family: fantasy;"  class="bodyMargin">Registration Form</div><br /><br />
<div style="position: relative; left: 50pt;width: 60%">
<span style="color: teal; font-size: 15pt;">Register and Enjoy Shopping<br /><br /></span>
<br /><br />

<form:form method="POST" commandName="customerForm" action="customerRegSuccess.htm">
<%-- <form:errors path="*" cssClass="errorblock" element="div" /> --%>

<table style="width: 100%; background-color: #eeeeee">
<tr>
<th colspan="3" align="left" style="background-color: #aaaaaa;" class="form">&nbsp;&nbsp;&nbsp;Personal Details</th>
</tr>
<tr class="form">
<td align="right" class="rowWidth">First Name:</td>
<td>&nbsp;&nbsp;&nbsp;<form:input path="person.fName" size="32" maxlength="64" value="" placeholder="First Name" title="Enter First Name"/></td>
<td><form:errors path="person.fName" cssClass="error" /></td>
</tr>
<tr class="form">
<td align="right">Last Name:</td>
<td>&nbsp;&nbsp;&nbsp;<form:input path="person.lName" size="32" value="" maxlength="100" placeholder="Last Name" title="Enter Last Name"/></td>
<td><form:errors path="person.lName" cssClass="error" /></td>
</tr>
<tr class="form">
<td align="right">Email address:</td>
<td>&nbsp;&nbsp;&nbsp;<form:input path="person.email" type="email" size="32" value="" maxlength="100" placeholder="E-mail" title="Enter E-mail"/></td>
<td><form:errors path="person.email" cssClass="error" /></td>
</tr>
<tr class="form">
<td align="right">Contact:</td>
<td>&nbsp;&nbsp;&nbsp;<form:input path="person.contact" size="32" value="" maxlength="15" placeholder="Contact" title="Enter Contact"/></td>
<td><form:errors path="person.contact" cssClass="error" /></td>
</tr>
<tr class="form">
<td align="right">Street address 1:</td>
<td>&nbsp;&nbsp;&nbsp;<form:input path="address.street1" size="32" value="" maxlength="100" placeholder="Street Address1" title="Enter Street1"/></td>
<td><form:errors path="address.street1" cssClass="error" /></td>
</tr>
<tr class="form">
<td align="right">Street address 2:</td>
<td>&nbsp;&nbsp;&nbsp;<form:input path="address.street2" size="32" value="" maxlength="100" placeholder="Street Address2" title="Enter Street2"/></td>
<td><form:errors path="address.street2" cssClass="error" /></td>
</tr>
<tr class="form">
<td align="right">City:</td>
<td>&nbsp;&nbsp;&nbsp;<form:input path="address.city" size="32" value="" maxlength="" placeholder="City" title="Enter City"/></td>
<td><form:errors path="address.city" cssClass="error" /></td>
</tr>
<tr class="form">
<td align="right">State / Province:</td>
<td>
&nbsp;&nbsp;&nbsp;<form:select path="address.state" id="State"><option value="default">-Select-</option>
<option value="AL">Alabama</option>
<option value="AK">Alaska</option>
<option value="AZ">Arizona</option>
<option value="AR">Arkansas</option>
<option value="CA">California</option>
<option value="CO">Colorado</option>
<option value="CT">Connecticut</option>
<option value="DE">Delaware</option>
<option value="DC">District Of Columbia</option>
<option value="FL">Florida</option>
<option value="GA">Georgia</option>
<option value="HI">Hawaii</option>
<option value="ID">Idaho</option>
<option value="IL">Illinois</option>
<option value="IN">Indiana</option>
<option value="IA">Iowa</option>
<option value="KS">Kansas</option>
<option value="KY">Kentucky</option>
<option value="LA">Louisiana</option>
<option value="ME">Maine</option>
<option value="MD">Maryland</option>
<option value="MA">Massachusetts</option>
<option value="MI">Michigan</option>
<option value="MN">Minnesota</option>
<option value="MS">Mississippi</option>
<option value="MO">Missouri</option>
<option value="MT">Montana</option>
<option value="NE">Nebraska</option>
<option value="NV">Nevada</option>
<option value="NH">New Hampshire</option>
<option value="NJ">New Jersey</option>
<option value="NM">New Mexico</option>
<option value="NY">New York</option>
<option value="NC">North Carolina</option>
<option value="ND">North Dakota</option>
<option value="OH">Ohio</option>
<option value="OK">Oklahoma</option>
<option value="OR">Oregon</option>
<option value="PA">Pennsylvania</option>
<option value="RI">Rhode Island</option>
<option value="SC">South Carolina</option>
<option value="SD">South Dakota</option>
<option value="TN">Tennessee</option>
<option value="TX">Texas</option>
<option value="UT">Utah</option>
<option value="VT">Vermont</option>
<option value="VA">Virginia</option>
<option value="WA">Washington</option>
<option value="WV">West Virginia</option>
<option value="WI">Wisconsin</option>
<option value="WY">Wyoming</option>
<option value="PR">Puerto Rico</option>
<option value="VI">Virgin Islands</option>
<option value="MP">Northern Mariana Islands</option>
<option value="GU">Guam</option>
<option value="AS">American Samoa</option>
<option value="PW">Palau</option>
<option value="AA">Armed Forces (AA)</option>
<option value="AE">Armed Forces (AE)</option>
<option value="AP">Armed Forces (AP)</option></form:select></td>
<td><form:errors path="address.state" cssClass="error" /></td>
</tr>
<tr class="form">
<td align="right">Zip:</td>
<td>&nbsp;&nbsp;&nbsp;<form:input path="address.zip" size="12" value="" maxlength="5" placeholder="zip" title="Enter Zip"/></td>
<td><form:errors path="address.zip" cssClass="error" /></td>
</tr>
</table>
<br /><br /><br />

<table style="width: 100%; background-color: #eeeeee">
<tr>
<th colspan="3" align="left" style="background-color: #aaaaaa;" class="form">&nbsp;&nbsp;&nbsp;User Account Details</th>
</tr>
<tr class="form">
<td align="right" class="rowWidth">Enter User Name:</td>
<td>&nbsp;&nbsp;&nbsp;<form:input path="userAccount.username" size="32" maxlength="64" value="" placeholder="User Name" title="Enter Username"/></td>
<td><form:errors path="userAccount.username" cssClass="error" /></td>
</tr>
<tr class="form">
<td align="right">Enter Password:</td>
<td>&nbsp;&nbsp;&nbsp;<form:password path="userAccount.password" size="32" value="" maxlength="100" placeholder="Password" title="Enter Password"/></td>
<td><form:errors path="userAccount.password" cssClass="error" /></td>
</tr>
<tr class="form">
<td align="right">Re-enter Password:</td>
<td>&nbsp;&nbsp;&nbsp;<form:password path="userAccount.rePassword" size="32" value="" maxlength="100" placeholder="Re-enter Password" title="Enter Re-Password"/></td>
<td><form:errors path="userAccount.rePassword" cssClass="error" /></td>
</tr>
</table>
<br /><br /><br />
<ul style="padding-left:40px;margin:0px">
<li>I am at least 18 years old.</li>
<li>I may receive communications from Smart Shopping Inc.</li>
</ul>
<br/><br/>
<input type="submit" value="Register" style="position: relative;left: 240pt;" class="button4" onclick="return confirmSubmit()" id="register"/>
</form:form><br /><br />
</div>
</div>



 
 
 
 
 
 
 
 
 