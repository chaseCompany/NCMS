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

@Service("counselService")
public class CounselServiceImpl extends EgovAbstractServiceImpl implements CounselService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CounselServiceImpl.class);

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
	public HashMap<String, Object> getCslRcp(HashMap<String, Object> map) throws Exception{
		return cslRcpDao.getCslRcp(map);
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
	 * 상담이력 등록 처리
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> counselAdd(HashMap<String, Object> map) throws Exception{
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		map.put("rcpNo", "R00000000000000");
		int result = this.insertCslRcp(map);
		if(result <= 0) {
			
		}else {
			
		}

		return resultMap;
	}

	/**
	 * 상담이력 목록 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, Object>> getCslRcpList(HashMap<String, Object> map) throws Exception{
		if(StringUtils.defaultString((String)map.get("currentPageNo"), "") == "") {
			throw processException("페이지 수 누락");
		}
		if(StringUtils.defaultString((String)map.get("recordCountPerPage"), "") == "") {
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
	 * 회원 목록 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getMstMbrList(HashMap<String, Object> map) throws Exception{
		if(StringUtils.defaultString((String)map.get("currentPageNo"), "") == "") {
			throw processException("페이지 수 누락");
		}
		if(StringUtils.defaultString((String)map.get("recordCountPerPage"), "") == "") {
			throw processException("목록 수 누락");
		}

		return mstMbrDao.getMstMbrList(map);
	}
}