<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="./../common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <title>인증코드 입력</title>
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

  .verify-container {
    width: 320px;
    background: white;
    padding: 20px;
    border-radius: 12px;
    box-shadow: 0 4px 12px rgba(0,0,0,0.08);
  }

  .verify-container form {
    width: 100%;
    box-sizing: border-box;
  }

  .verify-container input {
    width: 100%;
    padding: 10px;
    margin-bottom: 12px;
    border: 1px solid #ccc;
    border-radius: 6px;
    box-sizing: border-box;
  }

  .verify-container button {
    width: 100%;
    padding: 10px;
    background: #666;
    color: white;
    font-weight: bold;
    border: none;
    border-radius: 6px;
    cursor: pointer;
  }

  .verify-container button:hover {
    background: #555;
  }
</style>

</head>
<body>
  <div class="verify-container">
    <form action="verifyCode.erp" method="post">
      <input type="hidden" name="uuid" value="${uuid}">
      <input type="text" name="code" placeholder="인증코드" required>
      <button type="submit">인증 확인</button>
    </form>
    <c:if test="${not empty error}">
      <div style="color:red;">${error}</div>
    </c:if>
  </div>
</body>
</html>
