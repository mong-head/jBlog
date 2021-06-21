<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<div id="header">
			<h1>${authBlogVo.title }</h1>
			<ul>
				<li><a href="${pageContext.request.contextPath}/">홈</a></li>
				<c:if test="${empty authUser}">
					<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
				</c:if>
				<c:if test="${not empty authUser }">
					<li><a href="${pageContext.request.contextPath}/${authUser.id }">내블로그</a></li>
					<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
				</c:if>
				<c:if test='${authBlogVo.id == authUser.id }'>
					<li><a href="${pageContext.request.contextPath}/${authUser.id }/admin/basic">블로그 관리</a></li>
				</c:if>
			</ul>
		</div>