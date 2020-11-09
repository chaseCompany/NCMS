package kr.co.chase.ncms.individual.service;

import java.util.HashMap;
import java.util.List;

public interface IndividualService {
	/**
	 * 회원별 집중상담 이력 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getCslIdvList(HashMap<String, Object> map) throws Exception;

	/**
	 * 집중 상담 내용을 조회 한다
	 * @param cslNo
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getCslIdv(String cslNo) throws Exception;

	/**
	 * 집중 상담이력을 등록 한다.
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertCslIdv(HashMap<String, Object> map) throws Exception;

	/**
	 * 집중 상담 이력 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateCslIdv(HashMap<String, Object> map) throws Exception;

	/**
	 * 집중 상담 번호 생성
	 * @return
	 * @throws Exception
	 */
	public String getCslIdvSeq() throws Exception;

	/**
	 * 집중 상담 내용 저장 처리
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> cslIdvAdd(HashMap<String, Object> map) throws Exception;

	/**
	 * 집중 상담 내용 삭제
	 * @param clsNo
	 * @return
	 * @throws Exception
	 */
	public int deleteCslIdv(String clsNo) throws Exception;

	/**
	 * ISP 수립 목록 조회
	 * @param mbrNo
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getCslIspList(String mbrNo) throws Exception;

	/**
	 * ISP 수립 상세 내용 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getCslIspInfo(HashMap<String, Object> map) throws Exception;

	/**
	 * ISP 수립 삭제
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteCslIsp(HashMap<String, Object> map) throws Exception;

	/**
	 * ISP 수립 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertCslIsp(HashMap<String, Object> map) throws Exception;

	/**
	 * ISP 수립 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateCslIsp(HashMap<String, Object> map) throws Exception;

	/**
	 * ISP 수립 등록/수정 처리
	 * @param reqMap
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> cslIspAdd(HashMap<String, Object> map) throws Exception;

	/**
	 * 사정평가 내용 조회
	 * @param mbrNo
	 * @return
	 * @throws Excepton
	 */
	public HashMap<String, Object> getCslAssInfo(String mbrNo) throws Exception;

	/**
	 * 사정평가 내용 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertCslAss(HashMap<String, Object> map) throws Exception;

	/**
	 * 사정평가 내용 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateCslAss(HashMap<String, Object> map) throws Exception;

	/**
	 * 사정 평가 평가도구 상세 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getCslAssEvlInfo(HashMap<String, Object> map) throws Exception;

	/**
	 * 사정 평가 평가도구 목록 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getCslAssEvlList(String mbrNo) throws Exception;

	/**
	 * 사정 평가 평가도구 시퀀스 조회
	 * @param mbrNo
	 * @return
	 * @throws Exception
	 */
	public String getEvlSqeNext(String mbrNo) throws Exception;

	/**
	 * 사정 평가 평가도구 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertCslAssEvl(HashMap<String, Object> map) throws Exception;

	/**
	 * 사정 평가 평가도구 삭제
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteCslAssEvl(HashMap<String, Object> map) throws Exception;

	/**
	 * 사정 평가 내용 조회
	 * @param mbrNo
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getCslAssInfoView(String mbrNo) throws Exception;
}