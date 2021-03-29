package kr.co.chase.ncms.adminUsr.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.co.chase.ncms.adminUsr.service.AdminUsrService;
import kr.co.chase.ncms.dao.CslRcpDao;
import kr.co.chase.ncms.dao.MstMbrDao;
import kr.co.chase.ncms.dao.SysUsrDao;

@Service("adminUsrService")
public class AdminUsrServiceImpl extends EgovAbstractServiceImpl implements AdminUsrService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminUsrServiceImpl.class);

	@Resource(name="sysUsrDao")
	private SysUsrDao sysUsrDao;

	/**
	 * 사용자 목록 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<HashMap<String, Object>> getAdminUsrList(HashMap<String, Object> map) throws Exception {
		return sysUsrDao.getAdminUsrList(map);
	}

	@Override
	public HashMap<String, Object> getAdminUsrView(String usrId) throws Exception {
		return sysUsrDao.getAdminUsrView(usrId);
	}

	@Override
	public HashMap<String, Object> updateSysUsrAdmin(HashMap<String, Object> map) throws Exception {
		int result = 0;
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		result = sysUsrDao.updateSysUsrAdmin(map);

		resultMap.put("result", result);

		return resultMap;
	}

	@Override
	public List<HashMap<String, Object>> getSysUsrSearchList(HashMap<String, Object> map) throws Exception {
		return sysUsrDao.getSysUsrSearchList(map);
	}

	@Override
	public HashMap<String, Object> updatePwdReset(HashMap<String, Object> map) throws Exception {
		int result = 0;
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		result = sysUsrDao.updatePwdReset(map);

		resultMap.put("result", result);

		return resultMap;
	}

	@Override
	public HashMap<String, Object> deleteSysUsr(String usrId) throws Exception {
		int result = 0;
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		result = sysUsrDao.deleteSysUsr(usrId);

		resultMap.put("result", result);

		return resultMap;
	}

	@Override
	public HashMap<String, Object> findMaxSiteUserId(String siteCd) throws Exception {
		return sysUsrDao.findMaxSiteUserId(siteCd);
	}

	@Override
	public HashMap<String, Object> insertSysUsr(HashMap<String, Object> map) throws Exception {
		int result = 0;
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		result = sysUsrDao.insertSysUsr(map);

		resultMap.put("result", result);

		return resultMap;
	}

}