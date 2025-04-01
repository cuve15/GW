package attach.model;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AttachDao {
	
	private final String namespace = "attach.model.Attach";
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    // 泥⑤��뙆�씪 �궫�엯 硫붿꽌�뱶
    public int insertAttach(AttachBean attach) {
        // 泥⑤��뙆�씪 �젙蹂대�� �뜲�씠�꽣踰좎씠�뒪�뿉 �궫�엯
        return sqlSessionTemplate.insert(namespace + ".insertAttach", attach);
    }



	public String getServerFileNameByDocNo(String msg_no) {
		
		return sqlSessionTemplate.selectOne(namespace + ".getServerFileNameByDocNo", msg_no);
	}



	public AttachBean getOneSelect(String msg_no) {
		
		AttachBean attach = sqlSessionTemplate.selectOne(namespace + ".getOneSelect",msg_no);
		
		return attach;
	}


}
