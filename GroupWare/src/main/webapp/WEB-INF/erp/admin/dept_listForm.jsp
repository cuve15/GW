<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="./../common/common.jsp" %>

<form id="deptSearchForm">
  <select name="whatColumn">
    <option value="">전체 선택</option>
    <option value="dept_cd">부서코드</option>
    <option value="dept_nm">부서이름</option>
  </select>
  <input type="text" name="keyword" id="deptkeywordInput">
  <input type="button" value="검색" id="deptsearchBtn">  
</form>



<div id="deptListContainer">
	<table border="1">
		<tr>
			<th>부서코드</th>
			<th>부서이름</th>
			<th>상위부서코드</th>
			<th>수정</th>
		</tr>	
		
		<c:forEach var="dept" items="${lists}">
		<tr>
			<th>${dept.dept_cd }</th>
			<th>${dept.dept_nm }</th>
			<th>${dept.p_dept_cd }</th>
			<th>
			  <button class="open-edit-modal"
					  data-url="dept_update.erp"
					  data-id="${dept.dept_cd }">
					  수정
			  </button>
			</th>
		</tr>	
		</c:forEach>
	</table>
	<br><br>
<div id="paging">
${pageInfo.pagingHtml}
</div>
</div>

