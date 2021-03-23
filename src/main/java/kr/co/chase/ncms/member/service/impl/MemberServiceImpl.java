package kr.co.chase.ncms.member.service.impl;

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
import kr.co.chase.ncms.counsel.service.CounselService;
import kr.co.chase.ncms.dao.MstMbrDao;
import kr.co.chase.ncms.dao.MstRegHisDao;
import kr.co.chase.ncms.dao.MstTransDao;
import kr.co.chase.ncms.member.service.MemberService;

@Service("memberService")
public class MemberServiceImpl extends EgovAbstractServiceImpl implements MemberService {
	private static final Logger LOGGER = LoggerFactory.getLogger(MemberServiceImpl.class);

	@Resource(name="mstMbrDao")
	private MstMbrDao mstMbrDao;

	@Resource(name="mstRegHisDao")
	private MstRegHisDao mstRegHisDao;

	@Resource(name="mstTransDao")
	private MstTransDao mstTransDao;

	@Resource(name="counselService")
	private CounselService counselService;

	@Resource(name="fileInfoService")
	private FileInfoService fileInfoService;

	@Resource(name = "FileManagerUtil")
	private FileManagerUtil fileUtil;

	@Resource(name="propertiesService")
	protected EgovPropertyService propertiesService;

	/**
	 * 회원 정보 조회
	 * @param mbrNo
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getMstMbr(String mbrNo) throws Exception{
		if("".equals(StringUtils.defaultIfEmpty(mbrNo, ""))) {
			throw new Exception("MemberServiceImpl.getMstMbr mbrNo 필수값 누락");
		}

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("mbrNo", mbrNo);
		map.put("paswKey", propertiesService.getString("aes256Key"));

		HashMap<String, Object> mbrInfoMap = mstMbrDao.getMstMbr(map);
		if(mbrInfoMap != null) {
			String fmlyTree = StringUtils.defaultIfEmpty((String)mbrInfoMap.get("FMLY_TREE"), "");
			String personalInfo = StringUtils.defaultIfEmpty((String)mbrInfoMap.get("PERSONAL_INFO"), "");

			if(!"".equals(fmlyTree)){
				HashMap<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("fileId", fmlyTree);

				mbrInfoMap.put("fmlyTreeFileList", fileInfoService.getFileList(paramMap));
			}
			if(!"".equals(personalInfo)){
				HashMap<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("fileId", personalInfo);

				mbrInfoMap.put("personalInfoFileList", fileInfoService.getFileList(paramMap));
			}
		}

		return mbrInfoMap;
	}

	/**
	 * 회원 이력 조회
	 * @param mbrNo
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getMstRegHisList(String mbrNo) throws Exception{
		if("".equals(StringUtils.defaultIfEmpty(mbrNo, ""))) {
			throw new Exception("MemberServiceImpl.getMstRegHisList mbrNo 필수값 누락");
		}

		return mstRegHisDao.getMstRegHisList(mbrNo);
	}

	/**
	 * 회원 번호 생성
	 * @return
	 * @throws Exception
	 */
	public String getMbrNoSeq() throws Exception{
		return mstMbrDao.getMbrNoSeq();
	}

