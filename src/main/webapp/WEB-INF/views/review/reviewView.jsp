<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="welcome-section text-center ptb-110">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="breadcurbs-inner">
						<div class="breadcrubs">
							<h2>여행후기 상세보기</h2>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<!-- 중앙 컨텐츠 시작 -->
<div class="container write-form">
	<div class="row ">
		<!-- <h2 class="text-center margin-top">회원상세정보</h2> -->
		<div class="table-responsive col-xs-8 col-xs-offset-2">
		<table class="table table-striped pull-right">
		<tr>
			<th>번호</th>
			<td>${review.tr_idx}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${review.user_id}</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${review.tr_hit}</td>
		</tr>
		<tr>
			<th>등록일</th>
			<td>${review.tr_reg_date}</td>
		</tr>
		</table>
		<hr size="1" width="100%">
		<div align="center">
			<img src="imageView.do?tr_idx=${review.tr_idx}"
								  style="max-width:500px;">
		</div>			  
		<p style="color:white;">
			${review.tr_content}
		</p>
		</div>
		<div class="col-xs-12 btn-submit margin-bottom">
			 <c:if test="${!empty user_id && user_id == review.user_id}">
			<a class="button border_radius little button-black mb-20" href="update.do?tr_idx=${review.tr_idx}"><span>수정</span></a>
			<a class="button border_radius little button-black mb-20" href="delete.do?tr_idx=${review.tr_idx}"><span>삭제</span></a>
			</c:if>
			<a class="button border_radius little button-black mb-20" href="list.do"><span>목록</span></a>
			</div>
	</div>
</div>
<!-- 중앙 컨텐츠 끝 -->