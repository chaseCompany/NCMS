package kr.co.chase.ncms.common.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
 * 관리자사이트 IP접근제어 DAO
 * @author jhg
 *
 */
@Repository("ipControllMgrDao")
public class IpControllMgrDao extends EgovAbstractMapper{
	/**
	 * 시스템별 접근가능 IP목록 리스트를 조회한다.
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<?> getSystemAccessIpList(HashMap<String, Object> map)throws SQLException{
		return selectList("ipControllMgr.getSystemAccessIpList", map);
	}
}