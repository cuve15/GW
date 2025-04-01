package admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import admin.model.CmmCodeBean;
import admin.model.CmmCodeDao;

@Controller
public class CmmCodeListController {

	private final String command = "/cmm_list.erp";
	private final String gotoPage = "admin/cmmCode_listForm";
	
	@Autowired
	CmmCodeDao ccdao;
	
	@RequestMapping(command)
	public ModelAndView doAction() {
		
		ModelAndView mav = new ModelAndView();
		List<CmmCodeBean> lists = ccdao.getAllCmmCode();
		mav.addObject("lists",lists);
		mav.setViewName(gotoPage);
		
		return mav;
	}
	
}
