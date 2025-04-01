package message.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import employee.model.EmployeeDao;
import message.model.MessageBean;
import message.model.MessageDao;

@Controller
public class MessageReplyController {
	private final String command = "ymh_messageReply.erp";
	private String getPage = "message/MessageReplyForm";
	private final String gotoPage ="redirect:/main.erp?page=send";

	@Autowired
	MessageDao mdao;

	@Autowired
	EmployeeDao edao;

	@RequestMapping(value=command, method=RequestMethod.GET)
	public String doAction(HttpServletRequest request,@RequestParam("msg_no") String msg_no) {

		MessageBean mb = mdao.detailMessage(msg_no);

		System.out.println("reply send_name : " + mb.getSend_emp_name()); // ³ª°úÀå

		request.setAttribute("mb2", mb);

		return getPage;
	}

	@RequestMapping(value=command, method=RequestMethod.POST)
	public ModelAndView doAtion(@ModelAttribute("mb") @Valid MessageBean mb,BindingResult result,
			HttpServletRequest request,
			@RequestParam("msg2_no") String msg2_no) {

		System.out.println("reply receive_no : " + mb.getReceive_emp_no());


		ModelAndView mav = new ModelAndView();


		if(result.hasErrors()) {
			MessageBean mb2 = mdao.detailMessage(msg2_no);
			mav.addObject("mb2",mb2);
			
			mav.setViewName(getPage);
			
			return mav;
		}

		int cnt = -1;

		cnt = mdao.SendMessage(mb);
		
		System.out.println("reply cnt : " + cnt);
		
		mav.setViewName(gotoPage);

		return mav;
	}

}
