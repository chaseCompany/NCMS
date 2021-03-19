package kr.co.chase.nrds.client.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.co.chase.ncms.common.ConstantObject;
import kr.co.chase.nrds.client.service.ClientService;
import kr.co.chase.nrds.dao.EdMbrDao;

@Service("clientService")
public class ClientServiceImpl extends EgovAbstractServiceImpl implements ClientService {
	@Resource(name="edMbrDao")
	private EdMbrDao edMbrDao;

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
}