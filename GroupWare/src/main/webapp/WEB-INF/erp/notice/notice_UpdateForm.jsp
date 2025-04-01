<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file=".././common/common.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/styles/notice_write_style.css">

<form:form commandName="notice" method="post" action="notice_update.erp">
	<input type="hidden" name="emp_no" value="${sessionScope.emp_no }">
	<input type="hidden" name="emp_nm" value="${sessionScope.emp_nm }">
	<input type="hidden" name="notice_no" value="${notice.notice_no }">
    <table class="notice-form-table">
        <tr>
            <td colspan="2" align="right">
                <a href="notice_list.erp?pageNumber=${pageNumber}&whatColumn=${whatColumn }&keyword=${keyword }" class="notice-list-link">목록보기</a>
            </td>
        </tr>
        <tr>
            <td align="center" class="notice-label-1">부서선택</td>
            <td>
                <select name="dept_nm">
                    <option value="전체" <c:if test="${notice.dept_nm == '전체' }">selected</c:if>>전체</option>
                    <option value="${sessionScope.dept_nm }" <c:if test="${notice.dept_nm == sessionScope.dept_nm }">selected</c:if>>${sessionScope.dept_nm }</option>
                </select>
                <input type="checkbox" name="notice_level" value="1" class="notice-important-checkbox" <c:if test="${notice.notice_level == 1 }">checked</c:if>>필독
            </td>
        </tr>
        <tr>
            <td align="center" class="notice-label-2">제목</td>
            <td>
                <input type="text" name="notice_title" value="${notice.notice_title }">
                <form:errors cssClass="err" path="notice_title"/>
            </td>
        </tr>
        <tr>
            <td align="center" class="notice-label-3">파일첨부</td>
            <td><input type="file" name="file"></td>
        </tr>
        <tr>
            <td align="center" class="notice-label-4">내용</td>
            <td>
                <textarea name="notice_content" rows="40" cols="100">${notice.notice_content }</textarea>
                <form:errors cssClass="err" path="notice_content"/>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center" class="notice-form-buttons">
                <input type="submit" value="수정">
            </td>
        </tr>
    </table>
</form:form>
