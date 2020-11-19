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
	 * 유저 목록 검색
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getSysUsrSearchList(HashMap<String, Object> map) throws Exception;
}