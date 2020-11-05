package kr.co.chase.ncms.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("cslIdvDao")
public class CslIdvDAO extends EgovAbstractMapper {
	/**
	 * 회원별 집중상담 이력 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, Object>> getCslIdvList(HashMap<String, Object> map) throws SQLException{
		return selectList("cslIdv.getCslIdvList", map);
	}

	/**
	 * 집중 상담 내용을 조회 한다
	 * @param cslNo
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, Object> getCslIdv(String cslNo) throws SQLException{
		return selectOne("cslIdv.getCslIdv", cslNo);
	}

	/**
	 * 집중 상담이력을 등록 한다.
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int insertCslIdv(HashMap<String, Object> map) throws SQLException{
		return insert("cslIdv.insertCslIdv", map);
	}
}