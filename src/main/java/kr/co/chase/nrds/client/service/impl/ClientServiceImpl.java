package kr.co.chase.nrds.client.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.co.chase.ncms.common.ConstantObject;
import kr.co.chase.ncms.common.service.FileInfoService;
import kr.co.chase.ncms.common.util.FileManagerUtil;
import kr.co.chase.nrds.client.service.ClientService;
import kr.co.chase.nrds.dao.EdMbrDao;
import kr.co.chase.nrds.dao.EdMbrEdDao;
import kr.co.chase.nrds.dao.EdMbrGuDao;
import kr.co.chase.nrds.dao.EdMbrTransDao;

@Service("clientService")
public class ClientServiceImpl extends EgovAbstractServiceImpl implements ClientService {
	@Resource(name = "FileManagerUtil")
	private FileManagerUtil fileUtil;

	@Resource(name="fileInfoService")
	private FileInfoService fileInfoService;

	@Resource(name="edMbrDao")
	private EdMbrDao edMbrDao;

	@Resource(name="edMbrEdDao")
	private EdMbrEdDao edMbrEdDao;

	@Resource(name="edMbrGuDao")
	private EdMbrGuDao edMbrGuDao;

	@Resource(name="edMbrTransDao")
	private EdMbrTransDao edMbrTransDao;

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

	@Override
	public HashMap<String, Object> saveEdMbr(HashMap<String, Object> map) throws Exception{
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		int result = 0;

		String mbrNo = StringUtils.defaultIfEmpty((String)map.get("mbrNo"), "");
		if("".equals(mbrNo)){
			HashMap<String, Object> sechMap = new HashMap<String, Object>();
			sechMap.put("mbrNm", map.get("mbrNm"));
			sechMap.put("juminNo1", map.get("juminNo1"));

			int memCnt = this.getEdMbrListCount(sechMap);
			if(memCnt <= 0){
				String newMbrNo = this.getMbrNoSeq();
				map.put("mbrId", newMbrNo);
				map.put("mbrNo", newMbrNo);
				result = this.insertEdMbr(map);

				resultMap.put("MSG", "등록");
				resultMap.put("mbrNo", newMbrNo);
			}else{
				resultMap.put("err", ConstantObject.Y);
				resultMap.put("MSG", "동일 회원 정보 존재");
			}
		}else{
			result = this.updateEdMbr(map);
			resultMap.put("MSG", "수정");
			resultMap.put("mbrNo", map.get("mbrNo"));
		}

		if(result <= 0) {
			resultMap.put("err", ConstantObject.Y);
		}

		return resultMap;
	}

	@Override
	public int deleteEdMbr(String mbrNo) throws Exception {
		return edMbrDao.deleteEdMbr(mbrNo);
	}

	@Override
	public int insertEdMbrEd(HashMap<String, Object> map) throws Exception {
		return edMbrEdDao.insertEdMbrEd(map);
	}

	@Override
	public HashMap<String, Object> getEdMbrEdLastInfo(String mbrNo) throws Exception {
		return edMbrEdDao.getEdMbrEdLastInfo(mbrNo);
	}

	@Override
	public HashMap<String, Object> getEdMbrEdInfo(HashMap<String, Object> map) throws Exception {
		return edMbrEdDao.getEdMbrEdInfo(map);
	}

	@Override
	public List<HashMap<String, Object>> getEdMbrEdList(HashMap<String, Object> map) throws Exception {
		return edMbrEdDao.getEdMbrEdList(map);
	}

	@Override
	public int updateEdMbrEd(HashMap<String, Object> map) throws Exception {
		return edMbrEdDao.updateEdMbrEd(map);
	}

	@Override
	public int deleteEdMbrEd(HashMap<String, Object> map) throws Exception {
		return edMbrEdDao.deleteEdMbrEd(map);
	}

