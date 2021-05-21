package kr.co.chase.nrds.recyclePrg.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.co.chase.ncms.common.ConstantObject;
import kr.co.chase.ncms.common.service.FileInfoService;
import kr.co.chase.ncms.common.util.FileManagerUtil;
import kr.co.chase.nrds.recyclePrg.dao.RecyclePrgDao;
import kr.co.chase.nrds.recyclePrg.dao.RecyclePrgMbrDao;
import kr.co.chase.nrds.recyclePrg.service.RecyclePrgService;

@Service("RecyclePrgService")
public class RecyclePrgServiceImpl implements RecyclePrgService{

	@Resource(name = "RecyclePrgDao")
	private RecyclePrgDao recyclePrgDao;
	
	@Resource(name = "RecyclePrgMbrDao")
	private RecyclePrgMbrDao recyclePrgMbrDao;
	
	@Resource(name="fileInfoService")
	private FileInfoService fileInfoService;
	
	@Resource(name = "FileManagerUtil")
	private FileManagerUtil fileUtil;
	
	@Resource(name="propertiesService")
	protected EgovPropertyService propertiesService;

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
		int EdPrmKey = recyclePrgDao.selectEdPrmPK();
		map.put("pgmId", EdPrmKey);
		recyclePrgDao.insertEdPrmInfo(map);
		return EdPrmKey;
	}
	
	public int updateEdPrmInfo(HashMap<String, Object> map) throws Exception{
		return recyclePrgDao.updateEdPrmInfo(map);
	}
	
	public int deleteEdPrmInfo(HashMap<String, Object> map) throws Exception{
		int result = this.deleteEdPrmMbr(map);
		result = this.deleteEdPrm(map);

		return result;
	}

	@Override
	public int deleteEdPrmMbr(HashMap<String, Object> map) throws Exception {
		return recyclePrgDao.deleteEdPrmMbr(map);
	}

	@Override
	public int deleteEdPrm(HashMap<String, Object> map) throws Exception {
		return recyclePrgDao.deleteEdPrmInfo(map);
	}

	@Override
	public HashMap<String, Object> processEdPrm(HashMap<String, Object> map) throws Exception {
		int result = 0;
		int EdPrmKey =0;
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String deletePgmSeq = StringUtils.defaultIfEmpty((String)map.get("deletePgmSeq"), "");

		if(map.get("fileList") != null) {
			List<HashMap<String, Object>> fileList = (List<HashMap<String, Object>>)map.get("fileList");

			System.out.println("fileList길이: "+fileList.size());
			for(HashMap<String, Object> info : fileList){
				fileInfoService.insertFileInfo(info);
 			}
		}

		HashMap<String, Object> grpInfoMap = this.getEdPrm(map);
		if(grpInfoMap != null){
			String oldFileId = StringUtils.defaultIfEmpty((String)grpInfoMap.get("FILE_ID"), "");

			if(
					ConstantObject.Y.equals(StringUtils.defaultIfEmpty((String)map.get("fileNameFlag"), ""))
				 || (!"".equals(StringUtils.defaultIfEmpty((String)map.get("fileId"), "")) && !"".equals(oldFileId))
			  ) {
				grpInfoMap.put("fileId", oldFileId);

				fileUtil.deleteFile(oldFileId);
				fileInfoService.deleteFileInfo(grpInfoMap);
			}else if(!"".equals(oldFileId)) {
				map.put("fileId", oldFileId);
			}

			result = this.updateEdPrmInfo(map);
			resultMap.put("MSG", "수정");
			
			this.deleteAllEdPrmMbr(map);
		}else{
			EdPrmKey = this.insertEdPrmInfo(map);
			result=1;
			resultMap.put("MSG", "등록");
		}

		if(map.get("grpPgmMbrList") != null) {
			List<HashMap<String, Object>> mbrList = (List<HashMap<String, Object>>)map.get("grpPgmMbrList");
			for(HashMap<String, Object> mbrMap : mbrList) {
				if(Integer.parseInt(StringUtils.defaultIfEmpty((String) mbrMap.get("pgmId"), "0")) ==  0) {
					mbrMap.put("pgmId", EdPrmKey+"");
				}
				this.insertEdPgmMbr(mbrMap);
			}
		}

		if(!"".equals(deletePgmSeq)){
			HashMap<String, Object> delMap = new HashMap<String, Object>();
			String pgmDt = StringUtils.defaultIfEmpty((String)map.get("pgmDt"), "");
			String pgmCd = StringUtils.defaultIfEmpty((String)map.get("pgmCd"), "");

			delMap.put("pgmDt", pgmDt);
			delMap.put("pgmCd", pgmCd);

			String[] pgmSeqList = deletePgmSeq.split(",");
			for(String pgmSeq : pgmSeqList) {
				delMap.put("seqNo", pgmSeq);
				this.deleteEdPgmMbr(delMap);
			}
		}

		if(result > 0) {
			resultMap.put("err", ConstantObject.N);
		}else {
			resultMap.put("err", ConstantObject.Y);
		}

		return resultMap;
	}

	@Override
	public HashMap<String, Object> getEdPrm(HashMap<String, Object> map) throws Exception {
		map.put("paswKey", propertiesService.getString("aes256Key"));

		HashMap<String, Object> result = recyclePrgDao.selectEdPrmInfoMap(map);
		if(result != null) {
			String fileId = StringUtils.defaultIfEmpty((String)result.get("FILE_ID"), "");
			if(!"".equals(fileId)) {
				HashMap<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("fileId", fileId);

				result.put("fileList", fileInfoService.getFileList(paramMap));
			}
		}

		return result;
	}
	public List<HashMap<String, Object>> getEdPrmStatistics(HashMap<String, Object> map) throws Exception {
		map.put("paswKey", propertiesService.getString("aes256Key"));
		return recyclePrgDao.selectEdPrmInfoMapStatistics(map); 
	}

	/**
	 * 주간 프로그램 참여자 진행 기록 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateEdPgmMbrCtnt(HashMap<String, Object> map) throws Exception{
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("pgmId"), ""))) {
			throw new Exception("WeeklyPrgServiceImp.updateGrpPgmMbrMbrCtnt pgmId 필수값 누락");
		}
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("mbrNo"), ""))) {
			throw new Exception("WeeklyPrgServiceImp.updateGrpPgmMbrMbrCtnt mbrNo 필수값 누락");
		}

		return recyclePrgMbrDao.updateEdPgmMbrCtnt(map);
	}

	/**
	 * 주간 프로그램 참여자 삭제
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteEdPgmMbr(HashMap<String, Object> map) throws Exception{
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("pgmId"), ""))) {
			throw new Exception("WeeklyPrgServiceImp.updateGrpPgmMbrMbrCtnt pgmId 필수값 누락");
		}
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("mbrNo"), ""))) {
			throw new Exception("WeeklyPrgServiceImp.updateGrpPgmMbrMbrCtnt mbrNo 필수값 누락");
		}

		return recyclePrgMbrDao.deleteEdPgmMbr(map);
	}

	/**
	 * 주간 프로그램 참여자 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertEdPgmMbr(HashMap<String, Object> map) throws Exception{
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("pgmId"), ""))) {
			throw new Exception("WeeklyPrgServiceImp.updateGrpPgmMbrMbrCtnt pgmId 필수값 누락");
		}
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("mbrNo"), ""))) {
			throw new Exception("WeeklyPrgServiceImp.updateGrpPgmMbrMbrCtnt mbrNo 필수값 누락");
		}

		return recyclePrgMbrDao.insertEdPgmMbr(map);
	}

	@Override
	public List<HashMap<String, Object>> getEdPgmMbrList(HashMap<String, Object> map) throws Exception {
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("pgmId"), ""))) {
			throw new Exception("WeeklyPrgServiceImp.getGrpPgmMbrList pgmId 필수값 누락");
		}
		return recyclePrgMbrDao.getEdPgmMbrList(map);
	}

	@Override
	public int deleteAllEdPrmMbr(HashMap<String, Object> map) throws Exception {
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("pgmId"), ""))) {
			throw new Exception("WeeklyPrgServiceImp.updateGrpPgmMbrMbrCtnt pgmId 필수값 누락");
		}
		return recyclePrgMbrDao.deleteAllEdPgmMbr(map);
	}
}
