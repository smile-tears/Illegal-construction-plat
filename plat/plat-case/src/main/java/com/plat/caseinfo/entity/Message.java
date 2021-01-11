package com.plat.caseinfo.entity;

import lombok.Data;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.GeneratedValue;
import java.util.Date;

/**
 * @description 消息表
 * @author admin
 * @date 2021-01-11 19:37:44
 */
@Entity
@Data
@Table(name = "Message")
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
	 * 删除标记
	 */
	@Column(name = "delTag", insertable = false, columnDefinition = "int default 1")
	private Integer delTag;

	public Message() {
	}

}
