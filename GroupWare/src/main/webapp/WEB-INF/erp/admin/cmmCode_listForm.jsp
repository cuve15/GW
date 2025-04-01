<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="./../common/common.jsp" %>

<div id="cmmCodeListContainer">
	<table border="1">
		<tr>
			<th>공통코드 class</th>
			<th>공통코드 코드</th>
			<th>공통코드 이름</th>
			<th>수정</th>
			<th>삭</th>
		</tr>	
		
		<c:forEach var="cmmCode" items="${lists}">
		<tr>
			<th>${cmmCode.cmm_class }</th>
			<th>${cmmCode.cmm_cd }</th>
			<th>${cmmCode.cmm_nm }</th>
			<th> <button class="open-edit-modal"
					  data-url="cmmCode_update.erp"
					  data-id="${cmmCode.cmm_nm }">
					  수정
			  </button>
			</th>
			<th>
			<a href="cmmCode_delete.erp?cmm_nm=${cmmCode.cmm_nm }">삭제</a>
			</th>
			
		</tr>	
		</c:forEach>
	</table>
</div>
