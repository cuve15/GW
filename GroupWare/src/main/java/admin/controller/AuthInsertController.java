package admin.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import admin.model.AuthInfoBean;
import admin.model.AuthInfoDao;

@Controller
public class AuthInsertController {
	
	private final String command ="auth_insert.erp";
	private final String getPage ="admin/auth_insertForm";
	private final String gotoPage ="redirect:/main.erp?page=auth";
	
	@Autowired
	AuthInfoDao authInfoDao;
	
	
	@RequestMapping(value=command, method=RequestMethod.GET)
	public ModelAndView doAction() {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(getPage);
		return mav;
	}
	
	@RequestMapping(value=command,method=RequestMethod.POST)
	public ModelAndView doActioin(@ModelAttribute("authBean") @Valid AuthInfoBean authBean,BindingResult result) {
		
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) {
			
			mav.addObject("authBean", authBean);
			mav.setViewName(getPage);
			return mav;
		}
		int cnt = authInfoDao.insertAuth(authBean);
		mav.setViewName(gotoPage);
		
		return mav;
	}
}
