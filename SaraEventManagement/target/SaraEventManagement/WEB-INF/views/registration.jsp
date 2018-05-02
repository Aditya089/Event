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

.error {
	color: #f03e30;
}

form {
  position:relative;
  width:360px;
  height:auto;
  margin:50px auto;
  text-align:center;
  background:#ecf0f1;
  padding:40px;

  
  box-shadow: 20px 20px 20px;
}

h4 {
  font-family: 'Source Sans Pro', sans-serif;
  font-size:2em;
  font-weight:300;
  margin-bottom:25px;
  color:#134d51;
  text-shadow:1px 1px 0px white;
}

</style>
</head>
<body>
	<c:set var="context" value="${pageContext.request.contextPath}" />

	<form:form action="signup" method="POST" modelAttribute="userform">
			<h4>Welcome ${username}</h4>
			<form:input class="username" type="text" placeholder="Enter Username" path="username"/>
			<form:errors path="username" cssClass="error" />
			<form:input class="username" type="text" placeholder="Enter Full Name" path="fullName"/>
			<form:errors path="fullName" cssClass="error" />
			<form:input class="username" type="text" placeholder="Enter Contact details" path="phone"/>
			<form:errors path="phone" cssClass="error" />
			<form:input class="username" type="text" placeholder="Enter E-mail" path="email"/>
			<form:errors path="email" cssClass="error" />
			<form:password class="pw" placeholder="Enter Password" path="password"/>
			<form:errors path="password" cssClass="error" />
			<form:password class="pw" placeholder="Re-Enter Password" path="rePassword"/>
			<form:errors path="rePassword" cssClass="error" />
			<input class="button" type="submit" value="Sign Up"/>
			<li><a href="${pageContext.request.contextPath}/">Go Back</a></li>
	</form:form>
</body>
</html>