<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- <script type="text/javascript">
	var result = '${result}';
	if(result == 'success'){
		alert('처리가 완료되었습니다.');
	}
</script> -->
<!-- 목록 시작 -->
<div class="welcome-section text-center ptb-110">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="breadcurbs-inner">
						<div class="breadcrubs">
							<h2>투어 신청자 목록</h2>
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
			<div class="col-xs-12">
				<!-- <div class=welcome-text> -->
				<c:if test="${count == 0}">
					<div class="align-center">등록한 투어 게시물이 없습니다.</div>
				</c:if>
				<c:if test="${count > 0}">
					<div class="table">
						<table class="table">
						<!-- <table class="table" style="width: 600px"> -->
							<tr>
								<th>투어 ID</th>
								<th width="250">투어 명</th>
								<th>신청자</th>
								<th>신청 일자</th>
								<!-- <th>조회수</th> -->
							</tr>
							<c:forEach var="tour_info" items="${list}">
								<tr>
								
									<td>${tour_info.ti_id}</td>
									<td><a href="detail.do?ti_id=${tour_info.ti_id}">${tour_info.ti_nm}</a></td>
									<td>${tour_info.user_id}</td>
									<td>${tour_info.ti_start_day}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
					<div class="col-xs-offset-4">
			<form action="GuideList.do" id="search_form" method="get">
				<select name="keyfield" class="col-xs-2" >
       				<option value="user_id">신청자</option>
					<option value="ti_id">투어 ID</option>
					<option value="ta_idx">신청번호</option>
					<option value="all">전체</option>
				</select>
				<input type="text" name="keyword" id="keyword" class="col-xs-2" >
				<input type="submit" value="찾기" class="col-xs-1" >
				<input type="button" value="목록" class="col-xs-1"
				onclick="location.href='GuideList.do'">
			</form>
			</div>
					<div class="text-center col-xs-12 paging">${pagingHtml}</div>
				  </c:if>
			</div>
		</div>
		</div>
	<!-- 중앙 컨텐츠 끝 -->