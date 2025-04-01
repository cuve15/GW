<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="./../common/common.jsp" %>

<form:form commandName="deptBean" action="dept_insert.erp" method="post" id="deptinsertForm">



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



<input type="button" value="추가하기" id="submitBtn" data-modal="dept_insert">
</form:form>

<%@include file = "./../js/validCheck.jsp"%>