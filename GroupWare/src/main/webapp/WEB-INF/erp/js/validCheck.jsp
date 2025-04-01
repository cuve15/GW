<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script>
window.formSubmitConfig = window.formSubmitConfig || {
	  emp_insert: {
	    url: "lsh_insert.erp",
	    formId: "empinsertForm",
	    defaultRedirect: "emp"
	  },
	  emp_update: {
	    url: "emp_update.erp",
	    formId: "empUpdateForm",
	    defaultRedirect: "empUpdate"
	  },
	  dept_insert: {
	    url: "dept_insert.erp",
	    formId: "deptinsertForm",
	    defaultRedirect: "dept"
	  },
	  dept_update: {
	    url: "dept_update.erp",
	    formId: "deptupdateForm",
	    defaultRedirect: "dept"
	  },
	  cmmCode_insert: {
	    url: "cmmCode_insert.erp",
	    formId: "cmmCodeinsertForm",
	    defaultRedirect: "cmmCode"
	  },
	  cmmCode_update: {
	    url: "cmmCode_update.erp",
	    formId: "cmmCodeupdateForm",
	    defaultRedirect: "cmmCode"
	  },
	 	auth_insert: {
		    url: "auth_insert.erp",
		    formId: "authinsertForm",
		    defaultRedirect: "auth"
		  },
	 	auth_update: {
		    url: "auth_update.erp",
		    formId: "authupdateForm",
		    defaultRedirect: "auth"
		  },
		  mail_insert : {
				url : "ymh_messageinsert.erp",
				formId : "MessageWriteForm",
				target : "received",
				userFormData: true
			},
			mail_reply : {
				url : "ymh_messageReply.erp",
				formId : "MessageReplyForm",
				target : "received"
			},
			mail_pass : {
				url : "ymh_messagePass.erp",
				formId : "MessagePassForm",
				target : "received"
			},
			 notice_insert: {
				    url: "notice_write.erp",
				    formId: "notice_writeForm",
				    target: "notice"
				  }
	  
	};

	$(document).off('click', '#submitBtn').on('click', '#submitBtn', function () {
		  const config = formSubmitConfig[$(this).data('modal')];
		  const $form = $('#' + config.formId);
		  let formData;
		  if(config.userFormData){
			  formData = new FormData($form[0]);
		  }else{
			formData = $form.serialize();  
		  }
		  

		  $.ajax({
		    url: config.url,
		    method: 'POST',
		    data: formData,
		    processData: !config.userFormData,
            contentType: config.userFormData ? false : 'application/x-www-form-urlencoded',
		    success: function (html) {
		      const $newForm = $('<div>').html(html).find('form');

		      if ($newForm.length > 0) {
		        // 유효성 실패
		        $form.replaceWith($newForm);
		      } else {
		        // 유효성 성공
		        $('#customModal').hide();
		        $('#modalContent').empty();

		   const redirectPage = $form.find('input[name="redirectPage"]').val();

		        if (redirectPage) {
		          $.ajax({
		            url: redirectPage,
		            method: 'GET',
		            success: function (html) {
		              $('.main-content').html(html);
		            },
		            error: function () {
		              alert('리디렉션 실패');
		            }
		          });
		        }else{
		          loadContent(config.defaultRedirect); // 기본 본문 리로드
		        	}
		        }
		      $('#submitBtn').prop('disabled', false);
              
		      const toggleBtn = document.getElementById('toggleCheckboxList');
		      if (toggleBtn) {
		        toggleBtn.addEventListener('click', function () {
		          const checkboxList = document.getElementById('checkboxList');
		          if (checkboxList.style.display === "none") {
		            checkboxList.style.display = "block";
		            this.innerHTML = "▲ 받는 사람 선택 숨기기";
		          } else {
		            checkboxList.style.display = "none";
		            this.innerHTML = "▼ 받는 사람 선택";
		          }
		        });
		      }
		    },
		    error: function () {
		      alert("요청 중 오류 발생");
		    }
		  });
		});


</script>