package document.model;
import java.util.Date;

public class DocumentBean {
    private String doc_no;            // 문서 번호
    private String doc_title;         // 문서 제목
    private String doc_content;       // 문서 내용
    private String emp_no;            // 사원 번호
    private String dept_cd;           // 부서 코드
    private String position_cd;       // 직위코드
    private String appr_status;       // 결재 상태
    private Date last_appr_dtm;       // 마지막 결재 일시
    private Date insert_dtm;          // 문서 작성 일시
    private Date update_dtm;          // 문서 수정 일시


    public String getDoc_no() {
        return doc_no;
    }

    public void setDoc_no(String doc_no) {
        this.doc_no = doc_no;
    }

    public String getDoc_title() {
        return doc_title;
    }

    public void setDoc_title(String doc_title) {
        this.doc_title = doc_title;
    }

    public String getDoc_content() {
        return doc_content;
    }

    public void setDoc_content(String doc_content) {
        this.doc_content = doc_content;
    }

    public String getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(String emp_no) {
        this.emp_no = emp_no;
    }

    public String getDept_cd() {
        return dept_cd;
    }

    public void setDept_cd(String dept_cd) {
        this.dept_cd = dept_cd;
    }

    public String getAppr_status() {
        return appr_status;
    }

    public void setAppr_status(String appr_status) {
        this.appr_status = appr_status;
    }

    public Date getLast_appr_dtm() {
        return last_appr_dtm;
    }

    public void setLast_appr_dtm(Date last_appr_dtm) {
        this.last_appr_dtm = last_appr_dtm;
    }

    public Date getInsert_dtm() {
        return insert_dtm;
    }

    public void setInsert_dtm(Date insert_dtm) {
        this.insert_dtm = insert_dtm;
    }

    public Date getUpdate_dtm() {
        return update_dtm;
    }

    public void setUpdate_dtm(Date update_dtm) {
        this.update_dtm = update_dtm;
    }

	public String getPosition_cd() {
		return position_cd;
	}

	public void setPosition_cd(String position_cd) {
		this.position_cd = position_cd;
	}
}
