package kr.co.chase.ncms.counsel.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.co.chase.ncms.counsel.service.CounselService;
import kr.co.chase.ncms.dao.CslRcpDao;
import kr.co.chase.ncms.dao.MstMbrDao;
import kr.co.chase.ncms.member.service.MemberService;

@Service("counselService")
public class CounselServiceImpl extends EgovAbstractServiceImpl implements CounselService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CounselServiceImpl.class);

	@Resource(name="memberService")
	private MemberService memberService;

	@Resource(name="cslRcpDao")
	private CslRcpDao cslRcpDao;

	@Resource(name="mstMbrDao")
	private MstMbrDao mstMbrDao;

	/**
	 * 상담이력 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, Object> getCslRcp(String rcpNo) throws Exception{
		return cslRcpDao.getCslRcp(rcpNo);
	}

	/**
	 * 상담이력 등록
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int insertCslRcp(HashMap<String, Object> map) throws Exception{
		return cslRcpDao.insertCslRcp(map);
	}

	/**
	 * 상담이력 번호 생성
	 * @return
	 * @throws Exception
	 */
	public String getCslRcpSeq() throws Exception{
		return cslRcpDao.getCslRcpSeq();
	}

	/**
	 * 상담이력 등록 처리
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String counselAdd(HashMap<String, Object> map) throws Exception{
		int result = 0;
		String rcpNo = StringUtils.defaultString(map.get("rcpNo") != null ? map.get("rcpNo").toString() : "", "");

		if(rcpNo.equals("")) {
/*
			// 회원 번호 미존재시 회원 등록
			String tgpMbrNo = StringUtils.defaultIfEmpty((String)map.get("tgpMbrNo"), "");
			if("".equals(tgpMbrNo)){
				map.put("mbrNm", StringUtils.defaultIfEmpty((String)map.get("tgpNm"), ""));
				map.put("gendCd", StringUtils.defaultIfEmpty((String)map.get("tgpGendCd"), ""));
				map.put("age", StringUtils.defaultIfEmpty((String)map.get("tgpAge"), ""));
				map.put("telNo1", StringUtils.defaultIfEmpty((String)map.get("tgpTelNo1"), ""));
				map.put("telNo2", StringUtils.defaultIfEmpty((String)map.get("tgpTelNo2"), ""));
				map.put("telNo3", StringUtils.defaultIfEmpty((String)map.get("tgpTelNo3"), ""));
				map.put("jobCd", StringUtils.defaultIfEmpty((String)map.get("tgpJobCd"), ""));
				map.put("frgCd", StringUtils.defaultIfEmpty((String)map.get("tgpFrgCd"), ""));
				map.put("addr1", StringUtils.defaultIfEmpty((String)map.get("tgpAreaCd"), ""));
				map.put("addr2", StringUtils.defaultIfEmpty((String)map.get("tgpAreaEtc"), ""));
				map.put("creId", StringUtils.defaultString((String)map.get("cslId"), ""));

				map.put("tgpMbrNo", memberService.saveMstMbrEt(map));
			}
*/
			map.put("rcpNo", this.getCslRcpSeq());
			result = this.insertCslRcp(map);
		}else{
			result = this.updateCslRcp(map);
		}

		if(result > 0) {
			return (String)map.get("rcpNo");
		}else {
			return "";
		}
	}

	/**
	 * 상담이력 목록 카운트
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int getCslRcpListCount(HashMap<String, Object> map) throws Exception{
		return cslRcpDao.getCslRcpListCount(map);
	}

	/**
	 * 상담이력 목록 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, Object>> getCslRcpList(HashMap<String, Object> map) throws Exception{
		if(map.get("currentPageNo")== null || StringUtils.defaultString(map.get("currentPageNo").toString(), "") == "") {
			throw processException("페이지 수 누락");
		}
		if(map.get("recordCountPerPage") == null || StringUtils.defaultString(map.get("recordCountPerPage").toString(), "") == "") {
			throw processException("목록 수 누락");
		}

		return cslRcpDao.getCslRcpList(map);
	}

	/**
	 * 상담 이력 삭제
	 * @param rcpNo
	 * @return
	 * @throws Exception
	 */
	public int deleteCslRcp(String rcpNo) throws Exception{
		if(StringUtils.defaultString(rcpNo, "") == "") {
			throw processException("상담번호 누락");
		}

		return cslRcpDao.deleteCslRcp(rcpNo);
	}

	/**
	 * 회원 목록 조회 카운트
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int getMstMbrListCount(HashMap<String, Object> map) throws Exception{
		return mstMbrDao.getMstMbrListCount(map);
	}

	/**
	 * 회원 목록 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getMstMbrList(HashMap<String, Object> map) throws Exception{
		if(map.get("currentPageNo") == null || StringUtils.defaultString(map.get("currentPageNo").toString(), "") == "") {
			throw processException("페이지 수 누락");
		}
		if(map.get("recordCountPerPage") == null || StringUtils.defaultString(map.get("recordCountPerPage").toString(), "") == "") {
			throw processException("목록 수 누락");
		}

		return mstMbrDao.getMstMbrList(map);
	}

	/**
	 * 상담정보 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateCslRcp(HashMap<String, Object> map) throws Exception{
		return cslRcpDao.updateCslRcp(map);
	}
}