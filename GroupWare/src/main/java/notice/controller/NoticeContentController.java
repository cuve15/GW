package notice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import notice.model.NoticeBean;
import notice.model.NoticeDao;
import reply.model.ReplyBean;
import reply.model.ReplyDao;

@Controller
public class NoticeContentController {
	private final String command = "notice_content.erp";
	private final String getPage = "notice/notice_content";
	
	@Autowired
	NoticeDao ndao;
	
	@Autowired
	ReplyDao rdao;
	
	@RequestMapping(value=command, method=RequestMethod.GET)
	public ModelAndView doAction(@RequestParam("notice_no") int notice_no,
			 					 @RequestParam(value="pageNumber") int pageNumber,
			 					 @RequestParam("whatColumn") String whatColumn,
			 					 @RequestParam("keyword") String keyword) {
		
		NoticeBean nb = ndao.selectOneNotice(notice_no);
		List<ReplyBean> lists = rdao.selectAllReply(notice_no);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("notice", nb);
		mav.addObject("replyLists", lists);
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("whatColumn", whatColumn);
		mav.addObject("keyword", keyword);
		mav.setViewName(getPage);
		
		return mav;
	}
}
