package com.plat.caseinfo.entity;

import lombok.Data;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.GeneratedValue;
import java.util.Date;

/**
 * @description 人员轨迹
 * @author admin
 * @date 2020-11-17 16:09:16
 */
@Entity
@Data
@Table(name = "UserPosition")
public class UserPosition implements Serializable {

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
	 * 人员id
	 */
	@Column(name = "userid")
	private String userid;

	/**
	 * 纬度
	 */
	@Column(name = "lat")
	private String lat;

	/**
	 * 经度
	 */
	@Column(name = "lng")
	private String lng;
	
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//@JsonIgnore // springboot 默认序列化工具是Jackson，如果用到阿里巴巴的序列列化类需要用@JSONField
	//@JSONField(serialize = false) //JSONField来自com.alibaba.fastjson.annotation包的
	private Date uploadTime;

	public UserPosition() {
	}

}
