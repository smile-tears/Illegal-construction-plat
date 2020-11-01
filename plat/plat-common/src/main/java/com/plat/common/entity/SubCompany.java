package com.plat.common.entity;

import lombok.Data;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.GeneratedValue;
import java.util.Date;

/**
 * @description 分部
 * @author admin
 * @date 2020-05-20 19:26:12
 */
@Entity
@Data
@Table(name = "SubCompany")
public class SubCompany implements Serializable {

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
	 * 分部名称
	 */
	@Column(name = "subCompanyName")
	private String subCompanyName;

	/**
	 * 上级分部ID
	 */
	@Column(name = "supSubCompanyId")
	private String supSubCompanyId;

	/**
	 * 排序
	 */
	@Column(name = "showOrder")
	private Integer showOrder;

	/**
	 * 封存标识
	 */
	@Column(name = "canceled")
	private Integer canceled;

	/**
	 * 分部编码
	 */
	@Column(name = "subCompanyCode")
	private String subCompanyCode;
	
	/**
	 * 分部树图标
	 */
	@Column(name = "icon")
	private String icon;

	/**
	 * 是否删除
	 */
	@Column(name = "delTag", insertable = false, columnDefinition = "int default 1")
	private Integer delTag;
	
	/**
	 * 是否是独立站点
	 */
	@Column(name = "aloneSite")
	private Integer aloneSite;
	
	public SubCompany() {
	}

}
