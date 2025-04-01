<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file=".././common/common.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/styles/notice_list_style.css">

<b>공지사항(등록된공지:${totalCount}/${pageInfo.pageNumber})</b>
<br><br>
<c:if test="${totalCount == 0 }">
	<table width="700">
		<tr>
			<td align="center">
				공지사항이 없습니다.
			</td>
		</tr>
	</table>
</c:if>
<c:if test="${totalCount != 0 }">
	<table border="1" width="700" align="center">
		<tr>
			<th width="250" colspan="2">제 목</th>
			<th width="100">작성자</th>
			<th width="150">작성일</th>
			<th width="50">조회</th>
		</tr>
	</table>
	<br><br>
	<table border="1" width="700" align="center">
		<c:forEach var="nl" items="${noticeLists }">
			<tr>
				<c:if test="${nl.temporary_save == 1 && nl.emp_no == sessionScope.emp_no}">
					<td align="center" width="50">
						필독
					</td>
					<td align="left" width="400">
						<a href= "notice_content.erp?notice_no=${nl.notice_no }&pageNumber=${pageInfo.pageNumber}&whatColumn=${param.whatColumn}&keyword=${param.keyword}">${nl.notice_title }</a>
					</td>
					<td align="left" width="100">
						${nl.emp_nm }
					</td>
					<td align="center" width="100">
						<fmt:formatDate value="${nl.notice_dtm}" pattern="${nl.isWithin24Hours() ? 'HH:mm' : 'yyyy-MM-dd'}" />
					</td>
					<td align="center"width="50">
						${nl.notice_views }
					</td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
	<br><br>
	<table border="1" width="700" align="center">
		<c:forEach var="nl" items="${noticeLists }">
			<tr>
				<c:if test="${nl.temporary_save == 0 && nl.emp_no == sessionScope.emp_no}">
					<td align="center" width="50">
						${nl.dept_nm }
					</td>
					<td align="left" width="400">
						<a href= "notice_content.erp?notice_no=${nl.notice_no }&pageNumber=${pageInfo.pageNumber}&whatColumn=${param.whatColumn}&keyword=${param.keyword}">${nl.notice_title }</a>
					</td>
					<td align="left" width="100">
						${nl.emp_nm }
					</td>
					<td align="center" width="100">
						<fmt:formatDate value="${nl.notice_dtm}" pattern="${nl.isWithin24Hours() ? 'HH:mm' : 'yyyy-MM-dd'}" />
					</td>
					<td align="center"width="50">
						${nl.notice_views }
					</td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
<br><br>
<center>
<form action="notice_list.erp" method="get">
	<select name="whatColumn">
		<option value="">전체 검색</option>
		<option value="emp_nm">작성자</option>
		<option value="notice_title">제목</option>
	</select>
	<input type="text" name="keyword">
	<input type="submit" value="검색">
</form>
</center>
</c:if>
<p align="center">
	${pageInfo.pagingHtml }
</p>
