package admin.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("AuthInfoDao")
public class AuthInfoDao {

		private final String namespace = "admin.model.Admin";
		
		@Autowired
		SqlSessionTemplate sqlSessionTemplate;

		public List<AuthInfoBean> getAllAuthInfo() {

			List<AuthInfoBean> authList = sqlSessionTemplate.selectList(namespace+".getAllAuthInfo");
			
			return authList;
		}

		public int insertAuth(AuthInfoBean authBean) {

				int cnt = sqlSessionTemplate.insert(namespace+".insertAuth",authBean);
			return cnt;
		}
}
