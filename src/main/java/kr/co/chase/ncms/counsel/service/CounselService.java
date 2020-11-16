package kr.co.chase.ncms.counsel.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface CounselService {
	/**
	 * 상담이력 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, Object> getCslRcp(String rcpNo) throws Exception;

	/**
	 * 상담이력 번호 생성
	 * @return
	 * @throws Exception
	 */
	public String getCslRcpSeq() throws Exception;

	/**
	 * 상담이력 등록
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int insertCslRcp(HashMap<String, Object> map) throws Exception;

	/**
	 * 상담이력 등록 처리
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String counselAdd(HashMap<String, Object> map) throws Exception;

	/**
	 * 상담이력 목록 카운트
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int getCslRcpListCount(HashMap<String, Object> map) throws Exception;

	/**
	 * 상담이력 목록 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, Object>> getCslRcpList(HashMap<String, Object> map) throws Exception;

	/**
	 * 상담 이력 삭제
	 * @param rcpNo
	 * @return
	 * @throws Exception
	 */
	public int deleteCslRcp(String rcpNo) throws Exception;

	/**
	 * 회원 목록 조회 카운트
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int getMstMbrListCount(HashMap<String, Object> map) throws Exception;

	/**
	 * 회원 목록 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getMstMbrList(HashMap<String, Object> map) throws Exception;

	/**
	 * 상담정보 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateCslRcp(HashMap<String, Object> map) throws Exception;
}