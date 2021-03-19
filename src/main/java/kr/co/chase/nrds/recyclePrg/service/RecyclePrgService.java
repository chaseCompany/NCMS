package kr.co.chase.nrds.recyclePrg.service;

import java.util.HashMap;
import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface RecyclePrgService {
	
	public List<EgovMap> selectEdPrmList(HashMap<String, Object> map) throws Exception;
	
	public EgovMap selectEdPrmInfo(HashMap<String, Object> map) throws Exception;
	
	public EgovMap selectEdPrmListCount(HashMap<String, Object> map) throws Exception;
	
	public int insertEdPrmInfo(HashMap<String, Object> map) throws Exception;
	
	public int updateEdPrmInfo(HashMap<String, Object> map) throws Exception;
	
	public void deleteEdPrmInfo(HashMap<String, Object> map) throws Exception;
}
