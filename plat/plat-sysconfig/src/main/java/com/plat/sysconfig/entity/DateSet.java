package com.plat.sysconfig.entity;

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
 * @description 日期设置
 * @author admin
 * @date 2020-06-01 23:54:27
 */
@Entity
@Data
@Table(name="DateSet")
public class DateSet implements Serializable {

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
    * 日期
    */
    @Column(name="datetime"  )
    private String datetime;

    /**
    * 类型
    */
    @Column(name="type"  )
    private String type;

    /**
    * 删除标记
    */
    @Column(name="delTag"  , insertable = false, columnDefinition = "int default 1")
    private Integer delTag;

    public DateSet() {
    }

}
