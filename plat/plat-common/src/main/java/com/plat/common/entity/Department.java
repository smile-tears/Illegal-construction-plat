package com.plat.common.entity;

import lombok.Data;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.GeneratedValue;
import java.util.Date;
import java.util.List;

/**
 * @description 部门
 * @author admin
 * @date 2020-05-19 23:05:55
 */
@Entity
@Data
@Table(name = "Department")
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	/**
	 * id
	 */
	@Column(name = "id", length = 32)
	private String id;

	/**
	 * 部门编码
	 */
	@Column(name = "departmentCode")
	private String departmentCode;

	/**
	 * 部门名称
	 */
	@Column(name = "departmentName")
	private String departmentName;

	/**
	 * 部门简称
	 */
	@Column(name = "departmentMask")
	private String departmentMask;

	/**
	 * 部门描述
	 */
	@Column(name = "departmentDesc")
	private String departmentDesc;

	/**
	 * 父级部门id
	 */
	@Column(name = "supDeptId")
	private String supDeptId;

	/**
	 * 状态
	 */
	@Column(name = "canceled")
	private Integer canceled;

	/**
	 * 显示顺序
	 */
	@Column(name = "showOrder")
	private Integer showOrder;

	/**
	 * 部门树图标
	 */
	@Column(name = "icon")
	private String icon;

	/**
	 * 删除标记
	 */
	@Column(name = "delTag", insertable = false, columnDefinition = "int default 1")
	private Integer delTag;

	/**
	 * 所属分部
	 */
	private String subCompanyId;
	
	public Department() {
	}

//    /**
//	 * onetomany 一对多
//	 */
//	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
//	private List<User> users;

}
