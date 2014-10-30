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

/**
 * YwnrPhotos entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ywnr_photos", catalog = "bussiness_system")
public class YwnrPhotosDao implements java.io.Serializable {

	// Fields

	private Integer id;
	private YwnrDao ywnr;
	private byte[] photo;

	// Constructors

	/** default constructor */
	public YwnrPhotosDao() {
	}

	/** full constructor */
	public YwnrPhotosDao(YwnrDao ywnr, byte[] photo) {
		this.ywnr = ywnr;
		this.photo = photo;
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
	public YwnrDao getYwnr() {
		return this.ywnr;
	}

	public void setYwnr(YwnrDao ywnr) {
		this.ywnr = ywnr;
	}

	@Column(name = "photo")
	public byte[] getPhoto() {
		return this.photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "YwnrPhotosDao [id=" + id + ", photo=" + Arrays.toString(photo)
				+ "]";
	}

}