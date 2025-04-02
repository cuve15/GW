package vacation.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import vacation.model.VacationBean;
import vacation.model.VacationDao;


@Controller
public class VacationRequestController {
	
	private final String command = "vacation_insert.erp";
	private final String getPage = "vacation/vacationRequest";
	private final String gotoPage = "redirect:/vacationList.erp"; // 성공 시 이동할 페이지
	
	@Autowired
	private VacationDao vdao;
	
	@RequestMapping(value = command, method = RequestMethod.GET)
	public String showVacationForm() {
		return getPage;
	}
	
	@RequestMapping(value = command, method = RequestMethod.POST)
	public ModelAndView submitVacationRequest(@ModelAttribute("vacation") @Valid VacationBean vacation, 
			BindingResult result, HttpSession session) {  
		
		ModelAndView mav = new ModelAndView();

		// 유효성 검사 체크
		if (result.hasErrors()) {
			mav.setViewName(getPage);
			return mav;
		}

		// 세션에서 emp_no 가져와서 Bean에 저장
		String emp_no = (String) session.getAttribute("emp_no");
		

	
		 // 로그 확인 
		  System.out.println("vacation_no: " + vacation.getVacation_no());
		  System.out.println("vacation_type: " + vacation.getVacation_type());
		  System.out.println("vacation_reason: " + vacation.getVacation_reason());
		  System.out.println("vacation_status: " + vacation.getVacation_status());
		  System.out.println("start_dt: " + vacation.getStart_dt());
		  System.out.println("end_dt: " + vacation.getEnd_dt());

		// DB 저장
		int cnt = vdao.insertVacation(vacation);

		if (cnt > 0) {
			mav.setViewName(gotoPage);
		} else {
			mav.setViewName(getPage); // 저장 실패 시 폼으로 복귀
		}

		return mav;
	}
}
