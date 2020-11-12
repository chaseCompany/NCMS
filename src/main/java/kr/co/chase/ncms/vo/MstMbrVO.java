package kr.co.chase.ncms.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 회원정보관리
 * @author Molra
 */
public class MstMbrVO implements Serializable {
	private static final long serialVersionUID = -858838578081269358L;

	private String mbrNo;
	private String mbrNm;
	private String gendCd;
	private String gendNm;
	private String age;
	private String juminNo1;
	private String frgCd;
	private String telNo1;
	private String telNo2;
	private String telNo3;
	private String jobCd;
	private String zipCd;
	private String addr1;
	private String addr2;
	private String mbrTpCd;
	private String drgUseCd;
	private String mrgCd;
	private String eduCd;
	private String rlgnCd;
	private String reqPathCd;
	private String medicCareCd;
	private String medicCareNm;
	private String disEtc;
	private String regUsrId;
	private String regDt;
	private String stpDt;
	private String fmlyRmk;
	private String rmk;
	private String mngUsrId;
	private String lstUpdId;
	private String lstUpdDt;
	private String stsCd;
	private String creId;
	private Timestamp creDt;
	private String updId;
	private Timestamp updDt;

	/**
	 * 회원정보 번호(ex:M20272772504833)
	 * @return
	 */
	public String getMbrNo() {
		return mbrNo;
	}
	/**
	 * 회원정보 번호(ex:M20272772504833)
	 * @param mbrNo
	 */
	public void setMbrNo(String mbrNo) {
		this.mbrNo = mbrNo;
	}
	/**
	 * 성명
	 * @return
	 */
	public String getMbrNm() {
		return mbrNm;
	}
	/**
	 * 성명
	 * @param mbrNm
	 */
	public void setMbrNm(String mbrNm) {
		this.mbrNm = mbrNm;
	}
	/**
	 * 성별
	 * @return
	 */
	public String getGendCd() {
		return gendCd;
	}
	/**
	 * 성별
	 * @param gendCd
	 */
	public void setGendCd(String gendCd) {
		this.gendCd = gendCd;
	}
	/**
	 * 성별명
	 * @return
	 */
	public String getGendNm() {
		return gendNm;
	}
	/**
	 * 성별명
	 * @param gendNm
	 */
	public void setGendNm(String gendNm) {
		this.gendNm = gendNm;
	}
	/**
	 * 연령
	 * @return
	 */
	public String getAge() {
		return age;
	}
	/**
	 * 연령
	 * @param age
	 */
	public void setAge(String age) {
		this.age = age;
	}
	/**
	 * 생년월일
	 * @return
	 */
	public String getJuminNo1() {
		return juminNo1;
	}
	/**
	 * 생년월일
	 * @param juminNo1
	 */
	public void setJuminNo1(String juminNo1) {
		this.juminNo1 = juminNo1;
	}
	/**
	 * 내/외국인 
	 * @return
	 */
	public String getFrgCd() {
		return frgCd;
	}
	/**
	 * 내/외국인
	 * @param frgCd
	 */
	public void setFrgCd(String frgCd) {
		this.frgCd = frgCd;
	}
	/**
	 * 연락처1
	 * @return
	 */
	public String getTelNo1() {
		return telNo1;
	}
	/**
	 * 연락처1
	 * @param telNo1
	 */
	public void setTelNo1(String telNo1) {
		this.telNo1 = telNo1;
	}
	/**
	 * 연락처2
	 * @return
	 */
	public String getTelNo2() {
		return telNo2;
	}
	/**
	 * 연락처2
	 * @param telNo2
	 */
	public void setTelNo2(String telNo2) {
		this.telNo2 = telNo2;
	}
	/**
	 * 연락처3
	 * @return
	 */
	public String getTelNo3() {
		return telNo3;
	}
	/**
	 * 연락처3
	 * @param telNo3
	 */
	public void setTelNo3(String telNo3) {
		this.telNo3 = telNo3;
	}
	/**
	 * 직업
	 * @return
	 */
	public String getJobCd() {
		return jobCd;
	}
	/**
	 * 직업
	 * @param jobCd
	 */
	public void setJobCd(String jobCd) {
		this.jobCd = jobCd;
	}
	/**
	 * 우편번호
	 * @return
	 */
	public String getZipCd() {
		return zipCd;
	}
	/**
	 * 우편번호
	 * @param zipCd
	 */
	public void setZipCd(String zipCd) {
		this.zipCd = zipCd;
	}
	/**
	 * 주소
	 * @return
	 */
	public String getAddr1() {
		return addr1;
	}
	/**
	 * 주소
	 * @param addr1
	 */
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	/**
	 * 상세주소
	 * @return
	 */
	public String getAddr2() {
		return addr2;
	}
	/**
	 * 상세주소
	 * @param addr2
	 */
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	/**
	 * 회원구분
	 * @return
	 */
	public String getMbrTpCd() {
		return mbrTpCd;
	}
	/**
	 * 회원구분
	 * @param mbrTpCd
	 */
	public void setMbrTpCd(String mbrTpCd) {
		this.mbrTpCd = mbrTpCd;
	}
	/**
	 * 약물사용자와의 관계
	 * @return
	 */
	public String getDrgUseCd() {
		return drgUseCd;
	}
	/**
	 * 약물사용자와의 관계
	 * @param drgUseCd
	 */
	public void setDrgUseCd(String drgUseCd) {
		this.drgUseCd = drgUseCd;
	}
	/**
	 * 결혼여부
	 * @return
	 */
	public String getMrgCd() {
		return mrgCd;
	}
	/**
	 * 결혼여부
	 * @param mrgCd
	 */
	public void setMrgCd(String mrgCd) {
		this.mrgCd = mrgCd;
	}
	/**
	 * 학력
	 * @return
	 */
	public String getEduCd() {
		return eduCd;
	}
	/**
	 * 학력
	 * @param eduCd
	 */
	public void setEduCd(String eduCd) {
		this.eduCd = eduCd;
	}
	/**
	 * 종교
	 * @return
	 */
	public String getRlgnCd() {
		return rlgnCd;
	}
	/**
	 * 종교
	 * @param rlgnCd
	 */
	public void setRlgnCd(String rlgnCd) {
		this.rlgnCd = rlgnCd;
	}
	/**
	 * 의뢰경로
	 * @return
	 */
	public String getReqPathCd() {
		return reqPathCd;
	}
	/**
	 * 의뢰경로
	 * @param reqPathCd
	 */
	public void setReqPathCd(String reqPathCd) {
		this.reqPathCd = reqPathCd;
	}
	/**
	 * 의료보장
	 * @return
	 */
	public String getMedicCareCd() {
		return medicCareCd;
	}
	/**
	 * 의료보장
	 * @param medicCareCd
	 */
	public void setMedicCareCd(String medicCareCd) {
		this.medicCareCd = medicCareCd;
	}
	/**
	 * 의료보장 명
	 * @return
	 */
	public String getMedicCareNm() {
		return medicCareNm;
	}
	/**
	 * 의료보장 명
	 * @param medicCareNm
	 */
	public void setMedicCareNm(String medicCareNm) {
		this.medicCareNm = medicCareNm;
	}
	/**
	 * 기타장애
	 * @return
	 */
	public String getDisEtc() {
		return disEtc;
	}
	/**
	 * 기타장애
	 * @param disEtc
	 */
	public void setDisEtc(String disEtc) {
		this.disEtc = disEtc;
	}
	/**
	 * 최초등록자
	 * @return
	 */
	public String getRegUsrId() {
		return regUsrId;
	}
	/**
	 * 최초등록자
	 * @param regUsrId
	 */
	public void setRegUsrId(String regUsrId) {
		this.regUsrId = regUsrId;
	}
	/**
	 * 최초등록일자
	 * @return
	 */
	public String getRegDt() {
		return regDt;
	}
	/**
	 * 최초등록일자
	 * @param regDt
	 */
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	/**
	 * 퇴록일자
	 * @return
	 */
	public String getStpDt() {
		return stpDt;
	}
	/**
	 * 퇴록일자
	 * @param stpDt
	 */
	public void setStpDt(String stpDt) {
		this.stpDt = stpDt;
	}
	/**
	 * 가족사항
	 * @return
	 */
	public String getFmlyRmk() {
		return fmlyRmk;
	}
	/**
	 * 가족사항
	 * @param fmlyRmk
	 */
	public void setFmlyRmk(String fmlyRmk) {
		this.fmlyRmk = fmlyRmk;
	}
	/**
	 * 메모
	 * @return
	 */
	public String getRmk() {
		return rmk;
	}
	/**
	 * 메모
	 * @param rmk
	 */
	public void setRmk(String rmk) {
		this.rmk = rmk;
	}
	/**
	 * 사례관리자
	 * @return
	 */
	public String getMngUsrId() {
		return mngUsrId;
	}
	/**
	 * 사례관리자
	 * @param mngUsrId
	 */
	public void setMngUsrId(String mngUsrId) {
		this.mngUsrId = mngUsrId;
	}
	/**
	 * 최종수정자
	 * @return
	 */
	public String getLstUpdId() {
		return lstUpdId;
	}
	/**
	 * 최종수정자
	 * @param lstUpdId
	 */
	public void setLstUpdId(String lstUpdId) {
		this.lstUpdId = lstUpdId;
	}
	/**
	 * 최종수정일자
	 * @return
	 */
	public String getLstUpdDt() {
		return lstUpdDt;
	}
	/**
	 * 최종수정일자
	 * @param lstUpdDt
	 */
	public void setLstUpdDt(String lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	/**
	 * 퇴록코드
	 * @return
	 */
	public String getStsCd() {
		return stsCd;
	}
	/**
	 * 퇴록코드
	 * @param stsCd
	 */
	public void setStsCd(String stsCd) {
		this.stsCd = stsCd;
	}
	/**
	 * 생성자
	 * @return
	 */
	public String getCreId() {
		return creId;
	}
	/**
	 * 생성자
	 * @param creId
	 */
	public void setCreId(String creId) {
		this.creId = creId;
	}
	/**
	 * 생성일자
	 * @return
	 */
	public Timestamp getCreDt() {
		return creDt;
	}
	/**
	 * 생성일자
	 * @param creDt
	 */
	public void setCreDt(Timestamp creDt) {
		this.creDt = creDt;
	}
	/**
	 * 수정자
	 * @return
	 */
	public String getUpdId() {
		return updId;
	}
	/**
	 * 수정자
	 * @param updId
	 */
	public void setUpdId(String updId) {
		this.updId = updId;
	}
	/**
	 * 수정일자
	 * @return
	 */
	public Timestamp getUpdDt() {
		return updDt;
	}
	/**
	 * 수정일자
	 * @param updDt
	 */
	public void setUpdDt(Timestamp updDt) {
		this.updDt = updDt;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}