package admin.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import utility.Paging;

@Component("DeptDao")
public class DeptDao {
		
		private final String namespace = "admin.model.Admin";
		@Autowired
		SqlSessionTemplate sqlSessionTemplate;

		public List<DeptBean> getAllDept(Paging pageInfo, Map<String, String> map) {
			
			RowBounds rowBounds = new RowBounds(pageInfo.getOffset(),pageInfo.getLimit());
			List<DeptBean> lists = sqlSessionTemplate.selectList(namespace+".getAllDept",map,rowBounds);
			
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

		public int getTotalCount(Map<String, String> map) {
			
			int cnt = sqlSessionTemplate.selectOne(namespace+".getTotalCount",map);
			
			return cnt;
		}

		public List<DeptBean> getDeptCd() {
			
			List<DeptBean> lists= sqlSessionTemplate.selectList(namespace+".getDeptCd");
			
			return lists;
		}
		
}
