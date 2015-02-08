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
 * Ywnr entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ywnr", catalog = "bussiness_system")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler",
		"fieldHandler" })
public class YwnrDto implements java.io.Serializable {

	// Fields

	private Integer id;
	private YwsqDto ywsq;
	private Date nrTime;
	private String nrLocation;
	private Integer fee;
	private String description;
	@JsonIgnore
	private Set<YwnrPhotosDto> ywnrPhotoses = new HashSet<YwnrPhotosDto>(0);
	@JsonIgnore
	private Set<YwpjDto> ywpjs = new HashSet<YwpjDto>(0);

	// Constructors

	/** default constructor */
	public YwnrDto() {
	}

	/** full constructor */
	public YwnrDto(YwsqDto ywsq, Date nrTime, String nrLocation, Integer fee,
			String description, Set<YwnrPhotosDto> ywnrPhotoses,
			Set<YwpjDto> ywpjs) {
		this.ywsq = ywsq;
		this.nrTime = nrTime;
		this.nrLocation = nrLocation;
		this.fee = fee;
		this.description = description;
		this.ywnrPhotoses = ywnrPhotoses;
		this.ywpjs = ywpjs;
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
	@JoinColumn(name = "ywId", referencedColumnName = "ywId")
	public YwsqDto getYwsq() {
		return ywsq;
	}

	public void setYwsq(YwsqDto ywsq) {
		this.ywsq = ywsq;
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

	@Column(name = "fee")
	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ywnr")
	public Set<YwnrPhotosDto> getYwnrPhotoses() {
		return this.ywnrPhotoses;
	}

	public void setYwnrPhotoses(Set<YwnrPhotosDto> ywnrPhotoses) {
		this.ywnrPhotoses = ywnrPhotoses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ywnr")
	public Set<YwpjDto> getYwpjs() {
		return this.ywpjs;
	}

	public void setYwpjs(Set<YwpjDto> ywpjs) {
		this.ywpjs = ywpjs;
	}

	@Override
	public String toString() {
		return "YwnrDao [id=" + id + ", nrTime=" + nrTime + ", nrLocation="
				+ nrLocation + ", fee=" + fee + ", description=" + description
				+ "]";
	}

}