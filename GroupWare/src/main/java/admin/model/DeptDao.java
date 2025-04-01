package admin.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("DeptDao")
public class DeptDao {
		
		private final String namespace = "admin.model.Admin";
		@Autowired
		SqlSessionTemplate sqlSessionTemplate;

		public List<DeptBean> getAllDept() {
			
			List<DeptBean> lists = sqlSessionTemplate.selectList(namespace+".getAllDept");
			
			return lists;
		}

		public int insertDept(DeptBean deptBean) {
			int cnt = sqlSessionTemplate.insert(namespace+".insertDept",deptBean);
			return cnt;
		}

		public DeptBean getOneDept(String dept_cd) {
				DeptBean deptBean = sqlSessionTemplate.selectOne(namespace+".getOneDept",dept_cd);
			return deptBean;
		}

		public int updateDept(DeptBean deptBean) {
		 
			int cnt = sqlSessionTemplate.update(namespace+".updateDept",deptBean);
			
			return cnt;
		}
		
}
