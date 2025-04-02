package vacation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import utility.Paging;
import vacation.model.VacationBean;
import vacation.model.VacationDao;

@Controller
public class VacationListController {

    private final String command = "vacationList.erp";  // URL 매핑
    private final String getPage = "vacation/vacationList";  // JSP 파일 위치

    @Autowired
    VacationDao vdao;  // VacationDao 객체 주입

    @RequestMapping(command)
    public ModelAndView vacationList(HttpSession session, HttpServletRequest request, 
            @RequestParam(value = "pageNumber", required = false) String pageNumber,
            @RequestParam(value = "whatColumn", required = false) String whatColumn,
            @RequestParam(value = "keyword", required = false) String keyword) {
        
        // 세션에서 로그인한 사용자 사원 번호 가져오기
        String emp_no = (String) session.getAttribute("emp_no");
        
        // 검색할 컬럼과 키워드를 맵에 저장
        Map<String, String> map = new HashMap<String, String>();
        map.put("whatColumn", whatColumn);
        map.put("keyword", "%" + keyword + "%");
        map.put("emp_no", emp_no);
        
        System.out.println("whatColumn: " + whatColumn);
        System.out.println("keyword: " + keyword);
        System.out.println("emp_no: " + emp_no);
        
        // 전체 목록의 수를 구하기 위한 쿼리
        int totalCount = vdao.getTotalCount(map);
        
        // 페이지네이션 처리
        String url = request.getContextPath() + "/" + command;
        Paging pageInfo = new Paging(pageNumber, "5", totalCount, url, whatColumn, keyword);
        
        // 해당 페이지에 맞는 휴가 목록 조회 
        List<VacationBean> vacationList = vdao.getVacationList(pageInfo, map);
        
        // ModelAndView 객체 생성 후 데이터 전달
        ModelAndView mav = new ModelAndView();
        mav.addObject("totalCount", totalCount);
        mav.addObject("vacationList", vacationList);
        mav.addObject("pageInfo", pageInfo);
        
        // JSP 뷰로 이동
        mav.setViewName(getPage);
        return mav;
    }
}