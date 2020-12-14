package kr.co.chase.ncms.common.service;

import java.util.HashMap;
import java.util.List;

public interface FileInfoService {
	/**
	 * 파일 ID 생성
	 * @return
	 * @throws Exception
	 */
	public String getFileInfoId() throws Exception;

	/**
	 * 파일 정보 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertFileInfo(HashMap<String, Object> map) throws Exception;

	/**
	 * 파일 정보 삭제
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteFileInfo(HashMap<String, Object> map) throws Exception;

	/**
	 * 파일 목록 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getFileList(HashMap<String, Object> map) throws Exception;

	/**
	 * 파일 상세 정보
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getFileInfo(HashMap<String, Object> map) throws Exception;
}