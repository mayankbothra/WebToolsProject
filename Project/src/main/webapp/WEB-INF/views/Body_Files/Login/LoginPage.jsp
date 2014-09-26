<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.error {
	color: #ff0000; font-size: 10pt;
}
 
.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
<link href="${pageContext.request.contextPath}/static/style/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$(".username").focus(function() {
		$(".user-icon").css("left","-48px");
	});
	$(".username").blur(function() {
		$(".user-icon").css("left","0px");
	});
	
	$(".password").focus(function() {
		$(".pass-icon").css("left","-48px");
	});
	$(".password").blur(function() {
		$(".pass-icon").css("left","0px");
	});
});
</script>
<div id="wrapper">
<div class="user-icon"></div>
<div class="pass-icon"></div>

<form:form commandName="useraccount" action="login.htm" method="post" cssClass="login-form">
<div class="header">
<h1>Login Form</h1>
</div>
<div class="content">
<form:input path="username" cssClass="input username" placeholder="Username" onfocus="this.value=''" />
<form:errors path="username" cssClass="error" />
<form:password path="rePassword" cssClass="input password" placeholder="Password" onfocus="this.value=''" />
<form:errors path="rePassword" cssClass="error" />
<form:hidden path="password" value="password"/>
</div>
<div class="footer">
<input type="checkbox" name="rememberme" value="remember" style="position: relative;left:-10pt;top:-10pt;"><span style="font-size: 9pt;position: relative;left:-10pt;top:-10pt;">Remember Me</span>
<input type="submit" name="submit" value="Login" class="button" style="position: relative;top:-10pt;"/>
</form>
<form method="post" action="customerReg.htm">
<input type="submit" name="submit" value="Create New Account" class="register" style="position: relative;top:2pt;left:-13pt;"/>
</form:form>
</div>
</div>