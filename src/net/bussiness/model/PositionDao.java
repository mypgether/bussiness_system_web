package net.bussiness.model;

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

/**
 * Position entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "position", catalog = "bussiness_system")
public class PositionDao implements java.io.Serializable {

	// Fields

	private Integer id;
	private DeptDao dept;
	private String description;
	@JsonIgnore
	private Set<UserDao> users = new HashSet<UserDao>(0);

	// Constructors

	/** default constructor */
	public PositionDao() {
	}

	/** full constructor */
	public PositionDao(DeptDao dept, String description, Set<UserDao> users) {
		this.dept = dept;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "deptId", referencedColumnName = "id")
	public DeptDao getDept() {
		return this.dept;
	}

	public void setDept(DeptDao dept) {
		this.dept = dept;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "position")
	public Set<UserDao> getUsers() {
		return this.users;
	}

	public void setUsers(Set<UserDao> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "PositionDao [id=" + id + ", dept=" + dept + ", description="
				+ description + "]";
	}

}