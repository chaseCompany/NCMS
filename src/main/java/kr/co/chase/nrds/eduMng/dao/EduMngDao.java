package kr.co.chase.nrds.eduMng.dao;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("EduMngDao")
public class EduMngDao extends EgovAbstractMapper{
	public int deleteEduMngInfo(HashMap<String, Object> map) throws Exception {
		return delete("eduMng.deleteEdPrm", map);
	}
	
	public int updateEduMngInfo(HashMap<String, Object> map) throws Exception {
		return update("eduMng.updateEdPrmInfo", map);
	}
}
