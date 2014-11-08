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
	private String description;
	@JsonIgnore
	private Set<YwnrPhotosDto> ywnrPhotoses = new HashSet<YwnrPhotosDto>(0);

	// Constructors

	/** default constructor */
	public YwnrDto() {
	}

	/** full constructor */
	public YwnrDto(YwsqDto ywsq, Date nrTime, String nrLocation,
			String description, Set<YwnrPhotosDto> ywnrPhotoses) {
		this.ywsq = ywsq;
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

	@Override
	public String toString() {
		return "YwnrDao [id=" + id + ", nrTime=" + nrTime + ", nrLocation="
				+ nrLocation + ", description=" + description + "]";
	}

}