package employee.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import employee.model.EmployeeBean;
import employee.model.EmployeeDao;
import utility.AutoAuthority;

@Controller
public class EmpInsertController {
	
	private final String command ="lsh_insert.erp";
	private final String getPage ="employee/emp_insertForm";
	private final String gotoPage ="redirect:/main.erp?page=emp";
	
	@Autowired
	EmployeeDao empdao;
	
	@Autowired
	AutoAuthority authority;
	
	@RequestMapping(value=command, method=RequestMethod.GET)
	public String doAction() {
		
		return getPage;
	}
	
	@RequestMapping(value=command,method=RequestMethod.POST)
	public ModelAndView doActioin(@ModelAttribute("empBean") @Valid EmployeeBean empBean,BindingResult result) {
		System.out.println("입사일:"+empBean.getHire_date());
		
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) {
			
			mav.addObject("empBean", empBean);
			mav.setViewName(getPage);
			return mav;
		}
		
		int cnt = empdao.insertEmployee(empBean);
		authority.defaultAuthor(empBean);
		mav.setViewName(gotoPage);
		
		return mav;
	}
}
