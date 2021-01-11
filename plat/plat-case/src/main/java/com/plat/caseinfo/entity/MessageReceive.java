package com.plat.caseinfo.entity;

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
 * @description 消息接收
 * @author admin
 * @date 2021-01-11 19:39:32
 */
@Entity
@Data
@Table(name="MessageReceive")
public class MessageReceive implements Serializable {

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
    * 消息id
    */
    @Column(name="messageId"  )
    private String messageId;

    /**
    * 接收人id
    */
    @Column(name="userid"  )
    private String userid;

    /**
    * 姓名
    */
    @Column(name="username"  )
    private String username;

    
    /**
     * 状态 0：已阅读；1未阅读
     */
    @Column(name="status"  )
    private Integer status;
	/**
	 * 删除标记
	 */
	@Column(name = "delTag", insertable = false, columnDefinition = "int default 1")
	private Integer delTag;
    public MessageReceive() {
    }

}
