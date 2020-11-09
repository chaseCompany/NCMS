package kr.co.chase.ncms.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("cslAssEvlDao")
public class CslAssEvlDao extends EgovAbstractMapper {
	/**
	 * 사정 평가 평가도구 상세 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, Object> getCslAssEvlInfo(HashMap<String, Object> map) throws SQLException{
		return selectOne("cslAssEvl.getCslAssEvlInfo", map);
	}

	/**
	 * 사정 평가 평가도구 목록 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, Object>> getCslAssEvlList(String mbrNo) throws SQLException{
		return selectList("cslAssEvl.getCslAssEvlList", mbrNo);
	}

	/**
	 * 사정 평가 평가도구 시퀀스 조회
	 * @param mbrNo
	 * @return
	 * @throws SQLException
	 */
	public String getEvlSqeNext(String mbrNo) throws SQLException{
		return selectOne("cslAssEvl.getEvlSqeNext", mbrNo);
	}

	/**
	 * 사정 평가 평가도구 등록
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int insertCslAssEvl(HashMap<String, Object> map) throws SQLException{
		return insert("cslAssEvl.insertCslAssEvl", map);
	}

	/**
	 * 사정 평가 평가도구 삭제
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int deleteCslAssEvl(HashMap<String, Object> map) throws SQLException{
		return delete("cslAssEvl.deleteCslAssEvl", map);
	}
}