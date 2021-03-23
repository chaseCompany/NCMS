package kr.co.chase.ncms.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
 * 상담 이력 관리
 * @author Molra
 */
@Repository("cslRcpDao")
public class CslRcpDao extends EgovAbstractMapper {
	/**
	 * 상담이력 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, Object> getCslRcp(HashMap<String, Object> map) throws SQLException{
		return selectOne("cslRcp.getCslRcp", map);
	}

	/**
	 * 상담이력 번호 생성
	 * @return
	 * @throws SQLException
	 */
	public String getCslRcpSeq() throws SQLException{
		return selectOne("cslRcp.getCslRcpSeq");
	}

	/**
	 * 상담이력 등록
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int insertCslRcp(HashMap<String, Object> map) throws SQLException{
		return insert("cslRcp.insertCslRcp", map);
	}

	/**
	 * 상담이력 목록 카운트
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int getCslRcpListCount(HashMap<String, Object> map) throws SQLException{
		return selectOne("cslRcp.getCslRcpListCount", map);
	}

	/**
	 * 상담이력 목록 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, Object>> getCslRcpList(HashMap<String, Object> map) throws SQLException{
		return selectList("cslRcp.getCslRcpList", map);
	}

	/**
	 * 상담 이력 삭제
	 * @param rcpNo
	 * @return
	 * @throws SQLException
	 */
	public int deleteCslRcp(String rcpNo) throws SQLException{
		return delete("cslRcp.deleteCslRcp", rcpNo);
	}

	/**
	 * 상담정보 수정
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int updateCslRcp(HashMap<String, Object> map) throws SQLException{
		return update("cslRcp.updateCslRcp", map);
	}
}