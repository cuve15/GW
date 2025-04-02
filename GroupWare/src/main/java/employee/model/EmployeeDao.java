package employee.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import utility.Paging;

@Component("EmployeeDao")
public class EmployeeDao {
		
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	private final String namespace="employee.model.Employee";
	
	public List<EmployeeBean> getAllEmployee(Paging pageInfo,Map<String,String> map) {
		
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(),pageInfo.getLimit());
		List<EmployeeBean> lists = sqlSessionTemplate.selectList(namespace+".getAllEmployee",map,rowBounds);
		
		return lists;
	}

	public int insertEmployee(EmployeeBean empBean) {
		
		int cnt = sqlSessionTemplate.insert(namespace+".insertEmployee",empBean);
		
		return cnt;
	}

	public EmployeeBean getEmployeeInfo(String emp_no, String pw) {
		
		EmployeeBean empBean = new EmployeeBean();
		empBean.setEmp_no(emp_no);
		empBean.setPw(pw);
		System.out.println("empBean.emp_no"+empBean.getEmp_no());
		System.out.println("empBean.pw"+empBean.getPw());
		
		empBean = sqlSessionTemplate.selectOne(namespace+".getEmployeeInfo",empBean);
		
		
		return empBean;
	}

	public List<EmployeeBean> getAllEmployeeJoinAuth() {
		
		List<EmployeeBean> empList = sqlSessionTemplate.selectList(namespace+".getAllEmployeeAuth");
		
		return empList;
	}

	public int getTotalCount(Map<String, String> map) {
		
		int cnt = sqlSessionTemplate.selectOne(namespace+".getTotalCount",map);
		
		return cnt;
	}

	public EmployeeBean getOneEmp(String emp_no) {
		
		EmployeeBean empBean = sqlSessionTemplate.selectOne(namespace+".getOneEmp",emp_no);
		
		return empBean;
	}

	public int updateEmployee(EmployeeBean empBean) {

		int cnt = sqlSessionTemplate.update(namespace+".updateEmployee",empBean);
		
		return cnt;
	}

	public List<EmployeeBean> getAllEmployeeList() {
		
		List<EmployeeBean> lists = sqlSessionTemplate.selectList(namespace+".getAllEmployeeList");
		
		return lists;
	}

	public boolean checkEmployee(Map<String, String> map) {
		
		int count = sqlSessionTemplate.selectOne(namespace + ".checkEmployee",map);
		
		return count == 1;
	}

	public void updatePassword(Map<String, String> map) {
		sqlSessionTemplate.update(namespace+".updatePassword",map);
		
	}

	
}
