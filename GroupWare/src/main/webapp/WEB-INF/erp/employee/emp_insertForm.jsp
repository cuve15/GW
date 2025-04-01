<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="./../common/common.jsp" %>

<form:form commandName="empBean" action="lsh_insert.erp" method="post" id="empinsertForm">



사원번호:<input type="text" name="emp_no" value="${empBean.emp_no }">
<form:errors path="emp_no"/>
<br><br>

이름:<input type="text" name="emp_nm" value="${empBean.emp_nm }">
<form:errors path="emp_nm"/>
<br><br>

비밀번호:<input type="password" name="pw" value="${empBean.pw }">
<form:errors path="pw"/>
<br><br>

성별:
<input type="radio" name="gender" value="1" <c:if test="${empBean.gender.equals('1') }">checked</c:if>>남
<input type="radio" name="gender" value="2" <c:if test="${empBean.gender.equals('2') }">checked</c:if>>여
<form:errors path="gender"/>
<br><br>

부서: <select name="dept_cd">
		<option value="">선택</option>
		<option value="1100" <c:if test="${empBean.dept_cd == '1100' }">selected</c:if>>인사팀</option>
		<option value="1200" <c:if test="${empBean.dept_cd == '1200' }">selected</c:if>>경영팀</option>
		<option value="1300" <c:if test="${empBean.dept_cd == '1300' }">selected</c:if>>관리팀</option>
	</select>
<form:errors path="dept_cd"/>
<br><br>

직위: <select name="position_cd">
		<option value="">선택</option>
		<option value="4" <c:if test="${empBean.position_cd == '4' }">selected</c:if>>사원</option>
		<option value="3" <c:if test="${empBean.position_cd == '3' }">selected</c:if>>과장</option>
		<option value="2" <c:if test="${empBean.position_cd == '2' }">selected</c:if>>부장</option>
		<option value="1" <c:if test="${empBean.position_cd == '1' }">selected</c:if>>사장</option>
	</select>  
<form:errors path="position_cd"/>
<br><br>

입사일:<input type="date" name="hire_date">
<br><br>


생년월일:<input type="date" name="birth">
<br><br>

이메일:<input type="text" name="email" value="${empBean.email }">
<form:errors path="email"/>
<br><br>
<input type="button" value="추가하기" id="submitBtn" data-modal="emp_insert">
</form:form>

<%@include file = "./../js/validCheck.jsp"%>