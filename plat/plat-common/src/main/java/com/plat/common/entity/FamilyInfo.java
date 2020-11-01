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
 * @description 家庭信息
 * @author admin
 * @date 2020-06-06 19:22:22
 */
@Entity
@Data
@Table(name="FamilyInfo")
public class FamilyInfo implements Serializable {

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
    * 姓名
    */
    @Column(name="name"  )
    private String name;

    /**
    * 称谓
    */
    @Column(name="title"  )
    private String title;

    /**
    * 联系方式
    */
    @Column(name="contactNum"  )
    private String contactNum;

    /**
    * 住址
    */
    @Column(name="address"  )
    private String address;

    /**
    * 用户id
    */
    @Column(name="userId"  )
    private String userId;

    public FamilyInfo() {
    }

}
