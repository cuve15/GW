package message.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import message.model.MessageBean;
import message.model.MessageDao;

@Controller
public class MessageDeleteController {
	private final String command = "ymh_messageDelete.erp";
	private String receivePage = "redirect:/ymh_MessageReceive.erp"; // 받은 메일
	private String sendPage = "redirect:/ymh_MessageSend.erp"; // 보낸 메일
	
	@Autowired
	MessageDao mdao;
	
	@RequestMapping(command)
	public ModelAndView doAction(HttpServletRequest request,@RequestParam("msg_no") String msg_no,
						@RequestParam(value="whatColumn", required = false) String whatColumn,
						@RequestParam(value="keyword", required = false) String keyword,
						@RequestParam(value="pageNumber", required = false) String pageNumber,
						 HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		
		System.out.println("delete msg_no : " + msg_no);
		
		MessageBean mb = mdao.detailMessage(msg_no);
		
		String emp_no = (String)session.getAttribute("emp_no"); 
		
//		System.out.println("delete_yn : " + mb.getDel_yn());
//		System.out.println("emp_no : " + emp_no);
//		System.out.println("mb.getReceive_emp_no() : " + mb.getReceive_emp_no());
//		System.out.println("mb.getSend_emp_no() : " + mb.getSend_emp_no());
		
		int cnt = -1;
		
		if(emp_no.equals(mb.getReceive_emp_no())) { // 받은사람
			
//			System.out.println("받은사람");
			mav.setViewName(receivePage);
			
			if(mb.getDel_yn() != "O") {
				
				cnt = mdao.deleteMessage(mb);
				
			}else {
				
				cnt = mdao.deleteReceiveUpdateMessage(mb);
				
			}
		}else if(emp_no.equals(mb.getSend_emp_no())) { // 보낸사람
			
//			System.out.println("보낸사람");
			mav.setViewName(sendPage);
			
			if(mb.getDel_yn() != "O") {
				
				cnt = mdao.deleteMessage(mb);
				
			}else {
				
				cnt = mdao.deleteSendUpdateMessage(mb);
				
			}
			
		}
		
		System.out.println("cnt : " + cnt);
		
		return mav;
	}
	
}
