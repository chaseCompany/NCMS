package kr.co.chase.ncms.adminUsr.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface AdminUsrService {

	/**
	 * 사용자 목록 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, Object>> getAdminUsrList(HashMap<String, Object> map) throws Exception;

	/**
	 * 단일 사용자 정보 조회
	 * @param string
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getAdminUsrView(String usrId) throws Exception;

	/**
	 * 사용자 정보 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> updateSysUsrAdmin(HashMap<String, Object> map) throws Exception;

	/**
	 * 사용자 목록 검색
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getSysUsrSearchList(HashMap<String, Object> map) throws Exception;

	/**
	 * 사용자 비밀번호 초기화
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> updatePwdReset(HashMap<String, Object> map) throws Exception;

	/**
	 * 사용자 삭제
	 * @param string
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> deleteSysUsr(String usrId) throws Exception;

	/**
	 * 해당 지역 최대 유저값 조회
	 * @param string
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> findMaxSiteUserId(String siteCd) throws Exception;

	/**
	 * 신규 사용자 등록
	 * @param string
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> insertSysUsr(HashMap<String, Object> map) throws Exception;

}