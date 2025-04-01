package admin.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import admin.model.CmmCodeDao;

@Controller
public class CmmCodeDeleteController {
	
	private final String command ="cmmCode_delete.erp";
	private final String gotoPage ="redirect:/main.erp?page=cmmCode";
	
	@Autowired
	CmmCodeDao cmmCodeDao;
	
	
	@RequestMapping(value=command, method=RequestMethod.GET)
	public ModelAndView doAction(@RequestParam("cmm_nm") String cmm_nm) {
		
		ModelAndView mav = new ModelAndView();
		
		int cnt = cmmCodeDao.deleteCmmCode(cmm_nm);
		mav.setViewName(gotoPage);
		return mav;
	}
	
	
}
