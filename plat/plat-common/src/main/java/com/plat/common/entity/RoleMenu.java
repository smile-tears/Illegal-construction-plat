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
 * @description 角色菜单
 * @author admin
 * @date 2020-06-01 20:09:31
 */
@Entity
@Data
@Table(name = "Role_Menu")
public class RoleMenu implements Serializable {

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
	 * 角色id
	 */
	@Column(name = "roleId")
	private String roleId;

	/**
	 * 菜单id
	 */
	@Column(name = "menuId")
	private String menuId;

	public RoleMenu() {
	}

}
