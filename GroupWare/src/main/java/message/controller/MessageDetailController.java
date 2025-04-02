package message.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import attach.model.AttachBean;
import attach.model.AttachDao;
import message.model.MessageBean;
import message.model.MessageDao;

@Controller
public class MessageDetailController {
	private final String command = "messageDetail.erp";
	private String gotoPage = "message/MessageDetail";
	
	@Autowired
	MessageDao mdao;
	
	@Autowired
	AttachDao attachDao;
	
	@RequestMapping(command)
	public String doAction(@RequestParam("msg_no") String msg_no,HttpServletRequest request, HttpSession session,
							@RequestParam(value="whatColumn", required = false) String whatColumn,
							@RequestParam(value="keyword", required = false) String keyword,
							@RequestParam(value="pageNumber", required = false) String pageNumber) {
		
		String emp_no = (String)session.getAttribute("emp_no");
		MessageBean mb = mdao.detailMessage(msg_no,emp_no);
		AttachBean ab = attachDao.getOneSelect(msg_no);
		
		request.setAttribute("mb", mb);
		request.setAttribute("ab", ab);
		request.setAttribute("whatColumn", whatColumn);
		request.setAttribute("keyword", keyword);
		request.setAttribute("pageNumber", pageNumber);
		
//		System.out.println("filename : " + ab.getOrg_file_name());
		
		System.out.println("whatColumn : " + whatColumn);
		System.out.println("keyword : " + keyword);
		System.out.println("pageNumber : " + pageNumber);
		
//		System.out.println("send_emp_no : " + mb.getSend_emp_no());
//		System.out.println("receive_emp_no : " + mb.getReceive_emp_no());
		
		return gotoPage;
	}
	
	
	
}