	@Override
	public HashMap<String, Object> saveEdMbrEd(Map<String, MultipartFile> files, HashMap<String, Object> map) throws Exception {
		int result = 0;
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String loginId = StringUtils.defaultIfEmpty((String)map.get("loginId"), "");
		String mbrEdId = StringUtils.defaultIfEmpty((String)map.get("mbrEdId"), "");
		String fileDelFlag = StringUtils.defaultIfEmpty((String)map.get("fileDelFlag"), ConstantObject.N);
		String fileId = "";

		if(!files.isEmpty()){
			boolean flag = fileUtil.checkFiles(files);

			if(flag){
				HashMap<String, Object> fileMng = fileUtil.parseFileInf(files, "EME", loginId);
				if(!fileMng.isEmpty()) {
					List<HashMap<String, Object>> fileList = (List<HashMap<String, Object>>)fileMng.get("fileList");

					for(HashMap<String, Object> info : fileList){
						fileInfoService.insertFileInfo(info);
		 			}

					fileId = (String)fileMng.get("fileId");
				}
			}
		}

		if(!"".equals(mbrEdId)) {
			if("".equals(fileId) && ConstantObject.N.equals(fileDelFlag)) {
				HashMap<String, Object> edMbrEdInfo = this.getEdMbrEdInfo(map);

				if(edMbrEdInfo != null) {
					fileId = StringUtils.defaultIfEmpty((String)edMbrEdInfo.get("FILE_ID"), "");
				}
			}

			map.put("fileId", fileId);

			result = this.updateEdMbrEd(map);
			resultMap.put("MSG", "수정");
		}else {
			map.put("fileId", fileId);

			result = this.insertEdMbrEd(map);
			resultMap.put("MSG", "등록");
		}

		if(result > 0) {
			resultMap.put("err", ConstantObject.N);
		}else{
			resultMap.put("err", ConstantObject.Y);
			resultMap.put("MSG", resultMap.get("MSG") + " 오류");
		}

		return resultMap;
	}

	@Override
	public int insertEdMbrGu(HashMap<String, Object> map) throws Exception {
		return edMbrGuDao.insertEdMbrGu(map);
	}

	@Override
	public HashMap<String, Object> getEdMbrGuLastInfo(String mbrNo) throws Exception {
		return edMbrGuDao.getEdMbrGuLastInfo(mbrNo);
	}

	@Override
	public HashMap<String, Object> getEdMbrGuInfo(HashMap<String, Object> map) throws Exception {
		return edMbrGuDao.getEdMbrGuInfo(map);
	}

	@Override
	public List<HashMap<String, Object>> getEdMbrGuList(HashMap<String, Object> map) throws Exception {
		return edMbrGuDao.getEdMbrGuList(map);
	}

	@Override
	public int updateEdMbrGu(HashMap<String, Object> map) throws Exception {
		return edMbrGuDao.updateEdMbrGu(map);
	}

	@Override
	public int deleteEdMbrGu(HashMap<String, Object> map) throws Exception {
		return edMbrGuDao.deleteEdMbrGu(map);
	}

	@Override
	public HashMap<String, Object> saveEdMbrGu(Map<String, MultipartFile> files, HashMap<String, Object> map) throws Exception {
		int result = 0;
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String loginId = StringUtils.defaultIfEmpty((String)map.get("loginId"), "");
		String mbrGuId = StringUtils.defaultIfEmpty((String)map.get("mbrGuId"), "");
		String fileDelFlag = StringUtils.defaultIfEmpty((String)map.get("fileDelFlag"), ConstantObject.N);
		String fileId = "";

		if(!files.isEmpty()){
			boolean flag = fileUtil.checkFiles(files);

			if(flag){
				HashMap<String, Object> fileMng = fileUtil.parseFileInf(files, "EMG", loginId);
				if(!fileMng.isEmpty()) {
					List<HashMap<String, Object>> fileList = (List<HashMap<String, Object>>)fileMng.get("fileList");

					for(HashMap<String, Object> info : fileList){
						fileInfoService.insertFileInfo(info);
		 			}

					fileId = (String)fileMng.get("fileId");
				}
			}
		}

		if(!"".equals(mbrGuId)) {
			if("".equals(fileId) && ConstantObject.N.equals(fileDelFlag)) {
				HashMap<String, Object> edMbrGuInfo = this.getEdMbrGuInfo(map);

				if(edMbrGuInfo != null) {
					fileId = StringUtils.defaultIfEmpty((String)edMbrGuInfo.get("FILE_ID"), "");
				}
			}

			map.put("fileId", fileId);

			result = this.updateEdMbrGu(map);
			resultMap.put("MSG", "수정");
		}else{
			map.put("fileId", fileId);

			result = this.insertEdMbrGu(map);
			resultMap.put("MSG", "등록");
		}

		if(result > 0) {
			resultMap.put("err", ConstantObject.N);
		}else{
			resultMap.put("err", ConstantObject.Y);
			resultMap.put("MSG", resultMap.get("MSG") + " 오류");
		}

		return resultMap;
	}

