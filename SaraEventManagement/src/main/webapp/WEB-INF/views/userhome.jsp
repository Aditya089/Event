<%@page import="javax.naming.Context"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="/WEB-INF/jsps/include.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en-US">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sara Event Management</title>
<!-- <link rel="shortcut icon"
		href="http://static.tmimgcdn.com/img/favicon.ico">
	<link rel="icon" href="http://static.tmimgcdn.com/img/favicon.ico"> -->
<link rel="stylesheet" type="text/css" media="all"
	href="css/userhomestyles.css">
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
</head>

<body>
	<nav>
		<a href="#"><img src="images/App-logo.png"
			style="left: 30px; width: 70px; position: absolute" /></a>
		<div class="wrapper">
			<ul id="menu" class="clearfix">

				<li><a href="${pageContext.request.contextPath}/home">Home</a></li>
				<li><a href="#">Categories</a>
					<ul>
						<li class="purple"><a href="#">Events</a>
							<ul>
								<li><a href="#" id="create" onclick="enableView('EventDiv')">Create</a></li>
								<li><a href="#">View</a></li>
								<li><a href="#">Manage</a></li>
							</ul></li>
						<li class="green"><a href="#">Speaker</a>
							<ul>
								<li><a href="#"  onclick="enableView('SpeakerDiv')">New</a></li>
								<li><a href="#">Assign</a></li>
							</ul></li>
						<li><a href="gold">Social</a>
							<ul>
								<li><a href="#">Facebook</a></li>
								<li><a href="#">Twitter</a></li>
								<li><a href="#">YouTube</a></li>
								<li><a href="#">Instagram</a></li>
								<li><a href="#"></a></li>
							</ul></li>
					</ul></li>
				<li><a href="#">About</a>
					<ul>
						<li><a href="#">History</a></li>
						<li><a href="#">The Team</a></li>
						<li><a href="#">Our Mission</a></li>
					</ul></li>
				<li><a href="${pageContext.request.contextPath}/logout"><img
						src="images/logout.png"
						style="left: 700px; width: 50px; position: absolute; top: 12px;" /></a></li>
			</ul>

		</div>
	</nav>
	<script type="text/javascript">
		$(function() {
			$('a[href="#"]').on('click', function(e) {
				e.preventDefault();
			});

			$('#menu > li').on('mouseover', function(e) {
				$(this).find("ul:first").show();
				$(this).find('> a').addClass('active');
			}).on('mouseout', function(e) {
				$(this).find("ul:first").hide();
				$(this).find('> a').removeClass('active');
			});

			$('#menu li li').on('mouseover', function(e) {
				if ($(this).has('ul').length) {
					$(this).parent().addClass('expanded');
				}
				$('ul:first', this).parent().find('> a').addClass('active');
				$('ul:first', this).show();
			}).on('mouseout', function(e) {
				$(this).parent().removeClass('expanded');
				$('ul:first', this).parent().find('> a').removeClass('active');
				$('ul:first', this).hide();
			});
		});
	</script>
	<script type="text/javascript">
     /*  $(function() {
          $('#idDateField').datepicker();
      }); */
      
      var divs = ['EventDiv','SpeakerDiv','allEvents'];
      function enableView(id){
    	  document.getElementById(id).style.display = "block";
    	  hideOtherViews(id);
      }
      
      function hideOtherViews(id){
    	  var i = divs.length;
    	  for(var i = 0; i<divs.length; i++  ){
    		  if(id != divs[i]){
    			  document.getElementById(divs[i]).style.display = "none";
    		  }
    	  }
      }
      
      function hideView(id){
    	  
    	  
    	  document.getElementById(id).style.display = "none";
      }
  </script>
	<div id="EventDiv" style="display:none">
		<form:form action="${pageContext.request.contextPath}/event/create"
			method="POST" modelAttribute="EventForm">
			<table>
				<tr style="height:100px">
					<td>
						<form:input class="username" type="text" placeholder="Event Name" path="eventName" />
						<form:input type="date" path="startTime" />
						<form:input type="date" path="endTime" />
					</td>

					<td width="150 px"></td>
					<td>
						<form:textarea class="username" type="text" placeholder="Venue Location" path="location" />
						<div style="font:inherit;font-weight:600;font-size:19px;font-family: Helvetica, 'Trebuchet MS', Tahoma, sans-serif;float: left;
							padding-right: 15px;padding-top: 10px;">
							Cost </div><form:input class="username" type="text" placeholder="Cost" path="cost" style="width:100px"/>
					</td>
				</tr>
				
				<tr>
				<td colspan="1">
				<div style="font:inherit;font-weight:600;font-size:19px;font-family: Helvetica, 'Trebuchet MS', Tahoma, sans-serif;float: left;
	padding-right: 15px;padding-top: 10px;">
					Speaker </div> <form:select path="speaker.speakerId">
					  <form:option value="null" label="--- Select ---" />
					  <c:if test="${not empty availableSpeakers}">
						<c:forEach items="${availableSpeakers}" var="speaker">
						  <form:option value="${speaker.speakerId}" label="${speaker.name}" />
						</c:forEach>
					  </c:if>
					  <%-- <form:options items="${availableSpeakers.name}" /> --%>
				       </form:select>
				</td>
				</tr>
				<tr>
				<td colspan="1">
				<div style="font:inherit;font-weight:600;font-size:19px;font-family: Helvetica, 'Trebuchet MS', Tahoma, sans-serif;float: left;
	padding-right: 30px;padding-top: 10px;">
					Topics </div> <form:select  path="topics" items="${availableTopics}"
					multiple="true" />
				</td>
				</tr>
				
				
				<tr>
					<td colspan="3">
						<input type="submit" class="submithome" value="Create" style="left:230px"/>
						<input type="button" class="submithome" onclick="hideView('EventDiv')" value="Cancel" style="left:260px"/>	
					</td>
				</tr>
			</table>
	</form:form>
	</div>

	<div id="SpeakerDiv" style="display: none">
		<form:form action="${pageContext.request.contextPath}/speaker/create"
			method="POST" modelAttribute="SpeakerForm" class="speakerform">
			<table>
				<tr style="height: 100px">
				<td>
					<form:input class="username" type="text" placeholder="Full Name" path="name" />
					<form:input class="username" type="text" placeholder="Contact Number" path="mobile" />
					<form:input class="username" type="text" placeholder="Qualification" path="qualification" />
					<form:input class="username" type="text" placeholder="Email id" path="speakerEmail" />
					
					<%-- <form:select class="form-control" items="${availableTopics}" name="choices-multiple-remove-button" 
					id="choices-multiple-remove-button" path="topics" placeholder="Add Topics" multiple="true" ></form:select> --%>
				</td>
				<td style="vertical-align:top; height:85px" >
						<form:textarea class="username" type="text" placeholder="Address" path="address" />
					</td>  
					
				</tr>
				<tr>
				<td colspan="1">
						<div
							style="font: inherit; font-weight: 600; font-size: 19px; font-family: Helvetica, 'Trebuchet MS', Tahoma, sans-serif; float: left; padding-right: 30px; padding-top: 10px;">
							Topics</div> <form:select style="width: 225px" path="topics" items="${availableTopics}"
							multiple="true" />
					</td>
				</tr>
				<tr>
					<td colspan="3"><input type="submit" class="submithome"
						value="Register Speaker" style="left: 230px" /> <input
						type="button" class="submithome" onclick="hideView('EventDiv')"
						value="Cancel" style="left: 260px" /></td>
				</tr>
			</table>
		</form:form>
	</div>

	<div class="table100 ver4 m-b-110" id="allEvents">
		<div class="table100-head">
			<table>
				<thead>
					<tr class="row100 head">
						<th class="cell100 column1">Event name</th>
						<th class="cell100 column2">Location</th>
						<th class="cell100 column3">Speaker</th>
						<th class="cell100 column4">Cost</th>
						<th class="cell100 column5">Topics</th>
					</tr>
				</thead>
			</table>
		</div>

		<div class="table100-body js-pscroll">
			<table>
				<tbody>
					<c:if test="${not empty AllEvents}">
						<c:forEach items="${AllEvents}" var="event">
							<tr class="row100 body">
								<td class="cell100 column1">${event.eventName}</td>
								<td class="cell100 column2">${event.location}</td>
								<td class="cell100 column3">${event.speaker.name}</td>
								<td class="cell100 column4">${event.cost}</td>
								<td class="cell100 column5">${event.topics}</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>