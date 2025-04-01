<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="./../common/common.jsp" %>

<form:form commandName="cmmCodeBean" action="cmmCode_update.erp" method="post" id="cmmCodeupdateForm">

<input type="hidden" name="redirectPage" value="cmmCode_list.erp">

공통코드 Class:<input type="text" name="cmm_class" value="${cmmCodeBean.cmm_class }">
<form:errors path="cmm_class"/>
<br><br>

공통코드 코드:<input type="text" name="cmm_cd" value="${cmmCodeBean.cmm_cd }">
<form:errors path="cmm_cd"/>
<br><br>


공통코드 이름:<input type="text" name="cmm_nm" value="${cmmCodeBean.cmm_nm }">
<form:errors path="cmm_nm"/>
<br><br>





<button 
		id="submitBtn"
		type="button"
		class="back-form"
		data-url="cmmCode_update.erp"
		data-id="${cmmCodeBean.cmm_nm}"
		data-modal="cmmCode_update">
		수정하기</button>

</form:form>

<%@include file = "./../js/validCheck.jsp"%>