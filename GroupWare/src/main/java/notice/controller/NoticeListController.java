package notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import notice.model.NoticeBean;
import notice.model.NoticeDao;
import utility.Paging;

@Controller
public class NoticeListController {
	private final String command = "/notice_list.erp";
	private final String getPage1 = "notice/notice_all_listForm";
	private final String getPage2 = "notice/notice_dept_list";
	private final String getPage3 = "notice/notice_my_list";
	
	@Autowired
	NoticeDao ndao;
	
	@RequestMapping(command)
	public ModelAndView doAction(@RequestParam(value = "whatColumn", required = false) String whatColumn,
								 @RequestParam(value = "keyword", required = false) String keyword,
								 @RequestParam(value = "pageNumber", required = false) String pageNumber,
								 HttpServletRequest request) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("whatColumn", whatColumn);
		map.put("keyword", "%" + keyword + "%");
		
		int totalCount = ndao.getTotalCount(map);
		
		String url = request.getContextPath() + "/" + command;
		
		Paging pageInfo = new Paging(pageNumber, "10", totalCount, url, whatColumn, keyword);
		
		List<NoticeBean> lists = ndao.selectAllNotice(pageInfo, map);
		
		ModelAndView mav = new ModelAndView(); 
		mav.addObject("noticeLists", lists);
		mav.addObject("totalCount", totalCount);
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName(getPage1);
		return mav;
	}
}
