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
    	  if (confirm("Are you sure you want to create the employee?")) {
    	    document.getElementById("register").submit();
    	  }
    	  else{}
    	  return false;
    	}
    </script>
 <div style="background-color: #dbe2f3; height: 700pt;">
 <br /><br />
<div style="font-size: 21pt; color: maroon; position: relative; left: 30pt; font-family: fantasy;"  class="bodyMargin">Employee Creation Form</div><br /><br />
<div style="position: relative; left: 50pt;width: 60%">
<br /><br />
<form:form method="POST" commandName="employeeForm" action="employeeAdded.htm">
<table style="width: 100%; background-color: #eeeeee">
<tr>
<th colspan="3" align="left" style="background-color: #aaaaaa;" class="form">&nbsp;&nbsp;&nbsp;Employee Details</th>
</tr>
<tr class="form">
<td align="right" class="rowWidth">First Name:</td>
<td>&nbsp;&nbsp;&nbsp;<form:input path="person.fName" size="32" maxlength="64" value="" placeholder="First Name" /></td>
<td><form:errors path="person.fName" cssClass="error" /></td>
</tr>
<tr class="form">
<td align="right">Last Name:</td>
<td>&nbsp;&nbsp;&nbsp;<form:input path="person.lName" size="32" value="" maxlength="100" placeholder="Last Name" /></td>
<td><form:errors path="person.lName" cssClass="error" /></td>
</tr>
<tr class="form">
<td align="right">Email address:</td>
<td>&nbsp;&nbsp;&nbsp;<form:input path="person.email" type="email" size="32" value="" maxlength="100" placeholder="E-mail" /></td>
<td><form:errors path="person.email" cssClass="error" /></td>
</tr>
<tr class="form">
<td align="right">Contact:</td>
<td>&nbsp;&nbsp;&nbsp;<form:input path="person.contact" size="32" value="" maxlength="15" placeholder="Contact" /></td>
<td><form:errors path="person.contact" cssClass="error" /></td>
</tr>
</table>
<br /><br /><br />
<table style="width: 100%; background-color: #eeeeee">
<tr>
<th colspan="3" align="left" style="background-color: #aaaaaa;" class="form">&nbsp;&nbsp;&nbsp;Location Details</th>
</tr>
<tr class="form">
<td align="right">Street address 1:</td>
<td>&nbsp;&nbsp;&nbsp;<form:input path="address.street1" size="32" value="" maxlength="100" placeholder="Street Address1" /></td>
<td><form:errors path="address.street1" cssClass="error" /></td>
</tr>
<tr class="form">
<td align="right">Street address 2:</td>
<td>&nbsp;&nbsp;&nbsp;<form:input path="address.street2" size="32" value="" maxlength="100" placeholder="Street Address2" /></td>
<td><form:errors path="address.street2" cssClass="error" /></td>
</tr>
<tr class="form">
<td align="right">City:</td>
<td>&nbsp;&nbsp;&nbsp;<form:input path="address.city" size="32" value="" maxlength="" placeholder="City" /></td>
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
<td>&nbsp;&nbsp;&nbsp;<form:input path="address.zip" size="12" value="" maxlength="5" placeholder="zip" /></td>
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
<td>&nbsp;&nbsp;&nbsp;<form:input path="userAccount.username" size="32" maxlength="64" value="" placeholder="User Name" /></td>
<td><form:errors path="userAccount.username" cssClass="error" /></td>
</tr>
<tr class="form">
<td align="right">Enter Password:</td>
<td>&nbsp;&nbsp;&nbsp;<form:password path="userAccount.password" size="32" value="" maxlength="100" placeholder="Password" /></td>
<td><form:errors path="userAccount.password" cssClass="error" /></td>
</tr>
<tr class="form">
<td align="right">Re-enter Password:</td>
<td>&nbsp;&nbsp;&nbsp;<form:password path="userAccount.rePassword" size="32" value="" maxlength="100" placeholder="Re-enter Password" /></td>
<td><form:errors path="userAccount.rePassword" cssClass="error" /></td>
</tr>
<tr class="form">
<td align="right">Role:</td>
<td>&nbsp;&nbsp;&nbsp;<form:select path="userAccount.role" maxlength="100">
<option value="default">-Select-</option>
<option value="SM">Stock Manager</option>
<option value="PM">Product Manager</option></form:select>
</td>
<td><form:errors path="userAccount.role" cssClass="error" /></td>
</tr>
</table>
<br /><br /><br/><br/>
<input type="submit" value="Add" style="position: relative;left: 240pt;" class="button1" onclick="return confirmRegister()" id="register"/>
</form:form><br /><br />
</div>
</div>
<br /><br />