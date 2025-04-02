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

import Login.model.EmployeeBean;  
import Login.model.LoginDao;
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


	@RequestMapping("/auth/qr/login")
	public String viewQR(Model model) throws Exception {
		String uuid = UUID.randomUUID().toString();
		String qrImage = qrGenerator.generateQRBase64(uuid);

		model.addAttribute("uuid", uuid);
		model.addAttribute("qrImage", qrImage);
		return "view";
	}

	@RequestMapping("/scan/qr/login")
	public String scanQR(@RequestParam("uuid") String uuid,
			HttpServletRequest request,
			Model model) {

		Date createdAt = loginDao.selectCreatedAtByUUID(uuid);
		if (createdAt == null || isExpired(createdAt, 10)) {
			model.addAttribute("error", "QR 코드가 만료되었습니다. 다시 시도하세요");
			return "/auth/qr/login";
		}

		String empId = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("empId".equals(cookie.getName())) {
					empId = cookie.getValue();
					break;
				}
			}
		}

		model.addAttribute("uuid", uuid);

		if (empId != null) {
			model.addAttribute("empId", empId);
			return "send/email/login";
		} else {
			return "empidlogin";
		}
	}

	@RequestMapping(value = "/write/empid/login", method = RequestMethod.POST)
	public String writeEmpId(@RequestParam("empId") String inputEmpId,
			@RequestParam("uuid") String uuid,
			HttpServletResponse response,
			Model model) {

		int cnt=loginDao.selectoneEmpID(inputEmpId);
		if(cnt==1) {
			Cookie cookie=new Cookie("empId", inputEmpId);
			cookie.setMaxAge(365*24*60*60);
			cookie.setPath("/"); 
			response.addCookie(cookie);
			return "/send/email/login";
		}else {
			model.addAttribute("error", "존재하지 않는 사원번호 입니다. 다시 입력하세요");
			return "empidlogin";
		}
	}

	@RequestMapping(value = "/send/email/login", method = RequestMethod.POST)
	public String sendEmail(@RequestParam("uuid") String uuid,
			@RequestParam("empId") String empId,
			Model model) {
		String email=loginDao.selectemail(empId);

		String code = String.format("%06d", new Random().nextInt(999999));

		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

			helper.setTo(email);
			helper.setSubject("로그인 인증 코드");
			helper.setText("인증코드:<br><h2>" + code + "</h2><br>", true);
			javaMailSender.send(message);

			loginDao.updateCode(uuid, code);

			model.addAttribute("uuid", uuid);
			model.addAttribute("empId", empId);
			return "codelogin";
		} catch (MessagingException e) {
			model.addAttribute("error", "메일 전송 실패 다시 시도");
			return "error";
		}
	}

	@RequestMapping("/write/email/login")
	public String verifyCode(@RequestParam("uuid") String uuid,
	                         @RequestParam("code") String code,
	                         @RequestParam("empId") String empId,
	                         HttpServletResponse response,
	                         Model model) {

	    Date createdAt = loginDao.selectCreatedAtByUUID(uuid);
	    if (createdAt == null || isExpired(createdAt, 5)) {
	        model.addAttribute("error", "인증코드가 만료되었습니다.");
	        return "codelogin";
	    }

	    Map<String, String> map = new HashMap<String, String>();
	    map.put("uuid", uuid);
	    map.put("code", code);
	    int result = loginDao.check_code(map);

	    if (result != 1) {
	        model.addAttribute("error", "인증코드가 일치하지 않습니다.");
	        return "codelogin";
	    }

	    EmployeeBean employee = loginDao.findEmployeeByEmpId(empId);
	    String position = employee.getPosition();     
	    String department = employee.getDepartment(); 

	    String accessToken = JwtUtil.createToken(empId, position, department);

	    response.setHeader("Set-Cookie", "access_token=" + accessToken + "; Path=/; HttpOnly; Max-Age=54000"); // 15시간

	    model.addAttribute("empId", empId);
	    return "/check_in";
	}



	// [공통] 유효시간 검사 메서드
	private boolean isExpired(Date createdAt, int minutes) {
		long diff = System.currentTimeMillis() - createdAt.getTime();
		return diff > minutes * 60 * 1000;
	}
}
