<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="./../common/common.jsp" %>
<%
	String path = request.getContextPath();
%>

<div style="display: flex; justify-content: space-between; align-items: center;">
	<div>
		<h4>받은 메신저(${readCount }/${totalCount })</h4>
	</div>
	<form id="receiveSearchForm">
		<select name="whatColumn">
			<option value="">전체 선택</option>
			<option value="send_emp_name">보낸 사람</option>
			<option value="msg_title">제목</option>
		</select>
		<input type="text" name="keyword" id="receiveKeywordInput">
		<input type="button" value="검색" id="receiveSearchBtn">
	</form>
</div>

<div id="msgReceiveListContainer">
	<div id="receivelist">
	    <div style="display: flex; padding: 10px; font-weight: bold; border-bottom: 2px solid #000; gap: 20px;">
		    <div style="min-width: 75px;">읽음 상태</div>
		    <div style="min-width: 170px;">보낸 사람</div>
		    <div style="flex-grow: 1;">제목</div>
		    <div style="min-width: 120px;">시간</div>
		</div>
		<div style="border-bottom: 2px solid #000;">
		
			<c:forEach var="mb" items="${receivelist}">
		        <div style="display: flex; align-items: center; padding: 10px; border-bottom: 1px solid #ddd; gap: 20px;">
		            <div style="min-width: 75px; text-align: center;">
		                ${mb.read_yn == 'Y' ? '✔' : '✖'}
		            </div>
		            <div style="min-width: 60px; font-weight: bold;">
		                ${mb.send_emp_name}
		            </div>
		            <div style="min-width: 90px; font-size: 12px; color: #808080;">
		                ${mb.send_dept_nm} · ${mb.send_position_nm}
		            </div>
		            <div style="flex-grow: 1; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
		                <a href="javascript:void(0)" onclick="Detail('${mb.msg_no},${pageInfo.pageNumber },${pageInfo.whatColumn },${pageInfo.keyword }', 'message')">
		                    ${mb.msg_title}
		                </a>
		            </div>
		            <div style="min-width: 120px;">
		                <fmt:formatDate value="${mb.send_dtm}" pattern="MM.dd HH:mm"/>
		            </div>
		        </div>
			</c:forEach>
		</div>
	</div>
	<div id="paging" align="center" style="margin-top: 10px;">
		${pageInfo.pagingHtml}
	</div>
</div>