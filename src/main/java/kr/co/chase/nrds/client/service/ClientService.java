package kr.co.chase.nrds.client.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface ClientService {
	/**
	 * 회원 기본 정보 상세 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getEdMbrInfo(HashMap<String, Object> map) throws Exception;

	/**
	 * 회원 고유번호 생성
	 * @return
	 * @throws Exception
	 */
	public String getMbrNoSeq() throws Exception;

	/**
	 * 회원 정보 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertEdMbr(HashMap<String, Object> map) throws Exception;

	/**
	 * 회원 정보 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateEdMbr(HashMap<String, Object> map) throws Exception;

	/**
	 * 회원 목록 카운트
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int getEdMbrListCount(HashMap<String, Object> map) throws Exception;

	/**
	 * 회원 목록 리스트
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getEdMbrList(HashMap<String, Object> map) throws Exception;

	/**
	 * 회원 정보 저장
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> saveEdMbr(HashMap<String, Object> map) throws Exception;

	/**
	 * 회원정보 삭제
	 * @param mbrNo
	 * @return
	 * @throws Exception
	 */
	public int deleteEdMbr(String mbrNo) throws Exception;
}