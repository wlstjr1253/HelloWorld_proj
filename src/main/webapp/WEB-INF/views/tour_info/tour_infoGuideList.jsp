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
								<th>시청 일자</th>
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
					<div class="align-center">${pagingHtml}</div>
				  </c:if>
			</div>
		</div>
		</div>
	<!-- 중앙 컨텐츠 끝 -->