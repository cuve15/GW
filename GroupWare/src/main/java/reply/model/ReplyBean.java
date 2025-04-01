package reply.model;

import java.sql.Timestamp;

public class ReplyBean {
	private String reply_no;
	private String reply_content;
	private int notice_no;
	private String emp_no;
	private String emp_nm;
	private Timestamp reply_dtm;
	
	public String getReply_no() {
		return reply_no;
	}
	public void setReply_no(String reply_no) {
		this.reply_no = reply_no;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public int getNotice_no() {
		return notice_no;
	}
	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
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
	public Timestamp getReply_dtm() {
		return reply_dtm;
	}
	public void setReply_dtm(Timestamp reply_dtm) {
		this.reply_dtm = reply_dtm;
	}
	
}
