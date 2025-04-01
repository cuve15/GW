<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<h2>QR 코드 로그인</h2>

<input type="hidden" name="uuid" value="${uuid}">

<p>핸드폰으로 아래 QR을 스캔해주세요.</p>
<p style="color:red;">${error}</p>
<img src="https://api.qrserver.com/v1/create-qr-code/?data=http://localhost:8080/login/scan/qr/login?uuid=${uuid}&size=200x200" />



