<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="./../common/common.jsp" %>

<form:form commandName="deptBean" action="dept_update.erp" method="post" id="deptupdateForm">

<input type="hidden" name="redirectPage" value="dept_list.erp">

부서코드:<input type="text" name="dept_cd" value="${deptBean.dept_cd }">
<form:errors path="dept_cd"/>
<br><br>

부서이름:<input type="text" name="dept_nm" value="${deptBean.dept_nm }">
<form:errors path="dept_nm"/>
<br><br>

상위 부서 코드:
<select name="p_dept_cd">
<option value="">선택</option>
<c:forEach var="dept" items="${lists}">
<option value="${dept.dept_cd }" <c:if test="${dept.dept_cd == deptBean.p_dept_cd}">selected</c:if>>${dept.dept_cd}</option>


</c:forEach>
</select>
<br><br>

<button 
		id="submitBtn"
		type="button"
		class="back-form"
		data-url="dept_update.erp"
		data-id="${deptBean.dept_cd}"
		data-modal="dept_update">
		수정하기</button>

</form:form>

<%@include file = "./../js/validCheck.jsp"%>