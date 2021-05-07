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
	public List<HashMap<String, Object>> getCslIdvStatistics(HashMap<String, Object> map) throws Exception;

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
	public int deleteCslIdv(String cslNo) throws Exception;

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
	public List<HashMap<String, Object>> getCslIspInfoStatistics(HashMap<String, Object> map) throws Exception;

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
	 * 병력정보 목록 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getCslAnmList(HashMap<String, Object> map) throws Exception;

	/**
	 * 병력정보 상세 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getCslAnmInfo(HashMap<String, Object> map) throws Exception;

	/**
	 * 병력정보 고유키 생성
	 * @param mbrNo
	 * @return
	 * @throws Exception
	 */
	public String getCslAnmSeq(String mbrNo) throws Exception;

	/**
	 *병력정보 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertCslAnm(HashMap<String, Object> map) throws Exception;

	/**
	 * 병력정보 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateCslAnm(HashMap<String, Object> map) throws Exception;

	/**
	 * 병력정보 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> addCslAns(HashMap<String, Object> map) throws Exception;

	/**
	 * 병력정보 삭제
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteCslAnm(HashMap<String, Object> map) throws Exception;

	/**
	 * 치료재활정보 목록 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getCslHealList(HashMap<String, Object> map) throws Exception;

	/**
	 * 치료재활정보 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertCslHeal(HashMap<String, Object> map) throws Exception;

	/**
	 * 치료재활정보 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateCslHeal(HashMap<String, Object> map) throws Exception;

	/**
	 * 치료재활정보 고유키 생성
	 * @return
	 * @throws Exception
	 */
	public String getCslHealSeq() throws Exception;

	/**
	 * 치료재활정보 저장
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> addCslHeal(HashMap<String, Object> map) throws Exception;

	/**
	 * 치료재활정보 상세 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getCslHealInfo(HashMap<String, Object> map) throws Exception;

	/**
	 * 치료재활정보 삭제
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteCslHeal(HashMap<String, Object> map) throws Exception;
}