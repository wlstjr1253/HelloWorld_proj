<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>




<!-- 중앙 컨텐츠 시작 -->
<title>주문완료</title>

<title>상품 대여</title>

<div class="welcome-section text-center ptb-110">
   <div class="container">
      <div class="row">
         <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="breadcurbs-inner">
               <div class="breadcrubs">
                  <h2>대여/결제</h2>
               </div>
            </div>
         </div>
      </div>
   </div>
</div>
</div>


<div class="container">
   <div class="row">
      <div class="col-md-12">
         <div class="booking-rooms-tab" style="width: 100%;">
            <ul class="nav" role="tablist" style="width: 100%;">
               <li class="col-md-4"><a href="#booking" data-toggle="disabled"
                  id="move_1"><span class="tab-border">1</span> <span>대여물품
                        정보</span> </a></li>
               <li class="col-md-4"><a href="#personal"
                  data-toggle="disabled" id="move_2"><span class="tab-border">2</span>
                     <span>결제 정보</span> </a></li>
               <li class="active col-md-3"><a> <span class="tab-border">3</span>
                     <span> 대여 완료 </span>
               </a></li>
            </ul>



         </div>
      </div>
   </div>
</div>


<div class="our-news text-center ptb-80 white_bg">
   <div class="container table-list">
      <div class="container">
         <div class="row">
            <div class="col-md-12">
               <div class="section-title mb-80">
                  <div class="align-center">주문이 완료되었습니다!</div>
                  <div class="align-center">주문내역은 회원정보에서 확인하실 수 있습니다.</div>


                  <section class="margin-bottom"></section>

                  <div class="explore">
                     <a href="${pageContext.request.contextPath}/main/main.do">메인으로</a>
                  </div>

                     
                  
               </div>
            </div>
         </div>
      </div>
   </div>
</div>


