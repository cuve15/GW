package vacation.model;

import java.sql.Date;

public class VacationBean {
    private String vacation_no;
    private String vacation_type;
    private String req_emp_no;
    private String vacation_reason;
    private Date start_dt;
    private Date end_dt;
    private Integer total_days;
    private String vacation_status;
    private String appr_emp_no;
    
	public String getVacation_no() {
		return vacation_no;
	}
	public void setVacation_no(String vacation_no) {
		this.vacation_no = vacation_no;
	}
	public String getVacation_type() {
		return vacation_type;
	}
	public void setVacation_type(String vacation_type) {
		this.vacation_type = vacation_type;
	}
	public String getReq_emp_no() {
		return req_emp_no;
	}
	public void setReq_emp_no(String req_emp_no) {
		this.req_emp_no = req_emp_no;
	}
	public String getVacation_reason() {
		return vacation_reason;
	}
	public void setVacation_reason(String vacation_reason) {
		this.vacation_reason = vacation_reason;
	}
	public Date getStart_dt() {
		return start_dt;
	}
	public void setStart_dt(Date start_dt) {
		this.start_dt = start_dt;
	}
	public Date getEnd_dt() {
		return end_dt;
	}
	public void setEnd_dt(Date end_dt) {
		this.end_dt = end_dt;
	}
	public Integer getTotal_days() {
		return total_days;
	}
	public void setTotal_days(Integer total_days) {
		this.total_days = total_days;
	}
	public String getVacation_status() {
		return vacation_status;
	}
	public void setVacation_status(String vacation_status) {
		this.vacation_status = vacation_status;
	}
	public String getAppr_emp_no() {
		return appr_emp_no;
	}
	public void setAppr_emp_no(String appr_emp_no) {
		this.appr_emp_no = appr_emp_no;
	}
}
