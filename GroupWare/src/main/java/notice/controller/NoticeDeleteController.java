package notice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import notice.model.NoticeDao;

@Controller
public class NoticeDeleteController {
	private final String command = "notice_delete.erp";
	private final String getPage = "redirect:/main.erp?page=totalNotice";
	
	@Autowired
	NoticeDao ndao;
	
	@RequestMapping(command)
	public ModelAndView doAction(@RequestParam("notice_no") int notice_no,
			 					 @RequestParam(value="pageNumber") int pageNumber,
			 					 @RequestParam(value="whatColumn",required = false) String whatColumn,
			 					 @RequestParam(value="keyword",required = false) String keyword) {
		
		int cnt = ndao.deleteNotice(notice_no);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("whatColumn", whatColumn);
		mav.addObject("keyword", keyword);
		mav.setViewName(getPage);
		
		return mav;
	}
	
}
