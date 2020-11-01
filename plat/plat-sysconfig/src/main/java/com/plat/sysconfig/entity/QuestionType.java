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
 * @description 问题类型
 * @author admin
 * @date 2020-06-08 21:52:10
 */
@Entity
@Data
@Table(name="QuestionType")
public class QuestionType implements Serializable {

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
    * 类型名称
    */
    @Column(name="typeName"  )
    private String typeName;

    /**
    * 行政区划代码
    */
    @Column(name="areaCode"  )
    private String areaCode;

    /**
    * 大类码
    */
    @Column(name="bigCode"  )
    private String bigCode;

    /**
    * 小类码
    */
    @Column(name="smallCode"  )
    private String smallCode;

    /**
    * 分数
    */
    @Column(name="score"  )
    private Integer score;

    /**
    * 别名
    */
    @Column(name="alias"  )
    private String alias;

    /**
    * 快捷名称
    */
    @Column(name="shortcutName"  )
    private String shortcutName;

    /**
    * 处理时限
    */
    @Column(name="processTimeLimit"  )
    private Float processTimeLimit;

    /**
    * 父级id
    */
    @Column(name="pid"  )
    private String pid;

    /**
     * showOrder
     */
     @Column(name="showOrder"  )
     private String showOrder;
     
	/**
	 * 删除标记
	 */
	@Column(name = "delTag", insertable = false, columnDefinition = "int default 1")
	private Integer delTag;
    public QuestionType() {
    }

}
