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
public class ApprovalCompleteListController {

    private final String command = "/approvalComplete.erp";
    private final String getPage = "approval/approvalCompleteList"; // JSP �뙆�씪 �씠由�

    @Autowired
    ApprovalDao adao; // ApprovalDao 二쇱엯

    @RequestMapping(command)
    public ModelAndView approvalList(HttpSession session, HttpServletRequest request, @RequestParam(value = "pageNumber", required = false) String pageNumber,
    		@RequestParam(value = "whatColumn", required = false) String whatColumn,
			@RequestParam(value = "keyword", required = false) String keyword) {
        // ApprovalDao瑜� �궗�슜�빐 �뜲�씠�꽣 媛��졇�삤湲�
    	String emp_no = (String) session.getAttribute("emp_no");
      
    	Map<String, String> map = new HashMap<String,String>();
    	map.put("whatColumn", whatColumn);
    	map.put("keyword", "%" + keyword + "%");
    	map.put("emp_no", emp_no);
    	
    	int totalCount = adao.getTotalCount(map);
        String url  = request.getContextPath() + "/" + command;
        
        Paging pageInfo = new Paging(pageNumber, "5", totalCount, url, whatColumn, keyword);
        
        List<ApprovalBean> ApprovalCompleteList = adao.getApprovalCompleteList(pageInfo, map);
           
        ModelAndView mav = new ModelAndView();
        mav.addObject("totalCount", totalCount);
        mav.addObject("ApprovalCompleteList", ApprovalCompleteList);
        mav.addObject("pageInfo", pageInfo); 
        
        mav.setViewName(getPage); // JSP �럹�씠吏� �꽕�젙
        return mav;
    }
}
