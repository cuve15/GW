package approval.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import document.model.DocumentBean;
import utility.Paging;


@Repository
public class ApprovalDao {

	private final String namespace = "approval.model.Approval";
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	// 결제 순서 삽입 메서드
	public int insertApprovalLine(ApprovalBean approval) {
		// 결제 순서 정보를 데이터베이스에 삽입
		return sqlSessionTemplate.insert(namespace + ".insertApprovalLine", approval);
	}


	public List<ApprovalBean> getApprovalList(Paging pageInfo, Map<String, String> map) {
				
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
		
		List<ApprovalBean> ApprovalList = sqlSessionTemplate.selectList(namespace + ".getApprovalList", map, rowBounds);

		return ApprovalList;
	}


	public int companionApprovalStatus(int apprNo, String appr_refuse_op) {
		
	    ApprovalBean ab = new ApprovalBean();
	    ab.setAppr_no(apprNo);
	    ab.setAppr_refuse_op(appr_refuse_op);
	    
		int cnt = sqlSessionTemplate.update(namespace + ".companionApprovalStatus", ab);
		
		return cnt;
	}


	public int signOffDocument(int appr_no, int appr_order, String position_cd) {
	    ApprovalBean ab = new ApprovalBean();
	    ab.setAppr_no(appr_no);
	    ab.setAppr_order(appr_order);
	    ab.setPosition_cd(position_cd);
		
		int cnt = sqlSessionTemplate.update(namespace + ".signOffDocument", ab); 
		
		return cnt;
	}


	public int getTotalCount(Map<String, String> map) {
		int cnt = sqlSessionTemplate.selectOne(namespace + ".getTotalCount" , map);
		return cnt;
	}


	public List<ApprovalBean> getApprovalSignList(Paging pageInfo, Map<String, String> map) {
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
		
		List<ApprovalBean> getApprovalSignList = sqlSessionTemplate.selectList(namespace + ".getApprovalSignList", map, rowBounds);
		return getApprovalSignList;
	}

}
