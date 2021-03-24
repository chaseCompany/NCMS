package kr.co.chase.nrds.eduMng.service;

import java.util.HashMap;

public interface EduMngService {

	public int deleteEduMngInfo(HashMap<String, Object> map) throws Exception;
	
	public int updateEduMngInfo(HashMap<String, Object> map) throws Exception;
}