	/**
	 * 회원 정보 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertMstMbr(HashMap<String, Object> map) throws Exception{
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("mbrNo"), ""))) {
			throw new Exception("MemberServiceImpl.insertMstMbr mbrNo 필수값 누락");
		}

		map.put("paswKey", propertiesService.getString("aes256Key"));

		return mstMbrDao.insertMstMbr(map);
	}

	/**
	 * 회원 이력 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertMstRegHis(HashMap<String, Object> map) throws Exception{
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("mbrNo"), ""))) {
			throw new Exception("MemberServiceImpl.insertMstMbr mbrNo 필수값 누락");
		}
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("regRlsCd"), ""))) {
			throw new Exception("MemberServiceImpl.insertMstMbr regRlsCd 필수값 누락");
		}

		return mstRegHisDao.insertMstRegHis(map);
	}

	/**
	 * 회원 정보 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateMstMbr(HashMap<String, Object> map) throws Exception{
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("mbrNo"), ""))) {
			throw new Exception("MemberServiceImpl.updateMstMbr mbrNo 필수값 누락");
		}

		map.put("paswKey", propertiesService.getString("aes256Key"));

		return mstMbrDao.updateMstMbr(map);
	}

	/**
	 * 회원 정보 저장
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> saveMstMbr(HashMap<String, Object> map) throws Exception{
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		int result = 0;

		if(map.get("fileList") != null) {
			List<HashMap<String, Object>> fileList = (List<HashMap<String, Object>>)map.get("fileList");

			for(HashMap<String, Object> info : fileList){
				fileInfoService.insertFileInfo(info);
 			}
		}

		String mbrNo = StringUtils.defaultIfEmpty((String)map.get("mbrNo"), "");
		if("".equals(mbrNo)){
			HashMap<String, Object> sechMap = new HashMap<String, Object>();
			sechMap.put("mbrNm", map.get("mbrNm"));
			sechMap.put("juminNo1", map.get("juminNo1"));

			int memCnt = counselService.getMstMbrListCount(sechMap);
			if(memCnt <= 0){
				String newMbrNo = this.getMbrNoSeq();
				map.put("mbrNo", newMbrNo);
				map.put("stsCd", ConstantObject.defaultMemStsCd);
				result = this.insertMstMbr(map);

				if(result > 0) {
					map.put("regRlsCd", ConstantObject.defaultMemStsCd);
					map.put("regRlsDt", map.get("regDt"));
					map.put("dtlCtnt", "최초등록");
					this.insertMstRegHis(map);
				}

				resultMap.put("MSG", "등록");
				resultMap.put("mbrNo", newMbrNo);
			}else{
				resultMap.put("err", ConstantObject.Y);
				resultMap.put("MSG", "동일 회원 정보 존재");
			}
		}else{
			HashMap<String, Object> mbrInfo = this.getMstMbr(mbrNo);
			if(mbrInfo != null) {
				String oldFmlyTree = StringUtils.defaultIfEmpty((String)mbrInfo.get("FMLY_TREE"), "");
				String oldPersonalInfo = StringUtils.defaultIfEmpty((String)mbrInfo.get("PERSONAL_INFO"), "");

				if(
						ConstantObject.Y.equals(StringUtils.defaultIfEmpty((String)map.get("fmlyTreeFileNameFlag"), ""))
					 || (!"".equals(StringUtils.defaultIfEmpty((String)map.get("fmlyTree"), "")) && !"".equals(oldFmlyTree))
				  ) {
					mbrInfo.put("fileId", oldFmlyTree);

					fileUtil.deleteFile(oldFmlyTree);
					fileInfoService.deleteFileInfo(mbrInfo);
				}else if(!"".equals(oldFmlyTree)) {
					map.put("fmlyTree", oldFmlyTree);
				}

				if(
						ConstantObject.Y.equals(StringUtils.defaultIfEmpty((String)map.get("personalInfoFileNameFlag"), ""))
					 || (!"".equals(StringUtils.defaultIfEmpty((String)map.get("personalInfo"), "")) && !"".equals(oldPersonalInfo))
				  ) {
					mbrInfo.put("fileId", oldPersonalInfo);

					fileUtil.deleteFile(oldPersonalInfo);
					fileInfoService.deleteFileInfo(mbrInfo);
				}else if(!"".equals(oldFmlyTree)) {
					map.put("personalInfo", oldPersonalInfo);
				}
			}

			result = this.updateMstMbr(map);
			resultMap.put("MSG", "수정");
			resultMap.put("mbrNo", map.get("mbrNo"));
		}

		if(result <= 0) {
			resultMap.put("err", ConstantObject.Y);
		}

		return resultMap;
	}

	/**
	 * 회원 이력 삭제
	 * @param mbrNo
	 * @return
	 * @throws Exception
	 */
	public int deleteMstRegHis(String mbrNo) throws Exception{
		if("".equals(StringUtils.defaultIfEmpty(mbrNo, ""))) {
			throw new Exception("MemberServiceImpl.deleteMstRegHis mbrNo 필수값 누락");
		}

		return mstRegHisDao.deleteMstRegHis(mbrNo);
	}

