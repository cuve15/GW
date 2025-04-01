package utility;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import admin.model.AuthInfoBean;
import admin.model.AuthInfoDao;
import admin.model.EmployeeAuthBean;
import admin.model.EmployeeAuthDao;
import employee.model.EmployeeBean;

@Component
public class AutoAuthority {
	
	@Autowired
	EmployeeAuthDao empAuthDao;
	
	@Autowired
	AuthInfoDao authInfoDao;
	
	
	
	public void defaultAuthor(EmployeeBean emp) {
		boolean deptHead = "2".equals(emp.getPosition_cd());
		boolean personalHead = "1100".equals(emp.getDept_cd()) &&"2".equals(emp.getPosition_cd());
		boolean CEO = "1".equals(emp.getPosition_cd());
		
		if(personalHead || CEO) {//인사부장,사장 모든권한 부
			List<AuthInfoBean> allAuth = authInfoDao.getAllAuthInfo();
			
			for(AuthInfoBean authInfoBean : allAuth) {
						String alreadyAuth = empAuthDao.getAuthByEmpNo(emp.getEmp_no(),authInfoBean.getAuth_cd());// 선택한 사원번호가 이미 권한 테이블안에 등록돼 있는지 확인하는 
						if(alreadyAuth == null) {
							int cnt1 = empAuthDao.insertEmployeeAuth(emp.getEmp_no(), authInfoBean.getAuth_cd());
			}
		}
		
		}else if(deptHead) {//부장들 공지사항 쓸 수 있는 권한
			String writeNotice = "10";
			String alreadyAuth = empAuthDao.getAuthByEmpNo(emp.getEmp_no(),writeNotice);
			if(alreadyAuth == null) {
				int cnt1 = empAuthDao.insertEmployeeAuth(emp.getEmp_no(), writeNotice);
				
			}
			 
		}
	
	}
}
