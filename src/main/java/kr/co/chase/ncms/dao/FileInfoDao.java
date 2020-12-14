package kr.co.chase.ncms.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("fileInfoDao")
public class FileInfoDao extends EgovAbstractMapper {
	/**
	 * 파일 ID 생성
	 * @return
	 * @throws SQLException
	 */
	public String getFileInfoId() throws SQLException {
		return selectOne("fileInfo.getFileInfoId");
	}

	/**
	 * 파일 정보 등록
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int insertFileInfo(HashMap<String, Object> map) throws SQLException{
		return insert("fileInfo.insertFileInfo", map);
	}

	/**
	 * 파일 정보 삭제
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int deleteFileInfo(HashMap<String, Object> map) throws SQLException{
		return delete("fileInfo.deleteFileInfo", map);
	}

	/**
	 * 파일 목록 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, Object>> getFileList(HashMap<String, Object> map) throws SQLException{
		return selectList("fileInfo.getFileInfo", map);
	}
}