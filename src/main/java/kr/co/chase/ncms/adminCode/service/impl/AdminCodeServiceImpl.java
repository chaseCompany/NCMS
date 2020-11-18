package kr.co.chase.ncms.adminCode.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.co.chase.ncms.adminCode.service.AdminCodeService;
import kr.co.chase.ncms.dao.SysCodeDao;

@Service("adminCodeService")
public class AdminCodeServiceImpl extends EgovAbstractServiceImpl implements AdminCodeService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminCodeServiceImpl.class);

	@Resource(name="sysCodeDao")
	private SysCodeDao sysCodeDao;

	/**
	 * 전체 코드 목록 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, Object>> getSysCdAdminGroupTotalList(HashMap<String, Object> map) throws Exception {
		return sysCodeDao.getSysCdAdminGroupTotalList(map);
	}

	/**
	 * 개별 코드 목록 조회
	 * @param grpCd
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, Object>> getSysCdAdminGroupCdList(String grpCd) throws Exception {
		return sysCodeDao.getSysCdAdminGroupCdList(grpCd);
	}

	/**
	 * 코드정보 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> updateSysCdAdmin(HashMap<String, Object> map) throws Exception {
		int result = 0;
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		result = sysCodeDao.updateSysCdAdmin(map);

		if(result <= 0) {
			
		}else {
			
		}

		return resultMap;
	}

	/**
	 * 코드그룹 검색
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, Object>> getSysGroupCdSearchList(HashMap<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return sysCodeDao.getSysGroupCdSearchList(map);
	}
}