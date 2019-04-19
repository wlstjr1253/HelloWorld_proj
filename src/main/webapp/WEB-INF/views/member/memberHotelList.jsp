<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/member.js"></script>
	<div class="welcome-section text-center ptb-110">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="breadcurbs-inner">
						<div class="breadcrubs">
							<h2>호텔 예약 내역</h2>
							<div class="breadcrubs-menu">
								<ul>
									<li><a href="${pageContext.request.contextPath}/main/main.do">Home<i class="mdi mdi-chevron-right"></i></a></li>
									<li>호텔 예약 내역</li>
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
<div class="container table-list">
	<div class="row">
		<div class="col-xs-12">
			<c:if test="${count == 0}">
			<div class="align-center">호텔 예약 내역이 없습니다.</div>
			</c:if>
			
			<c:if test="${count > 0}">
			<div class="table-responsive">
				<table class="table table-striped">
					<tr>
						<th>호텔 이름</th>
						<th>체크인</th>
						<th>체크 아웃</th>
						<th>상세 주소</th>
						<th>예약자</th>
						<th>결제 금액</th>
					</tr>
					<c:forEach var="hotel" items="${memberHotelList}">
					<tr>
						<td>${hotel.st_nm}</td>
						<td>${hotel.srl_check_in_dt }</td>
						<td>${hotel.srl_check_out_dt }</td>
						<td>${hotel.st_addr }</td>
						<td>${hotel.srl_nm }</td>
						<td><fmt:formatNumber value="${hotel.srl_total_pc}" pattern="#,###,###,###원"/> </td>
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