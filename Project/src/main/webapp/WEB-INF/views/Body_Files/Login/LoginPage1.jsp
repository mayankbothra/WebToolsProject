<link href="${pageContext.request.contextPath}/static/style/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$(".username").focus(function() {
		$(".user-iconn").css("left","-48px");
	});
	$(".username").blur(function() {
		$(".user-iconn").css("left","0px");
	});
	
	$(".password").focus(function() {
		$(".pass-iconn").css("left","-48px");
	});
	$(".password").blur(function() {
		$(".pass-iconn").css("left","0px");
	});
});
</script>
<div id="wrapper">
<div class="user-iconn"></div>
<div class="pass-iconn"></div>

<form name="login-form" class="login-form" action="login.htm" method="post">
<div class="header">
<span style="color: red;font-size: 10pt;">Invalid Username or Password<br/ >Please try again.</span>
<h1>Login Form</h1>
</div>
<div class="content">
<input name="username" type="text" class="input username" placeholder="Username" onfocus="this.value=''" />
<input name="password" type="password" class="input password" placeholder="Password" onfocus="this.value=''" />
</div>
<div class="footer">
<input type="checkbox" name="rememberme" value="remember" style="position: relative;left:-10pt;top:-10pt;"><span style="font-size: 9pt;position: relative;left:-10pt;top:-10pt;">Remember Me</span>
<input type="submit" name="submit" value="Login" class="button" style="position: relative;top:-10pt;"/>
</form>
<form method="post" action="customerReg.htm">
<input type="submit" name="submit" value="Create New Account" class="register" style="position: relative;top:2pt;left:-13pt;"/>
</form>
</div>
</div>
