package Login.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Login.model.LoginDao;
import employee.model.EmployeeBean;
import jwt.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import qrGenerator.util.QRGenerator;


@Controller
public class LoginController {

	@Autowired QRGenerator qrGenerator;
	@Autowired JavaMailSender javaMailSender;
	@Autowired LoginDao loginDao;


	@RequestMapping("/auth/qr/login.erp")
	public String viewQR(Model model) throws Exception {//qr코드생성해서 view로 전달
		String uuid = UUID.randomUUID().toString();//uuid 생성하여 qr코드의 고유한 식별자로 사용
		String qrImage = qrGenerator.generateQRBase64(uuid);

		model.addAttribute("uuid", uuid);
		model.addAttribute("qrImage", qrImage);
		return "view";
	}

	@RequestMapping("/scan/qr/login.erp") //클라이언트가 qr코드를 스캔하면 호출되는 메서드
	public String scanQR(@RequestParam("uuid") String uuid,
			HttpServletRequest request,
			Model model) {

		Date createdAt = loginDao.selectCreatedAtByUUID(uuid);
		if (createdAt == null || isExpired(createdAt, 10)) {//10분이 지났는지 확인
			model.addAttribute("error", "QR 코드가 만료되었습니다. 다시 시도하세요");//만료된 경우 error 메세지 
			return "/auth/qr/login.erp";
			//uuid로 데이터베이스에서 생선시간을 조회
		}

		String empId = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("empId".equals(cookie.getName())) {
					empId = cookie.getValue();
					break;
					//클라이언트의 쿠키에서 empid(사원번호)가져옴
				}
			}
		}

		model.addAttribute("uuid", uuid);

		if (empId != null) {
			model.addAttribute("empId", empId);
			return "send/email/login.erp";//메인화면으로 넘어가기(수정해야함)
		} else {
			return "empidlogin";//loginform으로 넘어가기(수정해야함)
		}
	}
	
	
	//qr코드 실패시 사원번호 치는 form으로 넘기는 메서드라 잠시 주석
//	@RequestMapping(value = "/write/empid/login.erp", method = RequestMethod.POST)
//	public String writeEmpId(@RequestParam("empId") String inputEmpId,
//			@RequestParam("uuid") String uuid,
//			HttpServletResponse response,
//			Model model) {
//
//		int cnt=loginDao.selectoneEmpID(inputEmpId);
//		if(cnt==1) {
//			Cookie cookie=new Cookie("empId", inputEmpId);
//			cookie.setMaxAge(365*24*60*60);
//			cookie.setPath("/"); 
//			response.addCookie(cookie);
//			return "/send/email/login.erp";
//		}else {
//			model.addAttribute("error", "존재하지 않는 사원번호 입니다. 다시 입력하세요");
//			return "empidlogin";
//		}
//	}
	
	//이메일 인증코드 전송 메서드 잠시 주석
//	@RequestMapping(value = "/send/email/login.erp", method = RequestMethod.POST)
//	public String sendEmail(@RequestParam("uuid") String uuid,
//			@RequestParam("empId") String empId,
//			Model model) {
//		String email=loginDao.selectemail(empId);
//
//		String code = String.format("%06d", new Random().nextInt(999999));
//
//		try {
//			MimeMessage message = javaMailSender.createMimeMessage();
//			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
//
//			helper.setTo(email);
//			helper.setSubject("로그인 인증 코드");
//			helper.setText("인증코드:<br><h2>" + code + "</h2><br>", true);
//			javaMailSender.send(message);
//
//			loginDao.updateCode(uuid, code);
//
//			model.addAttribute("uuid", uuid);
//			model.addAttribute("empId", empId);
//			return "codelogin";
//		} catch (MessagingException e) {
//			model.addAttribute("error", "메일 전송 실패 다시 시도");
//			return "error";
//		}
//	}

	//인증 코드 확인 메서드라 잠시주석
//	@RequestMapping("/write/email/login.erp")
//	public String verifyCode(@RequestParam("uuid") String uuid,
//	                         @RequestParam("code") String code,
//	                         @RequestParam("empId") String empId,
//	                         HttpServletResponse response,
//	                         Model model) {
//
//	    Date createdAt = loginDao.selectCreatedAtByUUID(uuid);
//	    if (createdAt == null || isExpired(createdAt, 5)) {
//	        model.addAttribute("error", "인증코드가 만료되었습니다.");
//	        return "codelogin";
//	    }
//
//	    Map<String, String> map = new HashMap<String, String>();
//	    map.put("uuid", uuid);
//	    map.put("code", code);
//	    int result = loginDao.check_code(map);
//
//	    if (result != 1) {
//	        model.addAttribute("error", "인증코드가 일치하지 않습니다.");
//	        return "codelogin";
//	    }
//
//	    EmployeeBean empBean = loginDao.findEmployeeByEmpId(empId);
//	    String position = empBean.getPosition();     
//	    String department = empBean.getDepartment(); 
//
//	    String accessToken = JwtUtil.createToken(empId, position, department);
//
//	    response.setHeader("Set-Cookie", "access_token=" + accessToken + "; Path=/; HttpOnly; Max-Age=54000"); // 15시간
//
//	    model.addAttribute("empId", empId);
//	    return "/check_in.erp";
//	}
//
//
//
	// [공통] 유효시간 검사 메서드
	private boolean isExpired(Date createdAt, int minutes) {//createdAT:db에서 조회한 qr코드 생선된 시간
	long diff = System.currentTimeMillis() - createdAt.getTime();
    return diff > minutes * 60 * 1000;
	}
}
