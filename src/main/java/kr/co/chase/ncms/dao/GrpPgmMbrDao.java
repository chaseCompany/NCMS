package kr.co.chase.ncms.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("grpPgmMbrDao")
public class GrpPgmMbrDao extends EgovAbstractMapper {
	/**
	 * 주간 프로그램 참여자 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, Object>> getGrpPgmMbrList(HashMap<String, Object> map) throws SQLException{
		return selectList("grpPgmMbr.getGrpPgmMbrList", map);
	}

	/**
	 * 주간 프로그램 참여자 시퀀스 생성
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int getNextSeqNo(HashMap<String, Object> map) throws SQLException{
		return selectOne("grpPgmMbr.getNextSeqNo", map);
	}

	/**
	 * 주간 프로그램 참여자 등록
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int insertGrpPgmMbr(HashMap<String, Object> map) throws SQLException{
		return insert("grpPgmMbr.insertGrpPgmMbr", map);
	}

	/**
	 * 주간 프로그램 참여자 진행 기록 수정
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int updateGrpPgmMbrMbrCtnt(HashMap<String, Object> map) throws SQLException{
		return update("grpPgmMbr.updateGrpPgmMbrMbrCtnt", map);
	}

	/**
	 * 주간 프로그램 참여자 삭제
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int deleteGrpPgmMbr(HashMap<String, Object> map) throws SQLException{
		return delete("grpPgmMbr.deleteGrpPgmMbr", map);
	}
}