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
	public List<HashMap<String, Object>> getSysUsrList(HashMap<String, Object> map) throws SQLException{
		return selectList("sysUsr.getSysUsrList", map);
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

	/**
	 * 관리자 전체 사용자 목록 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, Object>> getAdminUsrList(HashMap<String, Object> map) throws SQLException{
		return selectList("sysUsr.getAdminUsrList", map);
	}

	/**
	 * 관리자 단일 사용자 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, Object> getAdminUsrView(String usrId) throws SQLException{
		return selectOne("sysUsr.getAdminUsrView", usrId);
	}

	/**
	 * 시스템 사용자를 수정한다.
	 * @return
	 * @throws Exception
	 */
	public int updateSysUsrAdmin(HashMap<String, Object> map) throws SQLException{
		return update("sysUsr.updateSysUsrAdmin", map);
	}

	/**
	 * 시스템 사용자를 검색한다.
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getSysUsrSearchList(HashMap<String, Object> map) throws SQLException{
		return selectList("sysUsr.getSysUsrSearchList", map);
	}

	/**
	 * 시스템 사용자 비밀번호를 초기화한다.
	 * @return
	 * @throws Exception
	 */
	public int updatePwdReset(HashMap<String, Object> map) throws SQLException{
		return update("sysUsr.updatePwdReset", map);
	}

	/**
	 * 시스템 사용자 비밀번호를 초기화한다.
	 * @return
	 * @throws Exception
	 */
	public int deleteSysUsr(String usrId) throws SQLException{
		return update("sysUsr.deleteSysUsr", usrId);
	}

	/**
	 * 현재 지부의 최대 아이디값을 검색한다.
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> findMaxSiteUserId(String siteCd) throws SQLException{
		return selectOne("sysUsr.findMaxSiteUserId", siteCd);
	}

	/**
	 * 신규 사용자를 등록한다.
	 * @return
	 * @throws Exception
	 */
	public int insertSysUsr(HashMap<String, Object> map) throws SQLException{
		return insert("sysUsr.insertSysUsr", map);
	}

}