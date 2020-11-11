package kr.co.chase.ncms.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 코드 관리
 * @author ysp
 */
public class SysCD implements Serializable {
	private static final long serialVersionUID = -858838578081265859L;

	private String grpCd;					// 그룹 코드
	private String cdId;					// 코드 ID
	private String cdNm;					// 코드명
	private String dpSeq;					// 보기 순서
	private String useYn;					// 사용여부
	private Timestamp creDt;				// 생성일자
	private String creId;					// 생성자
	private Timestamp updDt;				// 수정일자
	private String updId;					// 수정자
	
	public String getGrpCd() {
		return grpCd;
	}

	public void setGrpCd(String grpCd) {
		this.grpCd = grpCd;
	}

	public String getCdId() {
		return cdId;
	}

	public void setCdId(String cdId) {
		this.cdId = cdId;
	}

	public String getCdNm() {
		return cdNm;
	}

	public void setCdNm(String cdNm) {
		this.cdNm = cdNm;
	}

	public String getDpSeq() {
		return dpSeq;
	}

	public void setDpSeq(String dpSeq) {
		this.dpSeq = dpSeq;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public Timestamp getCreDt() {
		return creDt;
	}

	public void setCreDt(Timestamp creDt) {
		this.creDt = creDt;
	}

	public String getCreId() {
		return creId;
	}

	public void setCreId(String creId) {
		this.creId = creId;
	}

	public Timestamp getUpdDt() {
		return updDt;
	}

	public void setUpdDt(Timestamp updDt) {
		this.updDt = updDt;
	}

	public String getUpdId() {
		return updId;
	}

	public void setUpdId(String updId) {
		this.updId = updId;
	}

	@Override
	public String toString() {
		return "SysCD [grpCd=" + grpCd + ", cdId=" + cdId + ", cdNm=" + cdNm + ", dpSeq=" + dpSeq + ", useYn=" + useYn
				+ ", creDt=" + creDt + ", creId=" + creId + ", updDt=" + updDt + ", updId=" + updId + "]";
	}

}
