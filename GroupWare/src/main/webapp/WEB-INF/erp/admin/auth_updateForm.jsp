<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="./../common/common.jsp" %>

<form:form commandName="authBean" action="auth_update.erp" method="post" id="authupdateForm">




권한코드:<input type="text" name="auth_cd" value="${authBean.auth_cd }">
<form:errors path="auth_cd"/>
<br><br>


권한코드 이름:<input type="text" name="auth_nm" value="${authBean.auth_nm }">
<form:errors path="auth_nm"/>
<br><br>





<button 
		id="submitBtn"
		type="button"
		class="back-form"
		data-url="auth_update.erp"
		data-id="${authBean.auth_cd}"
		data-modal="auth_update">
		수정하기</button>
</form:form>

<%@include file = "./../js/validCheck.jsp"%>