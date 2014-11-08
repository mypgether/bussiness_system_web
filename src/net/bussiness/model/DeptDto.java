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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Dept entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "dept", catalog = "bussiness_system")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler",
		"fieldHandler" })
public class DeptDto implements java.io.Serializable {

	// Fields

	private Integer id;
	private String deptName;
	private Integer createrId;
	private Date createTime;
	private String description;
	@JsonIgnore
	private Set<PositionDto> positions = new HashSet<PositionDto>(0);
	@JsonIgnore
	private Set<UserDto> users = new HashSet<UserDto>(0);

	// Constructors

	/** default constructor */
	public DeptDto() {
	}

	/** full constructor */
	public DeptDto(String deptName, Integer createrId, Date createTime,
			String description, Set<PositionDto> positions, Set<UserDto> users) {
		this.deptName = deptName;
		this.createrId = createrId;
		this.createTime = createTime;
		this.description = description;
		this.positions = positions;
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

	@Column(name = "deptName")
	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name = "createrId")
	public Integer getCreaterId() {
		return this.createrId;
	}

	public void setCreaterId(Integer createrId) {
		this.createrId = createrId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "createTime", length = 10)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dept")
	public Set<PositionDto> getPositions() {
		return this.positions;
	}

	public void setPositions(Set<PositionDto> positions) {
		this.positions = positions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dept")
	public Set<UserDto> getUsers() {
		return this.users;
	}

	public void setUsers(Set<UserDto> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "DeptDao [id=" + id + ", deptName=" + deptName + ", createrId="
				+ createrId + ", createTime=" + createTime + ", description="
				+ description + "]";
	}

}