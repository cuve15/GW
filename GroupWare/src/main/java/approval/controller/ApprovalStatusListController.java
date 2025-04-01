package approval.controller;

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

import approval.model.ApprovalBean;
import approval.model.ApprovalDao;
import utility.Paging;

@Controller
public class ApprovalStatusListController {

    private final String command = "/approvalList.erp";
    private final String getPage = "approval/approvalList"; // JSP 파일 이름

    @Autowired
    ApprovalDao adao; // ApprovalDao 주입

    @RequestMapping(command)
    public ModelAndView approvalList(HttpSession session, HttpServletRequest request, @RequestParam(value = "pageNumber", required = false) String pageNumber,
    		@RequestParam(value = "whatColumn", required = false) String whatColumn,
			@RequestParam(value = "keyword", required = false) String keyword) {
        // ApprovalDao를 사용해 데이터 가져오기
    	String emp_no = (String) session.getAttribute("emp_no");
      
    	Map<String, String> map = new HashMap<String,String>();
    	map.put("whatColumn", whatColumn);
    	map.put("keyword", "%" + keyword + "%");
    	map.put("emp_no", emp_no);
    	
    	int totalCount = adao.getTotalCount(map);
        String url  = request.getContextPath() + "/" + command;
        
        Paging pageInfo = new Paging(pageNumber, "5", totalCount, url, whatColumn, keyword);
        
        List<ApprovalBean> ApprovalList= adao.getApprovalList(pageInfo, map);
        
        ModelAndView mav = new ModelAndView();
        mav.addObject("totalCount", totalCount);
        mav.addObject("ApprovalList", ApprovalList);
        mav.addObject("pageInfo", pageInfo); 
        
        mav.setViewName(getPage); // JSP 페이지 설정
        return mav;
    }
}
