<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 본문,헤더버튼 관리 페이지 -->
<script>
window.pageConfig = window.pageConfig || {
    emp: {
      button: "사원 등록",
      modal: "emp_insert",
     container:"empListContainer",
      tabs: [
        { label: "사원 목록", target: "emp" }
      ]
    },
    dept: {
      button: "부서등록",
      modal: "dept_insert",
      tabs: [
        { label: "부서관리", target: "dept" }
      ]
    },
    cmmCode: {
      button: "공통코드등록",
      modal: "cmmCode_insert",
      tabs: [
        { label: "공통코드 관리", target: "cmmCode" }
      ]
    },
    authInfo: {
      button: "권한등록",
      modal: "authInfo_insert",
      tabs: [
        { label: "권한부여 관리", target: "authInfo" }
      ]
    },
    auth: {
      button: "권한등록",
      modal: "auth_insert",
      tabs: [
        { label: "권한관리", target: "auth" }
      ]
    },
    received: {
	      button: "메일 보내기",
	      modal: "mail_insert",
	      container:"msgReceiveListContainer",
	      tabs: [
	        { label: "받은 쪽지", target: "received" }
	      ]
	 },
	    send: {
		      button: "메일 보내기",
		      modal: "mail_insert",
		      tabs: [
		        { label: "보낸 쪽지", target: "send" }
		      ]
		    },
		    totalNotice: {
			      button: "공지사항 작성",
			      modal: "notice_insert",
			      tabs: [
			        { label: "전체 공지", target: "totalNotice" }
			      ]
			    },
			    appr: {
				      button: "결제 신청",
				      modal: "appr_insert",
				      tabs: [
				        { label: "결제 관리", target: "appr" }
				      ]
				    },
			    commute: {
				      button: "",
				      modal: "",
				      tabs: [
				        { label: "출퇴근 기록", target: "commute" }
				      ]
				    }
  };//본문을 헤더에 연결할 tabs 변수, button이름 & modal 연결할 url변수 설정을 위한 변
	

	window.modalPages = window.modalPages || {
			  emp_insert: 'lsh_insert.erp',
			  dept_insert: 'dept_insert.erp',
			  cmmCode_insert: 'cmmCode_insert.erp',
			  authInfo_insert: 'authInfo_insert.erp',
			  auth_insert: 'auth_insert.erp',
			  mail_insert: 'ymh_messageinsert.erp',
			  notice_insert: 'notice_write.erp',
			  appr_insert : 'approval_insert.erp'
			};//modal을 열면서 연결될 .erp 설정


	window.currentModal = '';
	window.currentPage ='';
	

		function loadContent(page) {
			currentPage = page;
		  	$.ajax({
		    	url: 'router.erp',
		   		type: 'GET',
		   		data: { page: page },
		   		success: function(html) {
		     	   $('.main-content').html(html);

		           const config = pageConfig[page];
		           if (config) {
		           $('#headerBtn').text(config.button);
		           currentModal = config.modal;
		         } else {
		           $('#headerBtn').text('');
		           currentModal = '';
		         }
		        },
		    error: function() {
		      alert('본문을 불러오는 중 오류가 발생했습니다.');
		    }
		  });
		}//본문 함수
		
		
		function bindTabEvents(){
			$(".tab").off("click").on("click",function(){
				$(".tab").removeClass("active");
				$(this).addClass("active");
				
				const targetPage = $(this).data("target");
				if(targetPage){
					loadContent(targetPage);
				}
			});
		}//tab 클릭시 페이지교체(헤더제목 누를시)
		
		window.onload = function() {//"초기화 역할" + "URL 진입 대응" + "자동 탭/본문 로딩"
			  const params = new URLSearchParams(window.location.search); 
			  const page = params.get("page");                            
			 //url에서 page=emp 같은 값을 추출
			  
			  if (page && pageConfig[page]) {//page가 pageConfig에 정의되어있는지 확인
			    const config = pageConfig[page];
				
			    // 탭 자동 구성
			    if (config.tabs) {
			      const $headerTabs = $("#headerTabs");
			      $headerTabs.empty();

			      $.each(config.tabs, function (index, tab) {
			        const isActive = index === 0 ? "active" : "";
			        const html = '<span class="tab '+isActive+ '"data-target="'+tab.target+'">'+tab.label+'</span>';
			        $headerTabs.append(html);
			      });

			      bindTabEvents();
			      loadContent(config.tabs[0].target);
			    }
				    }else{
			    loadContent(page);    
				
			  }
			};
			
			$(document).ready(function () {
			  $(".sideTr").on("click", function () {
			    handleSidebar(this);
			  });
			});
			
			
			$(document).ready(function () {
				console.log("검색함수 실행")
				  function performSearch() {
				    const searchData = $('#empSearchForm').serialize();
				    console.log("📦 검색 데이터:", searchData);

				    $.ajax({
				      url: 'lsh_list.erp',
				      method: 'GET',
				      data: searchData,
				      success: function (html) {
				        $('.main-content').html(html);
				      },
				      error: function () {
				        alert('검색 실패');
				      }
				    });
				  }

				  $(document).on('click','#searchBtn',function () {
				    performSearch();
				  });

				  $(document).on('submit','#empSearchForm', function (e) {
				    e.preventDefault();
				    performSearch();
				  });

				  $(document).on('keydown','#keywordInput', function (e) {
				    if (e.key === 'Enter') {
				      e.preventDefault();
				      performSearch();
				    }
				  });
				});//검색 클릭이나 엔터 누를식 본문만 바뀌는 함수


			
		    $(document).on('click', '#paging a', function(e) {
		    	  e.preventDefault(); // 기본 페이지 이동 막기

		    	  const url = $(this).attr('href');
				 const targetId = pageConfig[currentPage]?.container;
				 const $target = $('#'+targetId);
		    	  $.ajax({
		    	    url: url,
		    	    method: 'GET',
		    	    success: function(html) {
		    	      // 받은 html에서 리스트 영역만 추출해서 교체
		    	      const newList = $('<div>').html(html).find('#'+targetId).html();
		    	      $target.html(newList);
		    	    },
		    	    error: function(xhr) {
		    	      console.log("❌ 페이징 실패", xhr.status);
		    	    }
		    	  });
		    	});//페이징 클릭시 본문만 바뀌는함수
		
		    	function loadMessageDetail(msg_no) { // YMH detail
				    $.ajax({
				        url: 'messageDetail.erp',  // 서버에서 메세지 상세 정보를 처리할 URL
				        type: 'GET',
				        data: { msg_no: msg_no },  // 메세지 ID를 서버로 전달
				        success: function(html) {
				            $('.main-content').html(html);  // 가져온 HTML을 main-content에 삽입
				        },
				        error: function() {
				            alert('메세지 로딩 중 오류가 발생했습니다.');
				        }
				    });
				} // loadMessageDetail
				
				function MessageReply(msg_no) {
					currentModal = "mail_reply";
					$.ajax({
				        url: 'ymh_messageReply.erp',  // 서버에서 메세지 상세 정보를 처리할 URL
				        type: 'GET',
				        data: { msg_no: msg_no },  // 메세지 ID를 서버로 전달
				        success: function(html) {
				        	$('#modalContent').html(html);
							$('#customModal').show();
				        },
				        error: function() {
				            alert("모달 로딩 실패");
				        }
				    });
				} // MessageReply
				
				function MessagePass(msg_no) {
					currentModal = "mail_pass";
					$.ajax({
				        url: 'ymh_messagePass.erp',  // 서버에서 메세지 상세 정보를 처리할 URL
				        type: 'GET',
				        data: { msg_no: msg_no },  // 메세지 ID를 서버로 전달
				        success: function(html) {
				        	$('#modalContent').html(html);
							$('#customModal').show();
				        },
				        error: function() {
				            alert("모달 로딩 실패");
				        }
				    });
				} // MessagePass
				
				
				function MessageDelete(msg_no) {
					$.ajax({
				        url: 'ymh_messageDelete.erp',  // 서버에서 메세지 상세 정보를 처리할 URL
				        type: 'GET',
				        data: { msg_no: msg_no },  // 메세지 ID를 서버로 전달
				        success: function(html) {
				        	$('.main-content').html(html);
				        },
				        error: function() {
				            alert("삭제 중 오류가 발생했습니다.");
				        }
				    });
					
				} // MessageDelete
		
</script>