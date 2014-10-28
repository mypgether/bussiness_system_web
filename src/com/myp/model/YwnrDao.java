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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Ywnr entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ywnr", catalog = "bussiness_system")
public class YwnrDao implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer ywId;
	private Date nrTime;
	private String nrLocation;
	private String description;
	private Set<YwnrPhotosDao> ywnrPhotoses = new HashSet<YwnrPhotosDao>(0);

	// Constructors

	/** default constructor */
	public YwnrDao() {
	}

	/** full constructor */
	public YwnrDao(Integer ywId, Date nrTime, String nrLocation,
			String description, Set<YwnrPhotosDao> ywnrPhotoses) {
		this.ywId = ywId;
		this.nrTime = nrTime;
		this.nrLocation = nrLocation;
		this.description = description;
		this.ywnrPhotoses = ywnrPhotoses;
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

	@Column(name = "nrTime", length = 19)
	public Date getNrTime() {
		return this.nrTime;
	}

	public void setNrTime(Date nrTime) {
		this.nrTime = nrTime;
	}

	@Column(name = "nrLocation")
	public String getNrLocation() {
		return this.nrLocation;
	}

	public void setNrLocation(String nrLocation) {
		this.nrLocation = nrLocation;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ywId")
	public Set<YwnrPhotosDao> getYwnrPhotoses() {
		return this.ywnrPhotoses;
	}

	public void setYwnrPhotoses(Set<YwnrPhotosDao> ywnrPhotoses) {
		this.ywnrPhotoses = ywnrPhotoses;
	}

	@Override
	public String toString() {
		return "YwnrDao [id=" + id + ", ywId=" + ywId + ", nrTime=" + nrTime
				+ ", nrLocation=" + nrLocation + ", description=" + description
				+ ", ywnrPhotoses=" + ywnrPhotoses + "]";
	}

}