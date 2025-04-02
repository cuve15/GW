<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="./../common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <title>비밀번호 재설정</title>
  <style>
  body {
    background: #f8f9fa;
    font-family: 'Pretendard', sans-serif;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
  }

  .reset-container {
    width: 320px;
    background: white;
    padding: 20px;
    border-radius: 12px;
    box-shadow: 0 4px 12px rgba(0,0,0,0.08);
  }

  .reset-container form {
    width: 100%;
    box-sizing: border-box;
  }

  .reset-container input {
    width: 100%;
    padding: 10px;
    margin-bottom: 12px;
    border: 1px solid #ccc;
    border-radius: 6px;
    box-sizing: border-box;
  }

  .reset-container button {
    width: 100%;
    padding: 10px;
    background: #666;
    color: white;
    font-weight: bold;
    border: none;
    border-radius: 6px;
    cursor: pointer;
  }

  .reset-container button:hover {
    background: #555;
  }

  .error {
    color: red;
    font-size: 12px;
    text-align: center;
    margin-bottom: 10px;
  }
</style>

</head>
<body>
  <div class="reset-container">
    <form action="resetPwConfirm.erp" method="post">
      <input type="hidden" name="uuid" value="${uuid}">
      <input type="hidden" name="emp_no" value="${emp_no}">

      <input type="password" name="newPw" placeholder="새 비밀번호" required>
      <input type="password" name="confirmPw" placeholder="비밀번호 확인" required>
      <button type="submit">비밀번호 변경</button>

      <c:if test="${not empty error}">
        <div class="error">${error}</div>
      </c:if>
    </form>
  </div>
</body>
</html>
