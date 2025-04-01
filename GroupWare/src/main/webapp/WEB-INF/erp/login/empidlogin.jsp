<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp" %>

    
<h2>사원번호 입력</h2>

<form action="/write/empid/login.erp" method="post">
	<input type="hidden" name="uuid" value="${uuid}">

    사원번호:<input type="text" name="empId">
<p style="color:red;">${error}</p>

    <input type="submit" value="다음" />
</form>