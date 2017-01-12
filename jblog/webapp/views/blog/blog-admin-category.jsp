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
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script>
var index = 1;
var fetchList = function() {
	$.ajax({
		url : "/jblog/blog/${blogVo.user_id}/admin/category-list",
		type : "get",
		dataType : "json",
		data : "",
		success : function(response) {
			if (response.result != "success") {
				return;
			}
			if (response.data.length == 0) {
				return;
			}

			$.each(response.data, function(index, vo) {
				renderHtml(index, vo);
			})
			
			index = response.data.length;
		},
		error : function(xhr/*XMLHttpRequest*/, status, error) {
			console.error(status + ":" + error);
		}
	})
}
var renderHtml = function(index, vo) {
	//대신에 js template Library를 사용한다 ex)EJS, underscore.js
	var html = "<tr id='tr-"+vo.no+"' value="+vo.no+"><td>"+(index+1)+"</td>"
			+"<td>"+vo.name+"</td>"
			+"<td>"+vo.posting+"</td>"
			+"<td>"+vo.description+"</td>"
			+"<td><img class='btn-del' id="+vo.no+" src='${pageContext.request.contextPath}/assets/images/delete.jpg'></td>"
			+"</tr>";
			$(".admin-cat").append(html);
}
$(function() {
	$("#category-add").click(
		function() {
			var name = $("#name").val();
			var description = $("#description").val();
			$.ajax({
				url : "/jblog/blog/${blogVo.user_id}/admin/category-insert",
				type : "post",
				dataType : "json",
				data : "blog_no=${blogVo.no}&name="
						+name+"&description="
						+description,
				success : function(response) {
					if (response.result != "success") {
						return;
					}
					$("#name").val("");
					$("#description").val("");
					renderHtml(index, response.data);
					index++;
					},
				error : function(xhr, status, error) {
					console.log(status + ":" + error);
					}	
			})
		});
	$(document).on("click", ".btn-del",
			function(){
				var no = $(this).attr("id");
				console.log(no);
				$.ajax({
					url : "/jblog/blog/${blogVo.user_id}/admin/category-delete",
					type : "post",
					dataType : "json",
					data : "no=" + no,
					success : function(response) {
						if (response.result != "success") {
							return;
						}
						$("#tr-"+no).remove();
						index--;
					}
					});
		
	});
	fetchList();
});
</script>

</head>
<body>
	<div id="container">
		<c:import url="/views/blog/include/header.jsp"></c:import>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/views/blog/include/admin-menu.jsp"></c:import>
				<table class="admin-cat">
					<tr>
						<th>번호</th>
						<th>카테고리명</th>
						<th>포스트 수</th>
						<th>설명</th>
						<th>삭제</th>
					</tr>
				</table>

				<h4 class="n-c">새로운 카테고리 추가</h4>
				<table id="admin-cat-add">
					<tr>
						<td class="t">카테고리명</td>
						<td><input type="text" name="name" id="name"></td>
					</tr>
					<tr>
						<td class="t">설명</td>
						<td><input type="text" name="description" id="description"></td>
					</tr>
					<tr>
						<td class="s">&nbsp;</td>
						<td><input type="submit" id="category-add" value="카테고리 추가"></td>
					</tr>
				</table>
			</div>
		</div>

		<c:import url="/views/blog/include/footer.jsp"></c:import>

	</div>
</body>
</html>