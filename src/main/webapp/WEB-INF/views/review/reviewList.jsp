<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/board.js"></script>
<script type="text/javascript">
	var result = '${result}';
	if (result == 'success') {
		alert('처리가 완료 되었습니다.');
		location.reload();
	}
</script>
	<div class="welcome-section text-center ptb-110">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="breadcurbs-inner">
						<div class="breadcrubs"> 
							<h2>여행 후기</h2>
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
<div class="container table-list">
	<div class="row">
		<br><br>
		<br>
		
		<div class="col-xs-offset-4">
			<form action="list.do" id="search_form" method="get">
				<select name="keyfield" class="col-xs-2">
					<option value="tr_title">제목</option>
					<option value="user_id">ID</option>
					<option value="tr_content">내용</option>
					<option value="all">전체</option>
				</select>
				<input type="text" name="keyword" id="keyword" class="col-xs-2" >
				<input type="submit" value="찾기" class="col-xs-1" >
				<input type="button" value="목록" class="col-xs-1"
				onclick="location.href='list.do'">
			</form>
		</div>
		<div align="right">
			<c:if test="${!empty user_id}">
				<a class="button border_radius little button-black mb-20" href="write.do"><span>글쓰기</span></a>
			</c:if>
		</div>
		
		<div class="col-xs-12">
			<c:if test="${count == 0}">
			<div class="align-center">등록된 회원이 없습니다.</div>
			</c:if>
			
			<c:if test="${count > 0}">
			<div class="table-responsive">
				<table class="table table-striped">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>ID</th>
						<th>등록날짜</th>
						<th>조회수</th>
					</tr>
					<c:forEach var="review" items="${list}">
					<tr>
						<td>${review.tr_idx}</td>
						<td><a href="detail.do?tr_idx=${review.tr_idx}">${review.tr_title}</a></td>
						<td>${review.user_id}</td>
						<td>${review.tr_reg_date}</td>
						<td>${review.tr_hit}</td>
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