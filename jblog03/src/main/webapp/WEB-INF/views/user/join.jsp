<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<form:form modelAttribute="userVo" class="join-form" id="join-form" name="joinForm" method="post" action="${pageContext.request.contextPath }/user/join">
			<label class="block-label" for="name">이름</label> 
			<!-- input id="name"
				name="name" type="text" value="${userVo.name }"-->
			<form:input path="name"/>
			<!-- validation error check -->
			<p style="color: #f00; text-align: left; padding-left: 0">
				<form:errors path="name" />
			</p>

			<label class="block-label" for="blog-id">아이디</label> <input
				id="blog-id" name="id" type="text" value="${userVo.id }"> <input
				id="btn-checkemail" type="button" value="id 중복체크"> <img
				id="img-checkemail" style="display: none;"
				src="${pageContext.request.contextPath}/assets/images/check.png">
				
			<!-- validation error check -->
			<p style="color: #f00; text-align: left; padding-left: 0">
				<form:errors path="id" />
			</p>

			<label class="block-label" for="password">패스워드</label> <input
				id="password" name="password" type="password" value="${userVo.password }" />

			<!-- validation error check -->
			<p style="color: #f00; text-align: left; padding-left: 0">
				<form:errors path="password" />
			</p>

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form:form>
	</div>
</body>
</html>
