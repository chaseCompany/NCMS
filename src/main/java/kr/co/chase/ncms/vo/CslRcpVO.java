package kr.co.chase.ncms.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class CslRcpVO implements Serializable {
	private static final long serialVersionUID = -858838578081269358L;

	private String rcpNo;					// 일반 상담 번호
	private String cslDt;					// 상담 일자
	private String cslFmTm;					// 상담 시작시간
	private String cslToTm;					// 상담 종료시간
	private int cslTermTm;					// 소요시간
	private String cslId;					// 상담사ID
	private String cslNm;					// 상담사명
	private String ifpGbCd;					// 정보제공자/본인여부
	private String ifpGbEtc;				// 정보제공자/본인여부(기타)
	private String ifpMbrNo;				// 정보제공자 회원정보 번호
	private String ifpNm;					// 정보제공자 성명
	private String ifpGendCd;				// 정보제공자 성별
	private String ifpAge;					// 정보제공자 연령
	private String ifpTelNo1;				// 정보제공자 전화번호1
	private String ifpTelNo2;				// 정보제공자 전화번호2
	private String ifpTelNo3;				// 정보제공자 전화번호3
	private String ifpAreaCd;				// 정보제공자 지역 코드
	private String ifpJobCd;				// 정보제공자 직업
	private String tgpMbrNo;				// 대상자 회원정보 번호
	private String tgpNm;					// 대상자 성명
	private String tgpGendCd;				// 대상자 성별
	private String tgpAge;					// 대상자 연령
	private String tgpTelNo1;				// 대상자 전화번호1
	private String tgpTelNo2;				// 대상자 전화번호2
	private String tgpTelNo3;				// 대상자 전화번호3
	private String tgpAreaCd;				// 대상자 지역 코드
	private String tgpFrgCd;				// 대상자 내/외국인
	private String tgpJobCd;				// 대상자 직업
	private String ifPathCd;				// 정보취득경로
	private String pbmKndCd;				// 문제종류
	private String cslTpCd;					// 상담유형
	private String cslHisCd;				// 상담이력코드
	private String fstDrugCd;				// 최초사용약물
	private String mainDrugCd;				// 주요사용약물 분류
	private String mainDrug;				// 주요사용약물
	private String cslCtnt;					// 상담내용
	private String mjrMngCd;				// 주요조치
	private int astSco;						// ASSIST 점수
	private String rskaTpCd;				// 위기분류척도 Rating A-위험성
	private String rskbTpCd;				// 위기분류척도 Rating B-지지체계
	private String rskcTpCd;				// 위기분류척도 Rating C-협조능력
	private int rskSco;						// 위기분류척도 점수
	private String creId;					// 생성자
	private Timestamp creDt;				// 생성일자
	private String updId;					// 수정자
	private Timestamp updDt;				// 수정일자
	private String ifpAreaEtc;				// 정보제공자 지역(기타)
	private String tgpAreaEtc;				// 대상자 지역(기타)

	/**
	 * 일반 상담 번호
	 */
	public String getRcpNo() {
		return rcpNo;
	}
	/**
	 * 일반 상담 번호
	 */
	public void setRcpNo(String rcpNo) {
		this.rcpNo = rcpNo;
	}
	/**
	 * 상담 일자
	 */
	public String getCslDt() {
		return cslDt;
	}
	/**
	 * 상담 일자
	 */
	public void setCslDt(String cslDt) {
		this.cslDt = cslDt;
	}
	/**
	 * 상담 시작시간
	 */
	public String getCslFmTm() {
		return cslFmTm;
	}
	/**
	 * 상담 시작시간
	 */
	public void setCslFmTm(String cslFmTm) {
		this.cslFmTm = cslFmTm;
	}
	/**
	 * 상담 종료시간
	 */
	public String getCslToTm() {
		return cslToTm;
	}
	/**
	 * 상담 종료시간
	 */
	public void setCslToTm(String cslToTm) {
		this.cslToTm = cslToTm;
	}
	/**
	 * 소요시간
	 */
	public int getCslTermTm() {
		return cslTermTm;
	}
	/**
	 * 소요시간
	 */
	public void setCslTermTm(int cslTermTm) {
		this.cslTermTm = cslTermTm;
	}
	/**
	 * 상담사ID
	 */
	public String getCslId() {
		return cslId;
	}
	/**
	 * 상담사ID
	 */
	public void setCslId(String cslId) {
		this.cslId = cslId;
	}
	/**
	 * 상담사명
	 */
	public String getCslNm() {
		return cslNm;
	}
	/**
	 * 상담사명
	 */
	public void setCslNm(String cslNm) {
		this.cslNm = cslNm;
	}
	/**
	 * 정보제공자/본인여부
	 */
	public String getIfpGbCd() {
		return ifpGbCd;
	}
	/**
	 * 정보제공자/본인여부
	 */
	public void setIfpGbCd(String ifpGbCd) {
		this.ifpGbCd = ifpGbCd;
	}
	/**
	 * 정보제공자/본인여부(기타)
	 */
	public String getIfpGbEtc() {
		return ifpGbEtc;
	}
	/**
	 * 정보제공자/본인여부(기타)
	 */
	public void setIfpGbEtc(String ifpGbEtc) {
		this.ifpGbEtc = ifpGbEtc;
	}
	/**
	 * 정보제공자 회원정보 번호
	 */
	public String getIfpMbrNo() {
		return ifpMbrNo;
	}
	/**
	 * 정보제공자 회원정보 번호
	 */
	public void setIfpMbrNo(String ifpMbrNo) {
		this.ifpMbrNo = ifpMbrNo;
	}
	/**
	 * 정보제공자 성명
	 */
	public String getIfpNm() {
		return ifpNm;
	}
	/**
	 * 정보제공자 성명
	 */
	public void setIfpNm(String ifpNm) {
		this.ifpNm = ifpNm;
	}
	/**
	 * 정보제공자 성별
	 */
	public String getIfpGendCd() {
		return ifpGendCd;
	}
	/**
	 * 정보제공자 성별
	 */
	public void setIfpGendCd(String ifpGendCd) {
		this.ifpGendCd = ifpGendCd;
	}
	/**
	 * 정보제공자 연령
	 */
	public String getIfpAge() {
		return ifpAge;
	}
	/**
	 * 정보제공자 연령
	 */
	public void setIfpAge(String ifpAge) {
		this.ifpAge = ifpAge;
	}
	/**
	 * 정보제공자 전화번호1
	 */
	public String getIfpTelNo1() {
		return ifpTelNo1;
	}
	/**
	 * 정보제공자 전화번호1
	 */
	public void setIfpTelNo1(String ifpTelNo1) {
		this.ifpTelNo1 = ifpTelNo1;
	}
	/**
	 * 정보제공자 전화번호2
	 */
	public String getIfpTelNo2() {
		return ifpTelNo2;
	}
	/**
	 * 정보제공자 전화번호2
	 */
	public void setIfpTelNo2(String ifpTelNo2) {
		this.ifpTelNo2 = ifpTelNo2;
	}
	/**
	 * 정보제공자 전화번호3
	 */
	public String getIfpTelNo3() {
		return ifpTelNo3;
	}
	/**
	 * 정보제공자 전화번호3
	 */
	public void setIfpTelNo3(String ifpTelNo3) {
		this.ifpTelNo3 = ifpTelNo3;
	}
	/**
	 * 정보제공자 지역 코드
	 */
	public String getIfpAreaCd() {
		return ifpAreaCd;
	}
	/**
	 * 정보제공자 지역 코드
	 */
	public void setIfpAreaCd(String ifpAreaCd) {
		this.ifpAreaCd = ifpAreaCd;
	}
	/**
	 * 정보제공자 직업
	 */
	public String getIfpJobCd() {
		return ifpJobCd;
	}
	/**
	 * 정보제공자 직업
	 */
	public void setIfpJobCd(String ifpJobCd) {
		this.ifpJobCd = ifpJobCd;
	}
	/**
	 * 대상자 회원정보 번호
	 */
	public String getTgpMbrNo() {
		return tgpMbrNo;
	}
	/**
	 * 대상자 회원정보 번호
	 */
	public void setTgpMbrNo(String tgpMbrNo) {
		this.tgpMbrNo = tgpMbrNo;
	}
	/**
	 * 대상자 성명
	 */
	public String getTgpNm() {
		return tgpNm;
	}
	/**
	 * 대상자 성명
	 */
	public void setTgpNm(String tgpNm) {
		this.tgpNm = tgpNm;
	}
	/**
	 * 대상자 성별
	 */
	public String getTgpGendCd() {
		return tgpGendCd;
	}
	/**
	 * 대상자 성별
	 */
	public void setTgpGendCd(String tgpGendCd) {
		this.tgpGendCd = tgpGendCd;
	}
	/**
	 * 대상자 연령
	 */
	public String getTgpAge() {
		return tgpAge;
	}
	/**
	 * 대상자 연령
	 */
	public void setTgpAge(String tgpAge) {
		this.tgpAge = tgpAge;
	}
	/**
	 * 대상자 전화번호1
	 */
	public String getTgpTelNo1() {
		return tgpTelNo1;
	}
	/**
	 * 대상자 전화번호1
	 */
	public void setTgpTelNo1(String tgpTelNo1) {
		this.tgpTelNo1 = tgpTelNo1;
	}
	/**
	 * 대상자 전화번호2
	 */
	public String getTgpTelNo2() {
		return tgpTelNo2;
	}
	/**
	 * 대상자 전화번호2
	 */
	public void setTgpTelNo2(String tgpTelNo2) {
		this.tgpTelNo2 = tgpTelNo2;
	}
	/**
	 * 대상자 전화번호3
	 */
	public String getTgpTelNo3() {
		return tgpTelNo3;
	}
	/**
	 * 대상자 전화번호3
	 */
	public void setTgpTelNo3(String tgpTelNo3) {
		this.tgpTelNo3 = tgpTelNo3;
	}
	/**
	 * 대상자 지역 코드
	 */
	public String getTgpAreaCd() {
		return tgpAreaCd;
	}
	/**
	 * 대상자 지역 코드
	 */
	public void setTgpAreaCd(String tgpAreaCd) {
		this.tgpAreaCd = tgpAreaCd;
	}
	/**
	 * 대상자 내/외국인
	 */
	public String getTgpFrgCd() {
		return tgpFrgCd;
	}
	/**
	 * 대상자 내/외국인
	 */
	public void setTgpFrgCd(String tgpFrgCd) {
		this.tgpFrgCd = tgpFrgCd;
	}
	/**
	 * 대상자 직업
	 */
	public String getTgpJobCd() {
		return tgpJobCd;
	}
	/**
	 * 대상자 직업
	 */
	public void setTgpJobCd(String tgpJobCd) {
		this.tgpJobCd = tgpJobCd;
	}
	/**
	 * 정보취득경로
	 */
	public String getIfPathCd() {
		return ifPathCd;
	}
	/**
	 * 정보취득경로
	 */
	public void setIfPathCd(String ifPathCd) {
		this.ifPathCd = ifPathCd;
	}
	/**
	 * 주호소문제
	 */
	public String getPbmKndCd() {
		return pbmKndCd;
	}
	/**
	 * 주호소문제
	 */
	public void setPbmKndCd(String pbmKndCd) {
		this.pbmKndCd = pbmKndCd;
	}
	/**
	 * 상담유형
	 */
	public String getCslTpCd() {
		return cslTpCd;
	}
	/**
	 * 상담유형
	 */
	public void setCslTpCd(String cslTpCd) {
		this.cslTpCd = cslTpCd;
	}
	/**
	 * 상담이력코드
	 */
	public String getCslHisCd() {
		return cslHisCd;
	}
	/**
	 * 상담이력코드
	 */
	public void setCslHisCd(String cslHisCd) {
		this.cslHisCd = cslHisCd;
	}
	/**
	 * 최초사용약물
	 */
	public String getFstDrugCd() {
		return fstDrugCd;
	}
	/**
	 * 최초사용약물
	 */
	public void setFstDrugCd(String fstDrugCd) {
		this.fstDrugCd = fstDrugCd;
	}
	/**
	 * 주요사용약물 분류
	 */
	public String getMainDrugCd() {
		return mainDrugCd;
	}
	/**
	 * 주요사용약물 분류
	 */
	public void setMainDrugCd(String mainDrugCd) {
		this.mainDrugCd = mainDrugCd;
	}
	/**
	 * 주요사용약물
	 */
	public String getMainDrug() {
		return mainDrug;
	}
	/**
	 * 주요사용약물
	 */
	public void setMainDrug(String mainDrug) {
		this.mainDrug = mainDrug;
	}
	/**
	 * 상담내용
	 */
	public String getCslCtnt() {
		return cslCtnt;
	}
	/**
	 * 상담내용
	 */
	public void setCslCtnt(String cslCtnt) {
		this.cslCtnt = cslCtnt;
	}
	/**
	 * 주요조치
	 */
	public String getMjrMngCd() {
		return mjrMngCd;
	}
	/**
	 * 주요조치
	 */
	public void setMjrMngCd(String mjrMngCd) {
		this.mjrMngCd = mjrMngCd;
	}
	/**
	 * ASSIST 점수
	 */
	public int getAstSco() {
		return astSco;
	}
	/**
	 * ASSIST 점수
	 */
	public void setAstSco(int astSco) {
		this.astSco = astSco;
	}
	/**
	 * 위기분류척도 Rating A-위험성
	 */
	public String getRskaTpCd() {
		return rskaTpCd;
	}
	/**
	 * 위기분류척도 Rating A-위험성
	 */
	public void setRskaTpCd(String rskaTpCd) {
		this.rskaTpCd = rskaTpCd;
	}
	/**
	 * 위기분류척도 Rating B-지지체계
	 */
	public String getRskbTpCd() {
		return rskbTpCd;
	}
	/**
	 * 위기분류척도 Rating B-지지체계
	 */
	public void setRskbTpCd(String rskbTpCd) {
		this.rskbTpCd = rskbTpCd;
	}
	/**
	 * 위기분류척도 Rating C-협조능력
	 */
	public String getRskcTpCd() {
		return rskcTpCd;
	}
	/**
	 * 위기분류척도 Rating C-협조능력
	 */
	public void setRskcTpCd(String rskcTpCd) {
		this.rskcTpCd = rskcTpCd;
	}
	/**
	 * 위기분류척도 점수
	 */
	public int getRskSco() {
		return rskSco;
	}
	/**
	 * 위기분류척도 점수
	 */
	public void setRskSco(int rskSco) {
		this.rskSco = rskSco;
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
	 * 정보제공자 지역(기타)
	 */
	public String getIfpAreaEtc() {
		return ifpAreaEtc;
	}
	/**
	 * 정보제공자 지역(기타)
	 */
	public void setIfpAreaEtc(String ifpAreaEtc) {
		this.ifpAreaEtc = ifpAreaEtc;
	}
	/**
	 * 대상자 지역(기타)
	 */
	public String getTgpAreaEtc() {
		return tgpAreaEtc;
	}
	/**
	 * 대상자 지역(기타)
	 */
	public void setTgpAreaEtc(String tgpAreaEtc) {
		this.tgpAreaEtc = tgpAreaEtc;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}