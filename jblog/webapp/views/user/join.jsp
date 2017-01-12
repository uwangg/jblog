<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">

<script type="text/javascript"
	src="/jblog/assets/js/jquery/jquery-1.9.0.js"></script>	
<script>
$(function() {
	$("#join-form").submit(function() {
		// id 중복 체크
		if($("#img-checkid").is(":visible") == false) {
			alert("id 중복 체크를 해주세요.");
			return false;
		}
		
		if($("#agree-prov").is(":checked") == false) {
			alert("서비스 약관에 동의해주세요.")
			return false;
		}
		
		return true;
	});
	$("#blog-id").change(function() {
		$("#btn-checkid").show();
		$("#img-checkid").hide();
	});
	$("#btn-checkid").click(function(){
		var id = $("#blog-id").val();
		if(id == "") {
			return;
		}
		$.ajax({
			url : "/jblog/user/checkid?id=" + id, // 요청 url
			type : "get", // 통신방식 get/post
			dataType : "json", // 수신 데이터 타입 xml,text,json등
			data : "", // post방식인 경우 
			success : function(response) { // 성공시 callback
				if (response.result != "success") {
					return;
				}
				if (response.data == false) {
					alert("이미 존재하는 아이디입니다. 다른 아이디를 사용해 주세요.");
					$("#blog-id").val("").focus();
					return;
				}
				// 사용 가능한 이메일
				$("#btn-checkid").hide();
				$("#img-checkid").show();
			},
			error : function(jqXHR, status, error) { // 실패시 callback
				console.error(status + ":" + error);
			}
		});
	});
});
</script>
</head>
<body>
	<div class="center-content">
		<c:import url="/views/include/menu.jsp"></c:import>
		<form class="join-form" id="join-form" method="post"
			action="${pageContext.request.contextPath }/user/join">
			
			<label class="block-label" for="name">이름</label> <input id="name"
				name="name" type="text" value="${vo.name }">
				<br />
			<spring:hasBindErrors name="userVo">
				<c:if test="${errors.hasFieldErrors('name') }">
					<strong style="color: red"> <c:set var="errorName"
							value="${errors.getFieldError( 'name' ).codes[0] }" /> <spring:message
							code="${errorName }"
							text="${errors.getFieldError( 'name' ).defaultMessage }">
						</spring:message>
					</strong>
				</c:if>
			</spring:hasBindErrors>

			<label class="block-label" for="blog-id">아이디</label> <input
				id="blog-id" name="id" type="text" value="${vo.id }"> <input
				id="btn-checkid" type="button" value="id 중복체크"> <img
				id="img-checkid" style="display: none;"
				src="${pageContext.request.contextPath}/assets/images/check.png">
				<br />
			<spring:hasBindErrors name="userVo">
				<c:if test="${errors.hasFieldErrors('id') }">
					<strong style="color: red"> <c:set var="errorId"
							value="${errors.getFieldError( 'id' ).codes[0] }" /> <spring:message
							code="${errorId }"
							text="${errors.getFieldError( 'id' ).defaultMessage }">
						</spring:message>
					</strong>
				</c:if>
			</spring:hasBindErrors>


			<label class="block-label" for="password">패스워드</label> <input
				id="password" name="password" type="password" />
				<br />
			<spring:hasBindErrors name="userVo">
				<c:if test="${errors.hasFieldErrors('password') }">
					<strong style="color: red"> <c:set var="errorPassword"
							value="${errors.getFieldError( 'password' ).codes[0] }" /> <spring:message
							code="${errorPassword }"
							text="${errors.getFieldError( 'password' ).defaultMessage }">
						</spring:message>
					</strong>
				</c:if>
			</spring:hasBindErrors>


			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form>
	</div>
</body>
</html>
