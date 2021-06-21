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
<script
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"
	type="text/javascript"></script>
<script>
	$(function() {
		btn = $('#btn-checkemail');
		btn.click(function() {
			id = $('#id').val(); //변수로 만들 때 var사용
			if (id == "") {
				return;
			}
			$.ajax({
				url : "${pageContext.request.contextPath }/user/api/checkid?id=" + id,
				type : "get",
				dataType : "json",
				success : function(response) {
					console.log(response);

					if (response.result != "success") {
						console.error(response.message);
						return;
					}

					if (response.data) {
						alert("존재하는 아이디입니다. 다른 아이디를 사용하세요.");
						$("#id").val("");
						$("#id").focus();
						return;
					}

					$('#btn-checkemail').hide();
					$('#img-checkemail').show();
				}
			});
		});
	});
</script>
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

			<label class="block-label" for="blog-id">아이디</label>
			<!-- input
				id="blog-id" name="id" type="text" value="${userVo.id }"-->
			<form:input path="id"/>
			<input
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
