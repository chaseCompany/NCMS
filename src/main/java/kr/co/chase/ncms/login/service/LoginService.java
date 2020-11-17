package kr.co.chase.ncms.login.service;

import java.util.HashMap;
import java.util.List;

public interface LoginService {
	/**
	 * 회원 목록 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getSysUsrList(HashMap<String, Object> map) throws Exception;

	/**
	 * 회원 정보 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getSysUsrInfo(HashMap<String, Object> map) throws Exception;
}