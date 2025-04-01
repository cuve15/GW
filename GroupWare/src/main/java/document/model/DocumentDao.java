package document.model;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DocumentDao {
	
	private final String namespace = "document.model.Document";
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    // 문서 정보 삽입 메서드
    public int insertDocument(DocumentBean document) {

        // 문서 정보를 데이터베이스에 삽입
        return sqlSessionTemplate.insert(namespace + ".insertDocument", document);
    }

    public String selectOneNum() {
        return sqlSessionTemplate.selectOne(namespace + ".selectOneNum");
    }
}
