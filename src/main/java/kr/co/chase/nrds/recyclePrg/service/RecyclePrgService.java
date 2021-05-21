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
	
	public int deleteEdPrmInfo(HashMap<String, Object> map) throws Exception;

	public int deleteEdPrmMbr(HashMap<String, Object> map) throws Exception;

	public int deleteEdPrm(HashMap<String, Object> map) throws Exception;
	
	public HashMap<String, Object> processEdPrm(HashMap<String, Object> map) throws Exception;
	
	public HashMap<String, Object> getEdPrm(HashMap<String, Object> map) throws Exception;
	public List<HashMap<String, Object>> getEdPrmStatistics(HashMap<String, Object> map) throws Exception;

	public List<HashMap<String, Object>> getEdPgmMbrList(HashMap<String, Object> map) throws Exception;

	public int deleteAllEdPrmMbr(HashMap<String, Object> map) throws Exception;
}
