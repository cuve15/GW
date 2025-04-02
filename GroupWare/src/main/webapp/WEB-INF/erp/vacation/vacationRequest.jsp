<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<% 
    String emp_no = (String) session.getAttribute("emp_no"); 
    System.out.println("emp_no : " +  emp_no);
%>
<script>
function validateCheckbox() {
    let vacationType = document.querySelector('input[name="vacation_type"]:checked');
    
    if (!vacationType) {
        alert("휴가 유형을 선택하세요.");
        return false;
    }
    return true;
}
</script>

<!-- 휴가 신청 폼 -->

<form action="vacation_insert.erp" method="post" onsubmit="return validateCheckbox()">
    <!-- hidden 필드로 사원 번호 전달 -->
    <input type="hidden" name="emp_no" value="${sessionScope.emp_no}">
    <h2>휴가 신청</h2>

    <label>휴가 유형</label><br>
    <input type="radio" name="vacation_type" value="연차"> 연차
    <input type="radio" name="vacation_type" value="반차"> 반차
    <input type="radio" name="vacation_type" value="병가"> 병가
    <input type="radio" name="vacation_type" value="기타"> 기타 <br><br>
    
    <label>사원 번호 : </label>
    <input type="text" name="req_emp_no" value="${sessionScope.emp_no}" readonly><br><br>

    <label>휴가 사유:</label><br>
    <textarea name="vacation_reason" rows="4" cols="50"></textarea><br><br>

    <label>시작일:</label>
    <input type="date" name="start_dt"><br><br>

    <label>종료일:</label>
    <input type="date" name="end_dt"><br><br>

    <!-- 제출 버튼 -->
    <input type="submit" value="신청">
</form>


