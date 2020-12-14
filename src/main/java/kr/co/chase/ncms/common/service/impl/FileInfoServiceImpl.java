package kr.co.chase.ncms.common.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.co.chase.ncms.common.service.FileInfoService;
import kr.co.chase.ncms.dao.FileInfoDao;

@Service("fileInfoService")
public class FileInfoServiceImpl extends EgovAbstractServiceImpl implements FileInfoService {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileInfoServiceImpl.class);

	@Resource(name="fileInfoDao")
	private FileInfoDao fileInfoDao;

	/**
	 * 파일 ID 생성
	 * @return
	 * @throws Exception
	 */
	public String getFileInfoId() throws Exception{
		return fileInfoDao.getFileInfoId();
	}

	/**
	 * 파일 정보 등록
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertFileInfo(HashMap<String, Object> map) throws Exception{
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("fileId"), ""))) {
			throw new Exception("FileInfoServiceImpl.insertFileInfo fileId 필수 값 누락");
		}
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("fileSeq"), ""))) {
			throw new Exception("FileInfoServiceImpl.insertFileInfo fileSeq 필수 값 누락");
		}

		return fileInfoDao.insertFileInfo(map);
	}

	/**
	 * 파일 정보 삭제
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteFileInfo(HashMap<String, Object> map) throws Exception{
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("fileId"), ""))) {
			throw new Exception("FileInfoServiceImpl.deleteFileInfo fileId 필수 값 누락");
		}

		return fileInfoDao.deleteFileInfo(map);
	}

	/**
	 * 파일 목록 조회
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getFileList(HashMap<String, Object> map) throws Exception{
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("fileId"), ""))) {
			throw new Exception("FileInfoServiceImpl.getFileInfo fileId 필수 값 누락");
		}

		return fileInfoDao.getFileList(map);
	}

	/**
	 * 파일 상세 정보
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getFileInfo(HashMap<String, Object> map) throws Exception{
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("fileId"), ""))) {
			throw new Exception("FileInfoServiceImpl.getFileInfo fileId 필수 값 누락");
		}
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("fileSeq"), ""))) {
			throw new Exception("FileInfoServiceImpl.getFileInfo fileSeq 필수 값 누락");
		}

		List<HashMap<String, Object>> resultList = this.getFileList(map);
		if(!resultList.isEmpty()) {
			return (HashMap<String, Object>)resultList.get(0);
		}

		return new HashMap<String, Object>();
	}
}