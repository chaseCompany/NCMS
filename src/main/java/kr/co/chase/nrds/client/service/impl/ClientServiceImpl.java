package kr.co.chase.nrds.client.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.co.chase.nrds.client.service.ClientService;
import kr.co.chase.nrds.dao.EdMbrDao;

@Service("clientService")
public class ClientServiceImpl extends EgovAbstractServiceImpl implements ClientService {
	@Resource(name="edMbrDao")
	private EdMbrDao edMbrDao;

	@Override
	public HashMap<String, Object> getEdMbrInfo(HashMap<String, Object> map) throws Exception {
		return edMbrDao.getEdMbrInfo(map);
	}

	@Override
	public String getMbrNoSeq() throws Exception {
		return edMbrDao.getMbrNoSeq();
	}

	@Override
	public int insertEdMbr(HashMap<String, Object> map) throws Exception {
		return edMbrDao.insertEdMbr(map);
	}

	@Override
	public int updateEdMbr(HashMap<String, Object> map) throws Exception {
		return edMbrDao.updateEdMbr(map);
	}

	@Override
	public int getEdMbrListCount(HashMap<String, Object> map) throws Exception {
		return edMbrDao.getEdMbrListCount(map);
	}

	@Override
	public List<HashMap<String, Object>> getEdMbrList(HashMap<String, Object> map) throws Exception {
		return edMbrDao.getEdMbrList(map);
	}

}