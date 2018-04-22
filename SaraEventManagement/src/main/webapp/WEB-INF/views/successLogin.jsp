<%@page import="javax.naming.Context"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="/WEB-INF/jsps/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RMS 1.0</title>
<style type="text/css">
ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: #333;
}

li {
    float: left;
}

li a, .dropbtn {
    display: inline-block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

li a:hover, .dropdown:hover .dropbtn {
    background-color: red;
}

li.dropdown {
    display: inline-block;
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
}

.dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
    text-align: left;
}

.dropdown-content a:hover {background-color: #f1f1f1}

.dropdown:hover .dropdown-content {
    display: block;
}
</style>
<script type="text/javascript">
function createLink(base,suburl){
	document.userform.action=base+"/"+suburl;
	document.userform.submit();
}

</script>
</head>
<body>
<form name="userform" >
<c:set var="context" value="${pageContext.request.contextPath}" />
	<center>
		
		<table border="0" width="100%">
			<tr>
			<td width="33%"></td>
			<td align="center" width="33%"><h3> Welcome ${user.username}</h3></td>
			<td align="right" width="33%"><a href="<c:url value='/logout'/>">Log Out</a></td>
			</tr>
			
			<tr>
			<td align="left" width="33%">
			<c:if test="${user.group == 'A'}">Administrator </c:if> 
			<c:if test="${user.group != 'A'}">User</c:if>
			</td>
			<td width="33%"></td>
			<td align="right" width="33%">${user.lastLogin}</td>
			</tr>
			
			<tr>
			<ul>
				<c:if test="${not empty ROLES}">
					
						<c:forEach var="listValue" items="${ROLES}">
							<li class="dropdown">
								 <a href="javascript:void(0)" class="dropbtn">${listValue.key}</a>
								    <div class="dropdown-content">
								    <c:forEach var="grpOp" items="${listValue.value}">
								     <a href="#" onclick='createLink("${listValue.key}","${grpOp}")';>${grpOp}</a>
								    </c:forEach>
								      
								    </div>
								</li>

						</c:forEach>
				</c:if>
				<li style="float:right"><a class="active" href="#about">About</a></li>
			</ul>
			</tr>
		</table>
		
		
		
		
	</center>
	</form>
</body>
</html>