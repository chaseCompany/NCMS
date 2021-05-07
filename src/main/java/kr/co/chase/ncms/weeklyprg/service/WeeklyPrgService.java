package kr.co.chase.ncms.weeklyprg.service;

import java.util.HashMap;
import java.util.List;

public interface WeeklyPrgService {
	/**
	 * 주간프로그램 목록 조회 카운트
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int getGrpPgmListCount(HashMap<String, Object> map) throws Exception;

	/**
	 * 주간프로그램 목록 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getGrpPgmList(HashMap<String, Object> map) throws Exception;

	/**
	 * 주간 프로그램 상세 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getGrpPgm(HashMap<String, Object> map) throws Exception;
	public List<HashMap<String, Object>> getGrpPgmStatistics(HashMap<String, Object> map) throws Exception;

	/**
	 * 주간프로그램 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertGrpPgm(HashMap<String, Object> map) throws Exception;

	/**
	 * 주간프로그램 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateGrpPgm(HashMap<String, Object> map) throws Exception;

	/**
	 * 주간프로그램 등록/수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> addWeeklyPrg(HashMap<String, Object> map) throws Exception;

	/**
	 * 주간 프로그램 참여자 시퀀스 생성
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int getNextSeqNo(HashMap<String, Object> map) throws Exception;

	/**
	 * 주간 프로그램 참여자 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getGrpPgmMbrList(HashMap<String, Object> map) throws Exception;

	/**
	 * 주간 프로그램 참여자 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertGrpPgmMbr(HashMap<String, Object> map) throws Exception;

	/**
	 * 주간 프로그램 참여자 진행 기록 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateGrpPgmMbrMbrCtnt(HashMap<String, Object> map) throws Exception;

	/**
	 * 주간 프로그램 참여자 삭제
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteGrpPgmMbr(HashMap<String, Object> map) throws Exception;

	/**
	 * 주간 프로그램 정보 삭제
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteGrpPgm(HashMap<String, Object> map) throws Exception;

	/**
	 * 주간 프로그램 삭제
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteWeeklyPrg(HashMap<String, Object> map) throws Exception;
}