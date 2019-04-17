<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<div class="welcome-section">
	    <div class="container">
	        <div class="row">
	            <div class="col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3">
	                <div class="booking-box">
	                    <!-- S: flight-srch -->
	                    <div class="flight-srch">
	                        <div class="booking-title">
	                            <h3>투어 검색</h3>
	                            <p>원하는 조건으로 투어를 검색하세요</p>
	                        </div>
	                        <div class="booking-form">
	                            <form action="list.do" id="search_form" method="get">
	                                <div class="travel-city mb-15">
	                                    <input type="text" name="keyword" placeholder="키워드">
	                                </div>
	                                <div class="row">
	                                    <div class="b-date arrive mb-15 col-md-12">
	                                        <input class="date-picker" type="text" name="keyword2" placeholder="출발일">
	                                        <i class="mdi mdi-calendar-text"></i>
	                                    </div>
	                                </div>
	                                <div class="travel-city mb-15">
	                                    <input type="text" name="keyword3" placeholder="작성자">
	                                </div>
	                                <div class="submit-form">
	                                    <button type="submit">검색하기</button>
	                                </div>
	                            </form>
	                        </div>
	                    </div>
	                    <!-- E: flight-srch -->
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
				<!-- <div class=welcome-text> -->
				<c:if test="${count == 0}">
					<div class="align-center">등록된 투어 게시물이 없습니다.</div>
				</c:if>
				<c:if test="${count > 0}">
					<div class="table">
						<table class="table">
						<!-- <table class="table" style="width: 600px"> -->
							<tr>
								<th>투어 ID</th>
								<th width="250">투어 명</th>
								<th>작성자</th>
								<th>시작 일자</th>
								<th>비고</th>
							</tr>
							<c:forEach var="tour_info" items="${list}">
								<tr>
									<td>${tour_info.ti_id}</td>
									<td><a href="detail.do?ti_id=${tour_info.ti_id}">${tour_info.ti_nm}
											(${tour_info.re_cnt})</a></td>
									<td>${tour_info.user_id}</td>
									<td>${tour_info.ti_start_day}</td>
									<td><a class="button border_radius little button-black mb-10" href="adminDelete.do?ti_id=${tour_info.ti_id}"><span>삭제</span></a></td>
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