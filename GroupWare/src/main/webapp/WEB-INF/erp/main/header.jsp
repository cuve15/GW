<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>
    * {font-family: 'Pretendard', sans-serif !important;}

        /* 헤더 스타일 */
        #header {
            width: 100%;
            height: 7vh; /* 사이드바와 자연스럽게 이어지도록 조정 */
            min-height: 50px;
            max-height: 70px;
            background-color: #f9fafa; /* 사이드바와 동일한 배경색 */
            border-bottom: 1px solid #ddd;
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 0 20px;
            position: fixed;
            top: 0;
            left: 0;
            z-index: 1000;
            box-sizing: border-box;
        }

        /* 헤더 왼쪽 (메신저 탭) */
        .header-left {
            display: flex;
            align-items: center;
            margin-left: 250px; /* 사이드바 너비만큼 이동 */
        }

        /* 메신저 탭 버튼 */
        .tab {
            padding: 10px 15px;
            font-weight: bold;
            cursor: pointer;
            color: #687482;
        }

        /* 활성화된 탭 */
        .tab.active {
            border-bottom: 3px solid #239afe;
            color: #239afe;
        }

        /* 오른쪽 버튼 */
        .header-button {
            padding: 8px 15px;
            background-color: #ddd;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-right: 30px;
        }
</style>

<header id="header">
    <div class="header-left" id="headerTabs">
       
    </div>
    <button id="headerBtn" onclick="openModal(currentModal)">
    등록
    </button>
</header>
