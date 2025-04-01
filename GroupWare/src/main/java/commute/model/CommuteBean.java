package commute.model;

import java.util.Date;

public class CommuteBean {
	 private String workDate;
	 private String empNo;
	 private Date clockIn;
	 private Date clockOut;
	 private Double overtimeHours;
	 private String otpVerified;
	 private String empNm;
	 private String deptNm;
	 private String cmmNm; 
	 
	 
	 public CommuteBean() {
		super();
	}
	public CommuteBean(String workDate, String empNo, Date clockIn, Date clockOut, Double overtimeHours,
			String otpVerified, String empNm, String deptNm, String cmmNm) {
		super();
		this.workDate = workDate;
		this.empNo = empNo;
		this.clockIn = clockIn;
		this.clockOut = clockOut;
		this.overtimeHours = overtimeHours;
		this.otpVerified = otpVerified;
		this.empNm = empNm;
		this.deptNm = deptNm;
		this.cmmNm = cmmNm;
	}
	public String getEmpNm() {
		return empNm;
	}
	public void setEmpNm(String empNm) {
		this.empNm = empNm;
	}
	public String getDeptNm() {
		return deptNm;
	}
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}
	public String getCmmNm() {
		return cmmNm;
	}
	public void setCmmNm(String cmmNm) {
		this.cmmNm = cmmNm;
	}
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public Date getClockIn() {
		return clockIn;
	}
	public void setClockIn(Date clockIn) {
		this.clockIn = clockIn;
	}
	public Date getClockOut() {
		return clockOut;
	}
	public void setClockOut(Date clockOut) {
		this.clockOut = clockOut;
	}
	public Double getOvertimeHours() {
		return overtimeHours;
	}
	public void setOvertimeHours(Double overtimeHours) {
		this.overtimeHours = overtimeHours;
	}
	public String getOtpVerified() {
		return otpVerified;
	}
	public void setOtpVerified(String otpVerified) {
		this.otpVerified = otpVerified;
	}
	
	
}
