package kr.co.chase.ncms.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("grpPgmDao")
public class GrpPgmDao extends EgovAbstractMapper {
	/**
	 * 주간프로그램 상세 정보 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, Object> getGrpPgm(HashMap<String, Object> map) throws SQLException{
		return selectOne("grpPgm.getGrpPgm", map);
	}
	public List<HashMap<String, Object>> getGrpPgmStatsistics(HashMap<String, Object> map) throws SQLException{
		return selectList("grpPgm.getGrpPgm", map);
	}

	/**
	 * 주간프로그램 목록 조회 카운트
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int getGrpPgmListCount(HashMap<String, Object> map) throws SQLException{
		return selectOne("grpPgm.getGrpPgmListCount", map);
	}

	/**
	 * 주간프로그램 목록 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, Object>> getGrpPgmList(HashMap<String, Object> map) throws SQLException{
		return selectList("grpPgm.getGrpPgmList", map);
	}

	/**
	 * 주간프로그램 등록
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int insertGrpPgm(HashMap<String, Object> map) throws SQLException{
		return insert("grpPgm.insertGrpPgm", map);
	}

	/**
	 * 주간프로그램 수정
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int updateGrpPgm(HashMap<String, Object> map) throws SQLException{
		return update("grpPgm.updateGrpPgm", map);
	}

	/**
	 * 주간프로그램 삭제
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int deleteGrpPgm(HashMap<String, Object> map) throws SQLException{
		return delete("grpPgm.deleteGrpPgm", map);
	}
}