package approval.model;
import java.sql.Date;

public class ApprovalBean {

    private String doc_no;
    private int appr_no;
    private int appr_order;
    private String emp_no;
    private String dept_cd;
    private String position_cd;
    private String appr_status;
    private Date appr_dtm;
    private String appr_refuse_op;

    // Getter and Setter methods
    public String getDoc_no() {
        return doc_no;
    }

    public void setDoc_no(String doc_no) {
        this.doc_no = doc_no;
    }

    public int getAppr_no() {
        return appr_no;
    }

    public void setAppr_no(int appr_no) {
        this.appr_no = appr_no;
    }

    public int getAppr_order() {
        return appr_order;
    }

    public void setAppr_order(int appr_order) {
        this.appr_order = appr_order;
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

    public String getPosition_cd() {
        return position_cd;
    }

    public void setPosition_cd(String position_cd) {
        this.position_cd = position_cd;
    }

    public String getAppr_status() {
        return appr_status;
    }

    public void setAppr_status(String appr_status) {
        this.appr_status = appr_status;
    }

    public Date getAppr_dtm() {
        return appr_dtm;
    }

    public void setAppr_dtm(Date appr_dtm) {
        this.appr_dtm = appr_dtm;
    }

    public String getAppr_refuse_op() {
        return appr_refuse_op;
    }

    public void setAppr_refuse_op(String appr_refuse_op) {
        this.appr_refuse_op = appr_refuse_op;
    }

}
