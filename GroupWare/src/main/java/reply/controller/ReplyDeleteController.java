package reply.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import reply.model.ReplyDao;

@Controller
public class ReplyDeleteController {
	private final String command = "reply_delete.erp";
	private final String getPage = "redirect:/notice_content.erp";
	
	@Autowired
	ReplyDao rdao;
	
	@RequestMapping(command)
	public ModelAndView doAction(@RequestParam("reply_no") int reply_no,
								 @RequestParam("notice_no") int notice_no,
			 					 @RequestParam(value="pageNumber") int pageNumber,
			 					 @RequestParam(value="whatColumn",required = false) String whatColumn,
			 					 @RequestParam(value="keyword",required = false) String keyword) {
		
		int cnt = rdao.deleteReply(reply_no);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("notice_no", notice_no);
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("whatColumn", whatColumn);
		mav.addObject("keyword", keyword);
		mav.setViewName(getPage);
		
		return mav;
	}
	
}
