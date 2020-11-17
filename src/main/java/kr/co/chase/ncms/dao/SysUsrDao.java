package kr.co.chase.ncms.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("sysUsrDao")
public class SysUsrDao extends EgovAbstractMapper {
	/**
	 * 관리자 목록 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, Object>> getSysUsrList() throws SQLException{
		return selectList("sysUsr.getSysUsrList");
	}

	/**
	 * 관리자 로그인 정보 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, Object> getSysUsrInfo(HashMap<String, Object> map) throws SQLException{
		return selectOne("sysUsr.getSysUsrInfo", map);
	}
}