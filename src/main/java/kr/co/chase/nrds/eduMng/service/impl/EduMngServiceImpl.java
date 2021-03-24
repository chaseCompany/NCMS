package kr.co.chase.nrds.eduMng.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.chase.nrds.eduMng.dao.EduMngDao;
import kr.co.chase.nrds.eduMng.service.EduMngService;

@Service("EduMngService")
public class EduMngServiceImpl implements EduMngService{
	
	@Resource(name = "EduMngDao")
	private EduMngDao eduMngDao;
	
	/**
	 * 제활교육 프로그램 삭제
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteEduMngInfo(HashMap<String, Object> map) throws Exception{
		int result = eduMngDao.deleteEduMngInfo(map);

		return result;
	}
	
	/**
	 * 재활교육 프로그램 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateEduMngInfo(HashMap<String, Object> map) throws Exception{
		int result = eduMngDao.updateEduMngInfo(map);
		
		return result;
	}
}
