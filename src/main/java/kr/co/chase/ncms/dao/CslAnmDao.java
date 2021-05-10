package kr.co.chase.ncms.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("cslAnmDao")
public class CslAnmDao extends EgovAbstractMapper {
	/**
	 * 병력정보 목록 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, Object>> getCslAnmList(HashMap<String, Object> map) throws SQLException{
		return selectList("cslAnm.getCslAnmInfo", map);
	}

	/**
	 * 병력정보 상세 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, Object> getCslAnmInfo(HashMap<String, Object> map) throws SQLException{
		return selectOne("cslAnm.getCslAnmInfo", map);
	}
	public List<HashMap<String, Object>> getCslAnmInfoStatistics(HashMap<String, Object> map) throws SQLException{
		return selectList("cslAnm.getCslAnmInfo", map);
	}

	/**
	 * 병력정보 고유키 생성
	 * @param mbrNo
	 * @return
	 * @throws SQLException
	 */
	public String getCslAnmSeq(String mbrNo) throws SQLException{
		return selectOne("cslAnm.getCslAnmSeq", mbrNo);
	}

	/**
	 *병력정보 등록
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int insertCslAnm(HashMap<String, Object> map) throws SQLException{
		return insert("cslAnm.insertCslAnm", map);
	}

	/**
	 * 병력정보 수정
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int updateCslAnm(HashMap<String, Object> map) throws SQLException{
		return update("cslAnm.updateCslAnm", map);
	}

	/**
	 * 병력정보 삭제
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int deleteCslAnm(HashMap<String, Object> map) throws SQLException{
		return delete("cslAnm.deleteCslAnm", map);
	}
}