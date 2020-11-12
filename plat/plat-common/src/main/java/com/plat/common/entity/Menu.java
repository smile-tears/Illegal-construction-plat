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
 * @description 菜单
 * @author admin
 * @date 2020-05-21 18:49:06
 */
@Entity
@Data
@Table(name = "Menu")
public class Menu implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	/**
	 * id
	 */
	@Column(name = "id", length = 32)
	private String id;
	
	private String menuName;
	
	private String path;

	/**
	 * 菜单标识
	 */
	private String name;
	
	/**
	 * 组件名称
	 */
	private String component;
	
	private String props;
	
	/**
	 * 重定向
	 */
	private String redirect;

	private String meta;

	/**
	 * 父级id
	 */
	@Column(name = "supMenuid")
	private String supMenuid;

	/**
	 * 是否显示
	 */
	@Column(name = "visible")
	private Integer visible;

	/**
	 * 显示顺序
	 */
	@Column(name = "showOrder")
	private Integer showOrder;
	/**
	 * 删除标记
	 */
	@Column(name = "delTag", insertable = false, columnDefinition = "int default 1")
	private Integer delTag;
	
	

	
	public Menu() {
	}

}
