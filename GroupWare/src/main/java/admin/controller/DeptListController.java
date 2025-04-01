package admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import admin.model.DeptBean;
import admin.model.DeptDao;

@Controller
public class DeptListController {
	
		private final String command = "/dept_list.erp";
		private final String gotoPage = "admin/dept_listForm";
		
		@Autowired
		DeptDao deptDao;
		
		@RequestMapping(command)
		public ModelAndView doAction() {
			
			ModelAndView mav = new ModelAndView();
			List<DeptBean> lists = deptDao.getAllDept();
			mav.addObject("lists",lists);
			mav.setViewName(gotoPage);
			
			return mav;
		}
		
}
