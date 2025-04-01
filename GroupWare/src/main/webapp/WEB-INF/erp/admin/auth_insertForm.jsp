<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="./../common/common.jsp" %>

<form:form commandName="authBean" action="auth_insert.erp" method="post" id="authinsertForm">




권한코드:<input type="text" name="auth_cd" value="${authBean.auth_cd }">
<form:errors path="auth_cd"/>
<br><br>


권한코드 이름:<input type="text" name="auth_nm" value="${authBean.auth_nm }">
<form:errors path="auth_nm"/>
<br><br>





<input type="button" value="추가하기" id="submitBtn" data-modal="auth_insert">
</form:form>

<%@include file = "./../js/validCheck.jsp"%>