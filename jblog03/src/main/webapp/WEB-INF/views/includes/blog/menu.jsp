<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
				<ul class="admin-menu">
					<li <c:if test='${"basic" eq menu }'>class="selected"</c:if>><a href="${pageContext.request.contextPath}/${authUser.id }/admin/basic">기본설정</a></li>
					<li <c:if test='${"category" eq menu }'>class="selected"</c:if>><a href="${pageContext.request.contextPath}/${authUser.id }/admin/category">카테고리</a></li>
					<li <c:if test='${"write" eq menu }'>class="selected"</c:if>><a href="${pageContext.request.contextPath}/${authUser.id }/admin/write">글작성</a></li>
				</ul>