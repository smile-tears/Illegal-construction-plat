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
 * @description 岗位
 * @author admin
 * @date 2020-05-23 21:32:25
 */
@Entity
@Data
@Table(name="JobMask")
public class JobMask implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    /**
    * id
    */
    @Column(name="id" ,length = 32)
    private String id;

    /**
    * 岗位编码
    */
    @Column(name="jobCode" )
    private String jobCode;

    /**
    * 岗位名称
    */
    @Column(name="jobName" )
    private String jobName;
    
    /**
     * 岗位简称
     */
    @Column(name="jobMask" )
    private String jobMask;

    /**
    * 岗位描述
    */
    @Column(name="jobDesc" )
    private String jobDesc;

    /**
     * 岗位描述
     */
    @Column(name="showOrder" )
    //test
    private String showOrder;


    /**
    * 删除标记
    */
    @Column(name = "delTag", insertable = false, columnDefinition = "int default 1")
    private Integer delTag;

    public JobMask() {
    }

}
