<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--Welcome secton-->
	<jsp:include page="main_header.jsp" />
</div>
<!-- Header section end -->

<!--About Section Title start-->
<%-- <div class="about-section text-center ptb-80 white_bg clearfix container">
    <div class="row">
        <ul class="package-carousel">
            <li class="item">
                <div class="section-title mb-80">
                    <h2>사생결단 패키지 타이틀 <span>니가가라 하와이</span></h2>
                    <p>그래도 여행은 여행이니깐! 여행을 여행답게! take it!</p>
                </div>
                <div class="about-chondo">
                    <img src="${pageContext.request.contextPath}/resources/images/main/img1.jpg" alt="1번 패키지">
                </div>
            </li>

            <li class="item">
                <div class="section-title mb-80">
                    <h2>봄에는 마! 떠나라 아이가! <span>취준생을 위한 동남아 취업 박람회</span></h2>
                    <p>그래도 여행은 여행이니깐! 여행을 여행답게! take it!</p>
                </div>
                <div class="about-chondo">
                    <img src="${pageContext.request.contextPath}/resources/images/main/img2.jpg" alt="2번 패키지">
                </div>
            </li>

            <li class="item">
                <div class="section-title mb-80">
                    <h2>LALA랜드의 감동을 그대로<span>LA갈비 패키지</span></h2>
                    <p>그래도 여행은 여행이니깐! 여행을 여행답게! take it!</p>
                </div>
                <div class="about-chondo">
                    <img src="${pageContext.request.contextPath}/resources/images/main/img3.jpg" alt="3번 패키지">
                </div>
            </li> 
        </ul>
    </div>
</div> --%>
<!--About Section end-->

<!--Our services start-->
<div class="our-sevices text-center ptb-80 white_bg">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="section-title mb-80">
                    <h2>투어 상품 <span>TOUR</span></h2>
                    <p>새로운 사람들과 특별한 장소로 투어를 떠납니다! 무슨 일이 생길지 아무도 모르지요... <span>Good Luck!</span></p>
                </div>
            </div>
        </div>
    </div>
    <div class="our-services-list">
        <div class="container-fluid">
            <div class="row">
            	<c:forEach var="tour" items="${tour_list}">
	                <div class="col-md-3 col-sm-6 col-xs-12">
	                    <div class="single-services">
	                        <div class="services-img">
	                            <img src="${pageContext.request.contextPath}/tour_info/imageView.do?ti_id=${tour.ti_id}" width="443" height="291">
	                            <div class="services-title">
	                                <h2>${tour.ti_nm}</h2>
	                            </div>
	                            <div class="services-hover-desc">
	                                <div class="services-hover-inner">
	                                    <div class="services-hover-table">
	                                        <div class="services-hover-table-cell">
	                                            <a href="${pageContext.request.contextPath}/tour_info/detail.do?ti_id=${tour.ti_id}" target="_blank"><h2>${tour.ti_nm}</h2>
	                                            <p>${tour.ti_content}</p></a>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
                </c:forEach>
            </div>
			<div class="booking-form-list mt-10">
				<div class="prve-next-box" align="right">
					<button type="button" onclick="location.href='${pageContext.request.contextPath}/tour_info/list.do'">더 보기</button>
				</div>
			</div>
        </div>
    </div>
