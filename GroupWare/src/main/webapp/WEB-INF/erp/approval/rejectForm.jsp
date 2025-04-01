<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String apprNo = request.getParameter("appr_no");
%>
<html>
<head>
    <title>반려 사유 입력</title>
</head>
<body>
    <h2>반려 사유 입력</h2>
    <form action="rejectForm.erp" method="post">
        <input type="hidden" name="appr_no" value="<%= apprNo %>">
        <label for="appr_refuse_op">반려 사유:</label><br>
        <textarea name="appr_refuse_op" id="appr_refuse_op" rows="5" cols="40"></textarea><br>
        <input type="submit" value="제출">
    </form>
</body>
</html>
