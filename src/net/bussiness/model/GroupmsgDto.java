package net.bussiness.model;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Groupmsg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "groupmsg", catalog = "bussiness_system")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler",
		"fieldHandler" })
public class GroupmsgDto implements java.io.Serializable {

	// Fields

	private Integer msgId;
	private DeptDto group;
	private UserDto userBySenderId;
	private Integer msgType;
	private byte[] msgContent;
	private Date msgTime;

	// Constructors

	/** default constructor */
	public GroupmsgDto() {
	}

	/** full constructor */
	public GroupmsgDto(DeptDto group, UserDto userBySenderId, Integer msgType,
			byte[] msgContent, Date msgTime) {
		this.group = group;
		this.userBySenderId = userBySenderId;
		this.msgType = msgType;
		this.msgContent = msgContent;
		this.msgTime = msgTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "msgId", unique = true, nullable = false)
	public Integer getMsgId() {
		return this.msgId;
	}

	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "groupId", referencedColumnName = "id")
	public DeptDto getGroup() {
		return group;
	}

	public void setGroup(DeptDto group) {
		this.group = group;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "senderId", referencedColumnName = "userId")
	public UserDto getUserBySenderId() {
		return this.userBySenderId;
	}

	public void setUserBySenderId(UserDto userBySenderId) {
		this.userBySenderId = userBySenderId;
	}

	@Column(name = "msgType")
	public Integer getMsgType() {
		return this.msgType;
	}

	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}

	@Column(name = "msgContent")
	public byte[] getMsgContent() {
		return this.msgContent;
	}

	public void setMsgContent(byte[] msgContent) {
		this.msgContent = msgContent;
	}

	@Column(name = "msgTime", length = 19)
	public Date getMsgTime() {
		return this.msgTime;
	}

	public void setMsgTime(Date msgTime) {
		this.msgTime = msgTime;
	}

	@Override
	public String toString() {
		return "GroupmsgDto [msgId=" + msgId + ", msgType=" + msgType
				+ ", msgContent=" + Arrays.toString(msgContent) + ", msgTime="
				+ msgTime + "]";
	}

}