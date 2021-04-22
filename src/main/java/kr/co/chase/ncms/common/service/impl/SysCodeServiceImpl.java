package kr.co.chase.ncms.common.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.co.chase.ncms.common.service.SysCodeService;
import kr.co.chase.ncms.dao.SysCodeDao;

@Service("sysCodeService")
public class SysCodeServiceImpl extends EgovAbstractServiceImpl implements SysCodeService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SysCodeServiceImpl.class);

	@Resource(name="sysCodeDao")
	private SysCodeDao sysCodeDao;

	/**
	 * 코드 목록 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getSysCdList(HashMap<String, Object> map) throws Exception{
		return sysCodeDao.getSysCdList(map);
	}

	/**
	 * 코드 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertSysCd(HashMap<String, Object> map) throws Exception{
		return sysCodeDao.insertSysCd(map);
	}
	
	/**
	 * 시스템 코드를 조회한다.
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getSysCd(HashMap<String, Object> map) throws SQLException{
		return sysCodeDao.getSysCd(map);
	}
}
