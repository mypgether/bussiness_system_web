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

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Dept entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "dept", catalog = "bussiness_system")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler",
		"fieldHandler", "positions", "groupmsgs" })
public class DeptDto implements java.io.Serializable {

	// Fields

	private Integer id;
	private String deptName;
	private Integer createrId;
	private Date createTime;
	private String description;
	private Set<PositionDto> positions = new HashSet<PositionDto>(0);
	private Set<GroupmsgDto> groupmsgs = new HashSet<GroupmsgDto>(0);

	// Constructors

	/** default constructor */
	public DeptDto() {
	}

	/** full constructor */
	public DeptDto(String deptName, Integer createrId, Date createTime,
			String description, Set<PositionDto> positions,
			Set<GroupmsgDto> groupmsgs) {
		this.deptName = deptName;
		this.createrId = createrId;
		this.createTime = createTime;
		this.description = description;
		this.positions = positions;
		this.groupmsgs = groupmsgs;
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

	public Integer getCreaterId() {
		return createrId;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
	public Set<GroupmsgDto> getGroupmsgs() {
		return groupmsgs;
	}

	public void setGroupmsgs(Set<GroupmsgDto> groupmsgs) {
		this.groupmsgs = groupmsgs;
	}

	@Override
	public String toString() {
		return "DeptDao [id=" + id + ", deptName=" + deptName + ", createTime="
				+ createTime + ", description=" + description + "]";
	}

}