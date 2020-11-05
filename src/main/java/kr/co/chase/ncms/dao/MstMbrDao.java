package kr.co.chase.ncms.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("mstMbrDao")
public class MstMbrDao extends EgovAbstractMapper {
	/**
	 * 회원 목록 조회 카운트
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int getMstMbrListCount(HashMap<String, Object> map) throws SQLException{
		return selectOne("mstMbr.getMstMbrListCount", map);
	}

	/**
	 * 회원 목록 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, Object>> getMstMbrList(HashMap<String, Object> map) throws SQLException{
		return selectList("mstMbr.getMstMbrList", map);
	}
}