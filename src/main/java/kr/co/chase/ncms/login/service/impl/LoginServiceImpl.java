package kr.co.chase.ncms.login.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.co.chase.ncms.dao.SysUsrDao;
import kr.co.chase.ncms.login.service.LoginService;

@Service("loginService")
public class LoginServiceImpl extends EgovAbstractServiceImpl implements LoginService {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Resource(name="sysUsrDao")
	private SysUsrDao sysUsrDao;

	/**
	 * 회원 목록 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getSysUsrList(HashMap<String, Object> map) throws Exception{
		return sysUsrDao.getSysUsrList(map);
	}

	/**
	 * 회원 정보 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getSysUsrInfo(HashMap<String, Object> map) throws Exception{
		if(StringUtils.defaultIfEmpty((String)map.get("usrId"), "").equals("")){
			return null;
		}
		if(StringUtils.defaultIfEmpty((String)map.get("passwd"), "").equals("")) {
			return null;
		}

		return sysUsrDao.getSysUsrInfo(map);
	}
}
