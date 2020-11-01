package com.plat.caseinfo.entity;

import lombok.Data;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.GeneratedValue;
import java.util.Date;

/**
 * @description 案件附件表-市
 * @author admin
 * @date 2020-06-13 15:23:13
 */
@Entity
@Data
@Table(name = "CaseInfo_File_Area")
public class CaseInfoFileArea implements Serializable {

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
	 * 文件名称
	 */
	@Column(name = "fileName")
	private String fileName;
	
	/**
	 * 存储路径
	 */
	@Column(name = "url")
	private String url;
	
	/**
	 * 压缩存储路径
	 */
	@Column(name = "thumbUrl")
	private String thumbUrl;

	/**
	 * 附件类型 0：文件；1：图片
	 */
	@Column(name = "fileType")
	private Integer fileType;

	@JsonIgnoreProperties(value = "files")
	@ManyToOne (fetch = FetchType.LAZY) 
	@JoinColumn(name = "requestLogId") 
	private CaseInfoRequestLogArea requestLog;
	
	public CaseInfoFileArea() {
	}

}
