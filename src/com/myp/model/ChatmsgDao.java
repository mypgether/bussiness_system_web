package com.myp.model;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Chatmsg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "chatmsg", catalog = "bussiness_system")
public class ChatmsgDao implements java.io.Serializable {

	// Fields

	private Integer msgId;
	private Integer senderId;
	private Integer receiverId;
	private Integer msgType;
	private byte[] msgContent;
	private Date msgTime;

	// Constructors

	/** default constructor */
	public ChatmsgDao() {
	}

	/** full constructor */
	public ChatmsgDao(Integer senderId, Integer receiverId, Integer msgType,
			byte[] msgContent, Date msgTime) {
		this.senderId = senderId;
		this.receiverId = receiverId;
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

	@Column(name = "senderId")
	public Integer getSenderId() {
		return senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	@Column(name = "receiverId")
	public Integer getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
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
		return "ChatmsgDao [msgId=" + msgId + ", senderId=" + senderId
				+ ", receiverId=" + receiverId + ", msgType=" + msgType
				+ ", msgContent=" + Arrays.toString(msgContent) + ", msgTime="
				+ msgTime + "]";
	}

}