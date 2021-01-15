package com.plat.caseinfo.entity;

import lombok.Data;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.GeneratedValue;
import java.util.Date;
import java.util.List;

/**
 * @description 消息表
 * @author admin
 * @date 2021-01-11 19:37:44
 */
@Entity
@Data
@Table(name = "Message")
//配合MessageRepository 解决Springboot+JPA中多表关联查询会查询多次的问题（n+1查询问题）
//@NamedEntityGraph(name = "Message.MessageReceive", attributeNodes = {@NamedAttributeNode("messageReceiveList")})
@NamedEntityGraphs({
    @NamedEntityGraph(name = "Message.MessageReceive",attributeNodes = {
        @NamedAttributeNode(value = "messageReceiveList",subgraph = "MessageReceive.User")
    },
    subgraphs = {
        @NamedSubgraph(name = "MessageReceive.User",attributeNodes = {
            @NamedAttributeNode(value = "user",subgraph = "user")
        }),
        @NamedSubgraph(name = "user",attributeNodes = {})
    })
})
public class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	/**
	 * id
	 */
	@Column(name = "id", length = 32)
	private String id;

	/**
	 * 主题
	 */
	@Column(name = "title")
	private String title;

	/**
	 * 内容
	 */
	// @Lob代表是长字段类型，默认的话，是longtext类型，所以需要下面这个属性来指定对应的类型。
	@Lob
	@Column(name = "content", columnDefinition = "longtext")
	private String content;

	/**
	 * 是否推送短信 0：推送；1不推送；
	 */
	@Column(name = "sendSms")
	private Integer sendSms;
	
	/**
	 * 发送时间
	 */
	private String sendTime;
	@JsonIgnoreProperties(value = "message")
	@OneToMany(mappedBy = "message",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<MessageReceive> messageReceiveList;

	/**
	 * 删除标记
	 */
	@Column(name = "delTag", insertable = false, columnDefinition = "int default 1")
	private Integer delTag;
	
	
	public Message() {
	}

}
