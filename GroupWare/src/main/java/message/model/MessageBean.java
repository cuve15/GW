package message.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class MessageBean {
	private String msg_no;
	
	@NotEmpty(message="제목을 입력해주세요")
	private String msg_title;
	
	@NotEmpty(message="내용을 입력해주세요")
	private String msg_content;
	private Date send_dtm; // 시간
	private String send_emp_no;
	
	@NotNull(message="받는 사람을 선택하세요")
	private String receive_emp_no;
	private String send_yn;
	private String read_yn;
	private String del_yn;
	private String send_emp_name; // 보내는 사람
	private String receive_emp_name; // 받는 사람
	private String send_dept_nm;
	private String receive_dept_nm;
	private String send_position_nm;
	private String receive_position_nm;
	
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	public String getSend_dept_nm() {
		return send_dept_nm;
	}
	public void setSend_dept_nm(String send_dept_nm) {
		this.send_dept_nm = send_dept_nm;
	}
	public String getReceive_dept_nm() {
		return receive_dept_nm;
	}
	public void setReceive_dept_nm(String receive_dept_nm) {
		this.receive_dept_nm = receive_dept_nm;
	}
	public String getSend_position_nm() {
		return send_position_nm;
	}
	public void setSend_position_nm(String send_position_nm) {
		this.send_position_nm = send_position_nm;
	}
	public String getReceive_position_nm() {
		return receive_position_nm;
	}
	public void setReceive_position_nm(String receive_position_nm) {
		this.receive_position_nm = receive_position_nm;
	}
	public String getSend_emp_name() {
		return send_emp_name;
	}
	public void setSend_emp_name(String send_emp_name) {
		this.send_emp_name = send_emp_name;
	}
	public String getReceive_emp_name() {
		return receive_emp_name;
	}
	public void setReceive_emp_name(String receive_emp_name) {
		this.receive_emp_name = receive_emp_name;
	}
	public String getMsg_no() {
		return msg_no;
	}
	public void setMsg_no(String msg_no) {
		this.msg_no = msg_no;
	}
	public String getMsg_title() {
		return msg_title;
	}
	public void setMsg_title(String msg_title) {
		this.msg_title = msg_title;
	}
	public String getMsg_content() {
		return msg_content;
	}
	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}
	public Date getSend_dtm() {
		return send_dtm;
	}
	public void setSend_dtm(Date send_dtm) {
		this.send_dtm = send_dtm;
	}
	public String getSend_emp_no() {
		return send_emp_no;
	}
	public void setSend_emp_no(String send_emp_no) {
		this.send_emp_no = send_emp_no;
	}
	public String getReceive_emp_no() {
		return receive_emp_no;
	}
	public void setReceive_emp_no(String receive_emp_no) {
		this.receive_emp_no = receive_emp_no;
	}
	public String getSend_yn() {
		return send_yn;
	}
	public void setSend_yn(String send_yn) {
		this.send_yn = send_yn;
	}
	public String getRead_yn() {
		return read_yn;
	}
	public void setRead_yn(String read_yn) {
		this.read_yn = read_yn;
	}
	
}
