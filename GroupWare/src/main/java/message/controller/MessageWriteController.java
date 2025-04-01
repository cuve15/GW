package message.controller;

import java.util.List;

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
import employee.model.EmployeeBean;
import employee.model.EmployeeDao;
import message.model.MessageBean;
import message.model.MessageDao;

@Controller
public class MessageWriteController {
	private final String command = "ymh_messageinsert.erp";
	private String getPage = "message/MessageWriteForm";
	private final String gotoPage ="redirect:/main.erp?page=send";
	
	FileUploadController fileUploadController = new FileUploadController(); 
	@Autowired
	MessageDao mdao;
	
	@Autowired
	EmployeeDao edao;
	
	@Autowired
	AttachDao attachDao;
	
	@RequestMapping(value=command, method=RequestMethod.GET)
	public String doAction(HttpServletRequest request) {
		
		List<EmployeeBean> list = edao.getAllEmployeeList();
		
		request.setAttribute("list", list);
		
		return getPage;
	}
	
	@RequestMapping(value=command, method=RequestMethod.POST)
	public ModelAndView doAction(@ModelAttribute("mb") @Valid MessageBean mb,BindingResult result,
								HttpServletRequest request,
								HttpServletResponse response,
								@RequestParam("file") MultipartFile file) {
		
		String savedFileName = fileUploadController.uploadFile(file, response);
		
		System.out.println("write");
//		System.out.println("title : " + mb.getMsg_title());
//		System.out.println("contnet : " + mb.getMsg_content());
//		System.out.println("receive_emp_no : " + mb.getReceive_emp_no());
//		System.out.println("send_emp_no : " + mb.getSend_emp_no());
		
		if (!file.isEmpty()) {
		    System.out.println("파일 이름: " + file.getOriginalFilename());
		    System.out.println("파일 크기: " + file.getSize());
		}
		
		List<EmployeeBean> list = edao.getAllEmployeeList();
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("list",list);
		
//		System.out.println("msg_title1 : " + mb.getMsg_title());
//		System.out.println("msg_content1 : " + mb.getMsg_content());
		
		if (mb.getMsg_title() == null || mb.getMsg_title().trim().isEmpty()) {
		    mb.setMsg_title(file.getOriginalFilename());
		}
		if (mb.getMsg_content() == null || mb.getMsg_content().trim().isEmpty()) {
		    mb.setMsg_content(file.getOriginalFilename());
		}
		
//		System.out.println("msg_title2 : " + mb.getMsg_title());
//		System.out.println("msg_content2 : " + mb.getMsg_content());
		
		/*
		 * if(result.hasErrors()) { mav.setViewName(getPage); return mav; }
		 */
		
		String[] emp_no_arr = null;
		
		if(mb.getReceive_emp_no() != null) {
			
			String input = mb.getReceive_emp_no();
			emp_no_arr = input.split("\\s*,\\s*"); // 공백과 콤마 제거 후 배열로 변환
			
		}

//		System.out.println("emp_no_arr.length : " + emp_no_arr.length);
		
		int cnt = -1;
		
		
		for(int i=0;i<emp_no_arr.length;i++) {
		
			mb.setReceive_emp_no(emp_no_arr[i]);
			
			cnt = mdao.SendMessage(mb); 
			
			String msg_no_seq = mdao.selectOneNum();
			
			System.out.println("msg_no_seq : " + msg_no_seq);
			
			AttachBean attach = new AttachBean();
	        attach.setCon_key1(msg_no_seq); 
	        attach.setOrg_file_name(file.getOriginalFilename());
	        attach.setServer_file_name(savedFileName);
	        attach.setFile_size(file.getSize());

	        int attachInsertCount = attachDao.insertAttach(attach);
			
			mav.setViewName(gotoPage);
			
		}
		
		if(cnt == -1) {
			mav.setViewName(getPage);
		}
		
		return mav;
	}
	
}
