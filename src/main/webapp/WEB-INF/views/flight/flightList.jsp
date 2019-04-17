<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/flightList.js"></script>
<!--Welcome secton-->
	<jsp:include page="../main/main_header.jsp" />
</div>
<!--Header section end-->
<div class="our-news text-center ptb-80 white_bg">
	<div class="container table-list">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="section-title mb-80">
						<h2>
							Flight<span>List</span> 
						</h2>
						
						<c:if test="${fCount == 0}">
						<div class="align-center">조회된 항공권이 없습니다.</div>
						</c:if>
						<c:if test="${fCount > 0}">
						<div class="table-responsive margin-top-50">
							<table class="table table-striped"> 
								<tr>
									<th>로고</th>
									<th>항공사</th>
									<th>출발지</th>
									<th>도착지</th>
									<th>경유지1</th>
									
									<th>출발시간</th>
									<th>도착시간</th>
									<th>경유1시간</th>
									<th></th>
									
									
								</tr>
								<c:forEach var="flight" items="${fList}">
								<tr>
									<c:if test="${fn:endsWith(flight.fi_logo,'.jpg') ||
									              fn:endsWith(flight.fi_logo,'.JPG') ||
									              fn:endsWith(flight.fi_logo,'.gif') ||
									              fn:endsWith(flight.fi_logo,'.GIF') ||
									              fn:endsWith(flight.fi_logo,'.png') ||
									              fn:endsWith(flight.fi_logo,'.PNG')}">
									<td><img src="${pageContext.request.contextPath}/resources/images/logo/${flight.fi_logo}" style="max-width:40px;"></td>
									</c:if>
									<td>${flight.fi_nm}</td>
									<td>${flight.fsi_start_city}</td>
									<td>${flight.fsi_arrive_city}</td>
									<td>${flight.fsi_pass1_city}</td>
									
									<td>${flight.fsi_start_dt}</td>
									<td>${flight.fsi_arrive_dt}</td>
									<td>${flight.fsi_pass1_dt}</td>
									
									<td><input type="button" value="예약"
					    										onclick="location.href='flightRsrv.do?fsi_idx=${flight.fsi_idx}'"></td>
								</tr>
								</c:forEach>
							</table>
						</div>
						
						<div class="col-xs-offset-4">
						<form action="list.do" id="search_form" method="get" class="form">	
							<select name="keyfield" class="col-xs-2 search" >
								<option value="fi_nm">항공사</option>
								<option value="fsi_start_city">출발</option>
								<option value="fsi_arrive_city">도착</option>
								<option value="all">전체</option>
							</select>
							<input type="text" name="keyword" id="keyword" class="col-xs-2 search" >
							<input type="submit" value="찾기" class="col-xs-1 search" > 
							<input type="button" value="목록" class="col-xs-1 search" onclick="location.href='list.do'">
						</form>
						</div>
						
						<div class="text-center col-xs-12 paging">${pagingHtml}</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>




















