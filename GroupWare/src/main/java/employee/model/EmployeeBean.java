package employee.model;

import java.sql.Date;

import org.hibernate.validator.constraints.NotEmpty;


public class EmployeeBean {
	
	@NotEmpty(message="사원번호를 입력하세요.")
	private String emp_no; 
	@NotEmpty(message="사원이름을 입력하세요.")
	private String emp_nm;
	private String emp_status;
	@NotEmpty(message="부서를 선택하세요.")
	private String dept_cd;
	@NotEmpty(message="직위를 선택하세요.")
	private String position_cd;
	private Date hire_date;
	private Date retire_date;
	@NotEmpty(message="성별을 선택하세요.")
	private String gender;
	private Date birth;
	@NotEmpty(message="이메일을 입력하세요.")
	private String email;
	@NotEmpty(message="비밀번호를 입력하세요.")
	private String pw;
	
	private String dept_nm; 
    private String position_nm;
    private String gender_nm;
    private String emp_status_nm;
    private String currentAuth;
    
    
    public String getGender_nm() {
		return gender_nm;
	}
	public void setGender_nm(String gender_nm) {
		this.gender_nm = gender_nm;
	}
	public String getEmp_status_nm() {
		return emp_status_nm;
	}
	public void setEmp_status_nm(String emp_status_nm) {
		this.emp_status_nm = emp_status_nm;
	}
   
	
	
	
	
	public String getCurrentAuth() {
		return currentAuth;
	}
	public void setCurrentAuth(String currentAuth) {
		this.currentAuth = currentAuth;
	}
	
	public String getDept_nm() {
		return dept_nm;
	}
	public void setDept_nm(String dept_nm) {
		this.dept_nm = dept_nm;
	}
	public String getPosition_nm() {
		return position_nm;
	}
	public void setPosition_nm(String position_nm) {
		this.position_nm = position_nm;
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
	public String getEmp_status() {
		return emp_status;
	}
	public void setEmp_status(String emp_status) {
		this.emp_status = emp_status;
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
	public Date getHire_date() {
		return hire_date;
	}
	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}
	public Date getRetire_date() {
		return retire_date;
	}
	public void setRetire_date(Date retire_date) {
		this.retire_date = retire_date;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	
}
