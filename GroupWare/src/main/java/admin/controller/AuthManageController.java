package admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import admin.model.AuthInfoBean;
import admin.model.AuthInfoDao;
import admin.model.EmployeeAuthBean;
import admin.model.EmployeeAuthDao;
import employee.model.EmployeeBean;
import employee.model.EmployeeDao;

@Controller
public class AuthManageController {
	
		private final String command = "/empAuthInfo.erp";
		private final String getPage = "admin/authInfo_listForm";
		
	@Autowired
	AuthInfoDao authInfodao;
	
	@Autowired
	EmployeeAuthDao empAuthdao;
	
	@Autowired
	EmployeeDao empdao;
	
	@RequestMapping(value=command, method=RequestMethod.GET)
	public ModelAndView doAction() {
		ModelAndView mav = new ModelAndView();
		
		List<AuthInfoBean> authList = authInfodao.getAllAuthInfo();
		
		List<EmployeeBean> empList = empdao.getAllEmployeeJoinAuth();
		
		mav.addObject("authList",authList);
		mav.addObject("empList",empList);
		
		mav.setViewName(getPage);
		return mav;
	}
	
	@RequestMapping(value=command, method=RequestMethod.POST)
	public String doAction(@RequestParam("auth_cd") String auth_cd,
						   @RequestParam(value="emp_no", required=false) String[] empNo) {
		
			if(empNo != null) {//체크박스 하나라도 체크했을
				for(String emp_no : empNo) {
					String alreadyAuth = empAuthdao.getAuthByEmpNo(emp_no,auth_cd);// 선택한 사원번호가 이미 권한 테이블안에 등록돼 있는지 확인하는 
					if(alreadyAuth == null) {
						int cnt1 = empAuthdao.insertEmployeeAuth(emp_no,auth_cd);
					}else {
						int cnt2 = empAuthdao.updateEmployeeAuth(emp_no,auth_cd);
						
					}
				}
			}
		
		
		
		return null;
	}
}