	/**
	 * 회원 정보 삭제
	 * @param mbrNo
	 * @return
	 * @throws Exception
	 */
	public int deleteMstMbr(String mbrNo) throws Exception{
		if("".equals(StringUtils.defaultIfEmpty(mbrNo, ""))) {
			throw new Exception("MemberServiceImpl.deleteMstMbr mbrNo 필수값 누락");
		}

		return mstMbrDao.deleteMstMbr(mbrNo);
	}

	/**
	 * 회원 정보/이력 삭제 처리
	 * @param mbrNo
	 * @return
	 * @throws Exception
	 */
	public int mstMbrDel(String mbrNo) throws Exception{
		int result = 0;

		result = this.deleteMstRegHis(mbrNo);
		if(result > 0) {
			result = this.deleteMstMbr(mbrNo);
		}

		return result;
	}

	/**
	 * 회원 퇴록 정보 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateMstMbrStsCd(HashMap<String, Object> map) throws Exception{
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("mbrNo"), ""))) {
			throw new Exception("MemberServiceImpl.deleteMstMbr mbrNo 필수값 누락");
		}

		return mstMbrDao.updateMstMbrStsCd(map);
	}

	/**
	 * 회원 퇴록/재등록 처리
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int StsCdUpdate(HashMap<String, Object> map) throws Exception{
		int result = this.updateMstMbrStsCd(map);
		if(result > 0){
			map.put("regRlsCd", map.get("stsCd"));
			map.put("regRlsDt", map.get("stpDt"));

			this.insertMstRegHis(map);
		}

		return result;
	}

	/**
	 * 미등록 회원 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String saveMstMbrEt(HashMap<String, Object> map) throws Exception{
		int result = 0;

		String newMbrNo = this.getMbrNoSeq();
		map.put("mbrNo", newMbrNo);
		map.put("stsCd", ConstantObject.etMemStsCd);
		result = this.insertMstMbr(map);

		if(result > 0) {
			map.put("regRlsCd", ConstantObject.etMemStsCd);
			map.put("regRlsDt", map.get("regDt"));
			map.put("dtlCtnt", "최초등록");
			this.insertMstRegHis(map);
		}

		return newMbrNo;
	}

	/**
	 * 가족관계 정보 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateMbrFmly(HashMap<String, Object> map) throws Exception {
		return mstMbrDao.updateMbrFmly(map);
	}

	/**
	 * 의뢰 고유키 생성
	 * @return
	 * @throws Exception
	 */
	public String getMstTransSeq() throws Exception{
		return mstTransDao.getMstTransSeq();
	}

