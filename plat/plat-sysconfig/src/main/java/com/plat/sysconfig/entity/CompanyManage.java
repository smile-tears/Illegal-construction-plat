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
 * @description 公司管理
 * @author admin
 * @date 2020-11-03 20:28:50
 */
@Entity
@Data
@Table(name="CompanyManage")
public class CompanyManage implements Serializable {

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
    * 公司名称
    */
    @Column(name="companyName"  )
    private String companyName;

    /**
    * 公司地址
    */
    @Column(name="address"  )
    private String address;

    /**
    * 公司法人
    */
    @Column(name="legalPerson"  )
    private String legalPerson;

    /**
    * 网格
    */
    @Column(name="grid"  )
    private String grid;

	/**
	 * 删除标记
	 */
	@Column(name = "delTag", insertable = false, columnDefinition = "int default 1")
	private Integer delTag;
	
	@Column(name="showOrder"  )
    private Integer showOrder;
	
    public CompanyManage() {
    }

}
