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
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Ywsq entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ywsq", catalog = "bussiness_system")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler",
		"fieldHandler" })
public class YwsqDto implements java.io.Serializable {

	// Fields

	private Integer ywId;
	private UserDto userByProposerId;
	private UserDto userByApproverId;
	private String location;
	private Date timestamp;
	private String reason;
	private Date applyTime;
	private Integer approveState;
	private Date approveTime;
	private String approveReason;
	@JsonIgnore
	private Set<YwnrDto> ywnrs = new HashSet<YwnrDto>(0);
	@JsonIgnore
	private Set<YwpjDto> ywpjs = new HashSet<YwpjDto>(0);

	// Constructors

	/** default constructor */
	public YwsqDto() {
	}

	/** full constructor */
	public YwsqDto(UserDto userByProposerId, UserDto userByApproverId,
			String location, Date timestamp, String reason, Date applyTime,
			Integer approveState, Date approveTime, String approveReason,
			Set<YwnrDto> ywnrs, Set<YwpjDto> ywpjs) {
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
	public UserDto getUserByProposerId() {
		return this.userByProposerId;
	}

	public void setUserByProposerId(UserDto userByProposerId) {
		this.userByProposerId = userByProposerId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approverId", referencedColumnName = "userId")
	public UserDto getUserByApproverId() {
		return this.userByApproverId;
	}

	public void setUserByApproverId(UserDto userByApproverId) {
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
	public Set<YwnrDto> getYwnrs() {
		return this.ywnrs;
	}

	public void setYwnrs(Set<YwnrDto> ywnrs) {
		this.ywnrs = ywnrs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ywsq")
	public Set<YwpjDto> getYwpjs() {
		return this.ywpjs;
	}

	public void setYwpjs(Set<YwpjDto> ywpjs) {
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