package kr.co.chase.nrds.eduCounsel.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("eduCounselDao")
public class EduCounselDao extends EgovAbstractMapper{

	/**
	 * 회원별 교육상담 이력 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, Object>> getCslCureList(HashMap<String, Object> map) throws SQLException{
		return selectList("eduCounsel.getCslCureList", map);
	}

	/**
	 * 교육상담 내용을 조회 한다
	 * @param cslNo
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, Object> getCslCure(HashMap<String, Object> map) throws SQLException{
		return selectOne("eduCounsel.getCslCure", map);
	}
	public List<HashMap<String, Object>> getCslCureStatistics(HashMap<String, Object> map) throws SQLException{
		return selectList("eduCounsel.getCslCure", map);
	}

	/**
	 * 교육상담 이력을 등록 한다.
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int insertCslCure(HashMap<String, Object> map) throws SQLException{
		return insert("eduCounsel.insertCslCure", map);
	}

	/**
	 * 교육상담 이력 수정
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int updateCslCure(HashMap<String, Object> map) throws SQLException{
		return update("eduCounsel.updateCslCure", map);
	}

	/**
	 * 교육상담 번호 생성
	 * @return
	 * @throws SQLException
	 */
	public String getCslCureSeq() throws SQLException{
		return selectOne("eduCounsel.getCslCureSeq");
	}

	/**
	 * 교육상담 내용 삭제
	 * @param clsNo
	 * @return
	 * @throws SQLException
	 */
	public int deleteCslCure(String clsNo) throws SQLException{
		return delete("eduCounsel.deleteCslCure", clsNo);
	}
}
