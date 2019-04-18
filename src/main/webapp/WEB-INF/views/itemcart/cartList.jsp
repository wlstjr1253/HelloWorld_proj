<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
th {
	text-align: center;
}
th.no{}

td.nm {
	text-align: left;
}

td.bt {
	text-align: center;
}

td.price {
	text-align: right;
	font-size: 20px;
}

content {
	text-align: center;
}

nm {
	text-align: center;
	width: 700px;
	height: 100px;
	margin: auto;
	border: 1px solid green;
}
</style>


<div class="welcome-section text-center ptb-110">
	<div class="container">
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="breadcurbs-inner">
					<div class="breadcrubs">
						<h2>장바구니</h2>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
<!-- 중앙 컨텐츠 시작 -->

<div class="room-booking ptb-80 white_bg">
		<div class="row">
	<div class="table-responsive col-xs-4 col-xs-offset-4">
					<c:if test="${count == 0}">
						<table class="table table-striped pull-right">
						<tr>
						<th class="no">
						장바구니에 담긴 상품이 없습니다.
						</th>
						</tr>
						</table>
						
						<div class="explore">
							<a href="${pageContext.request.contextPath}/main/main.do">메인으로</a>
						</div>
						
					</c:if>

		</div>
	</div>
</div>

<div class="room-booking white_bg">
	<div class="container table-list">
		<div class="row">


			<!-- 장바구니에 상품이 들어있을 때 -->
			<c:if test="${count > 0}">

				<!-- 장바구니 표 -->
				<form:form commandName="command" id="orderFormPart"
					action="orderFormPart.do">
					<table class="table table-striped">

						<thead>
							<tr>
								<th></th>
								<th>상품명</th>
								<th>수량</th>
								<th>대여금액</th>
								<th></th>
							</tr>
						</thead>


						<c:forEach var="itemCart" items="${list}">

							<tr>
								<td><br> <input type="checkbox" name="checked_num"
									id="checked_num" value="${itemCart.ic_num}"
									style="width: 20px; height: 20px;"></td>
								<td class="nm"><a
									href="${pageContext.request.contextPath}/itemDetail.do?i_num=${itemCart.i_num}">
										<img src="imageView.do?i_num=${itemCart.i_num}" width="70"
										class="thumb-image"> ${itemCart.i_nm}
								</a></td>
								<td><br>${itemCart.ic_quan}</td>
								<td><br>${itemCart.sub_total}</td>

								<td>
									<div class="btn-list row" style="align: center;">
										<!-- <a href=""
                              class="button border_radius little button-black col-sm-4 on">대여</a> -->
										<a href="cartDelete.do?ic_num=${itemCart.ic_num}"
											class="button border_radius little button-black col-sm-6">삭제하기</a>

									</div>
								</td>

							</tr>
						</c:forEach>
						<tr>
							<td class="price" colspan="5"><br>총 주문 금액 :<fmt:formatNumber
									pattern="###,###,###" value="${getTotalById}" /><br></td>
						</tr>
						</tbody>
					</table>


					<div class="prve-next-box mt-20">


						<input type="button" value="전체상품 주문하기"
							onclick="location.href='${pageContext.request.contextPath}/itemcart/orderForm.do'">
						<input type="submit" value="선택상품 주문하기" name="button" id="checkBtn">

					</div>
				</form:form>

			</c:if>

			<section class="margin-bottom"></section>
		</div>
	</div>
</div>
<!-- 중앙 컨텐츠 끝 -->



<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ajax/item/item.order.js"></script>

