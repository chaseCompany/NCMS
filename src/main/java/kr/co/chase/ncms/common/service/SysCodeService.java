package kr.co.chase.ncms.common.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface SysCodeService {
	/**
	 * 코드 목록 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getSysCdList(HashMap<String, Object> map) throws Exception;

	/**
	 * 코드 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertSysCd(HashMap<String, Object> map) throws Exception;
	
	/**
	 * 시스템 코드를 조회한다.
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getSysCd(HashMap<String, Object> map) throws SQLException;
}