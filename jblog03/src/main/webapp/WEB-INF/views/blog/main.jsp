<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
pageContext.setAttribute("newline", "\n");
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog/header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<c:choose>
						<c:when test='${empty postVo }'>
							<p>포스트 내용이 없습니다.
							<p>
						</c:when>
						<c:otherwise>
							<h4>${postVo.title }</h4>
							<p>${fn:replace(postVo.contents ,newline,"<br/>")}
						</c:otherwise>
					</c:choose>
					<p>
				</div>
				<ul class="blog-list">
					<c:forEach items="${postList }" var="postVo" varStatus="status">
						<li><a href="${pageContext.request.contextPath}/${id}/${postVo.categoryNo }/${postVo.no }">
							${postVo.title }</a>
							<span>${postVo.regDate }</span>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${authBlogVo.logo }">
			</div>
		</div>

		<c:import url="/WEB-INF/views/includes/blog/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/blog/footer.jsp" />

	</div>
</body>
</html>