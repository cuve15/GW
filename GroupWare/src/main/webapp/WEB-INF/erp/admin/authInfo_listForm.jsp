<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file = "./../common/common.jsp" %>
authInfoForm.jsp<br>

<form id="authInfoSearchForm">
  <select name="whatColumn">
    <option value="">전체 선택</option>
    <option value="emp_nm">사원이름</option>
    <option value="dept_nm">부서이름</option>
  </select>
  <input type="text" name="keyword" id="authInfokeywordInput">
  <input type="button" value="검색" id="authInfosearchBtn">  
</form>
<br><br>

<form action="empAuthInfo.erp" method="post">
부여할 권한:
<select name="auth_cd">
	<c:forEach var="auth" items="${authList}">
		<option value="${auth.auth_cd }">${auth.auth_nm }</option>
	</c:forEach>	
</select>

<div id="authInfoListContainer">
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
<br><br>
<div id="paging">
${pageInfo.pagingHtml}
</div>
</div>
    <input type="submit" value="권한 부여" />

</form>