</div>
<!--Our services end-->
<!--Our staff start-->
<div class="our-staff text-center pb-80 white_bg">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="section-title mb-80">
                    <h2>여행 물품 <span>ITEM</span></h2>
                    <p>뭐라도 하나 더 있음 좋지 않을까요? 나중에 울며 불며 떼써도 못 알아 들을 겁니다. 영어로 울어보시든지...</p>
                </div>
            </div>
        </div>
        <div class="staff-list">
            <div class="row">
                <div class="carousel-list">
                    <div class="col-md-4">
                        <div class="single-staff">
                            <div class="sraff-inner">
                                <img src="/HelloWorld_proj/resources/images/item/camera1.jpg">
                                <div class="staff-title">
                                    <span class="fas fa-plus"></span>
                                    <h2>카메라</h2>
                                    <h5>[Nikon]D5300</h5>
                                </div>
                                <div class="staff-hover-desc">
                                    <p>여행의 추억을 남기세요, 가까운 공항에서 픽업 가능합니다. <br>사지말고 대여하세요</p>
                                    <div>
                                        <button type="button" class="btn btn-warning" onclick="location.href='${pageContext.request.contextPath}/item/itemDetail.do?i_num=56'">상세보기</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="single-staff">
                            <div class="sraff-inner">
                                <img src="/HelloWorld_proj/resources/images/item/m1.png" alt="">
                                <div class="staff-title">
                                    <span class="fas fa-plus"></span>
                                    <h2>등산용품</h2>
                                    <h5>등산용품세트</h5>

                                </div>
                                <div class="staff-hover-desc">
                                        <p>준비안된 초보 등산객들 위한 등산세트, 가까운 공항에서 픽업 가능합니다. <br>사지말고 대여하세요</p>
                                    <div>
                                        <button type="button" class="btn btn-warning" onclick="location.href='${pageContext.request.contextPath}/item/itemDetail.do?i_num=63'">상세보기</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="single-staff">
                            <div class="sraff-inner">
                                <img src="/HelloWorld_proj/resources/images/item/w1.jpg" alt="">
                                <div class="staff-title">
                                    <span class="fas fa-plus"></span>
                                    <h2>물놀이용품</h2>
                                    <h5>스노쿨링세트</h5>

                                </div>
                                <div class="staff-hover-desc">
                                     <p>즐거운 물놀이의 필수품, 가까운 공항에서 픽업 가능합니다. <br>사지말고 대여하세요</p>
                                    <div>
                                        <button type="button" class="btn btn-warning" onclick="location.href='${pageContext.request.contextPath}/item/itemDetail.do?i_num=73'">상세보기</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="single-staff">
                            <div class="sraff-inner">
                                <img src="/HelloWorld_proj/resources/images/item/camera6.jpg" alt="">
                                <div class="staff-title">
                                    <span class="fas fa-plus"></span>
                                    <h2>카메라</h2>
                                    <h5>필름카메라</h5>

                                </div>
                                <div class="staff-hover-desc">
                                    <p>여행의 추억을 남기세요, 가까운 공항에서 픽업 가능합니다. <br>사지말고 대여하세요</p>
                                    <div>
                                        <button type="button" class="btn btn-warning" onclick="location.href='${pageContext.request.contextPath}/item/itemDetail.do?i_num=52'">상세보기</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="single-staff">
                            <div class="sraff-inner">
                                <img src="/HelloWorld_proj/resources/images/item/ph1.jpg" alt="">
                                <div class="staff-title">
                                    <span class="fas fa-plus"></span>
                                    <h2>휴대폰용품</h2>
                                    <h5>샤오미 보조배터리</h5>

                                </div>
                                <div class="staff-hover-desc">
                                     <p>배터리가 없어도 당황하지 마세요,  가까운 공항에서 픽업 가능합니다. <br>사지말고 대여하세요</p>
                                    <div>
                                        <button type="button" class="btn btn-warning" onclick="location.href='${pageContext.request.contextPath}/item/itemDetail.do?i_num=74'">상세보기</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="single-staff">
                            <div class="sraff-inner">
                                <img src="/HelloWorld_proj/resources/images/item/pl1.PNG" alt="">
                                <div class="staff-title">
                                    <span class="fas fa-minus"></span>
                                    <h2>놀거리</h2>
                                    <h5>보드게임</h5>

                                </div>
                                <div class="staff-hover-desc">
                                     <p>즐거운 여행 함께 놀아요,  가까운 공항에서 픽업 가능합니다. <br>사지말고 대여하세요</p>
                                    <div>
                                        <button type="button" class="btn btn-warning" onclick="location.href='${pageContext.request.contextPath}/item/itemDetail.do?i_num=78'">상세보기</button>
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
<!--Our staff end-->

<!-- 여행후기 list -->
<div class="container main-board-list mb-100">
    <div class="row">
        <div class="section-title mb-20">
            <h2>여행 후기 <span>REVIEW</span></h2>
        </div>
        <table class="table table-striped table-responsive table-hover">
            <thead>
                <tr>
                	<th style="text-align: center;">글번호</th>
                    <th style="text-align: center;">등록일</th>
                    <th style="text-align: center;">제목</th>
                    <th style="text-align: center;">작성자</th>
                </tr>
            </thead>
            <tbody>
            	<c:forEach var="review" items="${review}">
                <tr>
                    <td>${review.tr_idx}</td>
                    <td>${review.tr_reg_date}</td>
                    <td><a href="${pageContext.request.contextPath}/review/detail.do?tr_idx=${review.tr_idx}" target="_blank">${review.tr_title}</a></td>
                    <td>${review.user_id}</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
	<div class="booking-form-list mt-10">
		<div class="prve-next-box" align="right">
			<button type="button" onclick="location.href='${pageContext.request.contextPath}/review/list.do'">더 보기</button>
		</div>
	</div>
</div>
<!-- 여행후기 list -->
