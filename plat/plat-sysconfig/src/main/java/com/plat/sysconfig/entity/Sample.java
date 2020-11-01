package com.plat.sysconfig.entity;

import lombok.Data;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.GeneratedValue;
import java.util.Date;

/**
 * @description 样本
 * @author admin
 * @date 2020-06-14 13:21:04
 */
@Entity
@Data
@Table(name = "Sample")
public class Sample implements Serializable {

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
	 * 样本名称
	 */
	@Column(name = "sampleName")
	private String sampleName;

	/**
	 * 关联网格
	 */
	@OneToOne
	@JoinColumn(name = "gridCommunityId", referencedColumnName = "id")
	private GridCommunity gridCommunity;

	/**
	 * 父级样本
	 */
	@Column(name = "supSampleId")
	private String supSampleId;

	/**
	 * 删除标记
	 */
	@Column(name = "delTag", insertable = false, columnDefinition = "int default 1")
	private Integer delTag;

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
	
	public Sample() {
	}

}
