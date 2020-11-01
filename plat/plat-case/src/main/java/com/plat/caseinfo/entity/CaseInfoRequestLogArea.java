package com.plat.caseinfo.entity;

import lombok.Data;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.GeneratedValue;
import java.util.Date;
import java.util.List;

/**
 * @description 案件流转表-市
 * @author admin
 * @date 2020-06-13 15:16:53
 */
@Entity
@Data
@Table(name = "CaseInfo_RequestLog_Area")
public class CaseInfoRequestLogArea implements Serializable {

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
	 * 关联案件id
	 */
	@Column(name = "caseid")
	private String caseid;
	
	/**
	 * 当前节点
	 */
	@Column(name = "nodeid")
	private String nodeid;
	
	/**
	 * 目标节点
	 */
	@Column(name = "destnodeid")
	private String destnodeid;


	/**
	 * 接收时间
	 */
	@Column(name = "receivetime")
	private String receivetime;

	/**
	 * 处理人
	 */
	@Column(name = "operator")
	private String operator;

	/**
	 * 处理时间
	 */
	@Column(name = "operatetime")
	private String operatetime;

	/**
	 * 处置类型/主办/协办/核查
	 */
	@Column(name = "operatorType")
	private Integer operatorType;
	
	/**
	 * 主办人
	 */
	private String sponsor;
	
	/**
	 * 协办人
	 */
	private String coOrganizer;
	
	/**
	 * 核查人
	 */
	private String verifier;
	
	/**
	 * 办理状态
	 */
	@Column(name = "status")
	private Integer status;

	/**
	 * 处理结果
	 */
	@Column(name = "result")
	private String result;
	
	/**
	 * 截止时限
	 */
	@Column(name = "enddate")
	private String enddate;
	
	/**
	 * 当前步骤上传图片地址
	 */
//	@Column(name = "picpath")
//	private String picpath;

	/**
	 * 处理意见
	 */
	@Column(name = "idea")
	private String idea;

	/**
	 * 处置时限
	 */
	@Column(name = "limittimes")
	private String limittimes;

	/**
	 * 案件描述
	 */
	@Column(name = "description")
	private String description;

	@JsonIgnoreProperties(value = "requestLog")
	@OneToMany(mappedBy = "requestLog", cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    private List<CaseInfoFileArea> files;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	// @JsonFormat主要是后台到前台的时间格式的转换，@DateTimeFormat主要是前后到后台的时间格式的转换
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	public CaseInfoRequestLogArea() {
	}

}
