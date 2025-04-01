package approval.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import approval.model.ApprovalBean;
import approval.model.ApprovalDao;
import attach.controller.FileUploadController;
import attach.model.AttachBean;
import attach.model.AttachDao;
import document.model.DocumentBean;
import document.model.DocumentDao;

@Controller
public class ApprovalRequestController {

    // URL 寃쎈줈 �젙�쓽
    private final String command = "approval_insert.erp"; // 寃곗젣 �슂泥� �뤌 �젣異� 寃쎈줈
    private final String getPage = "approval/approval_insert"; // 寃곗젣 �슂泥� �뤌 �럹�씠吏�
    private final String gotoPage = "approval/approvalList"; // 寃곗젣 �슂泥� �셿猷� �썑 由щ뵒�젆�뀡�븷 �럹�씠吏�

    @Autowired
    private DocumentDao documentDao; // 臾몄꽌 愿��젴 DAO
    
    @Autowired
    private ApprovalDao approvalDao;
    
    @Autowired
    private AttachDao attachDao;
    
    
    FileUploadController fileUploadController = new FileUploadController(); 
    // GET �슂泥�: 寃곗젣 �슂泥� �뤌 �럹�씠吏�濡� �씠�룞
    @RequestMapping(value = command, method = RequestMethod.GET)
    public String showApprovalRequestForm() {
        return getPage; // 寃곗젣 �슂泥� �뤌�쓣 蹂댁뿬二쇰뒗 JSP �럹�씠吏�濡� �씠�룞
    }

    // POST �슂泥�: 寃곗젣 �슂泥� 泥섎━
    @RequestMapping(value = command, method = RequestMethod.POST)
    public ModelAndView submitApprovalRequest(@ModelAttribute("document") @Valid DocumentBean document,  
                                              BindingResult result,
                                              HttpSession session,
                                              @RequestParam("file") MultipartFile file,
                                              HttpServletResponse response	  ) {
    	String savedFileName = fileUploadController.uploadFile(file, response);
    	
        String position_cd = (String) session.getAttribute("position_cd");
        String emp_no = (String) session.getAttribute("emp_no");
        String dept_cd = (String) session.getAttribute("dept_cd");
        if (position_cd != null) {
            document.setPosition_cd(position_cd);
        }
        ModelAndView mav = new ModelAndView();

        // �쑀�슚�꽦 寃��궗 �썑 �삤瑜섍� �엳�떎硫� �뤌 �럹�씠吏�瑜� �떎�떆 蹂댁뿬以�
        if (result.hasErrors()) {
            mav.setViewName(getPage);
            return mav;
        }

      
        // 寃곗젣 �슂泥��쓣 臾몄꽌�젙蹂� �뀒�씠釉붿뿉 �궫�엯
        System.out.println("doc_title: " + document.getDoc_title());
        System.out.println("doc_content: " + document.getDoc_content());
        System.out.println("emp_no: " + document.getEmp_no());
        System.out.println("dept_cd: " + document.getDept_cd());
        System.out.println("position_cd: " + document.getPosition_cd());
        System.out.println("1: " + document.getAppr_status());
        
        
        int cnt = documentDao.insertDocument(document);  // 臾몄꽌 �젙蹂� �궫�엯
        
        
        String doc_no_seq = documentDao.selectOneNum();
        if (cnt > 0) {
            System.out.println("�깮�꽦�맂 doc_no: " + doc_no_seq);
            
            ApprovalBean approval = new ApprovalBean();
            approval.setDoc_no(doc_no_seq);  // 臾몄꽌 踰덊샇
          //  approval.setAppr_order(); //order�� Dept_cd �씪移섏떆�궎湲�    
            approval.setEmp_no(document.getEmp_no());
            approval.setDept_cd(document.getDept_cd());
            approval.setPosition_cd(document.getPosition_cd());
            approval.setAppr_status("��湲�");
            approval.setAppr_dtm(null);  // 寃곗젣 �씪�떆 (�쁽�옱 �떆媛�)
            approval.setAppr_refuse_op("1");  // 諛섎젮 �궗�쑀 �뾾�쓬

            // appr_line �뀒�씠釉붿뿉 寃곗젣 �씪�씤 �궫�엯
            int lineInsertCount = approvalDao.insertApprovalLine(approval);
            

            if (lineInsertCount > 0) {
                System.out.println("寃곗젣 �씪�씤 異붽� �꽦怨�");
            } else {
                System.out.println("寃곗젣 �씪�씤 異붽� �떎�뙣");
            }
            
            if (file != null && !file.isEmpty()) {
                AttachBean attach = new AttachBean();
                attach.setCon_key3(doc_no_seq); // 臾몄꽌 踰덊샇 �뿰寃�
                attach.setOrg_file_name(file.getOriginalFilename());// �썝蹂� �뙆�씪紐�
                attach.setServer_file_name(savedFileName); // ���옣�맂 �뙆�씪紐�
                attach.setFile_size(file.getSize()); // �뙆�씪 �겕湲�
                
                System.out.println("�썝蹂� �뙆�씪 �씠由� : " + file.getOriginalFilename());
                System.out.println("�꽌踰� �뙆�씪 �씠由�: " + attach.getServer_file_name());  // �뙆�씪 �씠由꾩쓣 異쒕젰

                int attachInsertCount = attachDao.insertAttach(attach);
                if (attachInsertCount > 0) {
                    System.out.println("泥⑤� �뙆�씪 �젙蹂� ���옣 �꽦怨�");
                } else {
                    System.out.println("泥⑤� �뙆�씪 �젙蹂� ���옣 �떎�뙣");
                }
            }
            mav.setViewName(gotoPage);  // 寃곗젣 �슂泥��씠 �셿猷뚮맂 �썑 寃곗젣 �슂泥� 紐⑸줉 �럹�씠吏�濡� 由щ뵒�젆�뀡
        } else {
            mav.setViewName(getPage);  // 臾몄꽌 �궫�엯 �떎�뙣 �떆 �뤌 �럹�씠吏�濡� �룎�븘媛�
        }

        return mav;
    }
}
