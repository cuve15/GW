package message.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import message.model.MessageBean;
import message.model.MessageDao;

@Controller
public class MessageDetailController {
	private final String command = "messageDetail.erp";
	private String gotoPage = "message/MessageDetail";
	
	@Autowired
	MessageDao mdao;
	
	@RequestMapping(command)
	public String doAction(@RequestParam("msg_no") String msg_no,HttpServletRequest request) {
		
		MessageBean mb = mdao.detailMessage(msg_no);
		
		request.setAttribute("mb", mb);
		
//		System.out.println("send_emp_no : " + mb.getSend_emp_no());
//		System.out.println("receive_emp_no : " + mb.getReceive_emp_no());
		
		return gotoPage;
	}
	
	
	
}
