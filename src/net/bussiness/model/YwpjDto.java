package net.bussiness.model;

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
 * Ywpj entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ywpj", catalog = "bussiness_system")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler",
		"fieldHandler" })
public class YwpjDto implements java.io.Serializable {

	// Fields

	private Integer id;
	private YwnrDto ywnr;
	private UserDto userByRemarkerId;
	private byte[] remark;

	// Constructors

	/** default constructor */
	public YwpjDto() {
	}

	/** full constructor */
	public YwpjDto(YwnrDto ywnr, UserDto userByRemarkerId, byte[] remark) {
		this.ywnr = ywnr;
		this.userByRemarkerId = userByRemarkerId;
		this.remark = remark;
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
	@JoinColumn(name = "ywnrId", referencedColumnName = "id")
	public YwnrDto getYwnr() {
		return this.ywnr;
	}

	public void setYwnr(YwnrDto ywnr) {
		this.ywnr = ywnr;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "remarkerId", referencedColumnName = "userId")
	public UserDto getUserByRemarkerId() {
		return userByRemarkerId;
	}

	public void setUserByRemarkerId(UserDto userByRemarkerId) {
		this.userByRemarkerId = userByRemarkerId;
	}

	@Column(name = "remark")
	public byte[] getRemark() {
		return this.remark;
	}

	public void setRemark(byte[] remark) {
		this.remark = remark;
	}
}