package net.bussiness.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Ywsq entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ywsq", catalog = "bussiness_system")
public class YwsqDao implements java.io.Serializable {

	// Fields

	private Integer ywId;
	private UserDao userByProposerId;
	private UserDao userByApproverId;
	private String location;
	private Date timestamp;
	private String reason;
	private Date applyTime;
	private Integer approveState;
	private Date approveTime;
	private String approveReason;
	@JsonIgnore
	private Set<YwnrDao> ywnrs = new HashSet<YwnrDao>(0);
	@JsonIgnore
	private Set<YwpjDao> ywpjs = new HashSet<YwpjDao>(0);

	// Constructors

	/** default constructor */
	public YwsqDao() {
	}

	/** full constructor */
	public YwsqDao(UserDao userByProposerId, UserDao userByApproverId,
			String location, Date timestamp, String reason, Date applyTime,
			Integer approveState, Date approveTime, String approveReason,
			Set<YwnrDao> ywnrs, Set<YwpjDao> ywpjs) {
		this.userByProposerId = userByProposerId;
		this.userByApproverId = userByApproverId;
		this.location = location;
		this.timestamp = timestamp;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proposerId", referencedColumnName = "userId")
	public UserDao getUserByProposerId() {
		return this.userByProposerId;
	}

	public void setUserByProposerId(UserDao userByProposerId) {
		this.userByProposerId = userByProposerId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approverId", referencedColumnName = "userId")
	public UserDao getUserByApproverId() {
		return this.userByApproverId;
	}

	public void setUserByApproverId(UserDao userByApproverId) {
		this.userByApproverId = userByApproverId;
	}

	@Column(name = "location")
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "timestamp", length = 19)
	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ywsq")
	public Set<YwnrDao> getYwnrs() {
		return this.ywnrs;
	}

	public void setYwnrs(Set<YwnrDao> ywnrs) {
		this.ywnrs = ywnrs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ywsq")
	public Set<YwpjDao> getYwpjs() {
		return this.ywpjs;
	}

	public void setYwpjs(Set<YwpjDao> ywpjs) {
		this.ywpjs = ywpjs;
	}

	@Override
	public String toString() {
		return "YwsqDao [ywId=" + ywId + ", location=" + location
				+ ", timestamp=" + timestamp + ", reason=" + reason
				+ ", applyTime=" + applyTime + ", approveState=" + approveState
				+ ", approveTime=" + approveTime + ", approveReason="
				+ approveReason + "]";
	}

}