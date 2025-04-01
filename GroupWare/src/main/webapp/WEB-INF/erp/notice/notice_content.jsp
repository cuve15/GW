<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file=".././common/common.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/styles/notice_content_style.css">

<table border="1" width="500">
	<tr height="30">
		<td colspan="4" align="right">
		<input type="button" value="글목록" onClick="location.href='notice_list.erp?pageNumber=${pageNumber}&whatColumn=${whatColumn }&keyword=${keyword }'">
		</td>
	</tr>
	<tr height="30">
		<td width="375" colspan="4">${notice.notice_title }</td>
	</tr>
	<tr height="30">
		<td width="125" colspan="4">${notice.emp_nm }</td>
	</tr>
	<tr height="30">
		<td width="125">
			<fmt:formatDate value="${notice.notice_dtm }" type="date" pattern="yyyy-MM-dd HH:mm" />
		</td>
		<td width="125">${notice.notice_views }</td>
	</tr>
	<tr height="180">
		<td width="375" colspan="4">${notice.notice_content }</td>
	</tr>
	<tr height="30">
		<td colspan="4" align="right">
		<input type="button" value="글수정" onClick="location.href='notice_update.erp?notice_no=${notice.notice_no }&pageNumber=${pageNumber }&whatColumn=${whatColumn }&keyword=${keyword }'">
		<input type="button" value="글삭제" onClick="location.href='notice_delete.erp?notice_no=${notice.notice_no }&pageNumber=${pageNumber }&whatColumn=${whatColumn }&keyword=${keyword }'">
		</td>
	</tr>
</table>
