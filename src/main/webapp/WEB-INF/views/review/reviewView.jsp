<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
</script>
	<div class="welcome-section text-center ptb-110">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="breadcurbs-inner">
						<div class="breadcrubs"> 
							<h2>여행후기 상세보기</h2>
							<div class="breadcrubs-menu">
								<ul>
									<li><a href="${pageContext.request.contextPath}/main/main.do">Home<i class="mdi mdi-chevron-right"></i></a></li>
									<li>Review</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 중앙 컨텐츠 시작 -->
<div class="container">
	<div class="row">
	    <br><br>
		<h1 style="color:#000;">${review.tr_title}</h1>
		<ul>
			<li>번호 : ${review.tr_idx}</li>
			<li>작성자 : ${review.user_id}</li>
			<li>조회수 : ${review.tr_hit}</li>
			<li>등록일 : ${review.tr_reg_date}</li>
		</ul>
		<hr size="1" width="100%">
		<div class="align-center">
			<img src="imageView.do?tr_idx=${review.tr_idx}"
								  style="max-width:500px;">
		</div>			  
		<p>
			${review.tr_content}
		</p>
		<hr size="1" width="100%">
		<div align="right">
			 <c:if test="${!empty user_id && user_id == review.user_id}">
			<a class="button border_radius little button-black mb-20" href="update.do?tr_idx=${review.tr_idx}"><span>수정</span></a>
			<a class="button border_radius little button-black mb-20" href="delete.do?tr_idx=${review.tr_idx}"><span>삭제</span></a>
			</c:if>
			<a class="button border_radius little button-black mb-20" href="list.do"><span>목록</span></a>
		</div>
	</div>
</div>
<!-- 중앙 컨텐츠 끝 -->