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
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Position entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "position", catalog = "bussiness_system")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler",
		"fieldHandler" })
public class PositionDto implements java.io.Serializable {

	// Fields

	private Integer id;
	private DeptDto dept;
	private String description;
	@JsonIgnore
	private Set<UserDto> users = new HashSet<UserDto>(0);

	// Constructors

	/** default constructor */
	public PositionDto() {
	}

	/** full constructor */
	public PositionDto(DeptDto dept, String description, Set<UserDto> users) {
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
	public DeptDto getDept() {
		return this.dept;
	}

	public void setDept(DeptDto dept) {
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
	public Set<UserDto> getUsers() {
		return this.users;
	}

	public void setUsers(Set<UserDto> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "PositionDao [id=" + id + ", dept=" + dept + ", description="
				+ description + "]";
	}

}