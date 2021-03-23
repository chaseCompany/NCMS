package kr.co.chase.nrds.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("edMbrEdDao")
public class EdMbrEdDao extends EgovAbstractMapper {
	/**
	 * 의뢰 교육조건부 기소유예 등록
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int insertEdMbrEd(HashMap<String, Object> map) throws SQLException{
		return insert("edMbrEd.insertEdMbrEd", map);
	}

	/**
	 * 의뢰 교육조건부 기소유예 대상자 기본 정보 조회
	 * @param mbrNo
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, Object> getEdMbrEdLastInfo(String mbrNo) throws SQLException{
		return selectOne("edMbrEd.getEdMbrEdLastInfo", mbrNo);
	}

	/**
	 * 의뢰 교육조건부 기소유예 상세 정보 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, Object> getEdMbrEdInfo(HashMap<String, Object> map) throws SQLException{
		return selectOne("edMbrEd.getEdMbrEdInfo", map);
	}

	/**
	 * 의뢰 교육조건부 기소유예 목록 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, Object>> getEdMbrEdList(HashMap<String, Object> map) throws SQLException{
		return selectList("edMbrEd.getEdMbrEdList", map);
	}

	/**
	 * 의뢰 교육조건부 기소유예 내용 수정
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int updateEdMbrEd(HashMap<String, Object> map) throws SQLException{
		return update("edMbrEd.updateEdMbrEd", map);
	}

	/**
	 * 의뢰 교육조건부 기소유예 삭제
	 * @param mbrEdId
	 * @return
	 * @throws SQLException
	 */
	public int deleteEdMbrEd(HashMap<String, Object> map) throws SQLException{
		return delete("edMbrEd.deleteEdMbrEd", map);
	}
}