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

	/**
	 * 회원 정보 조회
	 * @param mbrNo
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, Object> getMstMbr(String mbrNo) throws SQLException{
		return selectOne("mstMbr.getMstMbr", mbrNo);
	}

	/**
	 * 회원 번호 생성
	 * @return
	 * @throws SQLException
	 */
	public String getMbrNoSeq() throws SQLException{
		return selectOne("mstMbr.getMbrNoSeq");
	}

	/**
	 * 회원 정보 등록
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int insertMstMbr(HashMap<String, Object> map) throws SQLException{
		return insert("mstMbr.insertMstMbr", map);
	}

	/**
	 * 회원 정보 수정
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int updateMstMbr(HashMap<String, Object> map) throws SQLException{
		return update("mstMbr.updateMstMbr", map);
	}

	/**
	 * 회원 정보 삭제
	 * @param mbrNo
	 * @return
	 * @throws SQLException
	 */
	public int deleteMstMbr(String mbrNo) throws SQLException{
		return delete("mstMbr.deleteMstMbr", mbrNo);
	}

	/**
	 * 회원 퇴록 정보 수정
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int updateMstMbrStsCd(HashMap<String, Object> map) throws SQLException{
		return update("mstMbr.updateMstMbrStsCd", map);
	}

	/**
	 * 가족관계 정보 수정
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int updateMbrFmly(HashMap<String, Object> map) throws SQLException{
		return update("mstMbr.updateMbrFmly", map);
	}

	/**
	 * 사례관리자 수정
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int updateMbrMngUsrId(HashMap<String, Object> map) throws SQLException{
		return update("mstMbr.updateMbrMngUsrId", map);
	}
}