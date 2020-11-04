package kr.co.chase.ncms.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 사정평가도구
 * @author Molra
 */
public class CslAssEvlVO implements Serializable {
	private static final long serialVersionUID = -858838578081269358L;

	private String mbrNo;					// 회원정보 번호(ex:M20272772504833)
	private String evlSeq;					// 평가 순번
	private String evlTolCd;				// 평가 도구
	private String evlSco;					// 평가 점수
	private String evlCtnt;					// 평가 내용

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
	 * 평가 순번
	 * @return
	 */
	public String getEvlSeq() {
		return evlSeq;
	}
	/**
	 * 평가 순번
	 * @param evlSeq
	 */
	public void setEvlSeq(String evlSeq) {
		this.evlSeq = evlSeq;
	}
	/**
	 * 평가 도구
	 * @return
	 */
	public String getEvlTolCd() {
		return evlTolCd;
	}
	/**
	 * 평가 도구
	 * @param evlTolCd
	 */
	public void setEvlTolCd(String evlTolCd) {
		this.evlTolCd = evlTolCd;
	}
	/**
	 * 평가 점수
	 * @return
	 */
	public String getEvlSco() {
		return evlSco;
	}
	/**
	 * 평가 점수
	 * @param evlSco
	 */
	public void setEvlSco(String evlSco) {
		this.evlSco = evlSco;
	}
	/**
	 * 평가 내용
	 * @return
	 */
	public String getEvlCtnt() {
		return evlCtnt;
	}
	/**
	 * 평가 내용
	 * @param evlCtnt
	 */
	public void setEvlCtnt(String evlCtnt) {
		this.evlCtnt = evlCtnt;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
