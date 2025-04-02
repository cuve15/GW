<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- header부분-->
<script>

	
window.tabSets = window.tabSets || {
		  msg: [
		    { label: "받은 쪽지", target: "received" },
		    { label: "보낸 쪽지", target: "send" }
		  ],
		  alarm: [
		    { label: "안읽은 알림", target: "alarmUnread" },
		    { label: "전체 알림", target: "alarmAll" }
		  ],
		  notice: [
			  { label: "전체 공지", target: "totalNotice" },
			    { label: "부서 공지", target: "deptNotice" },
			    { label: "내가 쓴 공지", target: "myNotice" }
		  ],
		  emp: [
		    { label: "사원 목록", target: "emp" }
		  ],
		  calender: [
		    { label: "월간 일정", target: "calendarMonth" },
		    { label: "주간 일정", target: "calendarWeek" }
		  ],
		  comment: [
		    { label: "출퇴근 기록", target: "commute" }
		  ],
		  vacation: [
		    { label: "휴가 확인", target: "vacation" },
		    { label: "휴가 신청", target: "vacationRequest" }
		  ],
		  appr: [
			  { label: "내 결재 요청", target: "appr" },
			    { label: "결재 완료", target: "apprSuc" },
			    { label: "결재 해야하는 리스트", target: "apprList" }
		  ],
		  pay: [
		    { label: "급여 내역", target: "payHistory" },
		    { label: "지급 요청", target: "payRequest" }
		  ],
		  admin: [
		    { label: "부서관리", target: "dept" },
		    { label: "공통코드 관리", target: "cmmCode" },
		    { label: "권한부여 관리", target: "authInfo" },
		    { label: "권한관리", target: "auth" }
		  ]
		};



	
	function handleSidebar(btn){
		
		
		const action = btn.dataset.action;//sidebar.jsp의 data-action
		const target = btn.dataset.target;//sidebar.jsp의 data-target
		
		const whatColumn = btn.querySelector("input[name='whatColumn']")?.value || "";
	    const keyword = btn.querySelector("input[name='keyword']")?.value || "";
	    const pageNumber = btn.querySelector("input[name='pageNumber']")?.value || "1";
	    
		if(action === "page"){
			const tabs = tabSets[target];//해당 메뉴의 탭세트 가져오기
			
			 
			if(tabs){//만약 tabs이 존재하면
				 const $headerTabs = $("#headerTabs");//$변수이름은 Jquery객체라고 표시해준것
				 //상단탭들이 들어갈 div나 span묶음의 jQuery객
				 $headerTabs.empty();//기존탭 비우기
				 
				 $.each(tabs, function(index, tab){//탭 배열을 하나씩 돌면서(jQuery 반복문)
					 
					 const isActive = index === 0 ? "active" :""; // 첫번째 탭에만 "active"클래스 부여해서 스타일링
					 const html = '<span class="tab '+isActive+ '"data-target="'+tab.target+'">'+tab.label+'</span>';
					 
					 $headerTabs.append(html);//생성한 탭 HTML을 실제 DOM에 추가
				 });
				 
				 bindTabEvents();
				 //span을 클릭했을때 이벤트를만들어주는것(어떤 본문을 불러올지 연결하는 작업)
				 loadContent(tabs[0].target, { whatColumn, keyword, pageNumber });
			 }else{
				 //탭세트 없으면 바로 로딩
			loadContent(target, { whatColumn, keyword, pageNumber });
				 
			 }
		}else if(action === "modal"){
			openModal(target);
		}
	}

</script>