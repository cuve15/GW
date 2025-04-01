package approval.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import approval.model.ApprovalDao;
   

@Controller
public class ApprovalCompanionController {
    private final String command = "rejectForm.erp";  
    private final String getPage = "approval/rejectForm";
    private final String gotoPage = "approval/approvalList";
      
        
    @Autowired
    private ApprovalDao approvalDao; // DAO 주입

    @RequestMapping(value = command, method = RequestMethod.GET)
    public String showRejectForm(@RequestParam("appr_no") int apprNo, Model model) {
        model.addAttribute("appr_no", apprNo); // appr_no 값을 모델에 담아 JSP로 전달
        return getPage; // 반려 사유 폼 페이지로 이동
    }
    
    @RequestMapping(value = command, method = RequestMethod.POST)
    public String rejectApproval(@RequestParam("appr_no") int apprNo,
                                 @RequestParam("appr_refuse_op") String appr_refuse_op,
                                 HttpServletRequest request) {
        // 반려 상태로 업데이트
    	System.out.println("appr_refuse_op : " + appr_refuse_op);
    	System.out.println("appr_no : " + apprNo);
        approvalDao.companionApprovalStatus(apprNo, appr_refuse_op); 
 
        // 반려 완료 후 승인 목록 페이지로 이동
        return gotoPage;  
    }
}
