package kr.co.chase.nrds.recyclePrg.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.co.chase.ncms.common.service.FileInfoService;
import kr.co.chase.nrds.recyclePrg.dao.RecyclePrgDao;
import kr.co.chase.nrds.recyclePrg.service.RecyclePrgService;

@Service("RecyclePrgService")
public class RecyclePrgServiceImpl implements RecyclePrgService{
	@Resource(name = "RecyclePrgDao")
	private RecyclePrgDao recyclePrgDao;
	
	@Resource(name="fileInfoService")
	private FileInfoService fileInfoService;
	
	public List<EgovMap> selectEdPrmList(HashMap<String, Object> map) throws Exception{
		return recyclePrgDao.selectEdPrmList(map);
	}
	
	public EgovMap selectEdPrmInfo(HashMap<String, Object> map) throws Exception{
		EgovMap result = recyclePrgDao.selectEdPrmInfo(map);
		if(result != null) {
			String fileId = StringUtils.defaultIfEmpty((String)result.get("fileId"), "");
			if(!"".equals(fileId)) {
				HashMap<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("fileId", fileId);

				result.put("fileList", fileInfoService.getFileList(paramMap));
			}
		}
		
		return result;
	}
	
	public EgovMap selectEdPrmListCount(HashMap<String, Object> map) throws Exception{
		return recyclePrgDao.selectEdPrmListCount(map);
	}
	
	public int insertEdPrmInfo(HashMap<String, Object> map) throws Exception{
		if(map.get("fileList") != null) {
			List<HashMap<String, Object>> fileList = (List<HashMap<String, Object>>)map.get("fileList");

			for(HashMap<String, Object> info : fileList){
				fileInfoService.insertFileInfo(info);
 			}
		}
		return recyclePrgDao.insertEdPrmInfo(map);
	}
	
	public int updateEdPrmInfo(HashMap<String, Object> map) throws Exception{
		return recyclePrgDao.updateEdPrmInfo(map);
	}
	
	public void deleteEdPrmInfo(HashMap<String, Object> map) throws Exception{
		recyclePrgDao.deleteEdPrmInfo(map);
	}
	
	
}
