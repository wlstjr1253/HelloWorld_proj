<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/member.js"></script>
<!-- 중앙 컨텐츠 시작 -->
</div>
<div class="container table-list">
	<div class="row">
		<br><br>
		<h3 class="text-center">결제내역</h3>
		<br>
		
		<div class="col-xs-12">
			<c:if test="${count == 0}">
			<div class="align-center">결제내역이 없습니다.</div>
			</c:if>
			
			<c:if test="${count > 0}">
			<div class="table-responsive">
				<table class="table table-striped">
					<tr>
						<th>날짜</th>
						<th>결제 내용</th>
						<th>결제 금액</th>
						<th>결제 방식</th>
					</tr>
					<c:forEach var="pay" items="${memberPayHistory}">
					<tr>
						<td>${pay.ph_reg_dt}</td>
						<td>
							<c:if test="${pay.ph_knd==1}">항공권 구매</c:if>
							<c:if test="${pay.ph_knd==2}">호텔 예약</c:if>
							<c:if test="${pay.ph_knd==3}">투어 결제</c:if>
							<c:if test="${pay.ph_knd==4}">물품 대여</c:if>
						</td>
						<td><fmt:formatNumber value="${pay.ph_pay}" pattern="#,###,###,###원"/> </td>
						<td>
							<c:if test="${pay.ph_pay_type==0}">현금</c:if>
							<c:if test="${pay.ph_pay_type==1}">카드</c:if>
							<c:if test="${pay.ph_pay_type==2}">계좌이체</c:if>
						</td>
					</tr>
					</c:forEach>
				</table>
			</div>
			
			<div class="text-center col-xs-12 paging">${pagingHtml}</div>
			</c:if>
		</div>
	</div>
</div>
<!-- 중앙 컨텐츠 끝 -->