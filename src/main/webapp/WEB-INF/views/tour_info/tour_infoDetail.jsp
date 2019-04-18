<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ajax/tour_infoReply.js"></script>
</div>
<script type="text/javascript">
/* $("#modal").modal('show').css({
	'margin-top': function (){
		return -($this).height() /2);
	},
	'margin-left': function () {
		return -($(this).width() /2);
	}
}); */

</script>
<div class="container">
	<div class="row align-center">
		<h1>${tour_info.ti_nm}</h1>
		
		<hr size="1" width="100%">
		<c:if test="${fn:endsWith(tour_info.ti_img,'.jpg') ||
		            fn:endsWith(tour_info.ti_img,'.JPG') ||
		            fn:endsWith(tour_info.ti_img,'.gif') ||
		            fn:endsWith(tour_info.ti_img,'.GIF') ||
		            fn:endsWith(tour_info.ti_img,'.png') ||
		            fn:endsWith(tour_info.ti_img,'.PNG')}">
    	   <div class="align-center">
		      <img src="imageView.do?ti_id=${tour_info.ti_id}" style="max-width:600px;">        
          </div>		
		</c:if>
		<ul>
		    <li><b>투어 ID : ${tour_info.ti_id}</b></li>
			<li>1. 작성자 : ${tour_info.user_id}</li>
			<li>2. 작성일자 : ${tour_info.reg_date}</li>
			<li>3. 투어 경비 : ${tour_info.ti_pc}</li>
			<li>4. 시작일자 : ${tour_info.ti_start_day}</li>
			<li>5. 종료일자 : ${tour_info.ti_end_day}</li>
			<li>6. 최소인원 : ${tour_info.ti_min_pp}</li>
			<li>7. 최대인원 : ${tour_info.ti_max_pp}</li>
			<li>8. 픽업 장소 : ${tour_info.ti_pickup_place}</li>
			<li>9. 픽업 시간 : ${tour_info.ti_pickup_time}</li>
		</ul><br>
		<p>
		10.상세 내용<br>
			${tour_info.ti_content}
		</p>
		</div>
		<hr size="1" width="50%">
		<div class="btn btn-submit col-xs-4 align-right">       <!-- 작성자 일 경우만 보여짐 -->
			<c:if test="${!empty user_id && user_id == tour_info.user_id}">
				<input type="button" value="삭제"  onclick="location.href='delete.do?ti_id=${tour_info.ti_id}'">
				<input type="button" value="수정" onclick="location.href='update.do?ti_id=${tour_info.ti_id}'">
			</c:if>
			<input type="button" value="목록" onclick="location.href='list.do'">
		</div>
		<div class="btn-submit col-xs-12 align-right">       <!-- 일반 회원일 경우 -->
				 <%-- <input type="button" value="신청하기"  onclick="location.href='apply.do?ti_id=${tour_info.ti_id}'"> --%> 
		<%-- <c:if test="${!empty user_id && user_id != tour_info.user_id}">
           		<form action="apply.do" method="post" id="tour_apply" enctype="multipart/form-data">
           		       <input type="button" class="btn btn-primaty" value="신청"  onclick="location.href='apply.do?ti_id=${tour_info.ti_id}'">
           					     <input type="hidden" name="ti_id" value="${tour_info.ti_id}" id="ti_id">
				                 <input type="hidden" name="user_id" value="${user_id}" id="user_id">
           		</form>
		</c:if> --%>
		<br><br><br><br>
		     <!-- 모달 시작 -->
		     <c:if test="${!empty user_id && user_id != tour_info.user_id}">
		     <%-- <input type="hidden" name="ta_idx" value="${tour_info.ta_idx}" id="ta_idx"> --%>
           		      <c:if test="${tour_info.ti_state != 1}">
           		      <a href="#writeModal" class="btn btn-primary" data-toggle="modal">신청하기</a>
           		      </c:if>
           		      <c:if test="${tour_info.ti_state == 1}">
           		      <a href="#" class="btn btn-primary" data-toggle="modal">신청완료</a>
           		      </c:if>
           		      
           				<div class="modal fade" id="writeModal">
           					<div class="modal-dialog modal-lg">
           						<form action="apply.do" method="post" id="tour_apply" enctype="multipart/form-data">
           					      	<input type="hidden" name="ti_id" value="${tour_info.ti_id}" id="ti_id">
				                    <input type="hidden" name="user_id" value="${user_id}" id="user_id">
           							<div class="container modify">
           								<div class="modal-content">
           									<div class="modal-header">
           										<button type="button" class="close" data-dismiss="modal">x</button>
           										<!-- <h3>투어 신청하기</h3> -->
           									</div>
           									<div class="modal-body">
           									<div class="header-section">
	<div class="bg-opacity"></div>
           									<!-- 	<div class="room-booking ptb-80 white_bg"> -->
	<div class="container">
	<div class="welcome-section text-center ptb-110">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="breadcurbs-inner">
						<div class="breadcrubs">
							<h2>투어 신청</h2>
							<div class="breadcrubs-menu">
								<ul>
									<li><a href="<%-- ${pageContext.request.contextPath}/main/main.do --%>">Home<i class="mdi mdi-chevron-right"></i></a></li>
									<li>tour</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
		<div class="row">
			<div class="col-md-12">
				<!-- <div class="service-tab-desc text-left mt-60"> -->
					<div class="tab-content" style="width:95%">
						<div role="tabpanel" class="tab-pane active" id="booking">
							<div class="booking-info-deatils">
								<div class="single-room-details fix">
								   <div class="room-img">
		                            <img src="imageView.do?ti_id=${tour_info.ti_id}" width="182" height="182">        
                                   </div>
									<%-- <div class="room-img">
										<img src="${tour_info.ti_img}" alt="" width="182" height="182"><!-- ti_img -->
									</div> --%>
									<div class="single-room-details pl-50">
										<h3 class="s_room_title">${tour_info.ti_nm}</h3><!-- ti_nm -->
										<div class="room_price">
											<h4>신청 내역</h4>
											<h5>
												￦ <fmt:formatNumber value="${tour_info.ti_pc}" pattern="#,###"/><!-- tour_info.ti_pc -->
											</h5>
											<h6>
												장소 ${tour_info.ti_pickup_place} / 시간 ${tour_info.ti_pickup_time}
											</h6>
										</div>
									</div>
								</div>
								<div class="single-room-booking-form mt-60">
									<div class="booking_form_inner">
										<div class="single-form-part">
											<div class="date-to">
												<input class="date-picker" type="text" value="${tour_info.ti_start_day}" disabled><!-- 시작일자 -->
												<i class="mdi mdi-calendar-text"></i>
											</div>
										</div>
										<div class="single-form-part">
											<div class="date-to">
												<input type="text" class="date-picker" value="${tour_info.ti_end_day}" disabled><!-- 종료일자 -->
												<i class="mdi mdi-calendar-text"></i>
											</div>
										</div>
										
										<div class="single-form-part">
											<div class="submit-form">
												<button type="submit" class="next_1" >신청하기</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div> 
				<!-- 	</div> -->
				</div>
			</div>
		</div>
	</div>
