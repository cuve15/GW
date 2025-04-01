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
	<c:if test="${notice.emp_no == sessionScope.emp_no}">
		<tr height="30">
			<td colspan="4" align="right">
			<input type="button" value="글수정" onClick="location.href='notice_update.erp?notice_no=${notice.notice_no }&pageNumber=${pageNumber }&whatColumn=${whatColumn }&keyword=${keyword }'">
			<input type="button" value="글삭제" onClick="location.href='notice_delete.erp?notice_no=${notice.notice_no }&pageNumber=${pageNumber }&whatColumn=${whatColumn }&keyword=${keyword }'">
			</td>
		</tr>
	</c:if>
</table>
<br><br>
<form:form commandName="reply" method="post" action="reply_write.erp">
	<input type="hidden" name="emp_no" value="${sessionScope.emp_no }">
	<input type="hidden" name="emp_nm" value="${sessionScope.emp_nm }">
	<input type="hidden" name="notice_no" value="${notice.notice_no }">
	<input type="hidden" name="pageNumber" value="${pageNumber }">
	<input type="hidden" name="whatColumn" value="${whatColumn }">
	<input type="hidden" name="keyword" value="${keyword }">
	
	<table border="1" width="500">
		<tr height="100">
			<td>
				<textarea name="reply_content" rows="6" cols="60">${reply.reply_content }</textarea>
				<form:errors cssClass="err" path="reply_content"/>
			</td>
			<td>
				<input type="submit" value="작성">
			</td>
		</tr>
	</table>
</form:form>
<table border="1" width="500">
	<c:forEach var="rl" items="${replyLists }">
			<c:if test="${rl.notice_no == notice.notice_no}">
				<tr>
					<td>
						${rl.emp_nm }
					</td>
				</tr>
				<tr>
					<td>
						${rl.reply_content }
					</td>
				</tr>
				<tr>
					<td>
						<fmt:formatDate value="${rl.reply_dtm }" type="date" pattern="yyyy-MM-dd HH:mm"/>
						<c:if test="${rl.emp_no == sessionScope.emp_no}">
							<!-- <a href="">수정</a> -->
							<a href="reply_delete.erp?notice_no=${notice.notice_no }&reply_no=${rl.reply_no }&pageNumber=${pageNumber }&whatColumn=${whatColumn }&keyword=${keyword }">삭제</a>
						</c:if>
					</td>
				</tr>
			</c:if>
		</c:forEach>
</table>
