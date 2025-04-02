<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- detail에서 작업하는 ajax모음 -->
<script>
$(document).on('click','.detail-link',function(e){
	e.preventDefault();
	
	const url = $(this).data('url');
	const id = $(this).data('id');
	const pageNumber = $(this).data('page');
	const whatColumn = $(this).data('column');
	const keyword = $(this).data('keyword');
	
	
	$.ajax({
		url: url,
		method:'GET',
		data:{id: id,
		      pageNumber: pageNumber,
		      whatColumn: whatColumn,
		      keyword: keyword},
		success: function(html){
			$('.main-content').html(html);
		},
		error: function(xhr){
			console.log("상세페이지 로딩 실패",xhr.status);
		}
	});//ajax
	
});//detail link로 들어가기 위한 ajax





</script>