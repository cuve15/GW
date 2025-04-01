<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- /WEB-INF/views/main.jsp -->
<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>main</title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>

    <style>
        .main-content {
            margin-left: 250px;
            margin-top: 7vh;
            padding: 20px;
            box-sizing: border-box;
        }
    </style>
</head>
<body>

    <!-- header include -->
    <jsp:include page="header.jsp" />

    <!-- sidebar include -->
    <jsp:include page="sidebar.jsp" />

    <!-- 본문 -->
    <div class="main-content">
    	
    </div>
    
    <!-- 모달 영역 -->
	<div id="customModal" 
		style="display:none; position:fixed; top:10%; left:50%; transform:translateX(-50%); background:#fff; border:1px solid #ccc; padding:20px; z-index:9999;">
 		<div id="modalContent"></div>
  		<button onclick="closeModal()">닫기</button>
	</div>
    
    
    <%@include file ="./../js/headerTap.jsp" %>
    <%@include file ="./../js/mainContent.jsp" %>
    <%@include file ="./../js/modalControl.jsp" %>
    <%@include file ="./../js/detailHandler.jsp" %>
    
   
	
</body>
</html>
    