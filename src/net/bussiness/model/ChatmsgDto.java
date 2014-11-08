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

/**
 * Chatmsg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "chatmsg", catalog = "bussiness_system")
public class ChatmsgDto implements java.io.Serializable {

	// Fields

	private Integer msgId;
	private UserDto userBySenderId;
	private UserDto userByReceiverId;
	private Integer msgType;
	private byte[] msgContent;
	private Date msgTime;

	// Constructors

	/** default constructor */
	public ChatmsgDto() {
	}

	/** full constructor */
	public ChatmsgDto(UserDto userBySenderId, UserDto userByReceiverId,
			Integer msgType, byte[] msgContent, Date msgTime) {
		this.userBySenderId = userBySenderId;
		this.userByReceiverId = userByReceiverId;
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
	@JoinColumn(name = "senderId", referencedColumnName = "userId")
	public UserDto getUserBySenderId() {
		return this.userBySenderId;
	}

	public void setUserBySenderId(UserDto userBySenderId) {
		this.userBySenderId = userBySenderId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receiverId", referencedColumnName = "userId")
	public UserDto getUserByReceiverId() {
		return this.userByReceiverId;
	}

	public void setUserByReceiverId(UserDto userByReceiverId) {
		this.userByReceiverId = userByReceiverId;
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
		return "ChatmsgDao [msgId=" + msgId + ", msgType=" + msgType
				+ ", msgContent=" + Arrays.toString(msgContent) + ", msgTime="
				+ msgTime + "]";
	}

}