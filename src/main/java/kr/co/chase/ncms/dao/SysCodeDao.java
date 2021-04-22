package kr.co.chase.ncms.dao;

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

	/**
	 * 시스템 코드 종류를 조회한다.
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getSysCdAdminGroupTotalList(HashMap<String, Object> map) throws SQLException{
		return selectList("sysCd.getSysCdAdminGroupTotalList", map);
	}

	/**
	 * 시스템 코드를 조회한다.
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getSysCdAdminGroupCdList(String grpCd) throws SQLException{
		return selectList("sysCd.getSysCdAdminGroupCdList", grpCd);
	}

	/**
	 * 시스템 코드를 수정한다.
	 * @return
	 * @throws Exception
	 */
	public int updateSysCdAdmin(HashMap<String, Object> map) throws SQLException{
		return update("sysCd.updateSysCdAdmin", map);
	}

	/**
	 * 시스템 그룹 코드를 검색한다.
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getSysGroupCdSearchList(HashMap<String, Object> map) throws SQLException{
		return selectList("sysCd.getSysGroupCdSearchList", map);
	}
	
	/**
	 * 시스템 코드를 조회한다.
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getSysCd(HashMap<String, Object> map) throws SQLException{
		return selectOne("sysCd.getSysCd", map);
	}

}
