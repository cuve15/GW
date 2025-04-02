package employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import employee.model.EmployeeBean;
import employee.model.EmployeeDao;

@Controller
public class EmpDetailController {

	
	private final String command = "emp_detail.erp";
	private final String getPage = "employee/emp_detailForm";
	
	@Autowired
	EmployeeDao empDao;
	
	@RequestMapping(value=command, method=RequestMethod.GET)
	public ModelAndView doAction(@RequestParam("id") String emp_no,
				 				 @RequestParam(value="keyword",required= false)String keyword,
				 				 @RequestParam(value ="whatColumn", required =false)String whatColumn,
				 				 @RequestParam(value="pageNumber",required = false) String pageNumber) {
		
		ModelAndView mav = new ModelAndView();
		
		EmployeeBean empBean = empDao.getOneEmp(emp_no);
		mav.addObject("empBean",empBean);
		mav.addObject("keyword",keyword);
		mav.addObject("whatColumn",whatColumn);
		mav.addObject("pageNumber",pageNumber);
		mav.setViewName(getPage);
		return mav;
	}
	
}