	@Override
	public int insertEdMbrTrans(HashMap<String, Object> map) throws Exception {
		return edMbrTransDao.insertEdMbrTrans(map);
	}

	@Override
	public HashMap<String, Object> getEdMbrTransLastInfo(String mbrNo) throws Exception {
		return edMbrTransDao.getEdMbrTransLastInfo(mbrNo);
	}

	@Override
	public HashMap<String, Object> getEdMbrTransInfo(HashMap<String, Object> map) throws Exception {
		return edMbrTransDao.getEdMbrTransInfo(map);
	}

	@Override
	public List<HashMap<String, Object>> getEdMbrTransList(HashMap<String, Object> map) throws Exception {
		return edMbrTransDao.getEdMbrTransList(map);
	}

	@Override
	public int updateEdMbrTrans(HashMap<String, Object> map) throws Exception {
		return edMbrTransDao.updateEdMbrTrans(map);
	}

	@Override
	public int deleteEdMbrTrans(HashMap<String, Object> map) throws Exception {
		return edMbrTransDao.deleteEdMbrTrans(map);
	}

	@Override
	public HashMap<String, Object> saveEdMbrTrans(Map<String, MultipartFile> files, HashMap<String, Object> map) throws Exception {
		int result = 0;
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String loginId = StringUtils.defaultIfEmpty((String)map.get("loginId"), "");
		String mbrTransId = StringUtils.defaultIfEmpty((String)map.get("mbrTransId"), "");
		String fileDelFlag = StringUtils.defaultIfEmpty((String)map.get("fileDelFlag"), ConstantObject.N);
		String fileId = "";

		if(!files.isEmpty()){
			boolean flag = fileUtil.checkFiles(files);

			if(flag){
				HashMap<String, Object> fileMng = fileUtil.parseFileInf(files, "EMG", loginId);
				if(!fileMng.isEmpty()) {
					List<HashMap<String, Object>> fileList = (List<HashMap<String, Object>>)fileMng.get("fileList");

					for(HashMap<String, Object> info : fileList){
						fileInfoService.insertFileInfo(info);
		 			}

					fileId = (String)fileMng.get("fileId");
				}
			}
		}

		if(!"".equals(mbrTransId)) {
			if("".equals(fileId) && ConstantObject.N.equals(fileDelFlag)) {
				HashMap<String, Object> edMbrTransInfo = this.getEdMbrTransInfo(map);

				if(edMbrTransInfo != null) {
					fileId = StringUtils.defaultIfEmpty((String)edMbrTransInfo.get("FILE_ID"), "");
				}
			}

			map.put("fileId", fileId);

			result = this.updateEdMbrTrans(map);
			resultMap.put("MSG", "수정");
		}else{
			map.put("fileId", fileId);

			result = this.insertEdMbrTrans(map);
			resultMap.put("MSG", "등록");
		}

		if(result > 0) {
			resultMap.put("err", ConstantObject.N);
		}else{
			resultMap.put("err", ConstantObject.Y);
			resultMap.put("MSG", resultMap.get("MSG") + " 오류");
		}

		return resultMap;
	}
}