package kr.co.chase.ncms.weeklyprg.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.property.EgovPropertyService;
import kr.co.chase.ncms.common.ConstantObject;
import kr.co.chase.ncms.common.service.FileInfoService;
import kr.co.chase.ncms.common.util.FileManagerUtil;
import kr.co.chase.ncms.dao.GrpPgmDao;
import kr.co.chase.ncms.dao.GrpPgmMbrDao;
import kr.co.chase.ncms.weeklyprg.service.WeeklyPrgService;

@Service("weeklyPrgService")
public class WeeklyPrgServiceImpl extends EgovAbstractServiceImpl implements WeeklyPrgService {
	private static final Logger LOGGER = LoggerFactory.getLogger(WeeklyPrgServiceImpl.class);

	@Resource(name="grpPgmDao")
	private GrpPgmDao grpPgmDao;

	@Resource(name="grpPgmMbrDao")
	private GrpPgmMbrDao grpPgmMbrDao;

	@Resource(name="fileInfoService")
	private FileInfoService fileInfoService;

	@Resource(name = "FileManagerUtil")
	private FileManagerUtil fileUtil;

	@Resource(name="propertiesService")
	protected EgovPropertyService propertiesService;

	/**
	 * 주간프로그램 목록 조회 카운트
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int getGrpPgmListCount(HashMap<String, Object> map) throws Exception{
		return grpPgmDao.getGrpPgmListCount(map);
	}

	/**
	 * 주간프로그램 목록 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getGrpPgmList(HashMap<String, Object> map) throws Exception{
		return grpPgmDao.getGrpPgmList(map);
	}

	/**
	 * 주간프로그램 상세 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getGrpPgm(HashMap<String, Object> map) throws Exception{
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("pgmDt"), ""))) {
			throw new Exception("WeeklyPrgServiceImp.getGrpPgm pgmDt 필수 값 누락");
		}
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("pgmCd"), ""))) {
			throw new Exception("WeeklyPrgServiceImp.getGrpPgm pgmCd 필수 값 누락");
		}

		HashMap<String, Object> result = grpPgmDao.getGrpPgm(map);
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
	public List<HashMap<String, Object>> getGrpPgmStatistics(HashMap<String, Object> map) throws Exception{
		map.put("paswKey", propertiesService.getString("aes256Key"));
		return grpPgmDao.getGrpPgmStatsistics(map);
	}

	/**
	 * 주간프로그램 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertGrpPgm(HashMap<String, Object> map) throws Exception{
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("pgmDt"), ""))) {
			throw new Exception("WeeklyPrgServiceImp.insertGrpPgm pgmDt 필수 값 누락");
		}
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("pgmCd"), ""))) {
			throw new Exception("WeeklyPrgServiceImp.insertGrpPgm pgmCd 필수 값 누락");
		}

		return grpPgmDao.insertGrpPgm(map);
	}

	/**
	 * 주간프로그램 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateGrpPgm(HashMap<String, Object> map) throws Exception{
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("pgmDt"), ""))) {
			throw new Exception("WeeklyPrgServiceImp.updateGrpPgm pgmDt 필수 값 누락");
		}
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("pgmDt"), ""))) {
			throw new Exception("WeeklyPrgServiceImp.updateGrpPgm pgmCd 필수 값 누락");
		}

		return grpPgmDao.updateGrpPgm(map);
	}

	/**
	 * 주간프로그램 등록/수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> addWeeklyPrg(HashMap<String, Object> map) throws Exception{
		int result = 0;
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String deletePgmSeq = StringUtils.defaultIfEmpty((String)map.get("deletePgmSeq"), "");

		if(map.get("fileList") != null) {
			List<HashMap<String, Object>> fileList = (List<HashMap<String, Object>>)map.get("fileList");

			for(HashMap<String, Object> info : fileList){
				fileInfoService.insertFileInfo(info);
 			}
		}

		HashMap<String, Object> grpInfoMap = this.getGrpPgm(map);
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

			result = this.updateGrpPgm(map);
			resultMap.put("MSG", "수정");
		}else{
			result = this.insertGrpPgm(map);
			resultMap.put("MSG", "등록");
		}

		if(map.get("grpPgmMbrList") != null) {
			List<HashMap<String, Object>> mbrList = (List<HashMap<String, Object>>)map.get("grpPgmMbrList");
			for(HashMap<String, Object> mbrMap : mbrList) {
				if(!"".equals(StringUtils.defaultIfEmpty((String)mbrMap.get("seqNo"), ""))){
					this.updateGrpPgmMbrMbrCtnt(mbrMap);
				}else{
					mbrMap.put("seqNo", this.getNextSeqNo(mbrMap) + "");
					this.insertGrpPgmMbr(mbrMap);
				}
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
				this.deleteGrpPgmMbr(delMap);
			}
		}

		if(result > 0) {
			resultMap.put("err", ConstantObject.N);
		}else {
			resultMap.put("err", ConstantObject.Y);
		}

		return resultMap;
	}

	/**
	 * 주간 프로그램 참여자 시퀀스 생성
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int getNextSeqNo(HashMap<String, Object> map) throws Exception{
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("pgmDt"), ""))) {
			throw new Exception("WeeklyPrgServiceImp.getNextSeqNo pgmDt 필수 값 누락");
		}
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("pgmCd"), ""))) {
			throw new Exception("WeeklyPrgServiceImp.getNextSeqNo gpmCd 필수 값 누락");
		}

		return grpPgmMbrDao.getNextSeqNo(map);
	}

	/**
	 * 주간 프로그램 참여자 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getGrpPgmMbrList(HashMap<String, Object> map) throws Exception{
		map.put("paswKey", propertiesService.getString("aes256Key"));
		
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("pgmDt"), ""))) {
			throw new Exception("WeeklyPrgServiceImp.getGrpPgmMbrList pgmDt 필수값 누락");
		}
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("pgmCd"), ""))) {
			throw new Exception("WeeklyPrgServiceImp.getGrpPgmMbrList pgmCd 필수값 누락");
		}

		return grpPgmMbrDao.getGrpPgmMbrList(map);
	}

	/**
	 * 주간 프로그램 참여자 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertGrpPgmMbr(HashMap<String, Object> map) throws Exception{
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("pgmDt"), ""))) {
			throw new Exception("WeeklyPrgServiceImp.insertGrpPgmMbr pgmDt 필수값 누락");
		}
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("pgmCd"), ""))) {
			throw new Exception("WeeklyPrgServiceImp.insertGrpPgmMbr pgmCd 필수값 누락");
		}
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("seqNo"), ""))) {
			throw new Exception("WeeklyPrgServiceImp.insertGrpPgmMbr seqNo 필수값 누락");
		}
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("mbrNo"), ""))) {
			throw new Exception("WeeklyPrgServiceImp.insertGrpPgmMbr mbrNo 필수값 누락");
		}

		return grpPgmMbrDao.insertGrpPgmMbr(map);
	}

	/**
	 * 주간 프로그램 참여자 진행 기록 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateGrpPgmMbrMbrCtnt(HashMap<String, Object> map) throws Exception{
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("pgmDt"), ""))) {
			throw new Exception("WeeklyPrgServiceImp.updateGrpPgmMbrMbrCtnt pgmDt 필수값 누락");
		}
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("pgmCd"), ""))) {
			throw new Exception("WeeklyPrgServiceImp.updateGrpPgmMbrMbrCtnt pgmCd 필수값 누락");
		}
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("seqNo"), ""))) {
			throw new Exception("WeeklyPrgServiceImp.updateGrpPgmMbrMbrCtnt seqNo 필수값 누락");
		}

		return grpPgmMbrDao.updateGrpPgmMbrMbrCtnt(map);
	}

	/**
	 * 주간 프로그램 참여자 삭제
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteGrpPgmMbr(HashMap<String, Object> map) throws Exception{
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("pgmDt"), ""))) {
			throw new Exception("WeeklyPrgServiceImp.deleteGrpPgmMbr pgmDt 필수값 누락");
		}
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("pgmCd"), ""))) {
			throw new Exception("WeeklyPrgServiceImp.deleteGrpPgmMbr pgmCd 필수값 누락");
		}

		return grpPgmMbrDao.deleteGrpPgmMbr(map);
	}

	/**
	 * 주간 프로그램 정보 삭제
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteGrpPgm(HashMap<String, Object> map) throws Exception{
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("pgmDt"), ""))) {
			throw new Exception("WeeklyPrgServiceImp.deleteGrpPgm pgmDt 필수값 누락");
		}
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("pgmCd"), ""))) {
			throw new Exception("WeeklyPrgServiceImp.deleteGrpPgm pgmCd 필수값 누락");
		}

		return grpPgmDao.deleteGrpPgm(map);
	}

	/**
	 * 주간 프로그램 삭제
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteWeeklyPrg(HashMap<String, Object> map) throws Exception{
		int result = this.deleteGrpPgmMbr(map);
		result = this.deleteGrpPgm(map);

		return result;
	}
}
