package kr.co.chase.ncms.dao;

import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("cslAssDao")
public class CslAssDao extends EgovAbstractMapper {
	/**
	 * 사정평가 내용 조회
	 * @param mbrNo
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, Object> getCslAssInfo(String mbrNo) throws SQLException{
		return selectOne("cslAss.getCslAssInfo", mbrNo);
	}

	/**
	 * 사정평가 내용 등록
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int insertCslAss(HashMap<String, Object> map) throws SQLException{
		return insert("cslAss.insertCslAss", map);
	}

	/**
	 * 사정평가 내용 수정
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int updateCslAss(HashMap<String, Object> map) throws SQLException{
		return update("cslAss.updateCslAss", map);
	}
}
