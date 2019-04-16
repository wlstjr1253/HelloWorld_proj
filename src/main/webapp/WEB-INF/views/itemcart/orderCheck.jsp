<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>




<!-- 중앙 컨텐츠 시작 -->
<title>주문완료</title>

<div class="welcome-section text-center ptb-110">
	<div class="container">
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="breadcurbs-inner">
					<div class="breadcrubs">
						<h2>주문완료</h2>
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
		<div class="section-title mb-80">
		<h1><span>주문이 완료되었습니다!</span></h1>
		</div>
		</div>
			<input type="button" value="홈으로" class="btn btn-ok" onclick="${pageContext.request.contextPath}/main/main.do"/>
			<input type="button" value="주문내역" class="btn btn-default" onclick="${pageContext.request.contextPath}/itemcart/mypage.do"/>
			
			
	</div>
</div>