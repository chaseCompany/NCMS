package kr.co.chase.ncms.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("mstRegHisDao")
public class MstRegHisDao extends EgovAbstractMapper {
	/**
	 * 회원 이력 등록
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int insertMstRegHis(HashMap<String, Object> map) throws SQLException{
		return insert("mstRegHis.insertMstRegHis", map);
	}

	/**
	 * 회원 이력 조회
	 * @param mbrNo
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, Object>> getMstRegHisList(String mbrNo) throws SQLException{
		return selectList("mstRegHis.getMstRegHisList", mbrNo);
	}

	/**
	 * 회원 이력 삭제
	 * @param mbrNo
	 * @return
	 * @throws SQLException
	 */
	public int deleteMstRegHis(String mbrNo) throws SQLException{
		return delete("mstRegHis.deleteMstRegHis", mbrNo);
	}
}