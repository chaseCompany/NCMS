package kr.co.chase.ncms.adminCode.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface AdminCodeService {

	/**
	 * 전체 코드 목록 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getSysCdAdminGroupTotalList(HashMap<String, Object> map) throws Exception;
	
	/**
	 * 개별 코드 목록 조회
	 * @param grpCd
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getSysCdAdminGroupCdList(String grpCd) throws Exception;

	/**
	 * 코드정보 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> updateSysCdAdmin(HashMap<String, Object> map) throws Exception;
}