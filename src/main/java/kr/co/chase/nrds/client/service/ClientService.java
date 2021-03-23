package kr.co.chase.nrds.client.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

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

	/**
	 * 의뢰 교육조건부 기소유예 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertEdMbrEd(HashMap<String, Object> map) throws Exception;

	/**
	 * 의뢰 교육조건부 기소유예 대상자 기본 정보 조회
	 * @param mbrNo
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getEdMbrEdLastInfo(String mbrNo) throws Exception;

	/**
	 * 의뢰 교육조건부 기소유예 상세 정보 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getEdMbrEdInfo(HashMap<String, Object> map) throws Exception;

	/**
	 * 의뢰 교육조건부 기소유예 목록 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getEdMbrEdList(HashMap<String, Object> map) throws Exception;

	/**
	 * 의뢰 교육조건부 기소유예 내용 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateEdMbrEd(HashMap<String, Object> map) throws Exception;

	/**
	 * 의뢰 교육조건부 기소유예 삭제
	 * @param mbrEdId
	 * @return
	 * @throws Exception
	 */
	public int deleteEdMbrEd(HashMap<String, Object> map) throws Exception;

	/**
	 * 의뢰 교육조건부 기소유예 정보 등록
	 * @param files
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> saveEdMbrEd(Map<String, MultipartFile> files, HashMap<String, Object> map) throws Exception;
}