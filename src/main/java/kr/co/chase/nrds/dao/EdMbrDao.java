package kr.co.chase.nrds.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("edMbrDao")
public class EdMbrDao extends EgovAbstractMapper {
	/**
	 * 회원 기본 정보 상세 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, Object> getEdMbrInfo(HashMap<String, Object> map) throws SQLException{
		return selectOne("edMbr.getEdMbrInfo", map);
	}

	/**
	 * 회원 고유번호 생성
	 * @return
	 * @throws SQLException
	 */
	public String getMbrNoSeq() throws SQLException{
		return selectOne("edMbr.getMbrNoSeq");
	}

	/**
	 * 회원 정보 등록
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int insertEdMbr(HashMap<String, Object> map) throws SQLException{
		return insert("edMbr.insertEdMbr", map);
	}

	/**
	 * 회원 정보 수정
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int updateEdMbr(HashMap<String, Object> map) throws SQLException{
		return update("edMbr.updateEdMbr", map);
	}

	/**
	 * 회원 목록 카운트
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int getEdMbrListCount(HashMap<String, Object> map) throws SQLException{
		return selectOne("edMbr.getEdMbrListCount", map);
	}

	/**
	 * 회원 목록 리스트
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, Object>> getEdMbrList(HashMap<String, Object> map) throws SQLException{
		return selectList("edMbr.getEdMbrList", map);
	}

	/**
	 * 회원정보 삭제
	 * @param mbrNo
	 * @return
	 * @throws SQLException
	 */
	public int deleteEdMbr(String mbrNo) throws SQLException{
		return delete("edMbr.deleteEdMbr", mbrNo);
	}
}