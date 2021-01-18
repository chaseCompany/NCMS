package kr.co.chase.ncms.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("cslHealDao")
public class CslHealDao extends EgovAbstractMapper {
	/**
	 * 치료재활정보 목록 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, Object>> getCslHealList(HashMap<String, Object> map) throws SQLException{
		return selectList("cslHeal.getCslHeal", map);
	}

	/**
	 * 치료재활정보 상세 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, Object> getCslHealInfo(HashMap<String, Object> map) throws SQLException{
		return selectOne("cslHeal.getCslHeal", map);
	}

	/**
	 * 치료재활정보 고유키 생성
	 * @return
	 * @throws SQLException
	 */
	public String getCslHealSeq() throws SQLException{
		return selectOne("cslHeal.getCslHealSeq");
	}

	/**
	 * 치료재활정보 등록
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int insertCslHeal(HashMap<String, Object> map) throws SQLException{
		return insert("cslHeal.insertCslHeal", map);
	}

	/**
	 * 치료재활정보 수정
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int updateCslHeal(HashMap<String, Object> map) throws SQLException{
		return update("cslHeal.updateCslHeal", map);
	}

	/**
	 * 치료재활정보 삭제
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int deleteCslHeal(HashMap<String, Object> map) throws SQLException{
		return delete("cslHeal.deleteCslHeal", map);
	}
}