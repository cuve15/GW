package attach.model;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AttachDao {
	
	private final String namespace = "attach.model.Attach";
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    // 첨부파일 삽입 메서드
    public int insertAttach(AttachBean attach) {
        // 첨부파일 정보를 데이터베이스에 삽입
        return sqlSessionTemplate.insert(namespace + ".insertAttach", attach);
    }



	public String getServerFileNameByDocNo(String doc_no) {
		
		return sqlSessionTemplate.selectOne(namespace + ".getServerFileNameByDocNo", doc_no);
	}

}
