<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>로그인</title>
    <style>
        body {
            font-family: 'Pretendard', sans-serif;
            background: #f8f9fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .login-container {
            width: 320px;
            background: white;
            border: 1px solid #ddd;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.05);
            padding: 20px;
        }
        .tabs {
            display: flex;
            border-bottom: 1px solid #ccc;
            margin-bottom: 15px;
        }
        .tabs button {
            flex: 1;
            background: none;
            border: none;
            padding: 10px;
            font-weight: bold;
            cursor: pointer;
            color: #888;
        }
        .tabs button.active {
            color: #333;
            border-bottom: 2px solid #666; /* 초록 → 회색으로 변경 */
        }
        .tab-content {
            display: none;
        }
        .tab-content.active {
            display: block;
        }
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 8px 12px;
            margin-top: 6px;
            margin-bottom: 12px;
            border: 1px solid #ccc;
            border-radius: 6px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            width: 100%;
            background: #666; /* 회색 계열 */
            color: white;
            border: none;
            padding: 10px;
            border-radius: 6px;
            cursor: pointer;
            font-weight: bold;
        }
        input[type="submit"]:hover {
            background: #555; /* hover 시 더 진한 회색 */
        }
        .qr-img {
            display: block;
            margin: 0 auto 10px;
            border: 1px solid #ccc;
            border-radius: 8px;
        }
        .small-text {
            font-size: 12px;
            color: #888;
            text-align: center;
        }
        .small-text a {
            color: #888;
            text-decoration: none;
        }
        .small-text a:hover {
            text-decoration: underline;
            color: #555;
        }
    </style>
    <script>
        function showTab(tabId) {
            document.querySelectorAll('.tab-content').forEach(el => el.classList.remove('active'));
            document.querySelectorAll('.tabs button').forEach(el => el.classList.remove('active'));
            document.getElementById(tabId).classList.add('active');
            document.getElementById(tabId + '-btn').classList.add('active');
        }
        window.onload = () => showTab('id-login');
    </script>
</head>
<body>

<div class="login-container">
    <div class="tabs">
        <button id="id-login-btn" onclick="showTab('id-login')">사원번호</button>
        <button id="qr-login-btn" onclick="showTab('qr-login')">QR코드</button>
    </div>

    <!-- ID 로그인 -->
    <div class="tab-content" id="id-login">
        <form action="lsh_login.erp" method="post">
            <input type="text" name="emp_no" placeholder="사원번호" required>
            <input type="password" name="pw" placeholder="비밀번호" required>
            <input type="submit" value="로그인">
        </form>
    </div>

    <!-- QR 로그인 -->
    <div class="tab-content" id="qr-login">
        <form action="lsh_login.erp" method="post">
            <input type="hidden" name="uuid" value="${uuid}">
            <img class="qr-img" width="200" height="200"
                 src="https://api.qrserver.com/v1/create-qr-code/?data=http://localhost:8080/login/scan/qr/login?uuid=${uuid}&size=200x200" />
            <div class="small-text">휴대폰으로 QR 코드를 스캔해 로그인하세요</div>
            <div class="small-text" style="color:red;">${error}</div>
        </form>
    </div>

    <!-- 비밀번호 찾기 링크 -->
    <div class="small-text" style="margin-top: 12px;">
        <a href="sendPwAuth.erp">비밀번호 찾기</a>
    </div>
</div>

</body>
</html>
