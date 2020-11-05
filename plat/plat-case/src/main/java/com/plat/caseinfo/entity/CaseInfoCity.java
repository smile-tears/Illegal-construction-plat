package com.plat.caseinfo.entity;

import lombok.Data;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;


import javax.persistence.GeneratedValue;
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
	 * 标题
	 */
	private String title;
	
	/**
	 * 上报日期
	 */
	@Column(name = "reportDate")
	private String reportDate;
	
	/**
	 * 上报时间
	 */
	@Column(name = "reportTime")
	private String reportTime;
	
	/**
	 * 上报人
	 */
	@Column(name = "reportor")
	private String reportor;
	
	/**
	 * 处置时限（小时）
	 */
	@Column(name = "limittimes")
	private double limittimes;
	
	/**
	 * 备注
	 */
	@Column(name = "caseDesc")
	private String caseDesc;
	
	/**
	 * 关联网格
	 */
	@Column(name = "gridId")
	private String gridId;
//	@OneToOne
//	@JoinColumn(name = "gridCommunityId", referencedColumnName = "id")
//	private GridCommunity gridCommunity;
	
	/**
	 * 公司id
	 */
	@Column(name = "companyId")
	private String companyId;
	
	/**
	 * 位置描述
	 */
	@Column(name = "locationDesc")
	private String locationDesc;

	/**
	 * 纬度
	 */
	private String lng;
	
	/**
	 * 经度
	 */
	private String lat;
	
	/**
     * 附件名称
     */
    @Column(length = 2000  )
    private String fileName;
    
    /**
     * 附件路径
     */
    @Column(length = 2000  )
    private String filePath;
	
	/**
	 * 案件当前状态
	 */
	@Column(name = "status")
	private Integer status;


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
	
	
	public CaseInfoCity() {
	}
	

}
