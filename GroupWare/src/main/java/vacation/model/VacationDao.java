package vacation.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import utility.Paging;

@Repository
public class VacationDao {
	
	private final String namespace = "vacation.model.Vaction";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	
	public int insertVacation(VacationBean vacation) {
		int cnt = sqlSessionTemplate.insert(namespace + ".insertVaction", vacation);
		
		return cnt;
	}


	public int getTotalCount(Map<String, String> map) {
		int cnt = sqlSessionTemplate.selectOne(namespace + ".getTotalCount", map);
		return cnt;
	}


	public List<VacationBean> getVacationList(Paging pageInfo, Map<String, String> map) {
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
		
		List<VacationBean> VacationList = sqlSessionTemplate.selectList(namespace + ".getVacationList", map, rowBounds);
		return VacationList;
	}


	public int vacationApproval(String vacation_no, String emp_no) {
	    System.out.println("DAO에서 받은 vacation_no: " + vacation_no);
	    System.out.println("DAO에서 받은 appr_emp_no: " + emp_no);

	    VacationBean vb = new VacationBean();
	    vb.setVacation_no(vacation_no);
	    vb.setAppr_emp_no(emp_no);

	    int cnt = sqlSessionTemplate.update(namespace + ".vacationApproval", vb);

	    System.out.println("업데이트된 행 개수: " + cnt);
	    return cnt;
	}
}
