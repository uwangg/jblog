<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="header">
	<a href="${pageContext.request.contextPath}/blog/${blogVo.user_id}">
		<h1>${blogVo.title }</h1>
	</a>
	<ul>
		<c:choose>
			<c:when test="${blogVo.user_id == authUser.id }">
				<li><a href="${pageContext.request.contextPath}/blog/${blogVo.user_id}/admin">블로그
						관리</a></li>
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
			</c:when>
			<c:when test="${authUser == null }">
				<li><a href="${pageContext.request.contextPath}/user/loginform">로그인</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>