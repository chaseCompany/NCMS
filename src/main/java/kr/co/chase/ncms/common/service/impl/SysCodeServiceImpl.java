package kr.co.chase.ncms.common.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.co.chase.ncms.common.dao.SysCodeDao;
import kr.co.chase.ncms.common.service.SysCodeService;

@Service("sysCodeService")
public class SysCodeServiceImpl extends EgovAbstractServiceImpl implements SysCodeService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SysCodeServiceImpl.class);

	@Resource(name="sysCodeDao")
	private SysCodeDao sysCodeDao;

	public List<HashMap<String, Object>> getSysCdList(HashMap<String, Object> map) throws Exception{
		return sysCodeDao.getSysCdList(map);
	}

	public int insertSysCd(HashMap<String, Object> map) throws Exception{
		return sysCodeDao.insertSysCd(map);
	}
}
