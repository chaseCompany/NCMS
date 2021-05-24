package kr.co.chase.nrds.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("edMbrLawDao")
public class EdMbrLawDao extends EgovAbstractMapper {
	/**
	 * 법정의무교육 등록
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int insertEdMbrLaw(HashMap<String, Object> map) throws SQLException{
		return insert("edMbrLaw.insertEdMbrLaw", map);
	}

	/**
	 * 법정의무교육 기본 정보 조회
	 * @param mbrNo
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, Object> getEdMbrLawLastInfo(String mbrNo) throws SQLException{
		return selectOne("edMbrLaw.getEdMbrLawLastInfo", mbrNo);
	}

	/**
	 * 법정의무교육 상세 정보 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, Object> getEdMbrLawInfo(HashMap<String, Object> map) throws SQLException{
		return selectOne("edMbrLaw.getEdMbrLawInfo", map);
	}

	/**
	 * 법정의무교육 목록 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, Object>> getEdMbrLawList(HashMap<String, Object> map) throws SQLException{
		return selectList("edMbrLaw.getEdMbrLawList", map);
	}

	/**
	 * 법정의무교육 수정
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int updateEdMbrLaw(HashMap<String, Object> map) throws SQLException{
		return update("edMbrLaw.updateEdMbrLaw", map);
	}

	/**
	 * 법정의무교육 삭제
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int deleteEdMbrLaw(HashMap<String, Object> map) throws SQLException{
		return delete("edMbrLaw.deleteEdMbrLaw", map);
	}
}