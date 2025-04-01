package reply.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("rdao")
public class ReplyDao {
	
private final String namespace="reply.model.Reply";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public int writeReply(ReplyBean reply) {
		int cnt = sqlSessionTemplate.insert(namespace + ".writeReply", reply);
		System.out.println(cnt);
		return cnt;
	}//writeReply

	public List<ReplyBean> selectAllReply(int notice_no) {
		List<ReplyBean> lists = sqlSessionTemplate.selectList(namespace + ".selectAllReply", notice_no);
		return lists;
	}//selectAllReply

	public int deleteReply(int reply_no) {
		int cnt = sqlSessionTemplate.delete(namespace + ".deleteReply", reply_no);
		System.out.println(cnt);
		return cnt;
	}//deleteReply
	
}
