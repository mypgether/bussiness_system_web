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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user", catalog = "bussiness_system")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler",
		"fieldHandler" })
public class UserDto implements java.io.Serializable {

	// Fields
	private Integer id;
	private PositionDto position;
	private DeptDto dept;
	private Integer userId;
	private String pushUserId;
	private String pushChannelId;
	private String userName;
	private String password;
	private String photoPath;
	private String tel;
	private String email;
	private Date joinTime;
	private String description;
	@JsonIgnore
	private Set<YwsqDto> ywsqsForProposerId = new HashSet<YwsqDto>(0);
	@JsonIgnore
	private Set<YwsqDto> ywsqsForApproverId = new HashSet<YwsqDto>(0);
	@JsonIgnore
	private Set<ChatmsgDto> chatmsgsForSenderId = new HashSet<ChatmsgDto>(0);
	@JsonIgnore
	private Set<ChatmsgDto> chatmsgsForReceiverId = new HashSet<ChatmsgDto>(0);
	@JsonIgnore
	private Set<GroupmsgDto> groupmsgsForSenderId = new HashSet<GroupmsgDto>(0);
	@JsonIgnore
	private Set<YwpjDto> ywpjsForRemarkerId = new HashSet<YwpjDto>(0);

	// Constructors

	/** default constructor */
	public UserDto() {
	}

	/** full constructor */
	public UserDto(PositionDto position, DeptDto dept, Integer userId,
			String pushUserId, String pushChannelId, String userName,
			String password, String photoPath, String tel, String email,
			Date joinTime, String description, Set<YwsqDto> ywsqsForProposerId,
			Set<YwsqDto> ywsqsForApproverId,
			Set<ChatmsgDto> chatmsgsForSenderId,
			Set<ChatmsgDto> chatmsgsForReceiverId,
			Set<GroupmsgDto> groupmsgsForSenderId,
			Set<YwpjDto> ywpjsForRemarkerId) {
		this.position = position;
		this.dept = dept;
		this.userId = userId;
		this.pushUserId = pushUserId;
		this.pushChannelId = pushChannelId;
		this.userName = userName;
		this.password = password;
		this.photoPath = photoPath;
		this.tel = tel;
		this.email = email;
		this.joinTime = joinTime;
		this.description = description;
		this.ywsqsForProposerId = ywsqsForProposerId;
		this.ywsqsForApproverId = ywsqsForApproverId;
		this.chatmsgsForSenderId = chatmsgsForSenderId;
		this.chatmsgsForReceiverId = chatmsgsForReceiverId;
		this.groupmsgsForSenderId = groupmsgsForSenderId;
		this.ywpjsForRemarkerId = ywpjsForRemarkerId;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "positionId", referencedColumnName = "id")
	public PositionDto getPosition() {
		return this.position;
	}

	public void setPosition(PositionDto position) {
		this.position = position;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "deptId", referencedColumnName = "id")
	public DeptDto getDept() {
		return this.dept;
	}

	public void setDept(DeptDto dept) {
		this.dept = dept;
	}

	@Column(name = "userId")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "pushUserId")
	public String getPushUserId() {
		return pushUserId;
	}

	public void setPushUserId(String pushUserId) {
		this.pushUserId = pushUserId;
	}

	@Column(name = "pushChannelId")
	public String getPushChannelId() {
		return pushChannelId;
	}

	public void setPushChannelId(String pushChannelId) {
		this.pushChannelId = pushChannelId;
	}

	@Column(name = "userName")
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "password")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "photoPath")
	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	@Column(name = "tel")
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "joinTime", length = 10)
	public Date getJoinTime() {
		return this.joinTime;
	}

	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userByProposerId")
	public Set<YwsqDto> getYwsqsForProposerId() {
		return this.ywsqsForProposerId;
	}

	public void setYwsqsForProposerId(Set<YwsqDto> ywsqsForProposerId) {
		this.ywsqsForProposerId = ywsqsForProposerId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userByApproverId")
	public Set<YwsqDto> getYwsqsForApproverId() {
		return this.ywsqsForApproverId;
	}

	public void setYwsqsForApproverId(Set<YwsqDto> ywsqsForApproverId) {
		this.ywsqsForApproverId = ywsqsForApproverId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userBySenderId")
	public Set<ChatmsgDto> getChatmsgsForSenderId() {
		return this.chatmsgsForSenderId;
	}

	public void setChatmsgsForSenderId(Set<ChatmsgDto> chatmsgsForSenderId) {
		this.chatmsgsForSenderId = chatmsgsForSenderId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userByReceiverId")
	public Set<ChatmsgDto> getChatmsgsForReceiverId() {
		return this.chatmsgsForReceiverId;
	}

	public void setChatmsgsForReceiverId(Set<ChatmsgDto> chatmsgsForReceiverId) {
		this.chatmsgsForReceiverId = chatmsgsForReceiverId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userBySenderId")
	public Set<GroupmsgDto> getGroupmsgsForSenderId() {
		return groupmsgsForSenderId;
	}

	public void setGroupmsgsForSenderId(Set<GroupmsgDto> groupmsgsForSenderId) {
		this.groupmsgsForSenderId = groupmsgsForSenderId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userByRemarkerId")
	public Set<YwpjDto> getYwpjsForRemarkerId() {
		return ywpjsForRemarkerId;
	}

	public void setYwpjsForRemarkerId(Set<YwpjDto> ywpjsForRemarkerId) {
		this.ywpjsForRemarkerId = ywpjsForRemarkerId;
	}

	@Override
	public String toString() {
		return "UserDao [id=" + id + ", userId=" + userId + ", pushUserId="
				+ pushUserId + ", pushChannelId=" + pushChannelId
				+ ", userName=" + userName + ", password=" + password
				+ ", photoPath=" + photoPath + ", tel=" + tel + ", email="
				+ email + ", joinTime=" + joinTime + ", description="
				+ description + "]";
	}

}