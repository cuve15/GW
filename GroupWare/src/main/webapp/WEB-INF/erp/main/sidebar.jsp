<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- /WEB-INF/views/sidebar.jsp -->
<style>
          /* 사이드바 */
        #sideBar {
            width: 250px;
            height: calc(100vh - 7vh); /* 헤더 높이를 제외한 사이드바 높이 */
            background-color: #f9fafa; /* 헤더와 동일한 배경색 */
            position: fixed;
            left: 0;
            top: 7vh; /* 헤더 높이와 일치 */
            padding: 15px;
            box-sizing: border-box;
        }

        .sideTr {
            width: 100%;
            height: 40px;
            border-radius: 0.5rem;
            display: flex;
            align-items: center;
            font-weight: bold;
            color: #687482;
            margin-top: 3px;
            padding: 10px;
            cursor: pointer;
        }

        .sideTr:hover {
            background-color: #efefef;
        }

        .profile-container {
            display: flex;
            align-items: center;
            gap: 12px;
        }

        div#prof {
            width: 50px;
            height: 50px;
            border-radius: 40%;
            background-color: #239afe;
            color: white;
            display: flex;
            align-items: center;  
            justify-content: center;
            font-weight: bold;
        }

        #top_prof {
            display: flex;
            flex-direction: column;
            padding: 6px 12px;
            border-radius: 8px;
        }

        .emp_nm {
            font-weight: bold;
            font-size: 14px;
            color: #4a4a4a;
        }

        .dept {
            font-weight: normal;
            color: gray;
            font-size: 10pt;
            display: flex;
            padding-top: 3px;
        }

</style>

<div id="sideBar">
    <div id="sideTop">
        <div class="profile-container">
            <div id="prof">${sessionScope.emp_nm}</div>
            <div id="top_prof">
                <div>
                    <span class="emp_nm">${sessionScope.emp_nm}</span>
                    <span class="dept">${sessionScope.dept_nm} · ${sessionScope.position_nm}</span>
                </div>
            </div>
        </div>
    </div>

    <div id="sideMiddle">
    <div class="sideTr" data-action="page" data-target="msg">
        <i class="fas fa-paper-plane sideIcon"></i>메신저
    </div>
    <div class="sideTr" data-action="page" data-target="alarm">
        <i class="fas fa-bell sideIcon"></i>새로운 소식
    </div>
    <div class="sideTr" data-action="page" data-target="notice">
        <i class="fas fa-flag sideIcon"></i>공지사항
    </div>
    <div class="sideTr" data-action="page" data-target="emp" >
        <i class="fas fa-users sideIcon"></i>구성원
    </div>
    <div class="sideTr" data-action="page" data-target="calender">
        <i class="fas fa-calendar-check sideIcon"></i>캘린더
    </div>
    <div class="sideTr" data-action="page" data-target="commute">
        <i class="fas fa-clock sideIcon"></i>출퇴근
    </div>
    <div class="sideTr" data-action="page" data-target="vacation">
        <i class="fas fa-plane sideIcon"></i>휴가
    </div>
    <div class="sideTr" data-action="page" data-target="appr">
        <i class="fas fa-pen-nib sideIcon"></i>전자결재
    </div>
    <div class="sideTr" data-action="page" data-target="pay">
        <i class="fas fa-dollar-sign sideIcon"></i>급여
    </div>
    <div class="sideTr" data-action="page" data-target="admin">
        <i class="fas fa-dollar-sign sideIcon"></i>관리자페이지
        
    </div>
</div>

</div>
    