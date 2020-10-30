package kr.co.chase.ncms.login.service;

import java.util.HashMap;
import java.util.List;

public interface LoginService {
	public List<HashMap<String, Object>> getSysUsrList(HashMap<String, Object> map) throws Exception;

	public HashMap<String, Object> getSysUsrInfo(HashMap<String, Object> map) throws Exception;
}