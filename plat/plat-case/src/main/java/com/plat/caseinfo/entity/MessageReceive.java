package com.plat.caseinfo.entity;

import lombok.Data;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.plat.common.entity.User;

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

//    /**
//    * 接收人id
//    */
//    @Column(name="userid"  )
//    private String userid;
    
    
    @OneToOne
    @JoinColumn(name = "userid" ,referencedColumnName="id")
    private User user;

    
    /**
     * 状态 0：已阅读；1未阅读
     */
    @Column(name="status"  )
    private Integer status;
    
    @JsonIgnoreProperties(value = "messageReceiveList")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "messageId")
    private Message message;
	/**
	 * 删除标记
	 */
	@Column(name = "delTag", insertable = false, columnDefinition = "int default 1")
	private Integer delTag;
    public MessageReceive() {
    }

}
