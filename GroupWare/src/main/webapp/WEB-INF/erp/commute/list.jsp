<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ include file="./../common/common.jsp" %>
<script type="text/javascript">
	function search(){
		location.href="/list/commute";
	}
</script>

 <h1>출근표</h1>

<!-- 권한 있는 사람만 검색 가능 -->
<c:if test="${position eq '사장' or department eq '인사팀' or position eq '부장'}">
    <form method="get" action="/list/commute" id="searchForm">
        <select name="type" id="searchType">
            <option value="emp_name">사원이름</option>
            <option value="dept_nm">부서</option>
            <option value="position">직급</option>
        </select>
        <input type="text" name="keyword" id="keyword" value="${param.keyword}">
        <input type="submit" value="검색">
    </form>
</c:if>

<table border="1">
    <tr>
        <th>날짜</th>
        <th>사원번호</th>
        <th>사원이름</th>
        <th>부서</th>
        <th>직급</th>
        <th>출근시간</th>
        <th>퇴근시간</th>
        <th>총 근무 시간</th>
        <th>초과시간</th>
    </tr>

    <c:forEach var="lists" items="${lists}">
        <tr>
            <td><fmt:formatDate value="${lists.work_date}" pattern="yyyy-MM-dd"/></td>
            <td>${lists.employee_id}</td>
            <td>${lists.empNm}</td>
            <td>${lists.deptNm}</td>
            <td>${lists.cmmNm}</td>
            <td>${lists.clock_in}</td>
            <td>${lists.clock_out}</td>
            <td>${lists.total_hours}</td>
            <td>${lists.overtime_hours}</td>
        </tr>
    </c:forEach>
</table>

  