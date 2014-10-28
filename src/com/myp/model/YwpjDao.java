package com.myp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Ywpj entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ywpj", catalog = "bussiness_system")
public class YwpjDao implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer ywId;
	private Integer remarkerId;
	private double ranker;
	private String remark;

	// Constructors

	/** default constructor */
	public YwpjDao() {
	}

	/** full constructor */
	public YwpjDao(Integer ywId, Integer remarkerId, double ranker,
			String remark) {
		this.ywId = ywId;
		this.remarkerId = remarkerId;
		this.ranker = ranker;
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

	@Column(name = "ywId")
	public Integer getYwId() {
		return ywId;
	}

	public void setYwId(Integer ywId) {
		this.ywId = ywId;
	}

	@Column(name = "remarkerId")
	public Integer getRemarkerId() {
		return this.remarkerId;
	}

	public void setRemarkerId(Integer remarkerId) {
		this.remarkerId = remarkerId;
	}

	@Column(name = "ranker", precision = 10, scale = 0)
	public double getRanker() {
		return this.ranker;
	}

	public void setRanker(double ranker) {
		this.ranker = ranker;
	}

	@Column(name = "remark")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "YwpjDao [id=" + id + ", ywId=" + ywId + ", remarkerId="
				+ remarkerId + ", ranker=" + ranker + ", remark=" + remark
				+ "]";
	}

}