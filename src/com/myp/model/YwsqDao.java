package com.myp.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Ywsq entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ywsq", catalog = "bussiness_system")
public class YwsqDao implements java.io.Serializable {

	// Fields

	private Integer ywId;
	private Date timestamp;
	private Integer proposerId;
	private Integer approverId;
	private String location;
	private String reason;
	private Date applyTime;
	private Integer approveState;
	private Date approveTime;
	private String approveReason;
	private Set<YwnrDao> ywnrs = new HashSet<YwnrDao>(0);
	private Set<YwpjDao> ywpjs = new HashSet<YwpjDao>(0);

	// Constructors

	/** default constructor */
	public YwsqDao() {
	}

	/** full constructor */
	public YwsqDao(Integer proposerId, Integer approverId, String location,
			String reason, Date applyTime, Integer approveState,
			Date approveTime, String approveReason, Set<YwnrDao> ywnrs,
			Set<YwpjDao> ywpjs) {
		this.proposerId = proposerId;
		this.approverId = approverId;
		this.location = location;
		this.reason = reason;
		this.applyTime = applyTime;
		this.approveState = approveState;
		this.approveTime = approveTime;
		this.approveReason = approveReason;
		this.ywnrs = ywnrs;
		this.ywpjs = ywpjs;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ywId", unique = true, nullable = false)
	public Integer getYwId() {
		return this.ywId;
	}

	public void setYwId(Integer ywId) {
		this.ywId = ywId;
	}

	@Version
	@Column(name = "timestamp", length = 19)
	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Column(name = "proposerId")
	public Integer getProposerId() {
		return proposerId;
	}

	public void setProposerId(Integer proposerId) {
		this.proposerId = proposerId;
	}

	@Column(name = "approverId")
	public Integer getApproverId() {
		return approverId;
	}

	public void setApproverId(Integer approverId) {
		this.approverId = approverId;
	}

	@Column(name = "location")
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "reason")
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "applyTime", length = 19)
	public Date getApplyTime() {
		return this.applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	@Column(name = "approveState")
	public Integer getApproveState() {
		return this.approveState;
	}

	public void setApproveState(Integer approveState) {
		this.approveState = approveState;
	}

	@Column(name = "approveTime", length = 19)
	public Date getApproveTime() {
		return this.approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	@Column(name = "approveReason")
	public String getApproveReason() {
		return this.approveReason;
	}

	public void setApproveReason(String approveReason) {
		this.approveReason = approveReason;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ywId")
	public Set<YwnrDao> getYwnrs() {
		return this.ywnrs;
	}

	public void setYwnrs(Set<YwnrDao> ywnrs) {
		this.ywnrs = ywnrs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ywId")
	public Set<YwpjDao> getYwpjs() {
		return this.ywpjs;
	}

	public void setYwpjs(Set<YwpjDao> ywpjs) {
		this.ywpjs = ywpjs;
	}

	@Override
	public String toString() {
		return "YwsqDao [ywId=" + ywId + ", timestamp=" + timestamp
				+ ", proposerId=" + proposerId + ", approverId=" + approverId
				+ ", location=" + location + ", reason=" + reason
				+ ", applyTime=" + applyTime + ", approveState=" + approveState
				+ ", approveTime=" + approveTime + ", approveReason="
				+ approveReason + ", ywnrs=" + ywnrs + ", ywpjs=" + ywpjs + "]";
	}

}