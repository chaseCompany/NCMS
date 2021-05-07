package kr.co.chase.ncms.individual.service.impl;

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
import kr.co.chase.ncms.common.util.DateUtil;
import kr.co.chase.ncms.common.util.FileManagerUtil;
import kr.co.chase.ncms.dao.CslAnmDao;
import kr.co.chase.ncms.dao.CslHealDao;
import kr.co.chase.ncms.dao.CslIdvDao;
import kr.co.chase.ncms.dao.CslIspDao;
import kr.co.chase.ncms.individual.service.IndividualService;

@Service("individualService")
public class IndividualServiceImpl extends EgovAbstractServiceImpl implements IndividualService{
	private static final Logger LOGGER = LoggerFactory.getLogger(IndividualServiceImpl.class);

	@Resource(name="cslIdvDao")
	private CslIdvDao cslIdvDao;

	@Resource(name="cslIspDao")
	private CslIspDao cslIspDao;

	@Resource(name="cslAnmDao")
	private CslAnmDao cslAnmDao;

	@Resource(name="cslHealDao")
	private CslHealDao cslHealDao;

	@Resource(name="fileInfoService")
	private FileInfoService fileInfoService;

	@Resource(name = "FileManagerUtil")
	private FileManagerUtil fileUtil;
	
	@Resource(name="propertiesService")
	protected EgovPropertyService propertiesService;

	/**
	 * 회원별 집중상담 이력 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getCslIdvList(HashMap<String, Object> map) throws Exception{
		return cslIdvDao.getCslIdvList(map);
	}

	/**
	 * 집중 상담 내용을 조회 한다
	 * @param cslNo
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getCslIdv(String cslNo) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cslNo", cslNo);
		map.put("paswKey", propertiesService.getString("aes256Key"));
		
		HashMap<String, Object> result = cslIdvDao.getCslIdv(map);

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
	public List<HashMap<String, Object>> getCslIdvStatistics(HashMap<String, Object> map) throws Exception{
		map.put("paswKey", propertiesService.getString("aes256Key"));
		return cslIdvDao.getCslIdvStatistics(map); 
	}

	/**
	 * 집중 상담이력을 등록 한다.
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertCslIdv(HashMap<String, Object> map) throws Exception{
		return cslIdvDao.insertCslIdv(map);
	}

	/**
	 * 집중 상담 이력 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateCslIdv(HashMap<String, Object> map) throws Exception{
		return cslIdvDao.updateCslIdv(map);
	}

	/**
	 * 집중 상담 번호 생성
	 * @return
	 * @throws Exception
	 */
	public String getCslIdvSeq() throws Exception{
		return cslIdvDao.getCslIdvSeq();
	}

	/**
	 * 집중 상담 내용 저장 처리
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> cslIdvAdd(HashMap<String, Object> map) throws Exception{
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String cslNo = StringUtils.defaultIfEmpty(map.get("cslNo").toString(), "");

		if(map.get("fileList") != null) {
			List<HashMap<String, Object>> fileList = (List<HashMap<String, Object>>)map.get("fileList");

			for(HashMap<String, Object> info : fileList){
				fileInfoService.insertFileInfo(info);
 			}
		}

		if("".equals(cslNo)){
			map.put("cslNo", this.getCslIdvSeq());

			int result = this.insertCslIdv(map);
			if(result > 0){
				resultMap.put("err", ConstantObject.N);
				resultMap.put("MSG", "사례관리 상담 등록 완료");
			}else{
				resultMap.put("err", ConstantObject.Y);
				resultMap.put("MSG", "사례관리 상담 등록 실패");
			}
		}else{
			HashMap<String, Object> cslIdvInfo = this.getCslIdv(cslNo);

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

			int result = this.updateCslIdv(map);
			if(result > 0){
				resultMap.put("err", ConstantObject.N);
				resultMap.put("MSG", "사례관리 상담 수정 완료");
			}else{
				resultMap.put("err", ConstantObject.Y);
				resultMap.put("MSG", "사례관리 상담 수정 실패");
			}
		}

		return resultMap;
	}

	/**
	 * 집중 상담 내용 삭제
	 * @param clsNo
	 * @return
	 * @throws Exception
	 */
	public int deleteCslIdv(String cslNo) throws Exception{
		HashMap<String, Object> result = this.getCslIdv(cslNo);

		if(!result.isEmpty()) {
			String fileId = StringUtils.defaultIfEmpty((String)result.get("FILE_ID"), "");
			if(!"".equals(fileId)) {			// 첨부 파일 존재시 삭제
				HashMap<String, Object> fileMap = new HashMap<String, Object>();
				fileMap.put("fileId", fileId);

				fileUtil.deleteFile(fileId);
				fileInfoService.deleteFileInfo(fileMap);
			}
		}

		return cslIdvDao.deleteCslIdv(cslNo);
	}

