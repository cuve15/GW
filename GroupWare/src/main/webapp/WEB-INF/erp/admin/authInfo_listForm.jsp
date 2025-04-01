<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file = "./../common/common.jsp" %>
authInfoForm.jsp<br>

<form action="empAuthInfo.erp" method="post">
부여할 권한:
<select name="auth_cd">
	<c:forEach var="auth" items="${authList}">
		<option value="${auth.auth_cd }">${auth.auth_nm }</option>
	</c:forEach>	
</select>

  <table border="1">
        <tr>
            <th>선택</th>
            <th>사원번호</th>
            <th>이름</th>
            <th>부서명</th>
            <th>직위</th>
            <th>현재 권한</th>
        </tr>
        <c:forEach var="emp" items="${empList}">
            <tr>
                <td><input type="checkbox" name="emp_no" value="${emp.emp_no}" /></td>
                <td>${emp.emp_no}</td>
                <td>${emp.emp_nm}</td>
                <td>${emp.dept_nm }</td>
                <td>${emp.position_nm }</td>
                <td>${emp.currentAuth }</td>
            </tr>
        </c:forEach>
    </table>

    <input type="submit" value="권한 부여" />

</form>