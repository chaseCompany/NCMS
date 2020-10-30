package kr.co.chase.ncms.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("sysUsrDao")
public class SysUsrDao extends EgovAbstractMapper {
	public List<HashMap<String, Object>> getSysUsrList(HashMap<String, Object> map) throws SQLException{
		return selectList("sysUsr.getSysUsrList", map);
	}

	public HashMap<String, Object> getSysUsrInfo(HashMap<String, Object> map) throws SQLException{
		return selectOne("sysUsr.getSysUsrInfo", map);
	}
}