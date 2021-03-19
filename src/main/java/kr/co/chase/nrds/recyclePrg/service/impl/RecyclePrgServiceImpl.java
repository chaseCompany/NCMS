package kr.co.chase.nrds.recyclePrg.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.co.chase.nrds.recyclePrg.dao.RecyclePrgDao;
import kr.co.chase.nrds.recyclePrg.service.RecyclePrgService;

@Service("RecyclePrgService")
public class RecyclePrgServiceImpl implements RecyclePrgService{
	@Resource(name = "RecyclePrgDao")
	private RecyclePrgDao recyclePrgDao;
	
	public List<EgovMap> selectEdPrmList(HashMap<String, Object> map) throws Exception{
		return recyclePrgDao.selectEdPrmList(map);
	}
	
	public EgovMap selectEdPrmInfo(HashMap<String, Object> map) throws Exception{
		return recyclePrgDao.selectEdPrmInfo(map);
	}
	
	public EgovMap selectEdPrmListCount(HashMap<String, Object> map) throws Exception{
		return recyclePrgDao.selectEdPrmListCount(map);
	}
	
	public int insertEdPrmInfo(HashMap<String, Object> map) throws Exception{
		return recyclePrgDao.insertEdPrmInfo(map);
	}
	
	public int updateEdPrmInfo(HashMap<String, Object> map) throws Exception{
		return recyclePrgDao.updateEdPrmInfo(map);
	}
	
	public void deleteEdPrmInfo(HashMap<String, Object> map) throws Exception{
		recyclePrgDao.deleteEdPrmInfo(map);
	}
}
