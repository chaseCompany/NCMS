package kr.co.chase.ncms.common.util;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import egovframework.rte.fdl.property.EgovPropertyService;
import kr.co.chase.ncms.common.service.FileInfoService;

@Component("FileManagerUtil")
public class FileManagerUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileManagerUtil.class);
	public static final int BUFF_SIZE = 2048;

	@Resource(name="propertiesService")
	protected EgovPropertyService propertiesService;

	@Resource(name="fileInfoService")
	private FileInfoService fileInfoService;

	/**
	 * 파일 유무 확인
	 * @param files
	 * @return
	 */
	public boolean checkFiles(Map<String, MultipartFile> files) {
		boolean flag = false;

		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		MultipartFile file;

		while(itr.hasNext()){
			Entry<String, MultipartFile> entry = itr.next();

			file = entry.getValue();
			String orginFileName = file.getOriginalFilename();

			if(file.getSize() > 0) { // && "".equals(orginFileName)
				return true;
			}
		}

		return flag;
	}

	/**
	 * 첨부파일에 대한 목록 정보를 취득한다.
	 * @param files 파일 객체
	 * @param KeyStr 접속 경로
	 * @param creId 등록자
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> parseFileInf(Map<String, MultipartFile> files, String KeyStr, String creId) throws Exception {
		HashMap<String, Object> result = new HashMap<String, Object>();

		int fileKey = 1;
		String timeStamp = this.getTimeStamp();
		String storePathString = propertiesService.getString("uploadFile") + this.getDirTime(timeStamp);
		String fileId = fileInfoService.getFileInfoId();

		File saveFolder = new File(WebUtil.filePathBlackList(storePathString));

		if (!saveFolder.exists() || saveFolder.isFile()) {
			if (saveFolder.mkdirs()){
				LOGGER.debug("[file.mkdirs] saveFolder : Creation Success ");
			}else{
				LOGGER.error("[file.mkdirs] saveFolder : Creation Fail ");
			}
		}

		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		MultipartFile file;
		String filePath = "";
		List<HashMap<String, Object>> fileList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> fileMap;

		while(itr.hasNext()){
			Entry<String, MultipartFile> entry = itr.next();

			file = entry.getValue();
			String orginFileName = file.getOriginalFilename();

			// 원 파일명이 없는 경우 처리 (첨부가 되지 않은 input file type)
			if ("".equals(orginFileName)) {
				continue;
			}

			int index = orginFileName.lastIndexOf(".");
			String fileExt = orginFileName.substring(index + 1);
			String newName = KeyStr + "_" + timeStamp + fileKey;

			if (!"".equals(orginFileName)) {
				filePath = storePathString + File.separator + newName;
				file.transferTo(new File(WebUtil.filePathBlackList(filePath)));
			}

			fileMap = new HashMap<String, Object>();
			fileMap.put("fileId", fileId);								// 파일 고유키
			fileMap.put("fileSeq", String.valueOf(fileKey));			// 동시등록 파일 순서
			fileMap.put("fileExtsn", fileExt);							// 파일 확장자
			fileMap.put("fileStreCours", storePathString);				// 파일 저장 경로
			fileMap.put("fileMg", Long.toString(file.getSize()));		// 파일 사이즈
			fileMap.put("orignlFileNm", orginFileName);					// 원 파일명
			fileMap.put("streFileNm", newName);							// 서버저장 파일명
			fileMap.put("creId", creId);								// 등록자

			fileList.add(fileMap);

			fileKey++;
		}

		result.put("fileId", fileId);
		result.put("fileList", fileList);

		return result;
	}

	/**
	 * 파일 삭제
	 * @param fileId
	 * @return
	 * @throws Exception
	 */
	public int deleteFile(String fileId) throws Exception{
		int result = 0;

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fileId", fileId);
		List<HashMap<String, Object>> files = fileInfoService.getFileList(paramMap);

		if(!files.isEmpty() && files.size() > 0) {
			for(HashMap<String, Object> fileInfo : files) {
				String fileStreCours = StringUtils.defaultIfEmpty((String)fileInfo.get("FILE_STRE_COURS"), "");
				String streFileNm = StringUtils.defaultIfEmpty((String)fileInfo.get("STRE_FILE_NM"), "");

				if(!"".equals(fileStreCours) && !"".equals(streFileNm)) {
					try {
						File file = new File(fileStreCours + streFileNm);
						if( file.exists() ){
							if(file.delete()){
								LOGGER.info("파일삭제 성공 : " + streFileNm);
							}else{
								LOGGER.info("파일삭제 실패 : " + streFileNm);
							}
						}
					}catch (Exception e) {
						LOGGER.error("FileManagerUtil.deleteFile fileId : " + fileId + " delete ERROR", e);
					}
				}
			}
		}

		return result;
	}

	/**
	 * 공통 컴포넌트 utl.fcc 패키지와 Dependency제거를 위해 내부 메서드로 추가 정의함
	 * 응용어플리케이션에서 고유값을 사용하기 위해 시스템에서17자리의TIMESTAMP값을 구하는 기능
	 * @param
	 * @return Timestamp 값
	 * @see
	 */
	private static String getTimeStamp() {
		String rtnStr = null;

		// 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
		String pattern = "yyyyMMddhhmmssSSS";

		SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
		Timestamp ts = new Timestamp(System.currentTimeMillis());

		rtnStr = sdfCurrent.format(ts.getTime());

		return rtnStr;
	}

	/**
	 * 년/월/일 식 경로
	 * @param str
	 * @return
	 */
	private static String getDirTime(String str) {
		if("".equals(StringUtils.defaultIfEmpty(str, ""))) {
			return str;
		}
		if(str.length() < 8) {
			return str;
		}

		return str.substring(0, 4) + "\\" + str.substring(4, 6) + "\\" + str.substring(6, 8) + "\\";
	}
}