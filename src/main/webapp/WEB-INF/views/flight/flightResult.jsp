<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/flightResult.js"></script>
	<!--Welcome secton-->
	<div class="welcome-section text-center ptb-110">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="breadcurbs-inner">
						<div class="breadcrubs">
							<h2>항공권 예약</h2>
							<div class="breadcrubs-menu">
								<ul>
									<li><a href="${pageContext.request.contextPath}/main/main.do">Home<i class="mdi mdi-chevron-right"></i></a></li>
									<li>flight</li>
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
<!--항공권 booking start-->
<div class="room-booking ptb-80 white_bg">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="section-title mb-80 text-center">
					<h2>
						<span>항공권</span> 예약
					</h2>
					<p></p>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="booking-rooms-tab" style="width: 100%;">
					<ul class="nav" role="tablist" style="width: 100%;">
						<li class="active">
							<a>
							<span class="tab-border">1</span>
							<span>예약 정보</span>
							</a>
						</li>
						<li>
							<a>
							<span class="tab-border">2</span>
							<span>예약자 정보</span>
							</a>
						</li>
						<li>
							<a>
							<span class="tab-border">3</span>
							<span>결제 정보</span>
							</a>
						</li>
						<li class="col-md-3">
							<a href="#done" data-toggle="tab" id="page_4">
								<span class="tab-border">3</span>
								<span>예약 완료</span>
							</a>
						</li>
					</ul>
				</div>
				<div class="service-tab-desc text-left mt-60">
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane" id="done">
							<div class="booking-done">
								<div class="booking-done-table table-responsive text-center">
									<table class="table">
										<tr>
											<td>
												<p>
													성인 <span><fmt:formatNumber value="${flightCommand.fsi_fir_pc}" pattern="#,###"/> 원</span>
												</p>
											</td>
											<td>
												<p>
													${rsrv.fr_adult_pp} 명 <span><fmt:formatNumber value="${flightCommand.fsi_fir_pc * rsrv.fr_adult_pp}" pattern="#,###"/> 원</span>
												</p>
											</td>
											
										</tr>
										
									</table>
								</div>
								<div class="booking-done-description">
									<p>예약 내역은 회원상세정보에서 확인하실 수 있습니다.</p>
									<div class="succesfully">
										<strong>예약이 성공적으로 완료되었습니다.</strong>
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
