package com.plat.sysconfig.entity;

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
import javax.persistence.GenerationType;
import java.util.List;

/**
 * @description 字典
 */
@Entity
@Data
@Table(name = "Dictionary")
public class Dictionary implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "id", length = 32)
	private String id;

	/**
	 * 类型代号
	 */
	@Column(name = "typeCode",unique = true,nullable = true,length = 50)
	private String typeCode;

	/**
	 * 类型名称
	 */
	@Column(name = "typeName")
	private String typeName;
	
	/**
	 * 是否显示
	 */
	@Column(name = "isShow")
	private Integer isShow;

	/**
	 * 删除标记
	 */
	@Column(name = "delTag",insertable = false,columnDefinition="int default 1")
	private Integer delTag;
	
	/**
	 * onetomany 一对多
	 */
	@OneToMany(mappedBy = "dictionary", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	private List<DictionaryDetail> dictionaryDetails;


}
