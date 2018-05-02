<%@ include file="../WEB-INF/jsps/include.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- Redirected because we can't set the welcome page to a virtual URL. --%>
<%-- <c:set var="context" value="${pageContext.request.contextPath}" /> --%>
<c:redirect url="/login" />