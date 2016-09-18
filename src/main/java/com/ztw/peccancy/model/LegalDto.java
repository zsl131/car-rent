package com.ztw.peccancy.model;

/**
 * 违法DTO对象
 * @author zslin.com
 *
 */
public class LegalDto {

	/** 类型，1：现场处罚；2：非现场处罚 */
	private String type;
	
	/** 处罚机构 */
	private String orgName;
	
	/** 违法编号 */
	private String legalNo;
	
	/** 违法时间 */
	private String time;
	
	/** 违法行为 */
	private String behavior;
	
	/** 违法地点 */
	private String address;
	
	/** 处罚金额 */
	private Double money;
	
	/** 扣分 */
	private Integer score;
	
	/** 违法时间，Long类型 */
	private Long legalong;

	public Long getLegalong() {
		return legalong;
	}

	public void setLegalong(Long legalong) {
		this.legalong = legalong;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getLegalNo() {
		return legalNo;
	}

	public void setLegalNo(String legalNo) {
		this.legalNo = legalNo;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getBehavior() {
		return behavior;
	}

	public void setBehavior(String behavior) {
		this.behavior = behavior;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "LegalDto [type=" + type + ", orgName=" + orgName + ", legalNo="
				+ legalNo + ", time=" + time + ", behavior=" + behavior
				+ ", address=" + address + ", money=" + money + ", score="
				+ score + "]";
	}
}
