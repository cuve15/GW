package notice.controller;

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
import notice.model.NoticeBean;
import notice.model.NoticeDao;

@Controller
public class NoticeWriteController {
	private final String command = "notice_write.erp";
	private final String getPage = "notice/notice_writeForm";
	private final String gotoPage = "redirect:/main.erp?page=totalNotice";
	
	FileUploadController notice_upload = new FileUploadController();
	
	@Autowired
	NoticeDao ndao;
	
	@RequestMapping(value=command, method=RequestMethod.GET)
	public ModelAndView doAction() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(getPage);
		
		return mav;
	}
	
	@RequestMapping(value=command, method=RequestMethod.POST)
	public ModelAndView doAction(HttpServletRequest request,
								 @ModelAttribute("notice") @Valid NoticeBean notice,
								 BindingResult result,
								 @RequestParam("file") MultipartFile file,
                                 HttpServletResponse response) {
		
		String savedFileName = notice_upload.uploadFile(file, response);
		
		ModelAndView mav = new ModelAndView();
		
		System.out.println("date"+notice.getNotice_dtm());
		System.out.println("notice_no"+notice.getNotice_title());
		System.out.println("title"+notice.getNotice_title());
		System.out.println("notice_content"+notice.getNotice_content());
		System.out.println("dept_nm"+notice.getDept_nm());
		System.out.println("emp_no"+notice.getEmp_no());
		System.out.println("emp_nm"+notice.getEmp_no());
		System.out.println("temporary_save"+notice.getTemporary_save());
		System.out.println("notice_level"+notice.getNotice_level());
		
		if(result.hasErrors()) {
			mav.setViewName(getPage);
			return mav;
		}
		
		int cnt = ndao.writeNotice(notice);
		mav.setViewName(gotoPage);
		return mav;
	}
}