	/**
	 * ISP 수립 목록 조회
	 * @param mbrNo
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getCslIspList(String mbrNo) throws Exception{
		return cslIspDao.getCslIspList(mbrNo);
	}

	/**
	 * ISP 수립 상세 내용 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getCslIspInfo(HashMap<String, Object> map) throws Exception{
		map.put("paswKey", propertiesService.getString("aes256Key"));
		return cslIspDao.getCslIspInfo(map);
	}
	public List<HashMap<String, Object>> getCslIspInfoStatistics(HashMap<String, Object> map) throws Exception{
		map.put("paswKey", propertiesService.getString("aes256Key"));
		return cslIspDao.getCslIspInfoStatistics(map);
	}

	/**
	 * ISP 수립 삭제
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteCslIsp(HashMap<String, Object> map) throws Exception{
		return cslIspDao.deleteCslIsp(map);
	}

	/**
	 * ISP 수립 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertCslIsp(HashMap<String, Object> map) throws Exception{
		return cslIspDao.insertCslIsp(map);
	}

	/**
	 * ISP 수립 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateCslIsp(HashMap<String, Object> map) throws Exception{
		return cslIspDao.updateCslIsp(map);
	}

	/**
	 * ISP 수립 등록/수정 처리
	 * @param reqMap
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> cslIspAdd(HashMap<String, Object> map) throws Exception{
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		String newFlag = StringUtils.defaultIfEmpty(map.get("newFlag").toString(), ConstantObject.N);

		HashMap<String, Object> ispInfo = this.getCslIspInfo(map);

		if(ConstantObject.Y.equals(newFlag)) {					// 신규 등록
			if(ispInfo != null && StringUtils.defaultIfEmpty(ispInfo.get("ISP_DT").toString(), "") != "") {
				resultMap.put("err", "Y");
				resultMap.put("MSG", "이미 저장된 ISP 수립일자와 회원정보가 존재합니다.");
			}else{
				int result = this.insertCslIsp(map);
				if(result > 0) {
					resultMap.put("err", "N");
					resultMap.put("MSG", "ISP 수립 등록 완료");
				}else{
					resultMap.put("err", "Y");
					resultMap.put("MSG", "ISP 수립 등록 실패");
				}
			}
		}else{
			if(ispInfo != null && StringUtils.defaultIfEmpty(ispInfo.get("ISP_DT").toString(), "").equals(StringUtils.defaultIfEmpty(map.get("ispDt").toString(), ""))) {
				int result = this.updateCslIsp(map);
				if(result > 0) {
					resultMap.put("err", "N");
					resultMap.put("MSG", "ISP 수립 수정 완료");
				}else{
					resultMap.put("err", "Y");
					resultMap.put("MSG", "ISP 수립 수정 실패");
				}
			}else{
				resultMap.put("err", "Y");
				resultMap.put("MSG", " 일치하는 저장된 ISP 수립일자와 회원정보가 존재하지 않습니다.");
			}
		}

		return resultMap;
	}

	/**
	 * 사례관리 병력정보 목록 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getCslAnmList(HashMap<String, Object> map) throws Exception{
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("mbrNo"), ""))) {
			throw new Exception("IndividualService.getCslAnmList mbrNo 필수값 누락");
		}

		return cslAnmDao.getCslAnmList(map);
	}

	/**
	 * 사례관리 병력정보 상세 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getCslAnmInfo(HashMap<String, Object> map) throws Exception{
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("mbrNo"), ""))) {
			throw new Exception("IndividualService.getCslAnmInfo mbrNo 필수값 누락");
		}
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("cslNo"), ""))) {
			throw new Exception("IndividualService.getCslAnmInfo cslNo 필수값 누락");
		}

		return cslAnmDao.getCslAnmInfo(map);
	}

	/**
	 * 사례관리 병력정보 고유키 생성
	 * @param mbrNo
	 * @return
	 * @throws Exception
	 */
	public String getCslAnmSeq(String mbrNo) throws Exception{
		if("".equals(StringUtils.defaultIfEmpty(mbrNo, ""))) {
			throw new Exception("IndividualService.getCslAnmSeq mbrNo 필수값 누락");
		}

		return cslAnmDao.getCslAnmSeq(mbrNo);
	}

