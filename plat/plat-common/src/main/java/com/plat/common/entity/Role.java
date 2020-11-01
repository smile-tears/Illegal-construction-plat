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
 * @description 角色
 * @author admin
 * @date 2020-05-27 23:40:46
 */
@Entity
@Data
@Table(name="Role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    /**
    * 角色id
    */
    @Column(name="id" ,length = 32 )
    private String id;

    /**
    * 角色名称
    */
    @Column(name="roleName"  )
    private String roleName;

    /**
    * 角色描述
    */
    @Column(name="roleDesc"  )
    private String roleDesc;

    /**
    * 删除标记
    */
    @Column(name="delTag"  , insertable = false, columnDefinition = "int default 1")
    private Integer delTag;

    public Role() {
    }

}
