package com.plat.common.entity;

import lombok.Data;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.plat.common.utils.EncryptionUtils;

import javax.persistence.GeneratedValue;
import java.util.Date;
import java.util.List;

/**
 * @description 用户
 * @author admin
 * @date 2020-05-19 22:57:34
 */
@Entity
@Data
@Table(name = "User")
public class User implements Serializable {

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
	 * 用户名
	 */
	@Column(name = "username",length = 50, unique = true)
	private String username;

	/**
	 * 用户名
	 */
	@Column(name = "englishName")
	private String englishName;

	/**
	 * 密码
	 */
	@Column(name = "password")
	private String password;
	
	/**
	 * 人员姓名
	 */
	@Column(name = "name")
	private String name;

	/**
	 * 头像
	 */
	@Column(name = "avatar")
	private String avatar;

	/**
	 * 直接上级
	 */
	@Column(name = "leader")
	private String leader;

	/**
	 * 账号类型
	 */
	@Column(name = "accountType")
	private Integer accountType;

	/**
	 * 所属主账号
	 */
	@Column(name = "belongto")
	private String belongto;

	/**
	 * 性别
	 */
	@Column(name = "sex")
	private Integer sex;

	/**
	 * 身份证号
	 */
	@Column(name = "idNumber")
	private String idNumber;

	/**
	 * 学历
	 */
	@Column(name = "qualification")
	private String qualification;

	/**
	 * 出生日期
	 */
	@Column(name = "birthDate")
	private String birthDate;

	/**
	 * 手机号
	 */
	@Column(name = "telephone")
	private String telephone;
	
	/**
	 * 岗位
	 */
	@Column(name = "post")
	private String post;

	/**
	 * 邮箱
	 */
	@Column(name = "email")
	private String email;

	/**
	 * 家庭地址
	 */
	@Column(name = "address")
	private String address;

	/**
	 * 工号
	 */
	@Column(name = "workcode")
	private String workcode;

	/**
	 * 状态
	 */
	@Column(name = "status")
	private Integer status;

	/**
	 * 排序
	 */
	@Column(name = "showOrder")
	private Integer showOrder;

	/**
	 * 职称
	 */
	@Column(name = "jobtitle")
	private Integer jobtitle;
	
	/**
	 * 合同开始日期
	 */
	private String contractStartDate;
	
	/**
	 * 合同结束日期
	 */
	private String contractEndDate;

	/**
	 * 紧急联系人
	 */
	@Column(name = "emergencyContact")
	private String emergencyContact;

	/**
	 * 紧急联系人地址
	 */
	@Column(name = "emergencyAdreess")
	private String emergencyAdreess;

	/**
	 * 紧急联系电话
	 */
	@Column(name = "emergencyContactNum")
	private String emergencyContactNum;

	public User() {
	}

	/**
	 * 所属分部
	 */
	private String subCompanyId;

	/**
	 * 所属部门
	 */
	private String departmentId;

	/**
	 * 注册id
	 */
	private String registrationID;
	/**
	 * 删除标记
	 */
	@Column(name = "delTag", insertable = false, columnDefinition = "int default 1")
	private Integer delTag;

//	@ManyToOne
//	@JoinColumn(name = "departmentId")
//	@JsonIgnoreProperties(value = "users")
//	private Department department;

//	/**
//	 * 角色
//	 */
//	@JsonIgnoreProperties(value = { "users" })
//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(name = "User_Role", joinColumns = {
//			@JoinColumn(name = "userId", referencedColumnName = "id") }, inverseJoinColumns = {
//					@JoinColumn(name = "roleId", referencedColumnName = "id") })
//	private List<Role> roles;
}