	/**
	 * 의뢰정보 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertMstTrans(HashMap<String, Object> map) throws Exception{
		return mstTransDao.insertMstTrans(map);
	}

	/**
	 * 의뢰정보 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateMstTrans(HashMap<String, Object> map) throws Exception{
		return mstTransDao.updateMstTrans(map);
	}

	/**
	 * 연계정보 저장
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateMstTransReceipt(HashMap<String, Object> map) throws Exception{
		return mstTransDao.updateMstTransReceipt(map);
	}

	/**
	 * 의뢰정보 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getMstTrans(HashMap<String, Object> map) throws Exception{
		HashMap<String, Object> resultMap = mstTransDao.getMstTrans(map);

		if(resultMap != null) {
			String fileId = StringUtils.defaultIfEmpty((String)resultMap.get("FILE_ID"), "");
			if(!"".equals(fileId)) {
				HashMap<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("fileId", fileId);

				resultMap.put("transFileList", fileInfoService.getFileList(paramMap));
			}
		}

		return resultMap;
	}

	/**
	 * 의뢰정보 저장
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> saveTrans(HashMap<String, Object> map) throws Exception{
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String tagMbrNo = StringUtils.defaultIfEmpty((String)map.get("mbrNo"), "");
		String transNo = StringUtils.defaultIfEmpty((String)map.get("transNo"), "");
		int result = 0;

		if(map.get("fileList") != null) {
			List<HashMap<String, Object>> fileList = (List<HashMap<String, Object>>)map.get("fileList");

			for(HashMap<String, Object> info : fileList){
				fileInfoService.insertFileInfo(info);
			}
		}

		if("".equals(transNo)) {
			map.put("transNo", this.getMstTransSeq());

			result = this.insertMstTrans(map);

			resultMap.put("MSG", "등록");
		}else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("transNo", transNo);
			paramMap = this.getMstTrans(paramMap);

			if(paramMap != null) {
				String oldTransFileId = StringUtils.defaultIfEmpty((String)paramMap.get("FILE_ID"), "");

				if(
						ConstantObject.Y.equals(StringUtils.defaultIfEmpty((String)map.get("transfileNameFlag"), ""))
					 || (!"".equals(StringUtils.defaultIfEmpty((String)map.get("fileId"), "")) && !"".equals(oldTransFileId))
				  ) {
					paramMap.put("fileId", oldTransFileId);

					fileUtil.deleteFile(oldTransFileId);
					fileInfoService.deleteFileInfo(paramMap);
				}else if(!"".equals(oldTransFileId)) {
					map.put("fileId", oldTransFileId);
				}

				result = this.updateMstTrans(map);

				resultMap.put("MSG", "수정");
			}
		}

		if(result > 0) {
			HashMap<String, Object> mbrInfo = this.getMstMbr(tagMbrNo);
			if(mbrInfo != null) {
				String oldFmlyTree = StringUtils.defaultIfEmpty((String)mbrInfo.get("FMLY_TREE"), "");
				String oldPersonalInfo = StringUtils.defaultIfEmpty((String)mbrInfo.get("PERSONAL_INFO"), "");

				if(
						ConstantObject.Y.equals(StringUtils.defaultIfEmpty((String)map.get("fmlyTreeFileNameFlag"), ""))
					 || (!"".equals(StringUtils.defaultIfEmpty((String)map.get("fmlyTree"), "")) && !"".equals(oldFmlyTree))
				  ) {
					mbrInfo.put("fileId", oldFmlyTree);

					fileUtil.deleteFile(oldFmlyTree);
					fileInfoService.deleteFileInfo(mbrInfo);
				}else if(!"".equals(oldFmlyTree)) {
					map.put("fmlyTree", oldFmlyTree);
				}

				if(
						ConstantObject.Y.equals(StringUtils.defaultIfEmpty((String)map.get("personalInfoFileNameFlag"), ""))
					 || (!"".equals(StringUtils.defaultIfEmpty((String)map.get("personalInfo"), "")) && !"".equals(oldPersonalInfo))
				  ) {
					mbrInfo.put("fileId", oldPersonalInfo);

					fileUtil.deleteFile(oldPersonalInfo);
					fileInfoService.deleteFileInfo(mbrInfo);
				}else if(!"".equals(oldFmlyTree)) {
					map.put("personalInfo", oldPersonalInfo);
				}
			}

			result = this.updateMbrFmly(map);
		}

		if(result <= 0) {
			resultMap.put("err", ConstantObject.Y);
		}

		return resultMap;
	}

	/**
	 * 의뢰정보 목록 카운트
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int getMstTransListCount(HashMap<String, Object> map) throws Exception{
		return mstTransDao.getMstTransListCount(map);
	}

	/**
	 * 의뢰정보 목록 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getMstTransList(HashMap<String, Object> map) throws Exception{
		if(map.get("currentPageNo")== null || StringUtils.defaultString(map.get("currentPageNo").toString(), "") == "") {
			throw new Exception("MemberServiceImpl.getMstTransList currentPageNo 페이지 수 누락");
		}
		if(map.get("recordCountPerPage") == null || StringUtils.defaultString(map.get("recordCountPerPage").toString(), "") == "") {
			throw new Exception("MemberServiceImpl.getMstTransList recordCountPerPage 목록 수 누락");
		}

		return mstTransDao.getMstTransList(map);
	}

	/**
	 * 연계처리 내용 저장
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> saveTransState(HashMap<String, Object> map) throws Exception{
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String tagMbrNo = StringUtils.defaultIfEmpty((String)map.get("mbrNo"), "");
		String transNo = StringUtils.defaultIfEmpty((String)map.get("transNo"), "");
		String linkStateCd = StringUtils.defaultIfEmpty((String)map.get("linkStateCd"), "");
		int result = 0;

		if(map.get("fileList") != null) {
			List<HashMap<String, Object>> fileList = (List<HashMap<String, Object>>)map.get("fileList");

			for(HashMap<String, Object> info : fileList){
				fileInfoService.insertFileInfo(info);
			}
		}

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("transNo", transNo);
		paramMap = this.getMstTrans(paramMap);

		if(paramMap != null) {
			String oldTransFileId = StringUtils.defaultIfEmpty((String)paramMap.get("FILE_ID"), "");

			if(
					ConstantObject.Y.equals(StringUtils.defaultIfEmpty((String)map.get("transfileNameFlag"), ""))
				 || (!"".equals(StringUtils.defaultIfEmpty((String)map.get("fileId"), "")) && !"".equals(oldTransFileId))
			  ) {
				paramMap.put("fileId", oldTransFileId);

				fileUtil.deleteFile(oldTransFileId);
				fileInfoService.deleteFileInfo(paramMap);
			}else if(!"".equals(oldTransFileId)) {
				map.put("fileId", oldTransFileId);
			}

			result = this.updateMstTransReceipt(map);
		}

		if("2".equals(linkStateCd) && result > 0) {			// 접수인 경우
			result = this.updateMbrMngUsrId(map);
		}

		if(result > 0) {
			HashMap<String, Object> mbrInfo = this.getMstMbr(tagMbrNo);
			if(mbrInfo != null) {
				String oldFmlyTree = StringUtils.defaultIfEmpty((String)mbrInfo.get("FMLY_TREE"), "");
				String oldPersonalInfo = StringUtils.defaultIfEmpty((String)mbrInfo.get("PERSONAL_INFO"), "");

				if(
						ConstantObject.Y.equals(StringUtils.defaultIfEmpty((String)map.get("fmlyTreeFileNameFlag"), ""))
					 || (!"".equals(StringUtils.defaultIfEmpty((String)map.get("fmlyTree"), "")) && !"".equals(oldFmlyTree))
				  ) {
					mbrInfo.put("fileId", oldFmlyTree);

					fileUtil.deleteFile(oldFmlyTree);
					fileInfoService.deleteFileInfo(mbrInfo);
				}else if(!"".equals(oldFmlyTree)) {
					map.put("fmlyTree", oldFmlyTree);
				}

				if(
						ConstantObject.Y.equals(StringUtils.defaultIfEmpty((String)map.get("personalInfoFileNameFlag"), ""))
					 || (!"".equals(StringUtils.defaultIfEmpty((String)map.get("personalInfo"), "")) && !"".equals(oldPersonalInfo))
				  ) {
					mbrInfo.put("fileId", oldPersonalInfo);

					fileUtil.deleteFile(oldPersonalInfo);
					fileInfoService.deleteFileInfo(mbrInfo);
				}else if(!"".equals(oldFmlyTree)) {
					map.put("personalInfo", oldPersonalInfo);
				}
			}

			result = this.updateMbrFmly(map);
		}

		if(result <= 0) {
			resultMap.put("err", ConstantObject.Y);
		}

		return resultMap;
	}

	/**
	 * 사례관리자 수정
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateMbrMngUsrId(HashMap<String, Object> map) throws Exception{
		return mstMbrDao.updateMbrMngUsrId(map);
	}
}