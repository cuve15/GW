<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="./../common/common.jsp" %>

<div id="authListContainer">
	<table border="1">
		<tr>
			<th>권한코드</th>
			<th>권한 이름</th>
			<th>수정</th>
			<th>삭제</th>
		</tr>	
		
		<c:forEach var="auth" items="${authList}">
		<tr>
			<th>${auth.auth_cd }</th>
			<th>${auth.auth_nm }</th>
			
			<th> <button class="open-edit-modal"
					  data-url="cmmCode_update.erp"
					  data-id="${auth.auth_cd }">
					  수정
			  </button>
			</th>
			<th>
			<a href="auth_delete.erp?auth_cd=${auth.auth_cd }">삭제</a>
			</th>
			
		</tr>	
		</c:forEach>
	</table>
</div>