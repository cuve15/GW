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

    public void save(String uuid, String empId, String createdAt) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("uuid", uuid);
        map.put("empId", empId);
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

	public String selectemail(String empId) {
		String email =sqlSessionTemplate.selectOne(namespace+".selectemail",empId);
		return email;
	}

	public int selectoneEmpID(String inputEmpId) {
		int cnt=sqlSessionTemplate.selectOne(namespace+".selectoneEmpID",inputEmpId);
		return cnt;
	}

	public EmployeeBean findEmployeeByEmpId(String empId) {
		EmployeeBean employeeBean=sqlSessionTemplate.selectOne(namespace+".findEmployeeByEmpId",empId);
		return employeeBean;
	}
}
