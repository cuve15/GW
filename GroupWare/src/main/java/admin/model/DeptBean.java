package admin.model;

import org.hibernate.validator.constraints.NotEmpty;

public class DeptBean {
	
	@NotEmpty(message="부서코드를 입력하세요.")
	private String dept_cd;
	@NotEmpty(message="부서이름을 입력하세요.")
	private String dept_nm;
	private String p_dept_cd;
	
	
	public String getDept_cd() {
		return dept_cd;
	}
	public void setDept_cd(String dept_cd) {
		this.dept_cd = dept_cd;
	}
	public String getDept_nm() {
		return dept_nm;
	}
	public void setDept_nm(String dept_nm) {
		this.dept_nm = dept_nm;
	}
	public String getP_dept_cd() {
		return p_dept_cd;
	}
	public void setP_dept_cd(String p_dept_cd) {
		this.p_dept_cd = p_dept_cd;
	}
}
