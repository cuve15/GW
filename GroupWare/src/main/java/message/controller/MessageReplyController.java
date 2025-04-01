package message.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import attach.controller.FileUploadController;
import attach.model.AttachBean;
import attach.model.AttachDao;
import employee.model.EmployeeDao;
import message.model.MessageBean;
import message.model.MessageDao;

@Controller
public class MessageReplyController {
	private final String command = "ymh_messageReply.erp";
	private String getPage = "message/MessageReplyForm";
	private final String gotoPage ="redirect:/main.erp?page=send";
	
	FileUploadController fileUploadController = new FileUploadController(); 
	@Autowired
	MessageDao mdao;

	@Autowired
	EmployeeDao edao;
	
	@Autowired
	AttachDao attachDao;

	@RequestMapping(value=command, method=RequestMethod.GET)
	public String doAction(HttpServletRequest request,@RequestParam("msg_no") String msg_no) {

		MessageBean mb = mdao.detailMessage(msg_no);

		System.out.println("reply send_name : " + mb.getSend_emp_name()); // 나과장

		request.setAttribute("mb2", mb);

		return getPage;
	}

	@RequestMapping(value=command, method=RequestMethod.POST)
	public ModelAndView doAtion(@ModelAttribute("mb") @Valid MessageBean mb,BindingResult result,
								HttpServletRequest request,
								@RequestParam("msg2_no") String msg2_no,
								HttpServletResponse response,
								@RequestParam("file") MultipartFile file) {

		System.out.println("reply receive_no : " + mb.getReceive_emp_no());
		
		String savedFileName = fileUploadController.uploadFile(file, response);
		
		if (!file.isEmpty()) {
		    System.out.println("파일 이름: " + file.getOriginalFilename());
		    System.out.println("파일 크기: " + file.getSize());
		}
		
		ModelAndView mav = new ModelAndView();
		
		if (mb.getMsg_title() == null || mb.getMsg_title().trim().isEmpty()) {
		    mb.setMsg_title(file.getOriginalFilename());
		}
		if (mb.getMsg_content() == null || mb.getMsg_content().trim().isEmpty()) {
		    mb.setMsg_content(file.getOriginalFilename());
		}
		
		String msg_no_seq = mdao.selectOneNum();
		
		System.out.println("msg_no_seq : " + msg_no_seq);
		
		AttachBean attach = new AttachBean();
        attach.setCon_key1(msg_no_seq); 
        attach.setOrg_file_name(file.getOriginalFilename());
        attach.setServer_file_name(savedFileName);
        attach.setFile_size(file.getSize());

        int attachInsertCount = attachDao.insertAttach(attach);
		
		/*
		 * if(result.hasErrors()) { MessageBean mb2 = mdao.detailMessage(msg2_no);
		 * mav.addObject("mb2",mb2);
		 * 
		 * mav.setViewName(getPage);
		 * 
		 * return mav; }
		 */

		int cnt = -1;

		cnt = mdao.SendMessage(mb);
		
		System.out.println("reply cnt : " + cnt);
		
		mav.setViewName(gotoPage);

		return mav;
	}

}
