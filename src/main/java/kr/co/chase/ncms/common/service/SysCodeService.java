package kr.co.chase.ncms.common.service;

import java.util.HashMap;
import java.util.List;

public interface SysCodeService {
	public List<HashMap<String, Object>> getSysCdList(HashMap<String, Object> map) throws Exception;

	public int insertSysCd(HashMap<String, Object> map) throws Exception;
}