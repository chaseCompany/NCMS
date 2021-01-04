package kr.co.chase.ncms.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("cslCureDao")
public class CslCureDao extends EgovAbstractMapper {
	/**
	 * 회원별 심리치유 이력 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, Object>> getCslCureList(HashMap<String, Object> map) throws SQLException{
		return selectList("cslCure.getCslCureList", map);
	}

	/**
	 * 심리치유 내용을 조회 한다
	 * @param cslNo
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, Object> getCslCure(String cslNo) throws SQLException{
		return selectOne("cslCure.getCslCure", cslNo);
	}

	/**
	 * 심리치유 이력을 등록 한다.
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int insertCslCure(HashMap<String, Object> map) throws SQLException{
		return insert("cslCure.insertCslCure", map);
	}

	/**
	 * 심리치유 이력 수정
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int updateCslCure(HashMap<String, Object> map) throws SQLException{
		return update("cslCure.updateCslCure", map);
	}

	/**
	 * 심리치유 번호 생성
	 * @return
	 * @throws SQLException
	 */
	public String getCslCureSeq() throws SQLException{
		return selectOne("cslCure.getCslCureSeq");
	}

	/**
	 * 심리치유 내용 삭제
	 * @param clsNo
	 * @return
	 * @throws SQLException
	 */
	public int deleteCslCure(String clsNo) throws SQLException{
		return delete("cslCure.deleteCslCure", clsNo);
	}
}