<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false"%>
<html>
<head>
<c:url value="/resources/css/main.css" var="mainCss" />
<c:url value="/resources/webjars/jquery/2.1.4/jquery.min.js"
	var="jqueryJs" />
<c:url value="/resources/js/main.js" var="mainJs" />

<link href="${mainCss}" rel="stylesheet" />
<script src="${jqueryJs}"></script>
<script src="${mainJs}"></script>
<title>Home</title>
</head>
<body>
	<h1>
		<spring:message code="app.title" />
	</h1>

	<a href="/ssmvcp3?language=en"><spring:message
			code="home.label.english" /></a>
	<a href="/ssmvcp3?language=es"><spring:message
			code="home.label.spanish" /></a>

	<P>
		<spring:message code="home.label.time" />
		${serverTime}.
	</P>
	
	<P>
		<spring:message code="home.label.time" />
		${serverTime}.
	</P>
	
	<h3>Datos en DIVS</h3>	
	<DIV th:text="${user1.name}">user1</DIV>
	<DIV th:text="${user2.name}">user2</DIV>
	<DIV th:text="${user3.name}">user3</DIV>
	
	<h3>datos en lista</h3>
	<ul>
		<li th:text="${user1.name}">user1</li>
		<li th:text="${user2.name}">user2</li>
		<li th:text="${user3.name}">user3</li>
	</ul>
	
	<ul th:each = "user : ${userlist}">
		<li th:text="${user.name}">user</li>
	</ul>
	
	<h3>Datos en tabla</h3>
	<table>
		<block th:each = "user : ${userlist}">
			<tr>
				<td th:text="${user.name}">user</td>
			</tr>
		</block>
	</table>

	<div id="msg"></div>
</body>
</html>
