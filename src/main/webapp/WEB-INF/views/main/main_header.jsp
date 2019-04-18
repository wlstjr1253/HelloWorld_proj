<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ajax/common/selectNationCity.js"></script>    
<div class="welcome-section">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-sm-6">
                <div class="booking-box">
                    <div class="btn-list row">
                        <a href="#" class="button border_radius little button-black col-md-6">항공권 검색</a>
                        <a href="#" class="button border_radius little button-black col-md-6">호텔 검색</a>
                    </div>
                    <!-- S: flight-srch -->
                    <div class="flight-srch">
                        <div class="booking-title">
                            <h3>항공권 검색</h3> 
                            <p>얼마없는 항공권을 검색해 보세요! 나오면 사고 없음 딴데 가보고</p>
                        </div>
                        <div class="booking-form">
                            <form action="${pageContext.request.contextPath}/flight/list.do" id="flight_reg_form" method="get">
                            	<!-- <ul style="width:200px;">
                            		<li style="float:left;"><label style="color:white;width:40px;margin-top:17px;">왕복</label></li>
                            		<li style="float:left;"><input type="radio" id="back" name="flight_type" style="width:20px;"></li>
                            		<li style="float:left;"><label style="color:white;width:40px;margin-top:17px;margin-left:10px;">편도</label></li>
                            		<li style="float:left;"><input type="radio" id="noback" name="flight_type" style="width:20px;"></li>
                            	</ul> -->
                                <div class="select-book mb-15" style="clear:left;">
	                                <select <%-- class="select-booking" --%> name="flight_s_nc" id="flight_s_nc">
				                        <option value="ALL" selected>출발 도시 선택</option>
				                    </select>
                                </div>
                                <div class="select-book mb-15">
	                                <select <%-- class="select-booking" --%> name="flight_a_nc" id="flight_a_nc">
				                        <option value="ALL" selected>도착 도시 선택</option>
				                    </select>
                                </div>
                                <div class="row">
                                    <div class="b-date arrive mb-15 col-md-6">
                                        <input class="date-picker" type="text" name="start_dt" id="start_dt" placeholder="출발일">
                                        <i class="mdi mdi-calendar-text"></i>
                                    </div>
                                    <div class="b-date departure mb-15 col-md-6">
                                        <input class="date-picker" type="text" name="arrive_dt" id="arrive_dt" placeholder="귀국일">
                                        <i class="mdi mdi-calendar-text"></i>
                                    </div>
                                </div>
                                <div class="submit-form">
                                    <button type="submit">검색하기</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <!-- E: flight-srch -->

                    <!-- S: hotel-srch -->
                    <div class="hotel-srch">
					    <div class="booking-title">
					        <h3>호텔 검색</h3>
					        <p>얼마없는 호텔을 검색해 보세요! 나오면 사고 없음 딴데 가보고</p>
					    </div>
					    <div class="booking-form">
					        <form action="${pageContext.request.contextPath}/hotel/hotelList.do" id="hotel_reg_form" method="post">
					         <div class="select-book mb-15">
					             <select <%-- class="select-booking" --%> name="hotel_nc" id="hotel_nc">
			                        <option value="ALL" selected>숙박 도시 선택</option>
			                    </select>
					         </div>
					        	<div class="row">
					             <div class="b-date arrive mb-15 col-md-6">
					                 <input name="hotel_check_in" class="date-picker" type="text" placeholder="체크인 날짜" autocomplete="off">
					                 <i class="mdi mdi-calendar-text"></i>
					             </div>
					             <div class="b-date departure mb-15 col-md-6">
					                 <input name="hotel_check_out" class="date-picker" type="text" placeholder="체크아웃 날짜" autocomplete="off">
					                 <i class="mdi mdi-calendar-text"></i>
					             </div>
					            </div>
					            <div class="row">
					                <div class="select-book mb-15 col-md-4">
					                    <select name="hotel_type" class="select-booking">
					                        <option value="ALL" selected>호텔 종류</option>
					                        <option value="HOTEL">호텔</option>
					                        <option value="MOTEL">모텔</option>
					                        <option value="GUEST">게스트 하우스</option>
					                    </select>
					                </div>
					                <div class="select-book mb-15 col-md-4">
					                    <select name="hotel_adult" class="select-booking">
					                        <option value="0" selected>성인</option>
					                        <option value="1">1</option>
					                        <option value="2">2</option>
					                        <option value="3">3</option>
					                        <option value="4">4</option>
					                        <option value="5">5</option>
					                        <option value="6">6</option>
					                        <option value="7">7</option>
					                        <option value="8">8</option>
					                    </select>
					                </div>
					                <div class="select-book mb-15 col-md-4">
					                    <select name="hotel_kid" class="select-booking">
					                        <option value="0" selected>어린이</option>
					                        <option value="1">1</option>
					                        <option value="2">2</option>
					                        <option value="3">3</option>
					                        <option value="4">4</option>
					                        <option value="5">5</option>
					                        <option value="6">6</option>
					                        <option value="7">7</option>
					                        <option value="8">8</option>
					                    </select>
					                </div>
					            </div>
					         <div class="submit-form">
					             <button type="submit">검색하기</button>
					         </div>
					        </form>
					    </div>
					</div>
					<!-- E: hotel-srch -->
                </div>
            </div>
            <div class="col-md-6 col-sm-6">
                <div class="welcome-text">
                    <h2>
                    <span>WELCOEM TO</span> <span class="coloring">Hello World</span>
                    </h2>
                    <h1 class="cd-headline clip">
                        <span>완벽한</span>
                        <span class="cd-words-wrapper coloring">
                            <b class="is-visible">항공권</b>
                            <b>호텔</b>
                            <b>투어</b>
                            <b>아이템</b>
                        </span>
                    </h1>
                    <!-- <p class="welcome-desc">There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable.</p> -->
                        <p class="welcome-desc">많은 여행자들에게 부담없는 가격으로 인기를 얻고 있는 패키지! <br> 현지인에게 안내받는 효율만점 투어! <br> Hello World 만의 특별한 여행을 즐겨 보세요!</p>
                        <%-- <div class="explore">
                            <a href="${pageContext.request.contextPath}/packTour/packTour.jsp">패키지/투어 떠나기</a>
                        </div> --%>
                    </div>
                </div>
            </div>
        </div>
    </div>