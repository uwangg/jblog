<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
			<div id="content" class="full-screen">
				<c:import url="/views/blog/include/admin-menu.jsp"></c:import>
				<form action="/jblog/blog/${blogVo.user_id }/admin/write" method="post">
			      	<table class="admin-cat-write">
			      		<tr>
			      			<td class="t">제목</td>
			      			<td>
			      				<input type="text" size="60" name="title" value="${postVo.title}">
				      			<select name="category_no">
				      				<c:set var="cnt" value="${fn:length(categoryList) }"/>
										<c:forEach items="${categoryList }" var="vo" varStatus="status">
				      						<option value="${vo.no }">${vo.name }</option>
				      					</c:forEach>
				      			</select>
				      			<br/>
								<spring:hasBindErrors name="postVo">
									<c:if test="${errors.hasFieldErrors('title') }">
										<strong style="color: red"> <c:set var="errorTitle"
											value="${errors.getFieldError( 'title' ).codes[0] }" /> <spring:message
											code="${errorTitle }"
											text="${errors.getFieldError( 'title' ).defaultMessage }">
											</spring:message>
										</strong>
									</c:if>
								</spring:hasBindErrors>
				      		</td>
			      		</tr>
			      		
			      		<tr>
			      			<td class="t">내용</td>
			      			<td><textarea name="content">${postVo.content}</textarea>
			      			<br/>
			      			<spring:hasBindErrors name="postVo">
								<c:if test="${errors.hasFieldErrors('content') }">
									<strong style="color: red"> <c:set var="errorContent"
										value="${errors.getFieldError( 'content' ).codes[0] }" /> <spring:message
										code="${errorContent }"
										text="${errors.getFieldError( 'content' ).defaultMessage }">
										</spring:message>
									</strong>
								</c:if>
							</spring:hasBindErrors>
			      			</td>
			      		</tr>
			      		<tr>
			      			<td>&nbsp;</td>
			      			<td class="s"><input type="submit" value="포스트하기"></td>
			      		</tr>
			      	</table>
				</form>
			</div>
		</div>
		
		<c:import url="/views/blog/include/footer.jsp"></c:import>
	</div>
</body>
</html>