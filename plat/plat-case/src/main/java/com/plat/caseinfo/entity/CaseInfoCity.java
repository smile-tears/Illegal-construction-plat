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
	 * 被检单位
	 */
	@Column(name = "companyId")
	private String companyId;
	
	/**
	 * 地址
	 */
	@Column(name = "locationDesc")
	private String locationDesc;
	
	/**
	 * 负责人
	 */
	private String manager;
	
	/**
	 * 负责人联系电话
	 */
	private String managerMobile;
	
	/**
     * 	安全员
     */
    private String reportor;
    
    /**
     * 	安全员联系电话
     */
    private String reportorMobile;
	
	/**
	 * 检查场所
	 */
	private String site;
	
	/**
	 * 检查时间
	 */
	@Column(name = "verifyTime")
	private String verifyTime;
	
	/**
	 * 上报时间
	 */
	@Column(name = "reportTime")
	private String reportTime;
	
	/**
	 * 处置时限（小时）
	 */
	@Column(name = "limittimes")
	private double limittimes;
	
	/**
	 * 案件结束时间
	 */
	private String endDate;
	
	/**
	 * 备注
	 */
	@Column(name = "caseDesc")
	private String caseDesc;
	
	/**
	 * 纬度
	 */
	private String lng;
	
	/**
	 * 经度
	 */
	private String lat;
	
	/**
	 * 开始时间
	 */
	private String startTime;
	
	/**
	 * 结束时间
	 */
	private String endTime;
	
//	/**
//	 * 关联网格
//	 */
//	@Column(name = "gridId")
//	private String gridId;
//	@OneToOne
//	@JoinColumn(name = "gridCommunityId", referencedColumnName = "id")
//	private GridCommunity gridCommunity;
	
//	

//	
	
	/**
     * 案件上报附件名称
     */
    @Column(length = 1000  )
    private String fileName;
    
    /**
     * 案件上报附件路径
     */
    @Column(length = 1000  )
    private String filePath;
    
    /**
     * 案件上报图片名称
     */
    @Column(length = 1000  )
    private String imageName;
    
    /**
     *案件上报图片路径
     */
    @Column(length = 1000  )
    private String imagePath;
	
    
	/**
	 * 案件当前状态 0暂存，1上报,2处置
	 */
	@Column(name = "status")
	private Integer status;

	/**
	 * 处置状态 0未处置，1已处置
	 */
	@Column(name = "handleState")
	private Integer handleState;
	
	/**
	 * 处置意见
	 */
	@Column(name = "handleIdea")
	private String handleIdea;
	
	/**
     * 案件处置附件路径
     */
    @Column(length = 1000  )
    private String filePath2;
    
    /**
     * 案件处置附件名称
     */
    @Column(length = 1000  )
    private String fileName2;
    
	/**
     * 案件处置图片名称
     */
	@Column(length = 1000  )
    private String imageName2;
    
    /**
     *案件处置图片路径
     */
	@Column(length = 1000  )
    private String imagePath2;

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
