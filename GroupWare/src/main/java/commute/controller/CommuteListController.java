package commute.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import commute.model.CommuteBean;
import commute.model.CommuteDao;
import jwt.util.JwtUtil;

@org.springframework.stereotype.Controller
public class CommuteListController {

    @Autowired
    CommuteDao cdao;

    
    @RequestMapping("/check_in/commute")
    public String checkIn(@CookieValue("access_token") String token,
                          @RequestParam("work_date") String workDate,
                          @RequestParam("clock_in") String clockIn,
                          Model model) {

        String empId = JwtUtil.getEmpId(token);
        String dept = JwtUtil.getDepartment(token);
        String position = JwtUtil.getPosition(token);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("work_date", workDate);
        map.put("emp_no", empId);

        CommuteBean cb = cdao.selectOne(map);
        if (cb != null) {
            return "check_in_result";
        }

        map.put("clock_in", clockIn);
        cdao.check_in(map);

        return "ㅇㄷ?";
    } 
    

   
    @RequestMapping("/list/commute")
    public String list(@CookieValue("access_token") String token,
                       @RequestParam(value = "type", required = false) String type,
                       @RequestParam(value = "keyword", required = false) String keyword,
                       Model model) {

        String empId = JwtUtil.getEmpId(token);
        String position = JwtUtil.getPosition(token);
        String dept = JwtUtil.getDepartment(token);

        List<CommuteBean> lists;

        if ("대표".equals(dept)) {
            lists = cdao.findAll();
        } else if ("인사과".equals(dept)) {
            Map<String, String> map = new HashMap<String, String>();
            if (type != null && keyword != null && !keyword.isEmpty()) {
                map.put("type", type);
                map.put("keyword", keyword);
            }
            lists = cdao.findAllExcludingPresident(map);
        } else if ("부장".equals(position) || "팀장".equals(position)) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("DEPT_NM", dept);
            map.put("CMM_NM", position);
            if (type != null && keyword != null && !keyword.isEmpty()) {
                map.put("type", type);
                map.put("keyword", keyword);
            }
            lists = cdao.findByDepartmentExcludingPresident(map);
        } else {
            lists = cdao.findByEmpId(empId);
        }

        model.addAttribute("lists", lists);
        model.addAttribute("empId", empId);
        model.addAttribute("position", position);
        model.addAttribute("department", dept);
        return "list";
    }

    
    @RequestMapping("/check_out/commute")
    public String checkOut(@CookieValue("access_token") String token,
                           @RequestParam("work_date") String workDate,
                           @RequestParam("clock_out") String clockOut,
                           Model model) {

        String empId = JwtUtil.getEmpId(token);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("work_date", workDate);
        map.put("emp_no", empId);
        map.put("clock_out", clockOut);

        cdao.check_out(map);
        return "ㅇㄷ?";
    }
}
