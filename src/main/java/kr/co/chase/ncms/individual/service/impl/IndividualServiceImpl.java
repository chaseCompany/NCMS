package kr.co.chase.ncms.individual.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.co.chase.ncms.dao.CslIdvDAO;
import kr.co.chase.ncms.dao.CslIspDAO;
import kr.co.chase.ncms.individual.service.IndividualService;

@Service("individualService")
public class IndividualServiceImpl extends EgovAbstractServiceImpl implements IndividualService{
	private static final Logger LOGGER = LoggerFactory.getLogger(IndividualServiceImpl.class);

	@Resource(name="cslIdvDao")
	private CslIdvDAO cslIdvDao;

	@Resource(name="cslIspDao")
	private CslIspDAO cslIspDao;

	/**
	 * 회원별 집중상담 이력 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getCslIdvList(HashMap<String, Object> map) throws Exception{
		return cslIdvDao.getCslIdvList(map);
	}

	/**
	 * 집중 상담 내용을 조회 한다
	 * @param cslNo
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getCslIdv(String cslNo) throws Exception{
		return cslIdvDao.getCslIdv(cslNo);
	}

	/**
	 * ISP 수립 목록 조회
	 * @param mbrNo
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getCslIspList(String mbrNo) throws Exception{
		return cslIspDao.getCslIspList(mbrNo);
	}

	/**
	 * ISP 수립 상세 내용 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getCslIspInfo(HashMap<String, Object> map) throws Exception{
		return cslIspDao.getCslIspInfo(map);
	}

	/**
	 * ISP 수립 삭제
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteCslIsp(HashMap<String, Object> map) throws Exception{
		return cslIspDao.deleteCslIsp(map);
	}
}
