package kr.co.chase.ncms.usrChgPwd.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface UsrChgPwdService {

	/**
	 * 신규 비밀번호 업데이트
	 * @param string
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> updateNewUsrPwd(HashMap<String, Object> map) throws Exception;

}