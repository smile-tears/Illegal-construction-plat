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
 * @description 用户菜单
 * @author admin
 * @date 2020-06-03 19:39:08
 */
@Entity
@Data
@Table(name="User_Menu")
public class UserMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    /**
    * id
    */
    @Column(name="id" ,length = 32 )
    private String id;

    /**
    * 用户id
    */
    @Column(name="userId"  )
    private String userId;

    /**
    * 菜单id
    */
    @Column(name="menuId"  )
    private String menuId;
	
    public UserMenu() {
    }

}
