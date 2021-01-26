package com.plat.caseinfo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Table(name = "CaseQuestion")
public class CaseQuestion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	/**
	 * id
	 */
	@Column(name = "id", length = 32)
	private String id;
	
	private String questionType;
	
	/**
	 * 所属一级类型
	 */
	private String fstLvlType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "caseInfoCityId")
	@JsonIgnoreProperties(value = "caseQuestions")
	private CaseInfoCity caseInfoCity;

}
