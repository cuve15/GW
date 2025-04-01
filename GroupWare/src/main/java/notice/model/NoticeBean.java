package notice.model;

import java.sql.Timestamp;

public class NoticeBean {
	private int notice_no;
	private String notice_title;
	private String notice_content;
	private String dept_nm;
	private Timestamp notice_dtm;
	private String emp_no;
	private String emp_nm;
	private int temporary_save;
	private int notice_level;
	private int notice_views;
	
	public int getNotice_no() {
		return notice_no;
	}
	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	public String getDept_nm() {
		return dept_nm;
	}
	public void setDept_nm(String dept_nm) {
		this.dept_nm = dept_nm;
	}
	public Timestamp getNotice_dtm() {
		return notice_dtm;
	}
	public void setNotice_dtm(Timestamp notice_dtm) {
		this.notice_dtm = notice_dtm;
	}
	public boolean isWithin24Hours() {
        long diff = System.currentTimeMillis() - this.notice_dtm.getTime();
        return diff <= 24 * 60 * 60 * 1000;
    }
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public String getEmp_nm() {
		return emp_nm;
	}
	public void setEmp_nm(String emp_nm) {
		this.emp_nm = emp_nm;
	}
	public int getTemporary_save() {
		return temporary_save;
	}
	public void setTemporary_save(int temporary_save) {
		this.temporary_save = temporary_save;
	}
	public int getNotice_level() {
		return notice_level;
	}
	public void setNotice_level(int notice_level) {
		this.notice_level = notice_level;
	}
	public int getNotice_views() {
		return notice_views;
	}
	public void setNotice_views(int notice_views) {
		this.notice_views = notice_views;
	}
	
}
