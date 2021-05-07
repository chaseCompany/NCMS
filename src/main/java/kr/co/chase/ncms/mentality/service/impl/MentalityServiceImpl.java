package kr.co.chase.ncms.mentality.service.impl;

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
import kr.co.chase.ncms.dao.CslCureDao;
import kr.co.chase.ncms.mentality.service.MentalityService;

@Service("mentalityService")
public class MentalityServiceImpl extends EgovAbstractServiceImpl implements MentalityService {
	private static final Logger LOGGER = LoggerFactory.getLogger(MentalityServiceImpl.class);

	@Resource(name="cslCureDao")
	private CslCureDao cslCureDao;

	@Resource(name="fileInfoService")
	private FileInfoService fileInfoService;

	@Resource(name = "FileManagerUtil")
	private FileManagerUtil fileUtil;
	
	@Resource(name="propertiesService")
	protected EgovPropertyService propertiesService;

	/**
	 * 회원별 심리치유 이력 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getCslCureList(HashMap<String, Object> map) throws Exception{
		return cslCureDao.getCslCureList(map);
	}

	/**
	 * 심리치유 내용을 조회 한다
	 * @param cslNo
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getCslCure(String cslNo) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cslNo", cslNo);
		map.put("paswKey", propertiesService.getString("aes256Key"));
		
		HashMap<String, Object> result = cslCureDao.getCslCure(map);

		if(!result.isEmpty()) {
			String fileId = StringUtils.defaultIfEmpty((String)result.get("FILE_ID"), "");
			if(!"".equals(fileId)) {
				HashMap<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("fileId", fileId);

				result.put("fileList", fileInfoService.getFileList(paramMap));
			}
		}

		return result;
	}
	public List<HashMap<String, Object>> getCslCureStatistics(HashMap<String, Object> map) throws Exception{
		map.put("paswKey", propertiesService.getString("aes256Key"));
		return cslCureDao.getCslCureStatistics(map);
	}

	/**
	 * 심리치유 이력을 등록 한다.
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertCslCure(HashMap<String, Object> map) throws Exception{
		return cslCureDao.insertCslCure(map);
	}

	/**
	 * 심리치유 이력 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateCslCure(HashMap<String, Object> map) throws Exception{
		return cslCureDao.updateCslCure(map);
	}

	/**
	 * 심리치유 번호 생성
	 * @return
	 * @throws Exception
	 */
	public String getCslCureSeq() throws Exception{
		return cslCureDao.getCslCureSeq();
	}

	/**
	 * 심리치유 내용 삭제
	 * @param clsNo
	 * @return
	 * @throws Exception
	 */
	public int deleteCslCure(String cslNo) throws Exception{
		HashMap<String, Object> result = this.getCslCure(cslNo);

		if(!result.isEmpty()) {
			String fileId = StringUtils.defaultIfEmpty((String)result.get("FILE_ID"), "");
			if(!"".equals(fileId)) {			// 첨부 파일 존재시 삭제
				HashMap<String, Object> fileMap = new HashMap<String, Object>();
				fileMap.put("fileId", fileId);

				fileUtil.deleteFile(fileId);
				fileInfoService.deleteFileInfo(fileMap);
			}
		}

		return cslCureDao.deleteCslCure(cslNo);
	}

	/**
	 * 심리치유 내용 저장 처리
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> cslCureAdd(HashMap<String, Object> map) throws Exception{
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String cslNo = StringUtils.defaultIfEmpty(map.get("cslNo").toString(), "");

		if(map.get("fileList") != null) {
			List<HashMap<String, Object>> fileList = (List<HashMap<String, Object>>)map.get("fileList");

			for(HashMap<String, Object> info : fileList){
				fileInfoService.insertFileInfo(info);
 			}
		}

		if("".equals(cslNo)){
			map.put("cslNo", this.getCslCureSeq());

			int result = this.insertCslCure(map);
			if(result > 0){
				resultMap.put("err", ConstantObject.N);
				resultMap.put("MSG", "심리치유 등록 완료");
			}else{
				resultMap.put("err", ConstantObject.Y);
				resultMap.put("MSG", "심리치유 등록 실패");
			}
		}else{
			HashMap<String, Object> cslIdvInfo = this.getCslCure(cslNo);

			if(!cslIdvInfo.isEmpty() && cslIdvInfo != null) {
				String oldFileId = StringUtils.defaultIfEmpty((String)cslIdvInfo.get("FILE_ID"), "");

				if(
						ConstantObject.Y.equals(StringUtils.defaultIfEmpty((String)map.get("fileNameFlag"), ""))
					 || (!"".equals(StringUtils.defaultIfEmpty((String)map.get("fileId"), "")) && !"".equals(oldFileId))
				  ) {
					cslIdvInfo.put("fileId", oldFileId);

					fileUtil.deleteFile(oldFileId);
					fileInfoService.deleteFileInfo(cslIdvInfo);
				}else if(!"".equals(oldFileId)) {
					map.put("fileId", oldFileId);
				}
			}

			int result = this.updateCslCure(map);
			if(result > 0){
				resultMap.put("err", ConstantObject.N);
				resultMap.put("MSG", "심리치유 수정 완료");
			}else{
				resultMap.put("err", ConstantObject.Y);
				resultMap.put("MSG", "심리치유 수정 실패");
			}
		}

		return resultMap;
	}
}