	/**
	 *사례관리 병력정보 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertCslAnm(HashMap<String, Object> map) throws Exception{
		String mbrNo = StringUtils.defaultIfEmpty((String)map.get("mbrNo"), "");

		if("".equals(mbrNo)) {
			throw new Exception("IndividualService.insertCslAnm mbrNo 필수값 누락");
		}

		map.put("cslNo", this.getCslAnmSeq(mbrNo));

		return cslAnmDao.insertCslAnm(map);
	}

	/**
	 * 사례관리 병력정보 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateCslAnm(HashMap<String, Object> map) throws Exception{
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("mbrNo"), ""))) {
			throw new Exception("IndividualService.updateCslAnm mbrNo 필수값 누락");
		}
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("cslNo"), ""))) {
			throw new Exception("IndividualService.updateCslAnm cslNo 필수값 누락");
		}

		return cslAnmDao.updateCslAnm(map);
	}

	/**
	 * 사례관리 병력정보 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> addCslAns(HashMap<String, Object> map) throws Exception{
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		int result = 0;

		String tagMbrNo = StringUtils.defaultIfEmpty((String)map.get("mbrNo"), "");
		String tagCslNo = StringUtils.defaultIfEmpty((String)map.get("cslNo"), "");

		if(
			(
				!"".equals(StringUtils.defaultIfEmpty((String)map.get("sudIndt"), "")) ||
				!"".equals(StringUtils.defaultIfEmpty((String)map.get("sudAge"), "")) ||
				!"".equals(StringUtils.defaultIfEmpty((String)map.get("sudTypeCd"), "")) ||
				!"".equals(StringUtils.defaultIfEmpty((String)map.get("sudSoulCd"), "")) ||
				!"".equals(StringUtils.defaultIfEmpty((String)map.get("sudWayCd"), "")) ||
				!"".equals(StringUtils.defaultIfEmpty((String)map.get("sudWayEtc"), "")) ||
				!"".equals(StringUtils.defaultIfEmpty((String)map.get("sudCtnt"), ""))
			) &&
			"".equals(StringUtils.defaultIfEmpty((String)map.get("sudCreDt"), ""))
		  ) {
			map.put("sudCreDt", DateUtil.getToday("yyyyMMdd"));
		}
		if(
			(!"".equals(StringUtils.defaultIfEmpty((String)map.get("devBabyPregCd"), "")) ||
			!"".equals(StringUtils.defaultIfEmpty((String)map.get("devBabyDevCd"), "")) ||
			!"".equals(StringUtils.defaultIfEmpty((String)map.get("devBabyNurtCd"), "")) ||
			!"".equals(StringUtils.defaultIfEmpty((String)map.get("devChildDiscCd"), "")) ||
			!"".equals(StringUtils.defaultIfEmpty((String)map.get("devChildLearnCd"), "")) ||
			!"".equals(StringUtils.defaultIfEmpty((String)map.get("devChildRelationCd"), "")) ||
			!"".equals(StringUtils.defaultIfEmpty((String)map.get("devChildTec"), "")) ||
			!"".equals(StringUtils.defaultIfEmpty((String)map.get("devTeenLearnCd"), "")) ||
			!"".equals(StringUtils.defaultIfEmpty((String)map.get("devTeenRelationCd"), "")) ||
			!"".equals(StringUtils.defaultIfEmpty((String)map.get("devTeenUniCd"), "")) ||
			!"".equals(StringUtils.defaultIfEmpty((String)map.get("devTeenEtc"), "")) ||
			!"".equals(StringUtils.defaultIfEmpty((String)map.get("devAdulRelationCd"), "")) ||
			!"".equals(StringUtils.defaultIfEmpty((String)map.get("devAdulSexCd"), "")) ||
			!"".equals(StringUtils.defaultIfEmpty((String)map.get("devAdulEtc"), ""))) &&
			"".equals(StringUtils.defaultIfEmpty((String)map.get("devCreDt"), ""))
		  ) {
			map.put("devCreDt", DateUtil.getToday("yyyyMMdd"));
		}

		if(tagMbrNo != null && !"".equals(tagMbrNo)) {
			if(tagCslNo != null && !"".equals(tagCslNo)) {
				result = this.updateCslAnm(map);
				resultMap.put("MSG", "수정");
			}else {
				result = this.insertCslAnm(map);
				resultMap.put("MSG", "등록");
			}
		}else {
			resultMap.put("MSG", "필수값 누락");
		}

		if(result > 0) {
			resultMap.put("err", ConstantObject.N);
		}else {
			resultMap.put("err", ConstantObject.Y);
		}

		return resultMap;
	}

	/**
	 * 병력정보 삭제
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteCslAnm(HashMap<String, Object> map) throws Exception{
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("mbrNo"), ""))) {
			throw new Exception("IndividualService.deleteCslAnm mbrNo 필수값 누락");
		}
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("cslNo"), ""))) {
			throw new Exception("IndividualService.deleteCslAnm cslNo 필수값 누락");
		}

		return cslAnmDao.deleteCslAnm(map);
	}

	/**
	 * 치료재활정보 목록 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getCslHealList(HashMap<String, Object> map) throws Exception{
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("mbrNo"), ""))) {
			throw new Exception("IndividualService.getCslHealList mbrNo 필수값 누락");
		}

		return cslHealDao.getCslHealList(map);
	}

	/**
	 * 치료재활정보 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertCslHeal(HashMap<String, Object> map) throws Exception{
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("mbrNo"), ""))) {
			throw new Exception("IndividualService.insertCslHeal mbrNo 필수값 누락");
		}

		map.put("cslNo", this.getCslHealSeq());

		return cslHealDao.insertCslHeal(map);
	}

	/**
	 * 치료재활정보 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateCslHeal(HashMap<String, Object> map) throws Exception{
		return cslHealDao.updateCslHeal(map);
	}

	/**
	 * 치료재활정보 고유키 생성
	 * @return
	 * @throws Exception
	 */
	public String getCslHealSeq() throws Exception{
		return cslHealDao.getCslHealSeq();
	}

