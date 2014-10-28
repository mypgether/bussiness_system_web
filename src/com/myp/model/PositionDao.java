package com.myp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Position entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "position", catalog = "bussiness_system")
public class PositionDao implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer deptId;
	private String description;
	private Set<UserDao> users = new HashSet<UserDao>(0);

	// Constructors

	/** default constructor */
	public PositionDao() {
	}

	/** full constructor */
	public PositionDao(Integer deptId, String description, Set<UserDao> users) {
		this.deptId = deptId;
		this.description = description;
		this.users = users;
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

	@Column(name = "deptId")
	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "positionId")
	public Set<UserDao> getUsers() {
		return this.users;
	}

	public void setUsers(Set<UserDao> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "PositionDao [id=" + id + ", deptId=" + deptId
				+ ", description=" + description + ", users=" + users + "]";
	}

}