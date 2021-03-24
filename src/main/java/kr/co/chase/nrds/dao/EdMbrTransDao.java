package kr.co.chase.nrds.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("edMbrTransDao")
public class EdMbrTransDao extends EgovAbstractMapper {
	/**
	 * 연계 정보 등록
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int insertEdMbrTrans(HashMap<String, Object> map) throws SQLException{
		return insert("edMbrTrans.insertEdMbrTrans", map);
	}

	/**
	 * 연계 기본 정보 조회
	 * @param mbrNo
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, Object> getEdMbrTransLastInfo(String mbrNo) throws SQLException{
		return selectOne("edMbrTrans.getEdMbrTransLastInfo", mbrNo);
	}

	/**
	 * 연계 상세 정보 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, Object> getEdMbrTransInfo(HashMap<String, Object> map) throws SQLException{
		return selectOne("edMbrTrans.getEdMbrTransInfo", map);
	}

	/**
	 * 연계 목록 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, Object>> getEdMbrTransList(HashMap<String, Object> map) throws SQLException{
		return selectList("edMbrTrans.getEdMbrTransList", map);
	}

	/**
	 * 연계 정보 수정
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int updateEdMbrTrans(HashMap<String, Object> map) throws SQLException{
		return update("edMbrTrans.updateEdMbrTrans", map);
	}

	/**
	 * 연계 정보 삭제
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int deleteEdMbrTrans(HashMap<String, Object> map) throws SQLException{
		return delete("edMbrTrans.deleteEdMbrTrans", map);
	}
}