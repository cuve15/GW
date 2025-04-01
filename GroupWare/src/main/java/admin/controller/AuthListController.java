package admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import admin.model.AuthInfoBean;
import admin.model.AuthInfoDao;

@Controller
public class AuthListController {

	
		private final String command = "auth_list.erp";
		private final String gotoPage = "admin/auth_listForm";
		
		@Autowired
		AuthInfoDao authInfoDao;
		
		@RequestMapping(value=command, method=RequestMethod.GET)
		public ModelAndView doAction() {
			ModelAndView mav = new ModelAndView();
			
			List<AuthInfoBean> authList = authInfoDao.getAllAuthInfo();
			
			
			mav.addObject("authList",authList);
			mav.setViewName(gotoPage);
			return mav;
		}
		
	
		
}
