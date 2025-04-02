<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="./../common/common.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>비밀번호 찾기</title>
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

    .findpw-container {
      width: 360px;
      padding: 24px;
      background: white;
      border-radius: 12px;
      box-shadow: 0 4px 12px rgba(0,0,0,0.08);
    }

    .findpw-container h2 {
      text-align: center;
      margin-bottom: 24px;
    }

    .findpw-container label {
      font-weight: bold;
      display: block;
      margin-bottom: 6px;
      margin-top: 12px;
    }

    .findpw-container input {
      width: 100%;
      padding: 10px 12px;
      border: 1px solid #ccc;
      border-radius: 6px;
      box-sizing: border-box;
      margin-bottom: 14px;
    }

    .findpw-container button {
      width: 100%;
      padding: 10px;
      background: #666;
      color: white;
      border: none;
      border-radius: 6px;
      font-weight: bold;
      cursor: pointer;
    }

    .findpw-container button:hover {
      background: #555;
    }

    .message {
      color: red;
      font-size: 13px;
      text-align: center;
      margin-top: 12px;
    }
  </style>
</head>
<body>
<div class="findpw-container">
  <h2>비밀번호 찾기</h2>
  <form action="sendPwAuth.erp" method="post">
    <label for="emp_no">사원번호</label>
    <input type="text" id="emp_no" name="emp_no" placeholder="사원번호 입력" required>

    <label for="email">이메일</label>
    <input type="email" id="email" name="email" placeholder="이메일 입력" required>

    <button type="submit">인증 메일 발송</button>

    <c:if test="${not empty error}">
      <div class="message" style="color:red;">${error}</div>
    </c:if>
    <c:if test="${not empty message}">
      <div class="message" style="color:green;">${message}</div>
    </c:if>
  </form>
</div>
</body>
</html>
    