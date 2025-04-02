package Login.controller;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import Login.model.LoginDao;
import employee.model.EmployeeDao;

@Controller
public class FindPassword {
	
	    @Autowired
	    EmployeeDao empDao;
	    
	    @Autowired
	    JavaMailSender mailSender;
	    
	    @Autowired
	    LoginDao loginDao;
	    
	    
	    //비밀번호찾기폼으로 이동하는 메서드
	    @RequestMapping(value = "/sendPwAuth.erp", method=RequestMethod.GET)
	    public String doAction() {
	    	
	    	
	    	return "/login/findPwForm";
	    }
	    
	    //인증번호 메일로 보내는 메서드
	    @RequestMapping(value = "/sendPwAuth.erp", method=RequestMethod.POST)
	    public ModelAndView doAction(@RequestParam("emp_no") String emp_no,
	    							 @RequestParam("email")String email) {
	    	
	    	ModelAndView mav = new ModelAndView();
	    	Map<String,String> map = new HashMap<String,String>();
	    	map.put("email", email);
	    	map.put("emp_no", emp_no);
	    	
	    	boolean checkEmpEmail = empDao.checkEmployee(map);
	    	if(!checkEmpEmail) {
	    		mav.addObject("error","입력하신 정보와 일치하는 사용자가 없습니다.");
	    		mav.setViewName("/login/findPwForm");
	    		return mav;
	    	}
	    	
	    	 	String uuid = UUID.randomUUID().toString();
	    	    String code = String.valueOf((int)(Math.random() * 900000) + 100000); // 6자리
	    	 try {
	    	        MimeMessage message = mailSender.createMimeMessage();
	    	        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

	    	        helper.setTo(email);
	    	        helper.setSubject("[GroupWare]비밀번호 재설정 인증코드");
	    	        helper.setText("<p>아래 인증코드를 입력해 주세요:</p><h2>" + code + "</h2>", true);

	    	        mailSender.send(message);
	    	        
	    	        Map<String, String> loginMap = new HashMap<String,String>();
	    	        loginMap.put("uuid", uuid);
	    	        loginMap.put("emp_no", emp_no);
	    	        loginMap.put("code", code);
	    	        loginDao.savePwAuthCode(loginMap);

	    	        // 세션이나 DB에 인증코드 저장 (지금은 생략 가능)
	    	        mav.addObject("message", "입력하신 이메일로 인증코드를 보냈습니다.");
	    	        mav.addObject("uuid",uuid);

	    	    } catch (Exception e) {
	    	        e.printStackTrace();
	    	        mav.addObject("error", "이메일 전송에 실패했습니다.");
	    	    }
	    	 	
	    	 	mav.setViewName("/login/certifyForm");
	    	    return mav;
	    
	    }
	    
		//메일에서 인증번호 쳐서 확인하는 메서드
		@RequestMapping(value = "/verifyCode.erp", method = RequestMethod.POST)
		public ModelAndView verifyCode(@RequestParam("uuid") String uuid,
		                               @RequestParam("code") String code) {

		    ModelAndView mav = new ModelAndView();

		    Map<String, String> map = new HashMap<String,String>();
		    map.put("uuid", uuid);
		    map.put("code", code);

		    // login 테이블에서 해당 인증코드가 존재하는지 확인
		    String emp_no = loginDao.getEmpIdByUuidAndCode(map);

		    if (emp_no == null) {
		        mav.setViewName("/login/certifyForm");
		        mav.addObject("error", "인증코드가 유효하지 않거나 만료되었습니다.");
		        mav.addObject("uuid", uuid); // 다시 인증 시 필요
		        return mav;
		    }

		    // 인증 성공 → 비밀번호 재설정 화면으로 이동
		    mav.setViewName("/login/resetPwForm");
		    mav.addObject("emp_no", emp_no);
		    mav.addObject("uuid", uuid);
		    return mav;
		}
		@RequestMapping(value = "/resetPwConfirm.erp", method = RequestMethod.POST)
		public ModelAndView resetPw(@RequestParam("uuid") String uuid,
		                            @RequestParam("emp_no") String emp_no,
		                            @RequestParam("newPw") String newPw,
		                            @RequestParam("confirmPw") String confirmPw) {

		    ModelAndView mav = new ModelAndView();

		    // 1. 비밀번호 확인 체크
		    if (!newPw.equals(confirmPw)) {
		        mav.setViewName("/login/resetPwForm");
		        mav.addObject("error", "비밀번호가 일치하지 않습니다.");
		        mav.addObject("uuid", uuid);
		        mav.addObject("emp_no", emp_no);
		        return mav;
		    }

		    // 2. 비밀번호 업데이트
		    Map<String, String> map = new HashMap<String,String>();
		    map.put("emp_no", emp_no);
		    map.put("newPw", newPw);
		    empDao.updatePassword(map);

		    // 3. 인증코드 삭제
		    loginDao.deletePwAuthCode(uuid);

		    // 4. 성공 페이지 이동
		    mav.setViewName("/login/resetPwSuccess");
		    return mav;
		}

	    
}
