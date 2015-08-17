<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>

<link type="text/css" href="resources/css/login.css" rel="stylesheet" />
<link type="text/css" href="resources/css/theme.css" rel="stylesheet" />

<script src="resources/scripts/login.js"></script>
<script src="resources/scripts/scripts.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="http://s.cssdeck.com/assets/js/prefixfree.min.js"></script>

</head>
<body oncontextmenu="return false;">
	<!--Header Section-->

	<div class="header-tile"></div>
	<div class="header">
		<div class="header-title">
			<img src="resources/images/fly.png" border="0" class="fly"
				height="3px" width="30px" /> <img src="resources/images/fly.png"
				border="0" class="fly1" height="3px" width="30px" /> <img
				src="resources/images/fly.png" border="0" class="fly2" height="3px"
				width="30px" />SMS
			<div class="wait"></div>
		</div>
	</div>
	<div class='login'>
		<form action="${pageContext.request.contextPath}/home" method="post"
			name="loginForm" autocomplete="OFF">
			<input name='username' id="username" placeholder='Username'
				type="text" required /> <input type="password" name="password"
				id="password" placeholder="Password" onblur="showText() " required />
			<input type='submit' value='Sign in' />
			
		</form>
		<c:if test="${invalidUser != null}" var="user" scope="request">
									<label id =errorMessage >${invalidUser}</label>  
			</c:if>
	</div>
	
	<div class="footer">
		@Copyright SMS<br>Developed by Subhaknata, Anirudha, Aditya
	</div>
</body>
</html>