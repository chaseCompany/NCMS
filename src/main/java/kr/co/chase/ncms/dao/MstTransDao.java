package kr.co.chase.ncms.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("mstTransDao")
public class MstTransDao extends EgovAbstractMapper {
	/**
	 * 의뢰 고유키 생성
	 * @return
	 * @throws SQLException
	 */
	public String getMstTransSeq() throws SQLException{
		return selectOne("mstTrans.getMstTransSeq");
	}

	/**
	 * 의뢰정보 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, Object> getMstTrans(HashMap<String, Object> map) throws SQLException{
		return selectOne("mstTrans.getMstTrans", map);
	}

	/**
	 * 의뢰정보 등록
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int insertMstTrans(HashMap<String, Object> map) throws SQLException{
		return insert("mstTrans.insertMstTrans", map);
	}

	/**
	 * 의뢰정보 수정
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int updateMstTrans(HashMap<String, Object> map) throws SQLException{
		return update("mstTrans.updateMstTrans", map);
	}

	/**
	 * 연계정보 저장
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int updateMstTransReceipt(HashMap<String, Object> map) throws SQLException{
		return update("mstTrans.updateMstTransReceipt", map);
	}

	/**
	 * 의뢰정보 목록 카운트
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int getMstTransListCount(HashMap<String, Object> map) throws SQLException{
		return selectOne("mstTrans.getMstTransListCount", map);
	}

	/**
	 * 의뢰정보 목록 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, Object>> getMstTransList(HashMap<String, Object> map) throws SQLException{
		return selectList("mstTrans.getMstTransList", map);
	}
}