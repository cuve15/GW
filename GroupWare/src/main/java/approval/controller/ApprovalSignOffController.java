package approval.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import approval.model.ApprovalDao;

@Controller
public class ApprovalSignOffController {
	
	private final String command = "signOff.erp";
	private final String gotoPage = "redirect:/approvalList.erp";
	
	@Autowired
	private ApprovalDao approvalDao;
	
	@RequestMapping(command)
	public String singOf(@RequestParam("appr_no") int appr_no,
						@RequestParam("appr_order") int appr_order,
						HttpSession session) {
		String position_cd = (String) session.getAttribute("position_cd");
		
		System.out.println("appr_no : " + appr_no);
		System.out.println("appr_order : " + appr_order);
		
		approvalDao.signOffDocument(appr_no, appr_order, position_cd);
		
		return gotoPage;
	}
}
