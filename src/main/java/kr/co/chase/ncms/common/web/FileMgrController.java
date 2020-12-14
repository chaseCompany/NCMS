package kr.co.chase.ncms.common.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.chase.ncms.common.EgovBrowserUtil;
import kr.co.chase.ncms.common.service.FileInfoService;

@Controller
public class FileMgrController {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileMgrController.class);

	@Resource(name="fileInfoService")
	private FileInfoService fileInfoService;

	/**
	 * 파일 다운로드
	 * @param map
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/fileDown.do")
	public void fileDown(@RequestParam HashMap<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("fileId"), ""))) {
			throw new Exception("fileId 필수 값 누락");
		}
		if("".equals(StringUtils.defaultIfEmpty((String)map.get("fileSeq"), ""))) {
			throw new Exception("fileSeq 필수 값 누락");
		}

		HashMap<String, Object> fileInfo = fileInfoService.getFileInfo(map);
		if(!fileInfo.isEmpty()) {
			String orignlFileNm = StringUtils.defaultIfEmpty((String)fileInfo.get("ORIGNL_FILE_NM"), "");
			String fileStreCours = StringUtils.defaultIfEmpty((String)fileInfo.get("FILE_STRE_COURS"), "");
			String streFileNm = StringUtils.defaultIfEmpty((String)fileInfo.get("STRE_FILE_NM"), "");

			File uFile = new File(fileStreCours, streFileNm);
			long fSize = uFile.length();

			if (fSize > 0) {
				String mimetype = "application/x-msdownload";

				String userAgent = request.getHeader("User-Agent");
				HashMap<String,String> result = EgovBrowserUtil.getBrowser(userAgent);
				if ( !EgovBrowserUtil.MSIE.equals(result.get(EgovBrowserUtil.TYPEKEY)) ) {
					mimetype = "application/x-stuff";
				}

				String contentDisposition = EgovBrowserUtil.getDisposition(orignlFileNm, userAgent, "UTF-8");
				response.setContentType(mimetype);
				response.setHeader("Content-Disposition", contentDisposition);
				response.setContentLengthLong(fSize);

				BufferedInputStream in = null;
				BufferedOutputStream out = null;

				try {
					in = new BufferedInputStream(new FileInputStream(uFile));
					out = new BufferedOutputStream(response.getOutputStream());

					FileCopyUtils.copy(in, out);
					out.flush();
				} catch (IOException ex) {
					// 다음 Exception 무시 처리
					// Connection reset by peer: socket write error
					LOGGER.error("IO Exception", ex);
				} finally {
					out.close();
					in.close();
				}
			}else {
				throw new Exception("Could not get file name:" + orignlFileNm);
/*
				response.setContentType("application/x-msdownload");

				PrintWriter printwriter = response.getWriter();

				printwriter.println("<html>");
				printwriter.println("<br><br><br><h2>Could not get file name:<br>" + orignlFileNm + "</h2>");
				printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
				printwriter.println("<br><br><br>&copy; webAccess");
				printwriter.println("</html>");

				printwriter.flush();
				printwriter.close();
*/
			}
		}
	}
}