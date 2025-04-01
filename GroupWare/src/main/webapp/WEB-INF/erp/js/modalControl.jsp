<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--modal작업하는 페이지 -->
<script>
function openModal(page) {
			  const url = modalPages[page];
			  if (!url) {
			    alert("모달 페이지가 설정되지 않았습니다.");
			    return;
			  }

			  $.ajax({
			    url: url,
			    method: 'GET',
			    success: function(html) {
			      $('#modalContent').html(html);
			      $('#customModal').show();
			    },
			    error: function() {
			      alert("모달 로딩 실패");
			    }
			  });
			}//모달열기 함수
		
		function closeModal(){
			$('#customModal').hide();
			$('#modalContent').empty();
		}//모달 닫기 함수
		
		$(document).on('click', '.open-edit-modal', function () {
			  const url = $(this).data('url');
			  const id = $(this).data('id');

			  $.ajax({
			    url: url,
			    method: 'GET',
			    data: { id: id },
			    success: function (html) {
			      $('#modalContent').html(html);
			      $('#customModal').show();
			    },
			    error: function () {
			      alert('수정 모달 로딩 실패');
			    }
			  });
			});//상세페이지 들어가서 수정,전달,답장 등을 위한 모달
</script>