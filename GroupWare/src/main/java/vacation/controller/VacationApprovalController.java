package vacation.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vacation.model.VacationDao;

@Controller
public class VacationApprovalController {

    // ��û URL�� �����̷�Ʈ�� ������ ����
    private final String command = "vacationApproval.erp";  // ��û URL ����
    private final String gotoPage = "vacation/vacationList";  // ���� �� �̵��� ������

    // VacationDao ��ü �ڵ� ����
    @Autowired
    private VacationDao vdao;

    // �ް� ���� ó�� �޼���
    @RequestMapping(command)
    public String vacationApproval(@RequestParam("vacation_no") String vacation_no, HttpSession session) throws Exception {

        // ���ǿ��� ����(position_cd)�� �α��ε� ����� ��ȣ(emp_no) ��������
        String position_cd = (String) session.getAttribute("position_cd");
        String emp_no = (String) session.getAttribute("emp_no");  // �α��ε� ����� emp_no

        // ������� ���� ���
        System.out.println("vacation_no : " + vacation_no);
        System.out.println("position_cd : " + position_cd);

        // ������ 4���� ���� ��쿡�� ���� ���� (����: 1-���, 2-����, 3-����, 4-���� ��)
        if (Integer.parseInt(position_cd) < 4) {
            // vacation_no�� emp_no�� ����Ͽ� ���� ó��
        	vdao.vacationApproval(vacation_no, emp_no);
            // ���� �� vacationList �������� �̵�
            return gotoPage;
        } else {
            // ������ 4 �̻��� ��� ������ �Ұ��ϹǷ� ���� ó��
            throw new Exception("������ �� ���� �����Դϴ�.");
        }
    }
}
