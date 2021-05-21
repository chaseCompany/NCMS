package kr.co.chase.nrds.eduCounsel.service;

import java.util.HashMap;
import java.util.List;

public interface EduCounselService {

	/**
	 * 회원별 교육상담 이력 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getCslCureList(HashMap<String, Object> map) throws Exception;

	/**
	 * 교육상담 내용을 조회 한다
	 * @param cslNo
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getCslCure(String cslNo) throws Exception;
	public List<HashMap<String, Object>> getCslCureStatistics(HashMap<String, Object> map) throws Exception;

	/**
	 * 교육상담 이력을 등록 한다.
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertCslCure(HashMap<String, Object> map) throws Exception;

	/**
	 * 교육상담 이력 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateCslCure(HashMap<String, Object> map) throws Exception;

	/**
	 * 교육상담 번호 생성
	 * @return
	 * @throws Exception
	 */
	public String getCslCureSeq() throws Exception;

	/**
	 * 교육상담 내용 삭제
	 * @param clsNo
	 * @return
	 * @throws Exception
	 */
	public int deleteCslCure(String cslNo) throws Exception;

	/**
	 * 교육상담 내용 저장 처리
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> cslCureAdd(HashMap<String, Object> map) throws Exception;
}
