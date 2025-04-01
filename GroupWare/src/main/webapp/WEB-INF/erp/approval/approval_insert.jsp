<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<% String ctxPath = request.getContextPath(); %>
<% 
    String emp_no = (String) session.getAttribute("emp_no"); 
    String dept_cd = (String) session.getAttribute("dept_cd");
    
    System.out.println("emp_no : " +  emp_no);
    System.out.println("dept_cd : " +  dept_cd);

%>
<!-- 
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
Bootstrap CSS
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
FontAwesome for Icons
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
	rel="stylesheet">

<style type="text/css">
* {
	font-family: 'Pretendard', sans-serif !important;
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}



body {
	display: flex;
	height: 100%;
	overflow: hidden;
}


/* 메인 콘텐츠 스타일 */
#mainContent {
	flex-grow: 1;
	padding: 30px;
	background-color: #ffffff;
	overflow-y: auto;
	border-radius: 8px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	margin-left: 10px;
}

h2 {
	margin-bottom: 20px;
	font-size: 1.8rem;
	font-weight: bold;
	color: #333;
}

.form-label {
	font-weight: 600;
	color: #555;
}

.form-control {
	border-radius: 5px;
	border: 1px solid #ccc;
	padding: 10px;
	font-size: 1rem;
	margin-bottom: 15px;
	transition: border-color 0.3s ease;
}

.form-control:focus {
	border-color: #3498db;
	box-shadow: 0 0 5px rgba(52, 152, 219, 0.5);
}

textarea.form-control {
	height: 150px;
	resize: none;
}

.btn-submit {
	background-color: #3498db;
	border: none;
	color: white;
	font-size: 1.1rem;
	font-weight: 600;
	padding: 12px 30px;
	border-radius: 5px;
	transition: background-color 0.3s ease, transform 0.2s ease;
}

.btn-submit:hover {
	background-color: #2980b9;
	cursor: pointer;
	transform: scale(1.05);
}

.btn-submit:active {
	transform: scale(0.98);
}

.btn-outline-secondary {
	font-weight: 600;
}

.file-info {
	font-size: 0.9rem;
	color: gray;
}

.form-group {
	margin-bottom: 25px;
}

/* 전체 입력 필드 스타일 */
.form-control::placeholder {
	color: #aaa;
	font-style: italic;
}

.form-control:focus::placeholder {
	color: transparent;
}


/* 폼 스타일 */
.form-container {
	background-color: #ffffff;
	border-radius: 10px;
	padding: 30px;
	box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
	width: 100%;
	max-width: 800px;
	margin: 0 auto;
	border: 1px solid #ddd;
	background-color: #f9f9f9;
}

.icon-container {
	display: flex;
	justify-content: center;
	margin-bottom: 20px;
}

.icon-container i {
	font-size: 40px;
	color: #3498db;
}
</style>
</head> -->

<!-- 결제 서류 제출 폼 -->
<form id="paymentForm" enctype="multipart/form-data" action="approval_insert.erp" method="post" >

    <!-- 문서 제목 -->
    <div class="mb-3">
        <label for="doc_title" class="form-label">문서 제목</label>
        <input type="text" id="doc_title" name="doc_title" class="form-control" placeholder="문서 제목을 입력하세요" required />
    </div>

    <!-- 결제 내용 -->
    <div class="mb-3">
        <label for="doc_content" class="form-label">결제 내용</label>
        <textarea id="doc_content" name="doc_content" class="form-control" placeholder="결제 내용을 작성하세요" required></textarea>
    </div>

    <!-- 첨부파일 업로드 -->
    <div class="mb-3">
        <label for="attachment_file" class="form-label">첨부파일 업로드</label>
        <input type="file" id="file" name="file" class="form-control" required />
        <small class="file-info">첨부 가능한 파일 형식: PDF, DOCX, JPG, PNG</small>
    </div>

    <!-- hidden 필드로 사원 번호 전달 -->
    <input type="hidden" name="emp_no" value='${sessionScope.emp_no }'>
    
    <!-- hidden 필드로 직책 코드 전달 -->
    <input type="hidden" name="dept_cd" value='${sessionScope.dept_cd }'>

    <!-- 제출 버튼 -->
    <input type="submit" value="추가하기" class="btn-submit">
</form>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</html>
