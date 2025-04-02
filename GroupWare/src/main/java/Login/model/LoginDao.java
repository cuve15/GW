package Login.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import employee.model.EmployeeBean;

@Component
public class LoginDao {

    String namespace = "QRLogin.QRLoginBean";
    

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    public void save(String uuid, String emp_no, String createdAt) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("uuid", uuid);
        map.put("emp_no", emp_no);
        map.put("createdAt", createdAt);
        sqlSessionTemplate.insert(namespace + ".save", map);
    }

    public int selectone(String uuid) {
        int cnt= sqlSessionTemplate.selectOne(namespace + ".selectone", uuid);
        return cnt;
    }

    public int check_code(Map<String, String> map) {
    	int cnt= sqlSessionTemplate.selectOne(namespace + ".check_one", map);
    	return cnt;
    }

    public int updateCode(String uuid, String code) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("uuid", uuid);
        map.put("code", code);
        int cnt= sqlSessionTemplate.update(namespace + ".updateCode", map);
        return cnt;
    }

    public Date selectCreatedAtByUUID(String uuid) {
         Date date= sqlSessionTemplate.selectOne(namespace + ".selectCreatedAtByUUID", uuid);
        return date;
    }

	public String selectemail(String emp_no) {
		String email =sqlSessionTemplate.selectOne(namespace+".selectemail",emp_no);
		return email;
	}

	public int selectoneEmpID(String inputEmpId) {
		int cnt=sqlSessionTemplate.selectOne(namespace+".selectoneEmpID",inputEmpId);
		return cnt;
	}

	public EmployeeBean findEmployeeByEmpId(String emp_no) {
		EmployeeBean employeeBean=sqlSessionTemplate.selectOne(namespace+".findEmployeeByEmpId",emp_no);
		return employeeBean;
	}

	public void savePwAuthCode(Map<String, String> loginMap) {
		sqlSessionTemplate.insert(namespace+".savePwAuthCode",loginMap);
		
	}

	public String getEmpIdByUuidAndCode(Map<String, String> map) {
		
		return sqlSessionTemplate.selectOne(namespace+".selectEmpNoByUuidAndCode",map);
	}

	public void deletePwAuthCode(String uuid) {
		sqlSessionTemplate.delete(namespace+".deletePwAuthCode",uuid);
	}
}
