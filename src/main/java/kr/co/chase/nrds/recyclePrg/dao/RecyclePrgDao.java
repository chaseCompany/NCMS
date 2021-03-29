package kr.co.chase.nrds.recyclePrg.dao;

import java.sql.SQLException;
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
	public HashMap selectEdPrmInfoMap(HashMap<String, Object> map) throws Exception {
		return selectOne("edPrg.selectEdPrmInfoMap", map);
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
	public int deleteEdPrmInfo(HashMap<String, Object> map) throws Exception {
		return delete("edPrg.deleteEdPrm", map);
	}
	public int deleteEdPrmMbr(HashMap<String, Object> map) throws Exception {
		return delete("edPrg.deleteEdPrmMbr", map);
	}
	public int insertEdPrmMbr(HashMap<String, Object> map) throws SQLException{
		return insert("edPrgMbr.insertGrpPgmMbr", map);
	}
}
