<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ajax/tour_infoWrite.js"></script> --%>
<script type="text/javascript" src="./js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#register_form").submit(function(){
		var ti_start_day = $('#ti_start_day').val();
		var ti_end_day = $('#ti_end_day').val();
		//-을 구분자로 연,월,일로 잘라서 배열로 반환
		var startArray = ti_start_day.split('-');
		var endArray = ti_end_day.split('-');
		//배열에 담겨있는 데이터를 이용하여 Date 객체 생성
		var n_start_day = new Date(startArray[0], startArray[1], startArray[2]);
		var n_end_day = new Date(endArray[0], endArray[1], endArray[2]);
		    //날짜를 숫자형태의 날짜 정보로 변환, 비교
		if(n_start_day.getTime()> n_end_day.getTime()){
			alert("종료날짜보다 시작날짜가 앞서야 합니다.");
			return false;
		}
		    
		var t_min_pp = $('#ti_min_pp').val();
		var t_max_pp = $('#ti_max_pp').val();
		
		if(t_min_pp > t_max_pp){
			alert("최소인원이 최대인원보다 같거나 작아야 합니다.");
			return false;
		}
	});
});
</script>
<!-- <div class="tour_infoWrite write-form w_600">
		<div class="container">
			<div class="row"> -->
<div class="container write-form w_800 ">
 
			<!-- ========================================================== -->
	
            <!-- ========================================================== -->
           
	<div class="row">
	 <c:if test="${!empty user_id && user_auth == 2}">
<h1 class="col-xs-12 text-center margin-top margin-bottom-50"><span class="text-green">투어</span> 수정</h1>
<form:form commandName="command" action="update.do" enctype="multipart/form-data" id="register_form" cssClass="form-horizontal">
					  <form:hidden path="ti_id"/>
<div class="row">
				<form:errors element="div" cssClass="error-color" />
				<ul class="col-xs-6 col-xs-offset-3 flightForm">
					<li>
						<label for="ti_nm" id="ti_nm">투어명</label>
						<form:input path="ti_nm"/>
						<form:errors path="ti_nm" cssClass="error-color" />
					</li>
					<li>
						<label for="ti_start_day">시작일자</label>
						<form:input path="ti_start_day" type="date" name="ti_start_day"/>
						<form:errors path="ti_start_day" cssClass="error-color" />
					</li>
					<li>
						<label for="ti_end_day">종료일자</label>
						<form:input path="ti_end_day" type="date" name="ti_end_day"/>
						<form:errors path="ti_end_day" cssClass="error-color" />
					</li>
					<li>
						<label for="ti_min_pp">최소인원</label>
						<form:select path="ti_min_pp">
						     <c:forEach var="i" begin="1" end="20">
						       <option value="${i}">${i}명</option>
						     </c:forEach>
						 </form:select>
						<form:errors path="ti_min_pp" cssClass="error-color" />
					</li>
					<li>
						<label for="ti_max_pp">최대인원</label>
						<form:select path="ti_max_pp">
						     <c:forEach var="i" begin="1" end="20">
						       <option value="${i}">${i }명</option>
						     </c:forEach>
						 </form:select>
						<form:errors path="ti_max_pp" cssClass="error-color" />
					</li>
					<li>
						<label for="ti_pickup_place">픽업장소</label>
						<form:input path="ti_pickup_place"/>
						<form:errors path="ti_pickup_place" cssClass="error-color" />
					</li>
					<li>
						<label for="ti_pickup_time">픽업시간</label>
						<form:input path="ti_pickup_time" type="time"/>
						<form:errors path="ti_pickup_time" cssClass="error-color" />
					</li>
					<li>
						<label for="ti_pc">가격</label>
						<form:input path="ti_pc"/>
						<form:errors path="ti_pc" cssClass="error-color" />
					</li>
					<li>
						<label for="upload">투어사진</label>
						<input type="file" id="upload" name="upload" class="form-control"/>
						<c:if test="${!empty command.ti_img}">
						<br>
						<span>(${command.ti_img})파일이 등록되어 있습니다. 다시 업로드하면 기존 파일은 삭제합ㄴ디ㅏ.</span>
						<form:errors path="ti_img" cssClass="error-color" />
						</c:if>
					</li>
					<li>
						<label for="ti_content">상세내용</label>
						<form:textarea path="ti_content" style="text-align:center; width:390px"/>
						<form:errors path="ti_content" cssClass="error-color" />
					</li>
					
				</ul>
				</div>
				<div class="btn-submit col-xs-12">
					  <input type="button" value="뒤로" class="btn btn-default" onclick="${pageContext.request.contextPath}/main/main.do"> 
						<input type="submit" value="전송" class="btn btn-ok">
				</div>
				<section class="margin-bottom"></section>
		</form:form>
		</c:if>
	</div>
	
</div>
