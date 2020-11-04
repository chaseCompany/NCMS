package kr.co.chase.ncms.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class CslIspVO implements Serializable {
	private static final long serialVersionUID = -858838578081269358L;

	private String ispDt;							// ISP 일자
	private String mbrNo;							// 회원정보 번호(ex:M20272772504833)
	private String mngTpCd;							// 관리구분
	private String mngTpNm;							// 관리구분명
	private String evlItmSco01;						// 약물사용문제-심각도
	private String evlItmSco02;						// 알코올사용문제-심각도
	private String evlItmSco03;						// 도박사용문제-심각도
	private String evlItmSco04;						// 인터넷사용문제-심각도
	private String evlItmSco05;						// 자해 및 타해 위험-심각도
	private String evlItmSco06;						// 정신과적 증상-심각도
	private String evlItmSco07;						// 정신약물관리-심각도
	private String evlItmSco08;						// 스트레스 상태-심각도
	private String evlItmSco09;						// 신체질환-심각도
	private String evlItmSco10;						// 가족관계-심각도
	private String evlItmSco11;						// 사회적관계-심각도
	private String evlItmSco12;						// 주거-심각도
	private String evlItmSco13;						// 경제활동 지원-심각도
	private String evlItmSco14;						// 취업 및 학업욕구-심각도
	private String evlItmSco15;						// 고용 및 교육가능성-심각도
	private String evlItmSco16;						// 법률적 문제-심각도
	private String evlItmLnk01;						// 약물사용문제-자원연계
	private String evlItmLnk02;						// 알코올사용문제-자원연계
	private String evlItmLnk03;						// 도박사용문제-자원연계
	private String evlItmLnk04;						// 인터넷사용문제-자원연계
	private String evlItmLnk05;						// 자해 및 타해 위험-자원연계
	private String evlItmLnk06;						// 정신과적 증상-자원연계
	private String evlItmLnk07;						// 정신약물관리-자원연계
	private String evlItmLnk08;						// 스트레스 상태-자원연계
	private String evlItmLnk09;						// 신체질환-자원연계
	private String evlItmLnk10;						// 가족관계-자원연계
	private String evlItmLnk11;						// 사회적관계-자원연계
	private String evlItmLnk12;						// 주거-자원연계
	private String evlItmLnk13;						// 경제활동 지원-자원연계
	private String evlItmLnk14;						// 취업 및 학업욕구-자원연계
	private String evlItmLnk15;						// 고용 및 교육가능성-자원연계
	private String evlItmLnk16;						// 법률적 문제-자원연계
	private String ispRst;							// ISP 결과
	private String tgtCtnt;							// 장단기 목표수립
	private String creId;							// 생성자
	private Timestamp creDt;						// 생성일자
	private String updId;							// 수정자
	private Timestamp updDt;						// 수정일자

	/**
	 * ISP 일자
	 * @return
	 */
	public String getIspDt() {
		return ispDt;
	}
	/**
	 * ISP 일자
	 * @param ispDt
	 */
	public void setIspDt(String ispDt) {
		this.ispDt = ispDt;
	}
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
	 * 관리구분
	 * @return
	 */
	public String getMngTpCd() {
		return mngTpCd;
	}
	/**
	 * 관리구분
	 * @param mngTpCd
	 */
	public void setMngTpCd(String mngTpCd) {
		this.mngTpCd = mngTpCd;
	}
	/**
	 * 관리구분명
	 * @return
	 */
	public String getMngTpNm() {
		return mngTpNm;
	}
	/**
	 * 관리구분명
	 * @param mngTpNm
	 */
	public void setMngTpNm(String mngTpNm) {
		this.mngTpNm = mngTpNm;
	}
	/**
	 * 약물사용문제-심각도
	 * @return
	 */
	public String getEvlItmSco01() {
		return evlItmSco01;
	}
	/**
	 * 약물사용문제-심각도
	 * @param evlItmSco01
	 */
	public void setEvlItmSco01(String evlItmSco01) {
		this.evlItmSco01 = evlItmSco01;
	}
	/**
	 * 알코올사용문제-심각도
	 * @return
	 */
	public String getEvlItmSco02() {
		return evlItmSco02;
	}
	/**
	 * 알코올사용문제-심각도
	 * @param evlItmSco02
	 */
	public void setEvlItmSco02(String evlItmSco02) {
		this.evlItmSco02 = evlItmSco02;
	}
	/**
	 * 도박사용문제-심각도
	 * @return
	 */
	public String getEvlItmSco03() {
		return evlItmSco03;
	}
	/**
	 * 도박사용문제-심각도
	 * @param evlItmSco03
	 */
	public void setEvlItmSco03(String evlItmSco03) {
		this.evlItmSco03 = evlItmSco03;
	}
	/**
	 * 인터넷사용문제-심각도
	 * @return
	 */
	public String getEvlItmSco04() {
		return evlItmSco04;
	}
	/**
	 * 인터넷사용문제-심각도
	 * @param evlItmSco04
	 */
	public void setEvlItmSco04(String evlItmSco04) {
		this.evlItmSco04 = evlItmSco04;
	}
	/**
	 * 자해 및 타해 위험-심각도
	 * @return
	 */
	public String getEvlItmSco05() {
		return evlItmSco05;
	}
	/**
	 * 자해 및 타해 위험-심각도
	 * @param evlItmSco05
	 */
	public void setEvlItmSco05(String evlItmSco05) {
		this.evlItmSco05 = evlItmSco05;
	}
	/**
	 * 정신과적 증상-심각도
	 * @return
	 */
	public String getEvlItmSco06() {
		return evlItmSco06;
	}
	/**
	 * 정신과적 증상-심각도
	 * @param evlItmSco06
	 */
	public void setEvlItmSco06(String evlItmSco06) {
		this.evlItmSco06 = evlItmSco06;
	}
	/**
	 * 정신약물관리-심각도
	 * @return
	 */
	public String getEvlItmSco07() {
		return evlItmSco07;
	}
	/**
	 * 정신약물관리-심각도
	 * @param evlItmSco07
	 */
	public void setEvlItmSco07(String evlItmSco07) {
		this.evlItmSco07 = evlItmSco07;
	}
	/**
	 * 스트레스 상태-심각도
	 * @return
	 */
	public String getEvlItmSco08() {
		return evlItmSco08;
	}
	/**
	 * 스트레스 상태-심각도
	 * @param evlItmSco08
	 */
	public void setEvlItmSco08(String evlItmSco08) {
		this.evlItmSco08 = evlItmSco08;
	}
	/**
	 * 신체질환-심각도
	 * @return
	 */
	public String getEvlItmSco09() {
		return evlItmSco09;
	}
	/**
	 * 신체질환-심각도
	 * @param evlItmSco09
	 */
	public void setEvlItmSco09(String evlItmSco09) {
		this.evlItmSco09 = evlItmSco09;
	}
	/**
	 * 가족관계-심각도
	 * @return
	 */
	public String getEvlItmSco10() {
		return evlItmSco10;
	}
	/**
	 * 가족관계-심각도
	 * @param evlItmSco10
	 */
	public void setEvlItmSco10(String evlItmSco10) {
		this.evlItmSco10 = evlItmSco10;
	}
	/**
	 * 사회적관계-심각도
	 * @return
	 */
	public String getEvlItmSco11() {
		return evlItmSco11;
	}
	/**
	 * 사회적관계-심각도
	 * @param evlItmSco11
	 */
	public void setEvlItmSco11(String evlItmSco11) {
		this.evlItmSco11 = evlItmSco11;
	}
	/**
	 * 주거-심각도
	 * @return
	 */
	public String getEvlItmSco12() {
		return evlItmSco12;
	}
	/**
	 * 주거-심각도
	 * @param evlItmSco12
	 */
	public void setEvlItmSco12(String evlItmSco12) {
		this.evlItmSco12 = evlItmSco12;
	}
	/**
	 * 경제활동 지원-심각도
	 * @return
	 */
	public String getEvlItmSco13() {
		return evlItmSco13;
	}
	/**
	 * 경제활동 지원-심각도
	 * @param evlItmSco13
	 */
	public void setEvlItmSco13(String evlItmSco13) {
		this.evlItmSco13 = evlItmSco13;
	}
	/**
	 * 취업 및 학업욕구-심각도
	 * @return
	 */
	public String getEvlItmSco14() {
		return evlItmSco14;
	}
	/**
	 * 취업 및 학업욕구-심각도
	 * @param evlItmSco14
	 */
	public void setEvlItmSco14(String evlItmSco14) {
		this.evlItmSco14 = evlItmSco14;
	}
	/**
	 * 고용 및 교육가능성-심각도
	 * @return
	 */
	public String getEvlItmSco15() {
		return evlItmSco15;
	}
	/**
	 * 고용 및 교육가능성-심각도
	 * @param evlItmSco15
	 */
	public void setEvlItmSco15(String evlItmSco15) {
		this.evlItmSco15 = evlItmSco15;
	}
	/**
	 * 법률적 문제-심각도
	 * @return
	 */
	public String getEvlItmSco16() {
		return evlItmSco16;
	}
	/**
	 * 법률적 문제-심각도
	 * @param evlItmSco16
	 */
	public void setEvlItmSco16(String evlItmSco16) {
		this.evlItmSco16 = evlItmSco16;
	}
	/**
	 * 약물사용문제-자원연계
	 * @return
	 */
	public String getEvlItmLnk01() {
		return evlItmLnk01;
	}
	/**
	 * 약물사용문제-자원연계
	 * @param evlItmLnk01
	 */
	public void setEvlItmLnk01(String evlItmLnk01) {
		this.evlItmLnk01 = evlItmLnk01;
	}
	/**
	 * 알코올사용문제-자원연계
	 * @return
	 */
	public String getEvlItmLnk02() {
		return evlItmLnk02;
	}
	/**
	 * 알코올사용문제-자원연계
	 * @param evlItmLnk02
	 */
	public void setEvlItmLnk02(String evlItmLnk02) {
		this.evlItmLnk02 = evlItmLnk02;
	}
	/**
	 * 도박사용문제-자원연계
	 * @return
	 */
	public String getEvlItmLnk03() {
		return evlItmLnk03;
	}
	/**
	 * 도박사용문제-자원연계
	 * @param evlItmLnk03
	 */
	public void setEvlItmLnk03(String evlItmLnk03) {
		this.evlItmLnk03 = evlItmLnk03;
	}
	/**
	 * 인터넷사용문제-자원연계
	 * @return
	 */
	public String getEvlItmLnk04() {
		return evlItmLnk04;
	}
	/**
	 * 인터넷사용문제-자원연계
	 * @param evlItmLnk04
	 */
	public void setEvlItmLnk04(String evlItmLnk04) {
		this.evlItmLnk04 = evlItmLnk04;
	}
	/**
	 * 자해 및 타해 위험-자원연계
	 * @return
	 */
	public String getEvlItmLnk05() {
		return evlItmLnk05;
	}
	/**
	 * 자해 및 타해 위험-자원연계
	 * @param evlItmLnk05
	 */
	public void setEvlItmLnk05(String evlItmLnk05) {
		this.evlItmLnk05 = evlItmLnk05;
	}
	/**
	 * 정신과적 증상-자원연계
	 * @return
	 */
	public String getEvlItmLnk06() {
		return evlItmLnk06;
	}
	/**
	 * 정신과적 증상-자원연계
	 * @param evlItmLnk06
	 */
	public void setEvlItmLnk06(String evlItmLnk06) {
		this.evlItmLnk06 = evlItmLnk06;
	}
	/**
	 * 정신약물관리-자원연계
	 * @return
	 */
	public String getEvlItmLnk07() {
		return evlItmLnk07;
	}
	/**
	 * 정신약물관리-자원연계
	 * @param evlItmLnk07
	 */
	public void setEvlItmLnk07(String evlItmLnk07) {
		this.evlItmLnk07 = evlItmLnk07;
	}
	/**
	 * 스트레스 상태-자원연계
	 * @return
	 */
	public String getEvlItmLnk08() {
		return evlItmLnk08;
	}
	/**
	 * 스트레스 상태-자원연계
	 * @param evlItmLnk08
	 */
	public void setEvlItmLnk08(String evlItmLnk08) {
		this.evlItmLnk08 = evlItmLnk08;
	}
	/**
	 * 신체질환-자원연계
	 * @return
	 */
	public String getEvlItmLnk09() {
		return evlItmLnk09;
	}
	/**
	 * 신체질환-자원연계
	 * @param evlItmLnk09
	 */
	public void setEvlItmLnk09(String evlItmLnk09) {
		this.evlItmLnk09 = evlItmLnk09;
	}
	/**
	 * 가족관계-자원연계
	 * @return
	 */
	public String getEvlItmLnk10() {
		return evlItmLnk10;
	}
	/**
	 * 가족관계-자원연계
	 * @param evlItmLnk10
	 */
	public void setEvlItmLnk10(String evlItmLnk10) {
		this.evlItmLnk10 = evlItmLnk10;
	}
	/**
	 * 사회적관계-자원연계
	 * @return
	 */
	public String getEvlItmLnk11() {
		return evlItmLnk11;
	}
	/**
	 * 사회적관계-자원연계
	 * @param evlItmLnk11
	 */
	public void setEvlItmLnk11(String evlItmLnk11) {
		this.evlItmLnk11 = evlItmLnk11;
	}
	/**
	 * 주거-자원연계
	 * @return
	 */
	public String getEvlItmLnk12() {
		return evlItmLnk12;
	}
	/**
	 * 주거-자원연계
	 * @param evlItmLnk12
	 */
	public void setEvlItmLnk12(String evlItmLnk12) {
		this.evlItmLnk12 = evlItmLnk12;
	}
	/**
	 * 경제활동 지원-자원연계
	 * @return
	 */
	public String getEvlItmLnk13() {
		return evlItmLnk13;
	}
	/**
	 * 경제활동 지원-자원연계
	 * @param evlItmLnk13
	 */
	public void setEvlItmLnk13(String evlItmLnk13) {
		this.evlItmLnk13 = evlItmLnk13;
	}
	/**
	 * 취업 및 학업욕구-자원연계
	 * @return
	 */
	public String getEvlItmLnk14() {
		return evlItmLnk14;
	}
	/**
	 * 취업 및 학업욕구-자원연계
	 * @param evlItmLnk14
	 */
	public void setEvlItmLnk14(String evlItmLnk14) {
		this.evlItmLnk14 = evlItmLnk14;
	}
	/**
	 * 고용 및 교육가능성-자원연계
	 * @return
	 */
	public String getEvlItmLnk15() {
		return evlItmLnk15;
	}
	/**
	 * 고용 및 교육가능성-자원연계
	 * @param evlItmLnk15
	 */
	public void setEvlItmLnk15(String evlItmLnk15) {
		this.evlItmLnk15 = evlItmLnk15;
	}
	/**
	 * 법률적 문제-자원연계
	 * @return
	 */
	public String getEvlItmLnk16() {
		return evlItmLnk16;
	}
	/**
	 * 법률적 문제-자원연계
	 * @param evlItmLnk16
	 */
	public void setEvlItmLnk16(String evlItmLnk16) {
		this.evlItmLnk16 = evlItmLnk16;
	}
	/**
	 * ISP 결과
	 * @return
	 */
	public String getIspRst() {
		return ispRst;
	}
	/**
	 * ISP 결과
	 * @param ispRst
	 */
	public void setIspRst(String ispRst) {
		this.ispRst = ispRst;
	}
	/**
	 * 장단기 목표수립
	 * @return
	 */
	public String getTgtCtnt() {
		return tgtCtnt;
	}
	/**
	 * 장단기 목표수립
	 * @param tgtCtnt
	 */
	public void setTgtCtnt(String tgtCtnt) {
		this.tgtCtnt = tgtCtnt;
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