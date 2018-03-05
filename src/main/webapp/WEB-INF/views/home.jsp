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
			code="home.label.english" /></a> |
	<a href="/ssmvcp3?language=es"><spring:message
			code="home.label.spanish" /></a>

	<P>
		<spring:message code="home.label.time" />
		${serverTime}.
	</P>
	<div id="msg"></div>
</body>
</html>
