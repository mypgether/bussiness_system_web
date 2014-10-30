package net.bussiness.model;

import java.util.Arrays;
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

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user", catalog = "bussiness_system")
public class UserDao implements java.io.Serializable {

	// Fields
	private Integer id;
	private PositionDao position;
	private DeptDao dept;
	private Integer userId;
	private String userName;
	private String password;
	private byte[] photo;
	private String tel;
	private String email;
	private Date joinTime;
	private String description;
	private Set<YwsqDao> ywsqsForProposerId = new HashSet<YwsqDao>(0);
	private Set<YwsqDao> ywsqsForApproverId = new HashSet<YwsqDao>(0);
	private Set<ChatmsgDao> chatmsgsForSenderId = new HashSet<ChatmsgDao>(0);
	private Set<ChatmsgDao> chatmsgsForReceiverId = new HashSet<ChatmsgDao>(0);

	// Constructors

	/** default constructor */
	public UserDao() {
	}

	/** full constructor */
	public UserDao(PositionDao position, DeptDao dept, Integer userId,
			String userName, String password, byte[] photo, String tel,
			String email, Date joinTime, String description,
			Set<YwsqDao> ywsqsForProposerId, Set<YwsqDao> ywsqsForApproverId,
			Set<ChatmsgDao> chatmsgsForSenderId,
			Set<ChatmsgDao> chatmsgsForReceiverId) {
		this.position = position;
		this.dept = dept;
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.photo = photo;
		this.tel = tel;
		this.email = email;
		this.joinTime = joinTime;
		this.description = description;
		this.ywsqsForProposerId = ywsqsForProposerId;
		this.ywsqsForApproverId = ywsqsForApproverId;
		this.chatmsgsForSenderId = chatmsgsForSenderId;
		this.chatmsgsForReceiverId = chatmsgsForReceiverId;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "positionId", referencedColumnName = "id")
	public PositionDao getPosition() {
		return this.position;
	}

	public void setPosition(PositionDao position) {
		this.position = position;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "deptId", referencedColumnName = "id")
	public DeptDao getDept() {
		return this.dept;
	}

	public void setDept(DeptDao dept) {
		this.dept = dept;
	}

	@Column(name = "userId")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	@Column(name = "photo")
	public byte[] getPhoto() {
		return this.photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
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
	public Set<YwsqDao> getYwsqsForProposerId() {
		return this.ywsqsForProposerId;
	}

	public void setYwsqsForProposerId(Set<YwsqDao> ywsqsForProposerId) {
		this.ywsqsForProposerId = ywsqsForProposerId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userByApproverId")
	public Set<YwsqDao> getYwsqsForApproverId() {
		return this.ywsqsForApproverId;
	}

	public void setYwsqsForApproverId(Set<YwsqDao> ywsqsForApproverId) {
		this.ywsqsForApproverId = ywsqsForApproverId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userBySenderId")
	public Set<ChatmsgDao> getChatmsgsForSenderId() {
		return this.chatmsgsForSenderId;
	}

	public void setChatmsgsForSenderId(Set<ChatmsgDao> chatmsgsForSenderId) {
		this.chatmsgsForSenderId = chatmsgsForSenderId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userByReceiverId")
	public Set<ChatmsgDao> getChatmsgsForReceiverId() {
		return this.chatmsgsForReceiverId;
	}

	public void setChatmsgsForReceiverId(Set<ChatmsgDao> chatmsgsForReceiverId) {
		this.chatmsgsForReceiverId = chatmsgsForReceiverId;
	}

	@Override
	public String toString() {
		return "UserDao [id=" + id + ", userId=" + userId + ", userName="
				+ userName + ", password=" + password + ", photo="
				+ Arrays.toString(photo) + ", tel=" + tel + ", email=" + email
				+ ", joinTime=" + joinTime + ", description=" + description
				+ "]";
	}

}