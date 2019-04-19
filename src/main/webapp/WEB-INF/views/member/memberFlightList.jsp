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
							<h2>항공권 예약 내역</h2>
							<div class="breadcrubs-menu">
								<ul>
									<li><a href="${pageContext.request.contextPath}/main/main.do">Home<i class="mdi mdi-chevron-right"></i></a></li>
									<li>항공권 예약 내역</li>
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
			<div class="text-center margin-top margin-bottom"><h1 class="text-black">항공권 예약 내역이 없습니다.</h1></div>
			</c:if>
			
			<c:if test="${count > 0}">
			<div class="table-responsive">
				<table class="table table-striped">
					<tr>
						<th>출발지</th>
						<th>경유지</th>
						<th>경유지2</th>
						<th>도착지</th>
						<th>출발 예정 시간</th>
						<th>도착 예정 시간</th>
						<th>좌석 등급</th>
						<th>항공사</th>
						<th>결제금액</th>
					</tr>
					<c:forEach var="flight" items="${memberFlightList}">
					<tr>
						<td>${flight.fsi_start_city}</td>
						<td>${flight.fsi_pass1_city}</td>
						<td>${flight.fsi_pass2_city}</td>
						<td>${flight.fsi_arrive_city}</td>
						<td>${flight.fsi_start_dt }</td>
						<td>${flight.fsi_arrive_dt }</td>
						<td>${flight.fr_rsrv_seat_type }</td>
						<td>${flight.fi_nm }</td>
						<td>${flight.fr_total_pc }</td>
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