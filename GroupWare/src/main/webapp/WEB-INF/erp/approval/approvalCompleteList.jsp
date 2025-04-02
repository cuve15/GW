<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp"%>
<!-- 
<script>
    function companion(apprNo) {
        location.href = "rejectForm.erp?appr_no=" + apprNo;
    }
</script> -->

<table border="1">
	<thead>
		<tr>
			<th>결제 번호</th>
			<th>문서 번호</th>
			<th>결제 순서</th>
			<th>사원 번호</th>
			<th>부서 코드</th>
			<th>직급 코드</th>
			<th>결제 상태</th>
			<th>결제 일시</th>
			<th>반려 사유</th>
			<th>서류</th>


		</tr>
	</thead>
	<tbody>
		<c:forEach var="approval" items="${ApprovalCompleteList}">
			<tr>
				<td>${approval.appr_no}</td>
				<td>${approval.doc_no}</td>
				<td>${approval.appr_order}</td>
				<td>${approval.emp_no}</td>
				<td>${approval.dept_cd}</td>
				<td>${approval.position_cd}</td>
				<td>${approval.appr_status}</td>
				<td>${approval.appr_dtm}</td>
				<td>${approval.appr_refuse_op}</td>
				<td><a href="download.erp?doc_no=${approval.doc_no}">다운로드</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<br>
<p align="center">
	${pageInfo.pagingHtml }
</p>

