<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h2>휴가 신청 목록</h2>

<!-- 리스트가 있을 때 테이블 출력 -->
<table border="1">
    <thead>
        <tr>
            <th>휴가 번호</th>
            <th>휴가 유형</th>
            <th>신청자</th>
            <th>휴가 시작일</th>
            <th>휴가 종료일</th>
            <th>총 휴가일수</th>
            <th>상태</th>
            <th>승인자</th>
            <th>승인</th>
            <th>반려</th>
        </tr>
    </thead>
    <tbody>
        <!-- 리스트가 비었을 때 메시지 출력 -->
        <c:if test="${empty vacationList}">
            <tr>
                <td colspan="10" style="text-align:center;">리스트가 없습니다.</td>
            </tr>
        </c:if>

        <!-- 리스트가 있을 때 휴가 목록 출력 -->
        <c:if test="${not empty vacationList}">
            <c:forEach var="vacation" items="${vacationList}">
                <tr>
                    <td>${vacation.vacation_no}</td>
                    <td>${vacation.vacation_type}</td>
                    <td>${vacation.req_emp_no}</td>
                    <td><fmt:formatDate value="${vacation.start_dt}" pattern="yyyy-MM-dd"/></td>
                    <td><fmt:formatDate value="${vacation.end_dt}" pattern="yyyy-MM-dd"/></td>
                    <td>${vacation.total_days}</td>
                    <td>${vacation.vacation_status}</td>
                    <td>${vacation.appr_emp_no}</td>
                    <td><a href = "vacationApproval.erp?vacation_no=${vacation.vacation_no}">승인</a></td>
                    <td><a href = "vacationReject.erp?vacation_no=${vacation.vacation_no}">반려</a></td>
                </tr>
            </c:forEach>
        </c:if>
    </tbody>
</table>

<br>
<p align="center">
    ${pageInfo.pagingHtml }
</p>
