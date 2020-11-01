package com.plat.caseinfo.entity;

import lombok.Data;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.plat.sysconfig.entity.GridCommunity;
import com.plat.sysconfig.entity.QuestionType;
import com.plat.sysconfig.entity.Sample;

import javax.persistence.GeneratedValue;
import java.util.Date;
import java.util.List;

/**
 * @description 案件信息表
 * @author admin
 * @date 2020-06-13 14:59:25
 */
@Entity
@Data
@Table(name = "CaseInfo_City")
public class CaseInfoCity implements Serializable {

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
	 * 案件编号
	 */
	@Column(name = "caseNo")
	private String caseNo;

	/**
	 * 案件来源1
	 */
	@Column(name = "casesource1")
	private String casesource1;

	/**
	 * 案件来源2
	 */
	@Column(name = "casesource2")
	private String casesource2;

	/**
	 * 案件类型1
	 */
	@Column(name = "casetype1")
	private String casetype1;

	/**
	 * 案件类型2
	 */
	@Column(name = "casetype2")
	private String casetype2;

	/**
	 * 案件类型3
	 */
	@Column(name = "casetype3")
	private String casetype3;
//	@OneToOne
//	@JoinColumn(name = "casetype3", referencedColumnName = "id")
//	private QuestionType casetype;


	/**
	 * 责任部门ids
	 */
	@Column(name = "managerDept")
	private String managerDept;

	/**
	 * 责任人
	 */
	@Column(name = "manager")
	private String manager;

	/**
	 * 处置时限（小时）
	 */
	@Column(name = "limittimes")
	private double limittimes;

	/**
	 * 案件计数
	 */
	@Column(name = "casecount")
	private String casecount;
	
	/**
	 * 案件描述
	 */
	@Column(name = "caseDesc")
	private String caseDesc;
	
	/**
	 * 位置描述
	 */
	@Column(name = "locationDesc")
	private String locationDesc;

	/**
	 * 上报人
	 */
	@Column(name = "reportor")
	private String reportor;
	
	/**
	 * 案件所属分部
	 */
	@Column(name = "subCompany")
	private String subCompany;

	/**
	 * 联系方式
	 */
	@Column(name = "contract")
	private String contract;

	/**
	 * 上报日期
	 */
	@Column(name = "reportDate")
	private String reportDate;
	
	/**
	 * 上报时间
	 */
	@Column(name = "reporTime")
	private String reportTime;
	
	/**
	 * 重大案件
	 */
	@Column(name = "importantCase")
	private Integer importantCase;
	
	/**
	 * 重复案件
	 */
	@Column(name = "repeatCase")
	private Integer repeatCase;

	/**
	 * 案件地址
	 */
	@Column(name = "address")
	private String address;

	/**
	 * 经度坐标
	 */
	@Column(name = "lng")
	private String lng;

	/**
	 * 纬度坐标
	 */
	@Column(name = "lat")
	private String lat;

	/**
	 * 紧急程度
	 */
	@Column(name = "urgency")
	private String urgency;

	/**
	 * 案件性质
	 */
	@Column(name = "casenature")
	private Integer casenature;

	/**
	 * 截止时限
	 */
	@Column(name = "enddate")
	private String enddate;

	/**
	 * 结案时间
	 */
	@Column(name = "closedate")
	private String closedate;

	/**
	 * 处置时间
	 */
	@Column(name = "dealdate")
	private String dealdate;

	/**
	 * 立案时间
	 */
	@Column(name = "setupdate")
	private String setupdate;

	/**
	 * 分派时间
	 */
	@Column(name = "assigndate")
	private String assigndate;

	/**
	 * 核查时间
	 */
	@Column(name = "verifydate")
	private String verifydate;
	
	/**
	 * 样本1
	 */
	@Column(name = "sample1")
	private String sample1;

	/**
	 * 样本2
	 */
	@Column(name = "sample2")
	private String sample2;

	/**
	 * 样本3
	 */
	@Column(name = "sample3")
	private String sample3;
//	@OneToOne
//	@JoinColumn(name = "sample3", referencedColumnName = "id")
//	private Sample sample;

	/**
	 * 案件当前状态
	 */
	@Column(name = "status")
	private Integer status;

	/**
	 * 上一步处理状态
	 */
	@Column(name = "laststatus")
	private String laststatus;

	/**
	 * 最近修改人
	 */
	@Column(name = "operator")
	private String operator;

	/**
	 * 最近修改时间
	 */
	@Column(name = "operatetime")
	private String operatetime;
	
	/**
	 * 申请延期截止日期
	 */
	@Column(name = "enddate2")
	private String enddate2;

	/**
	 * 是否督办
	 */
	@Column(name = "isduban")
	private Integer isduban;

	/**
	 * 督办次数
	 */
	@Column(name = "dubancount")
	private Integer dubancount;

	/**
	 * 删除标记
	 */
	@Column(name = "delTag", insertable = false, columnDefinition = "int default 1")
	private Integer delTag;
	
	/**
	 * 版本
	 */
	@Version
	@Column(name = "version")
	private Long version;
	
	/**
	 * 关联网格
	 */
	@Column(name = "gridCommunityId")
	private String gridCommunityId;
//	@OneToOne
//	@JoinColumn(name = "gridCommunityId", referencedColumnName = "id")
//	private GridCommunity gridCommunity;
	public CaseInfoCity() {
	}
	

}
