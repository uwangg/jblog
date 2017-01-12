<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	pageContext.setAttribute("newLine", "\r\n");
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/views/blog/include/header.jsp"></c:import>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${postVo.title }</h4>
					<p>
						${fn:replace(postVo.content, newLine, "<br>") }
					<p>
				</div>
				<ul class="blog-list">
					<c:set var="cnt" value="${fn:length(postList) }" />
						<c:forEach items="${postList }" var="vo" varStatus="status">
							<li><a href="${pageContext.request.contextPath}/blog/${blogVo.user_id }?category_no=${category_no }&post_no=${vo.no}">${vo.title }</a> <span>${vo.reg_date }</span>	</li>
						</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${blogVo.img }">
			</div>
		</div>

		<c:import url="/views/blog/include/navigation.jsp"></c:import>
		
		<c:import url="/views/blog/include/footer.jsp"></c:import>
	</div>
</body>
</html>