<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ include file="/WEB-INF/jsps/include.jsp" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sara Event Management</title>
<link rel="stylesheet" href="css/style.css">
<link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro' rel='stylesheet' type='text/css'>
<style type="text/css">
body {
    background-image:url(images/background-green.png);
    background-repeat: no-repeat;
    background-size: 100% 100%;
}
html {
    height: 100%
}
</style>
</head>
<body>
		<form:form action="login" method="POST" modelAttribute="loginform">
			<img src="images/App-logo.png" style="height: 45 px; width: 50px;"/>
			<h4>Sara Event Management</h4>
			
			<div class="error">
			<c:if test="${not empty error}">
   				${error}
			</c:if>
			</div>
			
			
  			<form:input class="username" type="text" placeholder="Enter Username" path="username"/>
  			<div>
  			<form:errors path="username" cssClass="error" />
  			</div>
  			
  			<form:password class="pw" placeholder="Enter Password" path="password"/>
  			
  			<div>
  			<form:errors path="password" cssClass="error" />
  			</div>
  			
  			<input class="button" style="margin-bottom:40px;" type="submit" value="Log in"/>
  			<li><a href="${pageContext.request.contextPath}/signup">New user Registration</a></li>
  			<li style="margin-left:200px;"><a href="#">Forgot your password?</a></li>
  			
  			
  			
  			
		</form:form>
</body>
</html>
