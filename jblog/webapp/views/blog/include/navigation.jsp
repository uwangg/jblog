<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="navigation">
	<h2>카테고리</h2>
	<ul>
		<c:set var="cnt" value="${fn:length(categoryList) }" />
		<c:forEach items="${categoryList }" var="vo" varStatus="status">
			<li><a href="${pageContext.request.contextPath}/blog/${blogVo.user_id }?category_no=${vo.no}">${vo.name }</a></li>
		</c:forEach>
	</ul>
</div>