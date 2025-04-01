<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="./../common/common.jsp" %>

<form:form commandName="cmmCodeBean" action="cmmCode_insert.erp" method="post" id="cmmCodeinsertForm">



공통코드 Class:<input type="text" name="cmm_class" value="${cmmCodeBean.cmm_class }">
<form:errors path="cmm_class"/>
<br><br>

공통코드 코드:<input type="text" name="cmm_cd" value="${cmmCodeBean.cmm_cd }">
<form:errors path="cmm_cd"/>
<br><br>


공통코드 이름:<input type="text" name="cmm_nm" value="${cmmCodeBean.cmm_nm }">
<form:errors path="cmm_nm"/>
<br><br>

<br><br>



<input type="button" value="추가하기" id="submitBtn" data-modal="cmmCode_insert">
</form:form>

<%@include file = "./../js/validCheck.jsp"%>