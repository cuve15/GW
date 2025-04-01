package admin.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import admin.model.CmmCodeBean;
import admin.model.CmmCodeDao;

@Controller
public class CmmCodeUpdateController {
	
	private final String command ="cmmCode_update.erp";
	private final String getPage ="admin/cmmCode_updateForm";
	private final String gotoPage ="redirect:/main.erp?page=cmmCode";
	
	@Autowired
	CmmCodeDao cmmCodeDao;
	
	
	@RequestMapping(value=command, method=RequestMethod.GET)
	public ModelAndView doAction(@RequestParam("id") String cmm_nm) {
		
		ModelAndView mav = new ModelAndView();
		CmmCodeBean cmmCodeBean = cmmCodeDao.getOneCmmCode(cmm_nm);
		
		mav.addObject("cmmCodeBean",cmmCodeBean);
		mav.setViewName(getPage);
		return mav;
	}
	
	@RequestMapping(value=command,method=RequestMethod.POST)
	public ModelAndView doActioin(@ModelAttribute("cmmCodeBean") @Valid CmmCodeBean cmmCodeBean,BindingResult result) {
		
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) {
			
			mav.addObject("cmmCodeBean", cmmCodeBean);
			mav.setViewName(getPage);
			return mav;
		}
		
		int cnt = cmmCodeDao.updateCmmCode(cmmCodeBean);
		mav.setViewName(gotoPage);
		
		return mav;
	}
}
