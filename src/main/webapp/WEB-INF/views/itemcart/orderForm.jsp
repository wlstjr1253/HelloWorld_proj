<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
th {
	text-align: center;
}

th.nm {
	width: 200px;
}

td.nm {
	text-align: left;
}

td.bt {
	text-align: center;
}

td.price {
	text-align: center;
	font-size: 20px;
}
</style>


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

<div class="welcome-section text-center ptb-110">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="section-title mb-60">
                           <h2>&nbsp;&nbsp;&nbsp;&nbsp;<i class="fas fa-suitcase-rolling"></i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>상품 대여</span>&nbsp;&nbsp;&nbsp;&nbsp;</h2>
                        </div>
                        
                    </div>
                </div>
            </div>
					
</div>


<!-- 탭 -->

<div class="booking-rooms-tab" style="width: 100%;">
	<ul class="nav" style="width: 100%;">
		<li class="active col-md-4"><a href="#booking" data-toggle="tab"
			id="move_1"> <span class="tab-border">1</span> <span>대여물품
					정보</span>
		</a></li>
		<li class="col-md-4"><a href="#personal" data-toggle="tab"
			id="move_2"><span class="tab-border">2</span> <span>결제 정보</span></a></li>
		<li class="col-md-3"><a> <span class="tab-border">3</span> <span>
					대여 완료 </span>
		</a></li>
	</ul>
</div>


<!-- 전체 탭 시작 -->
<div class="room-booking ptb-80 white_bg">
	<div class="container table-list">
		<div class="row">
			<div class="tab-content">
				<!-- 탭1 상세보기 시작 -->
				<div class="tab-pane active" id="booking">
						<table class="table table-striped">
							<thead>
								<tr>
									<br>
									<br>
									<br>
									<th class="nm">상품명</th>
									<th>수량</th>
									<th>대여금액</th>
									<th>대여기간</th>
									<th>수령공항</th>
									<th>반납공항</th>
								</tr>
							</thead>
					<c:forEach var="itemCart" items="${list}">
							<tr>
								<td class="nm"><a
									href="${pageContext.request.contextPath}/itemDetail.do?i_num=${itemCart.i_num}">
										<img src="imageView.do?i_num=${itemCart.i_num}" width="70" height="70" 
										class="thumb-image"> ${itemCart.i_nm}
								</a></td>
								<td><br>${itemCart.ic_quan}</td>
								<td><br>${itemCart.i_pc}</td>
								<td><br>${itemCart.i_rent_day}~
									${itemCart.i_return_day}</td>
								<td><br>${itemCart.i_rent_nc}</td>
								<td><br>${itemCart.i_return_nc}</td>

							</tr>
					</c:forEach>
							<tr>
							<tr>
								<td class="price" colspan="6"><br> 총 주문 금액 : <fmt:formatNumber
										pattern="###,###,###" value="${getTotalById}" />원<br></td>
							</tr>
							</tbody>
						</table>

						


					<div class="single-form-part">
						<div class="submit-form">
							<button type="submit" class="next_1">확인 완료</button>
						</div>
					</div>

				</div>
<section class="margin-bottom"></section>


	<!-- 탭1 상세보기 끝 -->

	<!-- 탭2 시작 -->
	<div class="tab-pane" id="personal">
		<div class="personal-info-details">
			<form:form commandName="command" id="orderForm" action="orderForm.do">
				<input type="hidden" name="i_num" id="i_num" value="${param.i_num}">
				<input type="hidden" name="user_id" id="user_id" value="${user_id}">
				<div class="booking-info-inner">
					<div class="booking-form-list">
						<div class="single-form-part">
							<div class="name mb-15">
								<input type="text" name="ibh_nm" id="ibh_nm" placeholder="예약자명">
							</div>
							<div class="name mb-15">
								<input type="text" name="cp_num" id="cp_num" placeholder="카드 번호">
							</div>
						</div>
						<div class="single-form-part">
							<div class="mail mb-15">
								<input type="email" name="ibh_email" id="ibh_email"
									placeholder="예약자 이메일"> <i class="mdi mdi-email"></i>
							</div>
							<div class="name mb-15">
								<input type="text" name="cp_pin_num" id="cp_pin_num"
									placeholder="카드 핀 넘버">
							</div>
						</div>
						<div class="single-form-part">
							<div class="mail mb-15">
								<input type="text" name="ibh_phone" id="ibh_phone"
									placeholder="예약자 연락처"> <i class="mdi mdi-phone"></i>
							</div>
							<div class="name mb-15">
								<input type="text" name="cp_year_month" id="cp_year_month"
									placeholder="MM/YY">

							</div>
						</div>
					</div>
					<div class="prve-next-box" align="right">
						<div class="back-link">
							<a class="before_2">이전</a>
						</div>
						<!-- <button type="submit">결제</button> -->
						<button type="button" onclick="location.href='${pageContext.request.contextPath}/itemcart/orderCheck.do'">결제</button>
					</div>
				</div>
			</form:form>
		</div>
		<section class="margin-bottom"></section>
	</div>
	<!-- 탭2끝 -->