</div>
           									</div>
           									<div class="modal-footer">
           									<!-- <div class="modal-footer">
           										<button class="btn" data-dismiss="modal">취소하기</button>
           										<button type="submit" class="btn htn-primary">신청하기</button><br><br>
           									</div> -->
           									</div>
           							</div>
           							</div>
           			    		</form>
           					</div>
           				</div>
           			</c:if> 
		</div>
		<%-- <c:if test="${!empty user_id && user_id != tour_info.user_id}">
           		      <a href="#writeModal" class="btn btn-primary" data-toggle="modal">신청하기</a>
           				<div class="modal fade" id="writeModal">
           					<div class="modal-dialog">
           						<form action="apply.do" method="post" id="tour_apply" enctype="multipart/form-data">
           					      	<input type="hidden" name="ti_id" value="${tour_info.ti_id}" id="ti_id">
				                    <input type="hidden" name="user_id" value="${user_id}" id="user_id">
           							<div class="container modify">
           								<div class="modal-content">
           									<div class="modal-header">
           										<button type="button" class="close" data-dismiss="modal">x</button>
           										<h3>투어 신청하기</h3>
           									</div>
           									<div class="modal-body">
           										<ul class="detailModal">
           											<li>
           											    <h3>하단의 정보를 확인하시고 신청 진행바랍니다.</h3>
           											    <label for="member_info">회원정보:${tour_info.user_id}</label><br>
           											    <label for="ti_nm">투어 명:${tour_info.ti_nm}</label>
           											    <label for="ti_start_day">이용시작일자: ${tour_info.ti_start_day}</label><br>
           											    <label for="ti_end_day">이용마감일자:${tour_info.ti_end_day}</label><br>
           											    <label for="ti_pc">투어 가격:${tour_info.ti_pc}</label>
           											</li>
           										</ul>
           									</div>
           									<div class="modal-footer">
           										<button class="btn" data-dismiss="modal">취소하기</button>
           										<button type="submit" class="btn htn-primary">신청하기</button><br><br>
           									</div>
           							</div>
           							</div>
           						</form>
           					</div>
           				</div>
		</c:if> --%>
		<br><br><br><br><br><br>
		     <!-- 모달 끝 -->
		</div>
	<div id="reply_div">
			<!-- <span class="reply-title">댓글 달기</span> -->
			<span class="btn-submit col-xs-6">가이드 관련 코멘트</span>
			<form id="re_form">
				<input type="hidden" name="ti_id" value="${tour_info.ti_id}" id="ti_id">
				<input type="hidden" name="user_id" value="${user_id}" id="user_id">
				<textarea rows="3" cols="50" name="tr_content" id="tr_content" class="rep-content"
				<c:if test="${empty user_id}">disabled="disabled"</c:if>
				><c:if test="${empty user_id}">로그인해야 작성할 수 있습니다.</c:if></textarea>
				<c:if test="${!empty user_id}">
				<div id="re_first">
					<span class="letter-count">200/200</span>
				</div>
				<div id="re_second" class="align-right ">
					<input type="submit" value="전송">
				</div>
				</c:if>
			</form>
		</div>
		<!-- 댓글 목록 출력 -->
		<div id="output"></div>
		<div class="paging-button" style="display:none;">
			<input type="button" value="다음글 보기">
		</div>
		<div id="loading" style="display:none;">
			<img src="${pageContext.request.contextPath}/resources/images/ajax-loader.gif">
		</div>
