package com.myp.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * YwnrPhotos entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ywnr_photos", catalog = "bussiness_system")
public class YwnrPhotosDao implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer ywId;
	private byte[] photo;

	// Constructors

	/** default constructor */
	public YwnrPhotosDao() {
	}

	/** full constructor */
	public YwnrPhotosDao(Integer ywId, byte[] photo) {
		this.ywId = ywId;
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

	@Column(name = "ywId")
	public Integer getYwId() {
		return ywId;
	}

	public void setYwId(Integer ywId) {
		this.ywId = ywId;
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
		return "YwnrPhotosDao [id=" + id + ", ywId=" + ywId + ", photo="
				+ Arrays.toString(photo) + "]";
	}

}