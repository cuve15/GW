package notice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import notice.model.NoticeBean;
import notice.model.NoticeDao;

@Controller
public class NoticeUpdateController {
	private final String command = "notice_update.erp";
	private final String getPage = "notice/notice_UpdateForm";
	private final String gotoPage = "redirect:/main.erp?page=totalNotice";
	
	@Autowired
	NoticeDao ndao;
	
	@RequestMapping(value=command, method=RequestMethod.GET)
	public ModelAndView doAction(@RequestParam("notice_no") int notice_no,
			 					 @RequestParam(value="pageNumber") int pageNumber,
			 					 @RequestParam(value="whatColumn",required = false) String whatColumn,
			 					 @RequestParam(value="keyword",required = false) String keyword) {
		
		NoticeBean nb = ndao.selectOneNotice(notice_no);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("notice", nb);
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("whatColumn", whatColumn);
		mav.addObject("keyword", keyword);
		mav.setViewName(getPage);
		
		return mav;
	}
	
	@RequestMapping(value=command, method=RequestMethod.POST)
	public ModelAndView doAction(@ModelAttribute("notice") @Valid NoticeBean notice, BindingResult result,
								 @RequestParam(value="pageNumber",required = false) String pageNumber,
								 @RequestParam(value="whatColumn",required = false) String whatColumn,
								 @RequestParam(value="keyword",required = false) String keyword) {
		
		ModelAndView mav = new ModelAndView();
		
		if(result.hasErrors()) {
        	mav.addObject("pageNumber", pageNumber);
            mav.addObject("whatColumn", whatColumn);
            mav.addObject("keyword", keyword);
			mav.setViewName(getPage);
			return mav;
		}
		
		int cnt = ndao.updateNotice(notice);
		mav.addObject("notice_no", notice.getNotice_no());
        mav.addObject("pageNumber", pageNumber);
        mav.addObject("whatColumn", whatColumn);
        mav.addObject("keyword", keyword);
        mav.setViewName(gotoPage);
        
		return mav;
	}
}
