<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>

<link type="text/css" href="resources/css/theme.css" rel="stylesheet" />
<link type="text/css" href="resources/css/login.css" rel="stylesheet" />
<link type="text/css" href="resources/css/datepicker.css" rel="stylesheet" />
<script src="resources/scripts/scripts.js"></script>
<script src="resources/scripts/login.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="http://s.cssdeck.com/assets/js/prefixfree.min.js"></script>

</head>
<body oncontextmenu="return false;" id="homeBody">



	<div class="wrapper">
		
		<div class="container">

			<!--  navigation sidebar starts-->
			<ul class="nav-sidebar">
				<c:forEach var="user" items="${userList}">
					<li><a href="${pageContext.request.contextPath}${user[0]}">${user[1]}</a></li>
				</c:forEach>
				<c:forEach var="user" items="${userList}">
					<li><a href="${pageContext.request.contextPath}${user[0]}">${user[1]}</a></li>
				</c:forEach>
				<c:forEach var="user" items="${userList}">
					<li><a href="${pageContext.request.contextPath}${user[0]}">${user[1]}</a></li>
				</c:forEach>

			</ul>
			<!--  navigation sidebar ends-->
			<div class="middle-container">
				<c:choose>
					<c:when test="${includePage== 'defaultHome'}">
    Welcome
  </c:when>
					<c:when test="${includePage== 'editProfile'}">
						<jsp:include page="StudentForm.jsp" flush="true" />
					</c:when>
					<c:when test="${includePage== 'ProfileSunbmitted'}">
   Profile Submitted Successfully
  </c:when>
					<c:when test="${includePage== 'registerProfile'}">
						<jsp:include page="RegisterForm.jsp" flush="true" />
					</c:when>
					<c:when test="${includePage== 'UserRegisterd'}">
   User registered sucessfully.
  </c:when>
					<c:otherwise>

					</c:otherwise>
				</c:choose>
			</div>
		</div>
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
			<button class="logout-button" type="button">
				<a href="${pageContext.request.contextPath}/logout" />Logout
			</button>
		</div>

	</div>

<div class="footer">
			@Copyright SMS<br>Developed by Subhakanta, Anirudha, Aditya
		</div>
</body>
</html>