<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</script>
<!-- 중앙 컨텐츠 시작 -->
<div class="container write-form w_800">
	<!-- 컨텐츠 시작 -->
	<div class="row">
		<div class="margin-top">
			<form:form commandName="command" action="write.do" id="register_form" enctype="multipart/form-data" accept="image/*">
			<form:hidden path="user_id"/>
			<form:hidden path="pi_id" value="1"/>
			<form:hidden path="ti_id" value="1"/>
			<div class="row">
				<form:errors element="div" cssClass="error-color" />
				<ul class="col-xs-6 col-xs-offset-3 flightForm">
					<li>
						<label for="tr_title">제목</label>
						<form:input path="tr_title"/>
						<form:errors path="tr_title" style="color:white;"  />
					</li>
					<li>
					   <label for="tr_content">내용</label>			
				       <form:textarea path="tr_content" style="height:250px;"/>
				       <form:errors path="tr_content" style="color:white;" />   
		            </li>
		            <li>		
					   <label for="tr_upload">파일업로드 </label> 
				       <input id="tr_upload" name="tr_upload" type="file" class="form-control" style="height:50px;"/> 						
		            </li>
		        </ul>
            </div>
            <div class="btn-submit col-xs-12 margin-bottom">
				<input type="submit" value="수정" class="btn btn-ok">
				<input type="button" value="목록" class="btn btn-ok" 
					onclick="location.href='list.do'">
			</div>
		    </form:form>
		 </div>
	</div>		
</div>
<!-- 중앙 컨텐츠 끝 -->