	/**
	 * 치료재활정보 저장
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> addCslHeal(HashMap<String, Object> map) throws Exception{
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		int result = 0;

		String tagMbrNo = StringUtils.defaultIfEmpty((String)map.get("mbrNo"), "");
		String tagCslNo = StringUtils.defaultIfEmpty((String)map.get("cslNo"), "");

		if(
			(
				!"".equals(StringUtils.defaultIfEmpty((String)map.get("jobStDt"), "")) ||
				!"".equals(StringUtils.defaultIfEmpty((String)map.get("jobEndDt"), "")) ||
				!"".equals(StringUtils.defaultIfEmpty((String)map.get("jobTerm"), "")) ||
				!"".equals(StringUtils.defaultIfEmpty((String)map.get("jobFormCd"), "")) ||
				!"".equals(StringUtils.defaultIfEmpty((String)map.get("jobTypeCd"), "")) ||
				!"".equals(StringUtils.defaultIfEmpty((String)map.get("jobIncome"), "")) ||
				!"".equals(StringUtils.defaultIfEmpty((String)map.get("jobResign"), ""))
			) &&
			"".equals(StringUtils.defaultIfEmpty((String)map.get("jobCreDt"), ""))
		  ) {
			map.put("jobCreDt", DateUtil.getToday("yyyyMMdd"));
		}
		if(
			(
				!"".equals(StringUtils.defaultIfEmpty((String)map.get("healStDt"), "")) ||
				!"".equals(StringUtils.defaultIfEmpty((String)map.get("healEndDt"), "")) ||
				!"".equals(StringUtils.defaultIfEmpty((String)map.get("healTerm"), "")) ||
				!"".equals(StringUtils.defaultIfEmpty((String)map.get("organFormCd"), "")) ||
				!"".equals(StringUtils.defaultIfEmpty((String)map.get("organName"), "")) ||
				!"".equals(StringUtils.defaultIfEmpty((String)map.get("organCtnt"), ""))
			) &&
			"".equals(StringUtils.defaultIfEmpty((String)map.get("healCreDt"), ""))
		  ){
			map.put("healCreDt", DateUtil.getToday("yyyyMMdd"));
		}

		if(tagMbrNo != null && !"".equals(tagMbrNo)) {
			if(tagCslNo != null && !"".equals(tagCslNo)) {
				result = this.updateCslHeal(map);
				resultMap.put("MSG", "수정");
			}else {
				result = this.insertCslHeal(map);
				resultMap.put("MSG", "등록");
			}
		}else {
			resultMap.put("MSG", "필수값 누락");
		}

		if(result > 0) {
			resultMap.put("err", ConstantObject.N);
		}else {
			resultMap.put("err", ConstantObject.Y);
		}

		return resultMap;
	}

	/**
	 * 치료재활정보 상세 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getCslHealInfo(HashMap<String, Object> map) throws Exception{
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("cslNo"), ""))) {
			throw new Exception("IndividualService.getCslHealInfo cslNo 필수값 누락");
		}

		return cslHealDao.getCslHealInfo(map);
	}

	/**
	 * 치료재활정보 삭제
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteCslHeal(HashMap<String, Object> map) throws Exception{
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("cslNo"), ""))) {
			throw new Exception("IndividualService.getCslHealInfo cslNo 필수값 누락");
		}

		return cslHealDao.deleteCslHeal(map);
	}
}