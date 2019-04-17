<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<div class="welcome-section text-center ptb-110">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="breadcurbs-inner">
						<div class="breadcrubs">
							<h2>주문내역</h2>
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
		<div class="table-responsive col-xs-4 col-xs-offset-4">
		<c:if test="${count == 0}">
						<table class="table table-striped pull-right">
						<tr>
						<th class="no">
						주문한 상품이 없습니다.
						</th>
						</tr>
						</table>
						
						<div class="explore">
							<a href="${pageContext.request.contextPath}/main/main.do">메인으로</a>
						</div>
						
					</c:if>
		<c:if test="${count>0}">
		<table class="table table-striped pull-right">
		<tr>
			<th>주문번호</th>
			<td>${item.detail_num}
			</td>
		<tr>
			<th>주문상품</th>
			<td>${item.item_nm}
			</td>
		</tr>
		<tr>
			<th>대여기간</th>
			<td>${item.i_rent_day}~${item.i_return_day}</td>
		</tr>
		<tr>
			<th>수령공항/반납공항</th>
			<td>${item.i_rent_nc} / ${item.i_return_nc}</td>
		</tr>
		<tr>
			<th>수량</th>
			<td>${${item.order_quan}}</td>
		</tr>
		<%-- <tr>
			<th>마일리지</th>
			<td>${member.user_mil}</td>
		</tr> --%>
		</table>
		</c:if>
		
		
		
		</div>
		<div class="col-xs-12 btn-submit margin-bottom">
			<c:if test="${user_auth==member.user_auth }">
			<input type="button" value="수정" class="btn btn-ok" onclick="location.href='update.do'"/>
			<input type="button" value="삭제" class="btn btn-default" onclick="location.href='delete.do'"/>
			</c:if>
		</div>
	</div>
</div>
<!-- 중앙 컨텐츠 끝 -->