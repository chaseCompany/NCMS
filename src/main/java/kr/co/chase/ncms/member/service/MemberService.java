package kr.co.chase.ncms.member.service;

import java.util.HashMap;
import java.util.List;

public interface MemberService {
	/**
	 * 회원 정보 조회
	 * @param mbrNo
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getMstMbr(String mbrNo) throws Exception;

	/**
	 * 회원 이력 조회
	 * @param mbrNo
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getMstRegHisList(String mbrNo) throws Exception;

	/**
	 * 회원 번호 생성
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
	public int insertMstMbr(HashMap<String, Object> map) throws Exception;

	/**
	 * 회원 이력 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertMstRegHis(HashMap<String, Object> map) throws Exception;

	/**
	 * 회원 정보 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateMstMbr(HashMap<String, Object> map) throws Exception;

	/**
	 * 회원 정보 저장
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> saveMstMbr(HashMap<String, Object> map) throws Exception;

	/**
	 * 회원 이력 삭제
	 * @param mbrNo
	 * @return
	 * @throws Exception
	 */
	public int deleteMstRegHis(String mbrNo) throws Exception;

	/**
	 * 회원 정보 삭제
	 * @param mbrNo
	 * @return
	 * @throws Exception
	 */
	public int deleteMstMbr(String mbrNo) throws Exception;

	/**
	 * 회원 정보/이력 삭제 처리
	 * @param mbrNo
	 * @return
	 * @throws Exception
	 */
	public int mstMbrDel(String mbrNo) throws Exception;

	/**
	 * 회원 퇴록 정보 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateMstMbrStsCd(HashMap<String, Object> map) throws Exception;

	/**
	 * 회원 퇴록/재등록 처리
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int StsCdUpdate(HashMap<String, Object> map) throws Exception;

	/**
	 * 미등록 회원 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String saveMstMbrEt(HashMap<String, Object> map) throws Exception;

	/**
	 * 가족관계 정보 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateMbrFmly(HashMap<String, Object> map) throws Exception;

	/**
	 * 의뢰 고유키 생성
	 * @return
	 * @throws Exception
	 */
	public String getMstTransSeq() throws Exception;

	/**
	 * 의뢰정보 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertMstTrans(HashMap<String, Object> map) throws Exception;

	/**
	 * 의뢰정보 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateMstTrans(HashMap<String, Object> map) throws Exception;

	/**
	 * 연계정보 저장
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateMstTransReceipt(HashMap<String, Object> map) throws Exception;

	/**
	 * 의뢰정보 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getMstTrans(HashMap<String, Object> map) throws Exception;

	/**
	 * 의뢰정보 저장
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> saveTrans(HashMap<String, Object> map) throws Exception;

	/**
	 * 의뢰정보 목록 카운트
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int getMstTransListCount(HashMap<String, Object> map) throws Exception;

	/**
	 * 의뢰정보 목록 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getMstTransList(HashMap<String, Object> map) throws Exception;

	/**
	 * 연계처리 내용 저장
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> saveTransState(HashMap<String, Object> map) throws Exception;

	/**
	 * 사례관리자 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateMbrMngUsrId(HashMap<String, Object> map) throws Exception;
}