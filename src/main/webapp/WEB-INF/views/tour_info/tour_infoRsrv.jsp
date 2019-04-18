<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<!--Welcome secton-->
	<div class="welcome-section text-center ptb-110">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="breadcurbs-inner">
						<div class="breadcrubs">
							<h2>Booking</h2>
							<div class="breadcrubs-menu">
								<ul>
									<li><a href="${pageContext.request.contextPath}/main/main.do">Home<i class="mdi mdi-chevron-right"></i></a></li>
									<li>hotel</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!--Header section end-->
<!--booking start-->
<div class="room-booking ptb-80 white_bg">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="section-title mb-80 text-center">
					<h2>
						<span>투어</span> 신청
					</h2>
					<p></p>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="booking-rooms-tab" style="width: 100%;">
					<ul class="nav" role="tablist" style="width: 100%;">
						<li class="active col-md-5">
							<a href="#booking" data-toggle="tab" id="move_1">
								<span class="tab-border">1</span>
								<span>투어 신청 정보 확인</span>
							</a>
						</li>
						<li class="col-md-6">
							<a>
								<span class="tab-border">2</span>
								<span>신청 완료</span>
							</a>
						</li>
					</ul>
				</div>
				<div class="service-tab-desc text-left mt-60">
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="booking">
							<div class="booking-info-deatils">
								<div class="single-room-details fix">
									<div class="room-img">
										<img src="${tour_info.ti_img}" alt="" width="182" height="182"><!-- ti_img -->
									</div>
									<div class="single-room-details pl-50">
										<h3 class="s_room_title">${tour_info.ti_nm}</h3><!-- ti_nm -->
										<div class="room_price">
											<h4>신청 내역</h4>
											<h5>
												￦ <fmt:formatNumber value="${tour_ti_pc}" pattern="#,###"/><!-- tour_info.ti_pc -->
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
												<input class="date-picker" type="text" value="${Tour_infoCommand.ti_start_day}" disabled><!-- 시작일자 -->
												<i class="mdi mdi-calendar-text"></i>
											</div>
										</div>
										<div class="single-form-part">
											<div class="date-to">
												<input type="text" class="date-picker" value="${Tour_infoCommand.ti_end_day}" disabled><!-- 종료일자 -->
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
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!--Room booking end-->
