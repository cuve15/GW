package notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import notice.model.NoticeBean;
import notice.model.NoticeDao;

@Controller
public class NoticeSaveController {
	private final String command = "notice_save.erp";
	private final String getPage = "notice/notice_writeForm";
	private final String gotoPage = "redirect:/main.erp?page=totalNotice";
	
	@Autowired
	NoticeDao ndao;
	
	@RequestMapping(command)
	public ModelAndView doAction(HttpServletRequest request,
			 					 @ModelAttribute("notice") @Valid NoticeBean notice,
			 					 BindingResult result) {
		
		ModelAndView mav = new ModelAndView();
		
		if(result.hasErrors()) {
			mav.setViewName(getPage);
			return mav;
		}
		
		int cnt = ndao.saveNotice(notice);
		mav.setViewName(gotoPage);
		return mav;
	}
}
