package kr.co.chase.nrds.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("edMbrGuDao")
public class EdMbrGuDao extends EgovAbstractMapper {
	/**
	 * 의뢰 선도조건부 기소유예 등록
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int insertEdMbrGu(HashMap<String, Object> map) throws SQLException{
		return insert("edMbrGu.insertEdMbrGu", map);
	}

	/**
	 * 의뢰 선도조건부 기소유예 기본 정보 조회
	 * @param mbrNo
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, Object> getEdMbrGuLastInfo(String mbrNo) throws SQLException{
		return selectOne("edMbrGu.getEdMbrGuLastInfo", mbrNo);
	}

	/**
	 * 의뢰 선도조건부 기소유예 상세 정보 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, Object> getEdMbrGuInfo(HashMap<String, Object> map) throws SQLException{
		return selectOne("edMbrGu.getEdMbrGuInfo", map);
	}

	/**
	 * 의뢰 선도조건부 기소유예 목록 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, Object>> getEdMbrGuList(HashMap<String, Object> map) throws SQLException{
		return selectList("edMbrGu.getEdMbrGuList", map);
	}

	/**
	 * 의뢰 선도조건부 기소유예 수정
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int updateEdMbrGu(HashMap<String, Object> map) throws SQLException{
		return update("edMbrGu.updateEdMbrGu", map);
	}

	/**
	 * 의뢰 선도조건부 기소유예 삭제
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int deleteEdMbrGu(HashMap<String, Object> map) throws SQLException{
		return delete("edMbrGu.deleteEdMbrGu", map);
	}
}