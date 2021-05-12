package kr.co.chase.nrds.recyclePrg.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Repository("RecyclePrgMbrDao")
public class RecyclePrgMbrDao extends EgovAbstractMapper{

	/**
	 * 프로그램 참여자 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, Object>> getEdPgmMbrList(HashMap<String, Object> map) throws SQLException{
		return selectList("EdPrgMbr.getEdPgmMbrList", map);
	}

	/**
	 * 프로그램 참여자 등록
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int insertEdPgmMbr(HashMap<String, Object> map) throws SQLException{
		System.out.println("pgmId::::::::::::  "+map.get("pgmId"));
		return insert("EdPrgMbr.insertEdPgmMbr", map);
	}

	/**
	 * 프로그램 참여자 진행 기록 수정
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int updateEdPgmMbrCtnt(HashMap<String, Object> map) throws SQLException{
		return update("EdPrgMbr.updateEdPgmMbrCtnt", map);
	}

	/**
	 * 프로그램 참여자 삭제
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int deleteEdPgmMbr(HashMap<String, Object> map) throws SQLException{
		return delete("EdPrgMbr.deleteEdPgmMbr", map);
	}
	
	/**
	 * 프로그램 참여자 전체 삭제
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int deleteAllEdPgmMbr(HashMap<String, Object> map) throws SQLException{
		return delete("EdPrgMbr.deleteAllEdPgmMbr", map);
	}
	
}
