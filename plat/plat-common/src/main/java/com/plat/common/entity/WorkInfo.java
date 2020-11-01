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
 * @description 工作信息
 * @author admin
 * @date 2020-06-06 19:18:00
 */
@Entity
@Data
@Table(name="WorkInfo")
public class WorkInfo implements Serializable {

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
    * 所在公司
    */
    @Column(name="companyName"  )
    private String companyName;

    /**
    * 岗位
    */
    @Column(name="post"  )
    private String post;

    /**
    * 开始时间
    */
    @Column(name="startDate"  )
    private Date startDate;

    /**
    * 结束时间
    */
    @Column(name="endDate"  )
    private Date endDate;

    /**
    * 用户id
    */
    @Column(name="userId"  )
    private String userId;

    public WorkInfo() {
    }

}
