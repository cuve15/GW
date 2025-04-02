<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp"%>


<h2>사원번호 입력</h2>

<form action="write/email/login.erp" method="post">
	 <input type="hidden" name="uuid" value="${uuid}" />
    <input type="hidden" name="emp_no" value="${emp_no}" />


	인증번호:<input type="text" name="code">
	<p style="color: red;">${error}</p>


	<input type="submit" value="로그인" />
</form>