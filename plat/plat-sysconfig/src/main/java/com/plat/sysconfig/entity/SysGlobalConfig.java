package com.plat.sysconfig.entity;

import lombok.Data;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.GeneratedValue;
import java.util.Date;

/**
 * @description 系统全局配置
 * @author admin
 * @date 2020-05-28 22:25:59
 */
@Entity
@Data
@Table(name = "SysGlobalConfig")
public class SysGlobalConfig implements Serializable {

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
	 * 上午上班开始时间
	 */
	@JsonFormat(pattern = "HH:mm")
	@DateTimeFormat(pattern = "HH:mm")
	@Column(name = "startTimeOfWork1")
	private String startTimeOfWork1;
	
	/**
	 * 上午上班结束时间
	 */
	@Column(name = "endTimeOfWork1")
	private String endTimeOfWork1;
	
	/**
	 * 下午上班开始时间
	 */
	@Column(name = "startTimeOfWork2")
	private String startTimeOfWork2;

	/**
	 * 下午上班结束时间
	 */
	@Column(name = "endTimeOfWork2")
	private String endTimeOfWork2;

	/**
	 * 上传轨迹频率
	 */
	@Column(name = "uploadTrackFrequency")
	private Integer uploadTrackFrequency;

	/**
	 * 地图引擎
	 */
	@Column(name = "mapEngine")
	private Integer mapEngine;

	/**
	 * 工作时间计算类型
	 */
	@Column(name = "calculateTypeOfWorkTime")
	private Integer calculateTypeOfWorkTime;

	public SysGlobalConfig() {
	}

}
