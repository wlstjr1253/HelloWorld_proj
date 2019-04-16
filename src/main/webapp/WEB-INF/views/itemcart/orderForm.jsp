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
	text-aglin: right;
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


<div class="container">
	<div class="row">

		<div class="table-responsive">
			<table class="table table-striped">
				<thead>
					<tr>
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



			<form:form commandName="command" id="orderForm" action="orderForm.do">
				<input type="hidden" name="i_num" id="i_num" value="${param.i_num}">
				<input type="hidden" name="user_id" id="user_id" value="${user_id}">
				<div>
					<br>
					<br>
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
	</div>
</div>

</html>