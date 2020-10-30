package kr.co.chase.ncms.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SysUsrVO implements Serializable {
	private static final long serialVersionUID = -858838578081269359L;

	private String loginFlag;				// 로그인 유무
	private String usrId;					// 사용자 ID
	private String usrNm;					// 사용자명
	private String passwd;					// 비밀번호
	private String roleCd;					// 사용자 권한
	private String siteCd;					// 소속 본부
	private String useYn;					// 사용여부
	private String regDt;					// 등록일시
	private Timestamp creDt;				// 생성일자
	private String creId;					// 생성자
	private Timestamp updDt;				// 수정일자
	private String updId;					// 수정자

	/**
	 * 로그인 유무
	 */
	public String getLoginFlag() {
		return loginFlag;
	}
	/**
	 * 로그인 유무
	 */
	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}
	/**
	 * 사용자 ID
	 */
	public String getUsrId() {
		return usrId;
	}
	/**
	 * 사용자 ID
	 */
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	/**
	 * 사용자명
	 */
	public String getUsrNm() {
		return usrNm;
	}
	/**
	 * 사용자명
	 */
	public void setUsrNm(String usrNm) {
		this.usrNm = usrNm;
	}
	/**
	 * 비밀번호
	 */
	public String getPasswd() {
		return passwd;
	}
	/**
	 * 비밀번호
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	/**
	 * 사용자 권한
	 */
	public String getRoleCd() {
		return roleCd;
	}
	/**
	 * 사용자 권한
	 */
	public void setRoleCd(String roleCd) {
		this.roleCd = roleCd;
	}
	/**
	 * 소속 본부
	 */
	public String getSiteCd() {
		return siteCd;
	}
	/**
	 * 소속 본부
	 */
	public void setSiteCd(String siteCd) {
		this.siteCd = siteCd;
	}
	/**
	 * 사용여부
	 */
	public String getUseYn() {
		return useYn;
	}
	/**
	 * 사용여부
	 */
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	/**
	 * 등록일시
	 */
	public String getRegDt() {
		return regDt;
	}
	/**
	 * 등록일시
	 */
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	/**
	 * 생성일자
	 */
	public Timestamp getCreDt() {
		return creDt;
	}
	/**
	 * 생성일자
	 */
	public void setCreDt(Timestamp creDt) {
		this.creDt = creDt;
	}
	/**
	 * 생성자
	 */
	public String getCreId() {
		return creId;
	}
	/**
	 * 생성자
	 */
	public void setCreId(String creId) {
		this.creId = creId;
	}
	/**
	 * 수정일자
	 */
	public Timestamp getUpdDt() {
		return updDt;
	}
	/**
	 * 수정일자
	 */
	public void setUpdDt(Timestamp updDt) {
		this.updDt = updDt;
	}
	/**
	 * 수정자
	 */
	public String getUpdId() {
		return updId;
	}
	/**
	 * 수정자
	 */
	public void setUpdId(String updId) {
		this.updId = updId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
