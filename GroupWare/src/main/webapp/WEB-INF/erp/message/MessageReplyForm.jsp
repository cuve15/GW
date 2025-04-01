<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="./../common/common.jsp" %>

<style type="text/css">

	#checkboxList div {
        margin-bottom: 5px;
    }
    #checkboxList {
        margin-top: 10px;
    }
    #toggleCheckboxList {
        background: none;
        border: none;
        cursor: pointer;
        font-size: 14px;
        padding: 0;
        color: blue;
    }
	.err{
		font-size: 10pt;
		color: red;
		font-weight: bold;
	}
	
</style>

<form action="ymh_messageReply.erp" method="post" id="MessageReplyForm">
	<input type="hidden" value="${sessionScope.emp_no }" name="send_emp_no">
	<input type="hidden" value="${mb2.send_emp_no }" name="receive_emp_no">
	<input type="hidden" value="${mb2.msg_no }" name="msg2_no">
	<input type="hidden" name="redirectPage" value="messageDetail.erp?msg_no=${mb2.msg_no}">
    <div>
        <div>
            <label>받는사람 : </label>
			<label>${mb2.send_emp_name}</label>
        </div>
    </div>
    <div>
        <div>
            <label>제목</label>
            <input type="text" name="msg_title" value="${mb.msg_title }">
        </div>
    </div>
   
    <div>
        <div>
            <label>내용</label>
            <textarea cols="40" rows="5" name="msg_content">${mb.msg_content }</textarea>
        </div>
    </div>
    
    <div>
		<div>
			<label>첨부파일</label> <input type="file" name="file" required />
		</div>
	</div>
	<input type="button" value="메일 전송" id="submitBtn" data-modal="mail_reply">
</form>

<%@include file = "./../js/validCheck.jsp"%>
