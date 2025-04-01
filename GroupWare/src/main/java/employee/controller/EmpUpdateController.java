package employee.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import employee.model.EmployeeBean;
import employee.model.EmployeeDao;
import utility.AutoAuthority;

@Controller
public class EmpUpdateController {
	
		private final String command="emp_update.erp";
		private final String getPage = "employee/emp_updateForm";
		private final String gotoPage = "redirect:/emp_detail.erp";
		
		@Autowired
		EmployeeDao empDao;
		
		@Autowired
		AutoAuthority authority;
		
		@RequestMapping(value=command,method=RequestMethod.GET)
		public ModelAndView doAction(@RequestParam("id") String emp_no) {
			
			ModelAndView mav = new ModelAndView();
			
			EmployeeBean empBean = empDao.getOneEmp(emp_no);
			
			mav.addObject("empBean",empBean);
			mav.setViewName(getPage);
			
			return mav;
		}
		
		@RequestMapping(value=command,method=RequestMethod.POST)
		public ModelAndView doActioin(@ModelAttribute("empBean") @Valid EmployeeBean empBean,BindingResult result) {
			
			ModelAndView mav = new ModelAndView();
			if(result.hasErrors()) {
				
				mav.addObject("empBean", empBean);
				mav.setViewName(getPage);
				return mav;
			}
			
			int cnt = empDao.updateEmployee(empBean);
			
			authority.defaultAuthor(empBean);
			mav.setViewName(gotoPage+"?id="+empBean.getEmp_no());
			
			return mav;
		}
}
