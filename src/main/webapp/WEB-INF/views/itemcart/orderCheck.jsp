<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>




<!-- 중앙 컨텐츠 시작 -->
<div class="container write-form w_600">
	<div class="section-title mb-80" style="text-align: center;">
		<h1>
			<span style="color: #44b272">주문완료</span>

		</h1>
	</div>
</div>
</div>

<div class="container main-board-list mb-100">
	<div class="row">
		<div class="section-title mb-80">
			<span>주문이 성공적으로 완료되었습니다!</span>
			<input type="button" value="홈으로" class="btn btn-ok" onclick="${pageContext.request.contextPath}/main/main.do"/>
			<input type="button" value="주문내역" class="btn btn-default" onclick="${pageContext.request.contextPath}/itemcart/mypage.do"/>
			
			
			<table class="table">
					<thead>
						<tr>
							<th>상품명</th>
							<th>수량</th>
							<th>대여금액</th>
							<th>대여기간</th>
						<th>수령공항</th>
						<th>반납공항</th>
						</tr>
					</thead>
				<c:forEach var="itemCart" items="${list}">
					<tr>
						<td><a
							href="${pageContext.request.contextPath}/itemDetail.do?i_num=${itemCart.i_num}">
								<img src="imageView.do?i_num=${itemCart.i_num}" width="100"
								class="thumb-image"> ${itemCart.i_nm}
						</a></td>
						<td>${itemCart.ic_quan}</td>
						<td>${itemCart.i_pc}</td>
						<td>${itemCart.i_rent_day} ~ ${itemCart.i_return_day}</td>
						<td>${itemCart.i_rent_nc}</td>
						<td>${itemCart.i_return_nc}</td>

					</tr>
				</c:forEach>
				
				</tbody>
			</table>
			
		</div>
	</div>
</div>