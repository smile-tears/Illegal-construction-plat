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
 * @date 2020-11-26 18:34:01
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
    * 显示顺序
    */
    @Column(name="showOrder"  )
    private Integer showOrder;

	/**
	 * 删除标记
	 */
	@Column(name = "delTag", insertable = false, columnDefinition = "int default 1")
	private Integer delTag;
    public QuestionType() {
    }

}
