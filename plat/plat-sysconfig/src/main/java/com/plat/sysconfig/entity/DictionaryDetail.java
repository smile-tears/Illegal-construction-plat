package com.plat.sysconfig.entity;

import lombok.Data;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
/**
 * @description 字典
 */
@Entity
@Data
@Table(name = "DictionaryDetail")
public class DictionaryDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "id", length = 32)
	private String id;

	/**
	 *字典代号
	 */
	@Column(name = "selectName")
	private String selectName;

	/**
	 * 字典名称
	 */
	@Column(name = "selectValue")
	private String selectValue;
	
	/**
	 * 是否显示
	 */
	@Column(name = "isShow")
	private Integer isShow;

	/**
	 * 删除标记
	 */
	@Column(name = "delTag")
	private Integer delTag;
	
	/**
	 * 主表
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dictionaryId")
	private Dictionary dictionary;

}
