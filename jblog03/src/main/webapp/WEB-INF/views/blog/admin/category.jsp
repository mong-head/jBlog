<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath }/ejs/ejs.js"
	type="text/javascript"></script>
<script>
	var listEJS = new EJS({
		url : "${pageContext.request.contextPath }/ejs/list-template.ejs"
	})

	var listItemEJS = new EJS({
		url : "${pageContext.request.contextPath }/ejs/listitem-template.ejs"
	})

	var messageBox = function(type, message, callback) {

		if (type == 'error') {
			$("#dialog-message p").text(message);
			$("#dialog-message").dialog({
				modal : true,
				buttons : {
					"확인" : function() {
						// 확인 버튼 누르면 callback
						$(this).dialog("close");
					}
				},
				close : callback
			});
		}
	}

	$(function() {

		// list load
		$
				.ajax({
					url : "${pageContext.request.contextPath }/${authUser.id }/api/admin/category/",
					dataType : "json", // 받을 때 format
					type : "get", // 요청 method
					success : function(response) {
						response.authUserId = "${authUser.id }";
						var html = listEJS.render(response);
						$(".admin-cat tr:last").after(html);
					}
				});

		$("#add-form")
				.submit(
						function(event) {
							event.preventDefault(); // 막기

							vo = {}
							// validation
							if ($("#input-name").val() == "") {
								messageBox("error", "카테고리명이 비어있습니다.", $(
										"#input-name").focus());
								return;
							}

							if ($("#input-description").val() == "") {
								messageBox("error", "설명이 비어있습니다.", $(
										"#input-description").focus());
								return;
							}

							vo.name = $("#input-name").val();
							vo.description = $("#input-description").val();

							// data 등록
							$
									.ajax({
										url : "${pageContext.request.contextPath }/${authUser.id }/api/admin/category/",
										dataType : "json", // 받을 때 format
										type : "post", // 요청 method
										contentType : "application/json",
										data : JSON.stringify(vo),
										success : function(response) {
											// 번호 하나씩 올리기
											let no = $(
													".admin-cat tr td:first-child")
													.text();

											var len = $(".admin-cat tr").length;
											for(var i=1;i<=len;i++){
												console.log($(".admin-cat tr:nth-child("+i+") td:first").text(len-i+2))
											}
											// rendering code
											response.data.authUserId = "${authUser.id }";
											html = listItemEJS
													.render(response.data);
											$(".admin-cat tr:last").after(html);

											// 내용 지우기 ( form reset )
											$("#add-form")[0].reset();
										},
										fail : function(response) {
											console.log(response);
										}
									});
						});

		// live event : 존재하지 않는 element의 event handler를 미리 등록
		// delegation (위임) : document에게 위임시킬거임
		$(document).on("click", ".admin-cat td a", function() {
			event.preventDefault();
			
			console.log($(this));
			
			let no = $(this).data("no");
			$("#hidden-no").val(no);

			deleteDialog.dialog("open");
		})

		// delete dialog
		const deleteDialog = $("#dialog-delete-form")
				.dialog(
						{
							autoOpen : false, // 바로 자동으로 뜨지 마라
							width : 300,
							height : 220,
							modal : true, // 뒤에 클릭되지 않게
							buttons : {
								"삭제" : function() {
									const no = $("#hidden-no").val();
									$
											.ajax({
												url : "${pageContext.request.contextPath }/${authUser.id }/api/admin/category/",
												dataType : "json", // 받을 때 format
												type : "delete", // 요청 method
												data : "no="+no,
												success : function(response) {
													$(
															".admin-cat tr[data-no="
																	+ response.data
																	+ "]")
															.remove();
													
													var len = $(".admin-cat tr").length;
													for(var i=1;i<=len;i++){
														console.log($(".admin-cat tr:nth-child("+i+") td:first").text(len-i+1))
													}
													deleteDialog
															.dialog("close");
												}
											});
								},
								"취소" : function() {
									$(this).dialog("close");
								}
							},
							close : function() {
								// 1. password 비우기
								$("#password-delete").val("");
								// 2. no 비우기
								$("#hidden-no").val("");
								// 3. error message 숨기기
								$(".validateTips.error").hide();
								//console.log("dialog form data 정리작업")
							}
						})

	})
</script>
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog/header.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/blog/menu.jsp" />
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
				<form action="" method="post" id="add-form">
					<table id="admin-cat-add">
						<tr>
							<td class="t">카테고리명</td>
							<td><input type="text" name="name" id="input-name"></td>
						</tr>
						<tr>
							<td class="t">설명</td>
							<td><input type="text" name="description"
								id="input-description"></td>
						</tr>
						<tr>
							<td class="s">&nbsp;</td>
							<td><input type="submit" value="카테고리 추가"></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div id="dialog-delete-form" title="카테고리 삭제" style="display: none">
			<p class="validateTips normal">카테고리를 삭제하시겠습니까?</p>
			<form>
				<input type="hidden" id="hidden-no" value=""> 
			</form>
		</div>
		<div id="dialog-message" title="알림" style="display: none">
			<p></p>
		</div>
		<c:import url="/WEB-INF/views/includes/blog/footer.jsp" />
	</div>
</body>
</html>