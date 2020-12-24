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
}