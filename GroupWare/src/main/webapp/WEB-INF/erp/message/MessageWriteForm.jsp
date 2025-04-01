<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="./../common/common.jsp"%>

messageinsertForm
<br>
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

.err {
	font-size: 10pt;
	color: red;
	font-weight: bold;  
}
</style>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>
<script>
	
$(document).on('click', '#toggleCheckboxList', function () {
    const checkboxList = $('#checkboxList');
    if (checkboxList.css('display') === "none") {
        checkboxList.show();
        $(this).html("▲ 받는 사람 선택 숨기기");
    } else {
        checkboxList.hide();
        $(this).html("▼ 받는 사람 선택");
    }
});

</script>

<form action="ymh_messageinsert.erp" method="post" id="MessageWriteForm"
	enctype="multipart/form-data">
	<input type="hidden" name="redirectPage" value="ymh_MessageSend.erp">
	<input type="hidden" value="${sessionScope.emp_no }" name="send_emp_no">
	<div>
		<div>
			<!-- 화살표 버튼을 누르면 체크박스 목록이 나타남 -->
			<label>받는사람</label> <br>
			<button type="button" id="toggleCheckboxList">▼ 받는 사람 선택</button>
			<div id="checkboxList" style="display: none;">
				<c:forEach var="eb" items="${list}">
					<c:if test="${eb.emp_no != sessionScope.emp_no}">
						<div>
							<input type="checkbox" name="receive_emp_no" value="${eb.emp_no}"
								required> <label>${eb.emp_nm}</label>
						</div>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
	<div>
		<div>
			<label>제목</label> <input type="text" name="msg_title"
				value="${mb.msg_title }">
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
	<input type="button" value="메일 전송" id="submitBtn"
		data-modal="mail_insert">
</form>

<%@include file="./../js/validCheck.jsp"%>