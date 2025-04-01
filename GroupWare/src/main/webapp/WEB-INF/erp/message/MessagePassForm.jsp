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
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>
<script>
	
	document.getElementById('toggleCheckboxList').addEventListener('click', function() {
	    var checkboxList = document.getElementById('checkboxList');
	    if (checkboxList.style.display === "none") {
	        checkboxList.style.display = "block";
	        this.innerHTML = "▲ 받는 사람 선택 숨기기";
	    } else {
	        checkboxList.style.display = "none";
	        this.innerHTML = "▼ 받는 사람 선택";
	    }
	});

</script>

<form:form action="ymh_messageinsert.erp" commandName="mb" method="post" id="MessagePassForm">
	<input type="hidden" value="${sessionScope.emp_no }" name="send_emp_no">
	<input type="hidden" value="${mb.msg_no }" name="msg_no">
	<input type="hidden" name="redirectPage" value="messageDetail.erp?msg_no=${mb.msg_no }">
    <div>
        <div>
            <!-- 화살표 버튼을 누르면 체크박스 목록이 나타남 -->
            <label>받는사람</label>
	            <form:errors cssClass="err" path="receive_emp_no"/>
            <br>
            <button type="button" id="toggleCheckboxList">▼ 받는 사람 선택</button>
            <div id="checkboxList" style="display:none;">
                <c:forEach var="eb" items="${list}">
                	<c:if test="${eb.emp_no != sessionScope.emp_no}">
                        <div>
                            <input type="checkbox" name="receive_emp_no" value="${eb.emp_no}">
                            <label>${eb.emp_nm}</label>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </div>
    <div>
        <div>
            <label>제목</label>
            <input type="text" name="msg_title" value="${mb.msg_title }" readonly>
        </div>
    </div>
   
    <div>
        <div>
            <label>내용</label>
            <textarea cols="40" rows="5" name="msg_content" readonly>${mb.msg_content }</textarea>
        </div>
    </div>
    
    <div>
		<div>
			<label>${attach2.server_file_name }</label>	
		</div>
	</div>
	<input type="button" value="메일 전송" id="submitBtn" data-modal="mail_pass">
</form:form>

<%@include file = "./../js/validCheck.jsp"%>