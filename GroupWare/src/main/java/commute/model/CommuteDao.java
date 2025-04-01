package commute.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommuteDao {

    private String namespace = "commute.CommuteDao";

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    public CommuteBean selectOne(Map<String, Object> map) {
        return sqlSessionTemplate.selectOne(namespace + ".selectOne", map);
    }

    public int check_in(Map<String, Object> map) {
        return sqlSessionTemplate.insert(namespace + ".check_in", map);
    }

    public int check_out(Map<String, Object> map) {
        return sqlSessionTemplate.update(namespace + ".check_out", map);
    }

    public List<CommuteBean> findAll() {
        return sqlSessionTemplate.selectList(namespace + ".findAll");
    }

    public List<CommuteBean> findAllExcludingPresident(Map<String, String> map) {
        return sqlSessionTemplate.selectList(namespace + ".findAllExcludingPresident", map);
    }

    public List<CommuteBean> findByDepartmentExcludingPresident(Map<String, String> map) {
        return sqlSessionTemplate.selectList(namespace + ".findByDepartmentExcludingPresident", map);
    }

    public List<CommuteBean> findByEmpId(String empId) {
        return sqlSessionTemplate.selectList(namespace + ".findByEmpId", empId);
    }
}
