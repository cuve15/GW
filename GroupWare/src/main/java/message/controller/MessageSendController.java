package message.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import message.model.MessageBean;
import message.model.MessageDao;
import utility.Paging;

@Controller
public class MessageSendController {
	private final String command = "ymh_MessageSend.erp";
	private String getPage = "message/MessageSend";
	
	@Autowired
	MessageDao mdao;
	
	@RequestMapping(command)
	public ModelAndView doAction(HttpSession session, HttpServletRequest request,
								@RequestParam(value="whatColumn", required = false) String whatColumn,
								@RequestParam(value="keyword", required = false) String keyword,
								@RequestParam(value="pageNumber", required = false) String pageNumber) {
		
		System.out.println("send whatColumn : " + whatColumn);
		System.out.println("send keyword : " + keyword);
		System.out.println("send pageNumber : " + pageNumber);
		
//		System.out.println("whatColumn : " + whatColumn);
		
		ModelAndView mav = new ModelAndView();
		
		String pageSize= "10";
		
		Map<String,String> map = new HashMap<String,String>();
		
		map.put("whatColumn", whatColumn);
		map.put("keyword", "%" + keyword + "%");
		
		String url = request.getContextPath() + "/" + command;
		
		String emp_no = (String)session.getAttribute("emp_no");
		
		int totalCount = mdao.SendtotalCount(emp_no, map);
		
//		System.out.println("totalCount : " + totalCount);
		
		if(pageNumber != null) {
			if(Integer.parseInt(pageNumber) > Math.ceil((double)totalCount / Integer.parseInt(pageSize))) {
				pageNumber = String.valueOf(Integer.parseInt(pageNumber) - 1);
			}
		}
		
		Paging pageInfo = new Paging(pageNumber, pageSize, totalCount,url,whatColumn,keyword);
		
		// EmployeeBean loginInfo = session.getAttribute("loginInfo");
		
		int sendCount = mdao.sendCount(emp_no, map);
		
//		System.out.println("emp_no : " + emp_no);
		
		// 1 = loginInfo 의 receive_emp_no 로그인 한 대상이 받은 메시지를 모아온다.
		
		List<MessageBean> sendlist = mdao.getAllSend(emp_no, map, pageInfo);
		
//		System.out.println("receivelist.size() : " + sendlist.size());
//		System.out.println("sendCount : " + sendCount);
		
		mav.addObject("sendCount",sendCount);
		mav.addObject("sendlist",sendlist);
		mav.addObject("totalCount",totalCount);
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName(getPage);
		
		return mav;
	}
}
