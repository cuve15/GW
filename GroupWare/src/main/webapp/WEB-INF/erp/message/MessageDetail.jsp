<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="./../common/common.jsp" %>
<%
	String path = request.getContextPath();
%>

<div style="display: flex; justify-content: flex-start; align-items: center; gap: 20px;">
    <h4>
    	<c:if test="${mb.send_emp_no != sessionScope.emp_no }">
	        <div class="sideTr" data-action="page" data-target="received" onclick="handleSidebar(this)">
	            <input type="hidden" value="${whatColumn}" name="whatColumn">
	            <input type="hidden" value="${keyword}" name="keyword">
	            <input type="hidden" value="${pageNumber}" name="pageNumber">
	            <i class="fas fa-paper-plane sideIcon"></i>< 받은 메일함
	        </div>
        </c:if>
    </h4>
    <h4><c:if test="${mb.send_emp_no == sessionScope.emp_no }">
	        <div class="sideTr" data-action="page" data-target="send" onclick="handleSidebar(this)">
	            <input type="hidden" value="${whatColumn}" name="whatColumn">
	            <input type="hidden" value="${keyword}" name="keyword">
	            <input type="hidden" value="${pageNumber}" name="pageNumber">
	            <i class="fas fa-paper-plane sideIcon"></i>< 보낸 메일함
	        </div>
        </c:if>
    </h4>
</div>


<div id="receivelist">
    <div style="display: flex; padding: 10px; font-weight: bold; border-bottom: 2px solid #000; gap: 20px;">
    <c:if test="${mb.send_emp_no != sessionScope.emp_no }">
	    <div style="min-width: 50px;" align="center">
	    	<a href="javascript:void(0)" onclick="MessageReply('${mb.msg_no}')">답장</a>
	    </div>
	    <div>
	    	|
	    </div>
	    <div style="min-width: 50px;" align="center">
	    	<a href="javascript:void(0)" onclick="MessagePass('${mb.msg_no}')">전달</a>
	    </div>
	    <div>
	    	|
	    </div>
    </c:if>
	    <div style="min-width: 50px;" align="center">
	    	<a href="javascript:void(0)" onclick="MessageDelete('${mb.msg_no}')">삭제</a>
	    </div>
	</div>
</div>
<div style="display: flex; flex-direction: column; padding: 10px; border-bottom: 1px solid #ddd; gap: 20px;">
	<div style="font-weight: bold; font-size: 20px;">${mb.msg_title }</div>
	<div>보낸사람 : ${mb.send_emp_name }</div>
	<div>받는사람 : ${mb.receive_emp_name }</div>
	<div style="font-size: 14px; color: #808080;">
        <fmt:formatDate value="${mb.send_dtm}" pattern="yyyy년 M월 d일 (E) a h:mm"/>
    </div>
</div>
<div  style="border-bottom: 1px solid #ddd;">
	<div style="margin: 30px auto; width: 70%; padding: 10px 20px;">
	    ${mb.msg_content }
	</div>
	<div align="right" style="margin-bottom: 20px; position: relative; width: 70%;">
		<a href="download.erp?msg_no=${mb.msg_no}">
			${ab.org_file_name }
		</a>
	</div>
</div>
