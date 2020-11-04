package kr.co.chase.ncms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 사정평가정보
 * @author Molra
 */
public class CslAssVO implements Serializable {
	private static final long serialVersionUID = -858838578081269358L;

	private String mbrNo;					// 회원정보 번호(ex:M20272772504833)
	private String fstDrugCd;				// 최초사용약물
	private String fstDrug;					// 최초사용약물 입력
	private String mainDrugCd;				// 주요사용약물 코드
	private String mainDrug;				// 주요사용약물 입력
	private String fstAge;					// 최초사용시기
	private String useTerm;					// 사용기간
	private String useFrqCd;				// 사용빈도
	private String useCauCd;				// 사용원인
	private String useCauEtc;				// 사용원인 기타
	private String lawPbmCd;				// 약물관련 법적문제
	private String lawPbmEtc;				// 약물관련 법적문제 기타
	private String physPbm;					// 신체적 건강문제
	private String sprtPbmCd;				// 정신적 건강문제
	private String sprtPbmEtc;				// 정신적 건강문제 기타
	private String msrInf;					// 
	private String lstAge;					// 마지막사용시기
	private String prsnCd;					// 성격
	private String emtnCd;					// 정서-심리
	private String actnCd;					// 행동
	private String fmlyCd;					// 가족
	private String itRlCd;					// 대인관계
	private String miEtc;					// 기타
	private String svrRcgDgr;				// 심각성인식정도
	private String expEfc;					// 기대효과
	private String miMemo;					// 메모
	private String creId;					// 생성자
	private Timestamp creDt;				// 생성일자
	private String updId;					// 수정자
	private Timestamp updDt;				// 수정일자
	private List<CslAssEvlVO> assEvlList;	// 평가도구

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
	 * 최초사용약물
	 * @return
	 */
	public String getFstDrugCd() {
		return fstDrugCd;
	}
	/**
	 * 최초사용약물
	 * @param fstDrugCd
	 */
	public void setFstDrugCd(String fstDrugCd) {
		this.fstDrugCd = fstDrugCd;
	}
	/**
	 * 최초사용약물 입력
	 * @return
	 */
	public String getFstDrug() {
		return fstDrug;
	}
	/**
	 * 최초사용약물 입력
	 * @param fstDrug
	 */
	public void setFstDrug(String fstDrug) {
		this.fstDrug = fstDrug;
	}
	/**
	 * 주요사용약물 코드
	 * @return
	 */
	public String getMainDrugCd() {
		return mainDrugCd;
	}
	/**
	 * 주요사용약물 코드
	 * @param mainDrugCd
	 */
	public void setMainDrugCd(String mainDrugCd) {
		this.mainDrugCd = mainDrugCd;
	}
	/**
	 * 주요사용약물 입력
	 * @return
	 */
	public String getMainDrug() {
		return mainDrug;
	}
	/**
	 * 주요사용약물 입력
	 * @param mainDrug
	 */
	public void setMainDrug(String mainDrug) {
		this.mainDrug = mainDrug;
	}
	/**
	 * 최초사용시기
	 * @return
	 */
	public String getFstAge() {
		return fstAge;
	}
	/**
	 * 최초사용시기
	 * @param fstAge
	 */
	public void setFstAge(String fstAge) {
		this.fstAge = fstAge;
	}
	/**
	 * 사용기간
	 * @return
	 */
	public String getUseTerm() {
		return useTerm;
	}
	/**
	 * 사용기간
	 * @param useTerm
	 */
	public void setUseTerm(String useTerm) {
		this.useTerm = useTerm;
	}
	/**
	 * 사용빈도
	 * @return
	 */
	public String getUseFrqCd() {
		return useFrqCd;
	}
	/**
	 * 사용빈도
	 * @param useFrqCd
	 */
	public void setUseFrqCd(String useFrqCd) {
		this.useFrqCd = useFrqCd;
	}
	/**
	 * 사용원인
	 * @return
	 */
	public String getUseCauCd() {
		return useCauCd;
	}
	/**
	 * 사용원인
	 * @param useCauCd
	 */
	public void setUseCauCd(String useCauCd) {
		this.useCauCd = useCauCd;
	}
	/**
	 * 사용원인 기타
	 * @return
	 */
	public String getUseCauEtc() {
		return useCauEtc;
	}
	/**
	 * 사용원인 기타
	 * @param useCauEtc
	 */
	public void setUseCauEtc(String useCauEtc) {
		this.useCauEtc = useCauEtc;
	}
	/**
	 * 약물관련 법적문제
	 * @return
	 */
	public String getLawPbmCd() {
		return lawPbmCd;
	}
	/**
	 * 약물관련 법적문제
	 * @param lawPbmCd
	 */
	public void setLawPbmCd(String lawPbmCd) {
		this.lawPbmCd = lawPbmCd;
	}
	/**
	 * 약물관련 법적문제 기타
	 * @return
	 */
	public String getLawPbmEtc() {
		return lawPbmEtc;
	}
	/**
	 * 약물관련 법적문제 기타
	 * @param lawPbmEtc
	 */
	public void setLawPbmEtc(String lawPbmEtc) {
		this.lawPbmEtc = lawPbmEtc;
	}
	/**
	 * 신체적 건강문제
	 * @return
	 */
	public String getPhysPbm() {
		return physPbm;
	}
	/**
	 * 신체적 건강문제
	 * @param physPbm
	 */
	public void setPhysPbm(String physPbm) {
		this.physPbm = physPbm;
	}
	/**
	 * 정신적 건강문제
	 * @return
	 */
	public String getSprtPbmCd() {
		return sprtPbmCd;
	}
	/**
	 * 정신적 건강문제
	 * @param sprtPbmCd
	 */
	public void setSprtPbmCd(String sprtPbmCd) {
		this.sprtPbmCd = sprtPbmCd;
	}
	/**
	 * 정신적 건강문제 기타
	 * @return
	 */
	public String getSprtPbmEtc() {
		return sprtPbmEtc;
	}
	/**
	 * 정신적 건강문제 기타
	 * @param sprtPbmEtc
	 */
	public void setSprtPbmEtc(String sprtPbmEtc) {
		this.sprtPbmEtc = sprtPbmEtc;
	}
	public String getMsrInf() {
		return msrInf;
	}
	public void setMsrInf(String msrInf) {
		this.msrInf = msrInf;
	}
	/**
	 * 마지막사용시기
	 * @return
	 */
	public String getLstAge() {
		return lstAge;
	}
	/**
	 * 마지막사용시기
	 * @param lstAge
	 */
	public void setLstAge(String lstAge) {
		this.lstAge = lstAge;
	}
	/**
	 * 성격
	 * @return
	 */
	public String getPrsnCd() {
		return prsnCd;
	}
	/**
	 * 성격
	 * @param prsnCd
	 */
	public void setPrsnCd(String prsnCd) {
		this.prsnCd = prsnCd;
	}
	/**
	 * 정서-심리
	 * @return
	 */
	public String getEmtnCd() {
		return emtnCd;
	}
	/**
	 * 정서-심리
	 * @param emtnCd
	 */
	public void setEmtnCd(String emtnCd) {
		this.emtnCd = emtnCd;
	}
	/**
	 * 행동
	 * @return
	 */
	public String getActnCd() {
		return actnCd;
	}
	/**
	 * 행동
	 * @param actnCd
	 */
	public void setActnCd(String actnCd) {
		this.actnCd = actnCd;
	}
	/**
	 * 가족
	 * @return
	 */
	public String getFmlyCd() {
		return fmlyCd;
	}
	/**
	 * 가족
	 * @param fmlyCd
	 */
	public void setFmlyCd(String fmlyCd) {
		this.fmlyCd = fmlyCd;
	}
	/**
	 * 대인관계
	 * @return
	 */
	public String getItRlCd() {
		return itRlCd;
	}
	/**
	 * 대인관계
	 * @param itRlCd
	 */
	public void setItRlCd(String itRlCd) {
		this.itRlCd = itRlCd;
	}
	/**
	 * 기타
	 * @return
	 */
	public String getMiEtc() {
		return miEtc;
	}
	/**
	 * 기타
	 * @param miEtc
	 */
	public void setMiEtc(String miEtc) {
		this.miEtc = miEtc;
	}
	/**
	 * 심각성인식정도
	 * @return
	 */
	public String getSvrRcgDgr() {
		return svrRcgDgr;
	}
	/**
	 * 심각성인식정도
	 * @param svrRcgDgr
	 */
	public void setSvrRcgDgr(String svrRcgDgr) {
		this.svrRcgDgr = svrRcgDgr;
	}
	/**
	 * 기대효과
	 * @return
	 */
	public String getExpEfc() {
		return expEfc;
	}
	/**
	 * 기대효과
	 * @param expEfc
	 */
	public void setExpEfc(String expEfc) {
		this.expEfc = expEfc;
	}
	/**
	 * 메모
	 * @return
	 */
	public String getMiMemo() {
		return miMemo;
	}
	/**
	 * 메모
	 */
	public void setMiMemo(String miMemo) {
		this.miMemo = miMemo;
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
	/**
	 * 평가도구
	 * @return
	 */
	public List<CslAssEvlVO> getAssEvlList() {
		return assEvlList;
	}
	/**
	 * 평가도구
	 * @param assEvlList
	 */
	public void setAssEvlList(List<CslAssEvlVO> assEvlList) {
		this.assEvlList = assEvlList;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}