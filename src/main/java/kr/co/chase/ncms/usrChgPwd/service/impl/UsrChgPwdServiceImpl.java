package kr.co.chase.ncms.usrChgPwd.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.co.chase.ncms.dao.SysUsrDao;
import kr.co.chase.ncms.usrChgPwd.service.UsrChgPwdService;

@Service("usrChgPwdService")
public class UsrChgPwdServiceImpl extends EgovAbstractServiceImpl implements UsrChgPwdService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UsrChgPwdServiceImpl.class);

	@Resource(name="sysUsrDao")
	private SysUsrDao sysUsrDao;

	@Override
	public HashMap<String, Object> updateNewUsrPwd(HashMap<String, Object> map) throws Exception {
		int result = 0;
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		result = sysUsrDao.updatePwdReset(map);

		resultMap.put("result", result);

		return resultMap;
	}

}