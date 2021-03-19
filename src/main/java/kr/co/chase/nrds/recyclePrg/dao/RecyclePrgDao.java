package kr.co.chase.nrds.recyclePrg.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Repository("RecyclePrgDao")
public class RecyclePrgDao extends EgovAbstractMapper{
	
	public List<EgovMap> selectEdPrmList(HashMap<String, Object> map) throws Exception{
		return selectList("edPrg.selectEdPrmList", map);
	}
	
	public EgovMap selectEdPrmInfo(HashMap<String, Object> map) throws Exception {
		return selectOne("edPrg.selectEdPrmInfo", map);
	}
	public int insertEdPrmInfo(HashMap<String, Object> map) throws Exception {
		return insert("edPrg.insertEdPrmInfo", map);
	}
	
	public int updateEdPrmInfo(HashMap<String, Object> map) throws Exception {
		return update("edPrg.updateEdPrmInfo", map);
	}
	
	public EgovMap selectEdPrmListCount(HashMap<String, Object> map) throws Exception{
		return selectOne("edPrg.selectEdPrmListCount", map);
	}
	public void deleteEdPrmInfo(HashMap<String, Object> map) throws Exception {
		delete("edPrg.deleteEdPrm", map);
	}
}