</div>




<!-- 전체 탭 끝 -->

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/ajax/item/item.order.js"></script>


<%-- <div class="container">
   <div class="row">
      <div class="col-md-12">
         <div class="booking-rooms-tab" style="width: 100%;">
            <ul class="nav" style="width: 100%;">
               <li class="active col-md-4"><a href="#booking"
                  data-toggle="tab"> <span class="tab-border">1</span>
                     <span>대여물품 정보</span>
               </a></li>
               <li class="col-md-4"><a href="#personal" data-toggle="tab"> 
               <span class="tab-border">2</span> 
               <span>결제 정보</span>
               </a></li>
               <li class="col-md-3"><a> <span class="tab-border">3</span>
                     <span> 대여 완료 </span>
               </a></li>
            </ul>
         </div>
      </div>
   </div>
</div>





<!-- 전체탭시작 -->
<div class="tab-content">

   <!-- 상품정보탭 -->
   <div class="tap-pane active" id="booking">
      <div class="table-responsive">
         <table class="table table-striped">
            <thead>
               <tr>
                  
                  <th class="nm">상품명</th>
                  <th>수량</th>
                  <th>대여금액</th>
                  <th>대여기간</th>
                  <th>수령공항</th>
                  <th>반납공항</th>
               </tr>
            </thead>
            <c:forEach var="itemCart" items="${list}">
               <tr>
                  <td class="nm"><a
                     href="${pageContext.request.contextPath}/itemDetail.do?i_num=${itemCart.i_num}">
                        <img src="imageView.do?i_num=${itemCart.i_num}" width="70"
                        class="thumb-image"> ${itemCart.i_nm}
                  </a></td>
                  <td><br>${itemCart.ic_quan}</td>
                  <td><br>${itemCart.i_pc}</td>
                  <td><br>${itemCart.i_rent_day}~ ${itemCart.i_return_day}</td>
                  <td><br>${itemCart.i_rent_nc}</td>
                  <td><br>${itemCart.i_return_nc}</td>

               </tr>
            </c:forEach>
            <tr>
            <tr>
               <td class="price" colspan="5"><br>총 주문 금액 :<fmt:formatNumber
                     pattern="###,###,###" value="${getTotalById}" /><br></td>
            </tr>


            </tbody>
         </table>
      </div>
   </div>
   <!-- 상품정보탭 끝 -->
   
   
   <!-- 개인정보 탭 시작 -->


   <div class="tap-pane" id="personal">
      <form:form commandName="command" id="orderForm" action="orderForm.do">
         <input type="hidden" name="i_num" id="i_num" value="${param.i_num}">
         <input type="hidden" name="user_id" id="user_id" value="${user_id}">
         <div>
            <br> <br>
            <div class="name mb-15">
               <input type="text" name="ibh_nm" id="ibh_nm" required="required"
                  placeholder="수령자 이름">
            </div>

            <div class="name mb-15">
               <select name="ibh_pay">
                  <option value="0" selected>결제방식</option>
                  <option id="ibh_pay" value="1">카드결제</option>
                  <option id="ibh_pay" value="2">계좌이체</option>
                  <option id="ibh_pay" value="3">현금결제</option>
               </select> <br>
            </div>


            <div class="name mb-15">
               <input type="number" name="ibh_phone" id="ibh_phone"
                  placeholder="연락 가능한 번호">
            </div>
            <div class="mail mb-15">
               <input type="email" name="ibh_email" id="ibh_email"
                  placeholder="이메일 주소">
            </div>

         </div>




         <div class="request-box mt-15">
            <textarea name="ibh_request" id="ibh_request"
               placeholder="요청사항이 있으신가요?"></textarea>
         </div>



         <div class="btn-submit col-xs-12">
            <input type="button" value="뒤로가기" class="btn btn-default"
               onclick="location.href='${pageContext.request.contextPath}/itemcart/cartList'">
            <input type="submit" value="주문하기" class="btn btn-ok">

         </div>

      </form:form>
   </div>
   <!-- 개인정보탭 끝 -->
</div>
<!-- 전체탭 끝 --> --%>
