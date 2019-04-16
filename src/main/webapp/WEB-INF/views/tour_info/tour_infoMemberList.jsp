<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 중앙 컨텐츠 시작 -->
	<div class="welcome-section text-center ptb-110">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="breadcurbs-inner">
						<div class="breadcrubs">
							<h2>투어 신청 목록</h2>
							<div class="breadcrubs-menu">
								<ul>
									<li><a href="${pageContext.request.contextPath}/main/main.do">Home<i class="mdi mdi-chevron-right"></i></a></li>
									<li>tour</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="container table-list">
	<div class="row">
		<br><br>
		<br> 
		
		<div class="col-xs-12">
			<c:if test="${count == 0}">
			<div class="align-center">투어에 신청한 기록이 없습니다.</div>
			</c:if>
			
			<c:if test="${count > 0}">
			<div class="table-responsive">
				<table class="table table-striped">
					<tr>
						<th>번호</th>
						<th>투어명</th>
						<th>투어 ID</th>
						<th>신청일자</th>
						<th>비고</th>
					</tr>
					<c:forEach var="tour_info" items="${list}">
					<tr>
						<td>
						   ${tour_info.ta_idx}
						</td>
						<td>
							<a href="detail.do?ti_id=${tour_info.ti_id}">${tour_info.ti_nm}</a>
						</td>
						<td>${tour_info.ti_id}</td>
						<td>${tour_info.ti_reg_dt}</td>
						<td>
						   <a class="button border_radius little button-black mb-10" href="applyDelete.do?ta_idx=${tour_info.ta_idx}"><span>취소</span></a>
						</td>
					</tr>
					</c:forEach>
				</table>
			</div>
			
			<div class="col-xs-offset-4">
			<form action="applyList.do" id="search_form" method="get">
				<select name="keyfield" class="col-xs-2" >
       				<option value="ti_id">투어 ID</option>
					<option value="user_id">회원 ID</option>
					<option value="ti_reg_dt">신청일자</option>
					<option value="all">전체</option>
				</select>
				<input type="text" name="keyword" id="keyword" class="col-xs-2" >
				<input type="submit" value="찾기" class="col-xs-1" >
				<input type="button" value="목록" class="col-xs-1"
				onclick="location.href='applyList.do'">
			</form>
			</div>
			
			<div class="text-center col-xs-12 paging">${pagingHtml}</div>
			</c:if>
		</div>
	</div>
</div>
<!-- 중앙 컨텐츠 끝 -->