package message.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import utility.Paging;

@Component
public class MessageDao {
	private final String namespace = "message.model.Message";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public List<MessageBean> getAllReceive(String receive_emp_no, Map<String, String> map, Paging pageInfo) {
		
		map.put("receive_emp_no", receive_emp_no);
		
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(),pageInfo.getLimit());
		
		List<MessageBean> list = sqlSessionTemplate.selectList(namespace + ".getAllReceive",map,rowBounds);
		
		return list;
	}
	
	public List<MessageBean> getAllSend(String send_emp_no, Map<String, String> map, Paging pageInfo) {
		
		map.put("send_emp_no", send_emp_no);
		
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(),pageInfo.getLimit());
		
		List<MessageBean> list = sqlSessionTemplate.selectList(namespace + ".getAllSend",map,rowBounds);
		
		return list;
	}

	public int ReceivetotalCount(String receive_emp_no, Map<String, String> map) {
		
		map.put("receive_emp_no", receive_emp_no);
		
		int totalCount = sqlSessionTemplate.selectOne(namespace + ".ReceivetotalCount",map);
		
		return totalCount;
	}
	
	public int SendtotalCount(String send_emp_no, Map<String, String> map) {
		
		map.put("send_emp_no", send_emp_no);
		
		int totalCount = sqlSessionTemplate.selectOne(namespace + ".SendtotalCount",map);
		
		return totalCount;
	}

	public MessageBean detailMessage(String msg_no, String emp_no) {
		
		MessageBean mb1 = sqlSessionTemplate.selectOne(namespace + ".detailMessage",msg_no);
		
		if(emp_no.equals(mb1.getReceive_emp_no())) {
			sqlSessionTemplate.update(namespace + ".read_ynMessage",msg_no);
		}
		
		MessageBean mb = sqlSessionTemplate.selectOne(namespace + ".detailMessage",msg_no);
		
		return mb;
	}
	
	public MessageBean detailMessage(String msg_no) {
		
		MessageBean mb = sqlSessionTemplate.selectOne(namespace + ".detailMessage",msg_no);
		
		return mb;
	}

	public int SendMessage(MessageBean mb) {
		
		int cnt = -1;
		
		cnt = sqlSessionTemplate.insert(namespace + ".SendMessage",mb);
		System.out.println("SendMessage : " + cnt);
		return cnt;
	}

	public int readCount(String receive_emp_no, Map<String, String> map) {
		
		map.put("receive_emp_no", receive_emp_no);
		
		int cnt = sqlSessionTemplate.selectOne(namespace + ".readCount",map);
		
		return cnt;
	}
	
	public int sendCount(String send_emp_no, Map<String, String> map) {
		
		map.put("send_emp_no", send_emp_no);
		
		int cnt = sqlSessionTemplate.selectOne(namespace + ".sendCount",map);
		
		return cnt;
	}

	public int deleteMessage(MessageBean mb) {
		
		int cnt = sqlSessionTemplate.delete(namespace + ".deleteMessage",mb);
		
		return cnt;
	}

	public int deleteSendUpdateMessage(MessageBean mb) {
		
		int cnt = sqlSessionTemplate.update(namespace + ".deleteSendUpdateMessage",mb);
		
		return cnt;
	}

	public int deleteReceiveUpdateMessage(MessageBean mb) {
		
		int cnt = sqlSessionTemplate.update(namespace + ".deleteReceiveUpdateMessage",mb);
		
		return cnt;
	}

	public String selectOneNum() {
		
		String msg_no_seq = sqlSessionTemplate.selectOne(namespace + ".selectOneNum");
		
		return msg_no_seq;
	}
}
