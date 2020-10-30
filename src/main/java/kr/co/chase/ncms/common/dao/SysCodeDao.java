package kr.co.chase.ncms.common.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
 * 시스템 코드 관리 DAO
 * @author jhg
 *
 */
@Repository("sysCodeDao")
public class SysCodeDao extends EgovAbstractMapper {
	/**
	 * 시스템 코드 정보 리스트를 조회한다.
	 * @return
	 * @throws Exception
	 */
	
	public List<HashMap<String, Object>> getSysCdList(HashMap<String, Object> map) throws SQLException{
		return selectList("sysCd.getSysCdList", map);
	}

	/**
	 * 시스템 코드 정보를 신규추가한다.
	 * @param map
	 * @throws Exception
	 */
	
	public int insertSysCd(HashMap<String, Object> map) throws SQLException{
		return insert("sysCd.insertSysCd", map);
	}
}
