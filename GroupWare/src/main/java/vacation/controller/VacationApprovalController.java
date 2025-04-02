package vacation.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vacation.model.VacationDao;

@Controller
public class VacationApprovalController {

    // 요청 URL과 리다이렉트할 페이지 정의
    private final String command = "vacationApproval.erp";  // 요청 URL 매핑
    private final String gotoPage = "vacation/vacationList";  // 승인 후 이동할 페이지

    // VacationDao 객체 자동 주입
    @Autowired
    private VacationDao vdao;

    // 휴가 승인 처리 메서드
    @RequestMapping(command)
    public String vacationApproval(@RequestParam("vacation_no") String vacation_no, HttpSession session) throws Exception {

        // 세션에서 직급(position_cd)과 로그인된 사원의 번호(emp_no) 가져오기
        String position_cd = (String) session.getAttribute("position_cd");
        String emp_no = (String) session.getAttribute("emp_no");  // 로그인된 사원의 emp_no

        // 디버깅을 위한 출력
        System.out.println("vacation_no : " + vacation_no);
        System.out.println("position_cd : " + position_cd);

        // 직급이 4보다 작은 경우에만 승인 가능 (직급: 1-사원, 2-과장, 3-부장, 4-사장 등)
        if (Integer.parseInt(position_cd) < 4) {
            // vacation_no와 emp_no를 사용하여 승인 처리
        	vdao.vacationApproval(vacation_no, emp_no);
            // 승인 후 vacationList 페이지로 이동
            return gotoPage;
        } else {
            // 직급이 4 이상인 경우 승인이 불가하므로 예외 처리
            throw new Exception("승인할 수 없는 직급입니다.");
        }
    }
}
