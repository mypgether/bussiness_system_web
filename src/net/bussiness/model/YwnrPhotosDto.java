package net.bussiness.model;

import java.util.Arrays;

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
 * YwnrPhotos entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ywnr_photos", catalog = "bussiness_system")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler",
		"fieldHandler" })
public class YwnrPhotosDto implements java.io.Serializable {

	// Fields

	private Integer id;
	private YwnrDto ywnr;
	private String photoPath;

	// Constructors

	/** default constructor */
	public YwnrPhotosDto() {
	}

	/** full constructor */
	public YwnrPhotosDto(YwnrDto ywnr, String photoPath) {
		this.ywnr = ywnr;
		this.photoPath = photoPath;
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

	@Column(name = "photoPath")
	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	@Override
	public String toString() {
		return "YwnrPhotosDao [id=" + id + ", photoPath=" + photoPath + "]";
	}
}