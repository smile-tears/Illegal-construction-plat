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
 * @description 网格管理
 * @author admin
 * @date 2020-06-06 12:35:07
 */
@Entity
@Data
@Table(name="GridCommunity")
public class GridCommunity implements Serializable {

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
    * 网格名称
    */
    @Column(name="gridName"  )
    private String gridName;

    /**
    * 责任人
    */
    @Column(name="manager"  )
    private String manager;

    /**
    * 责任人联系方式
    */
    @Column(name="managerContactNum"  )
    private String managerContactNum;

    /**
     * 显示顺序
     */
    @Column(name = "showOrder")
    private Integer showOrder;

    /**
    * 责任部门
    */
    @Column(name="managerDept"  )
    private String managerDept;

    /**
    * 网格区域
    */
    @Column(name="area"  )
    private String area;

    /**
    * 网格区域编码
    */
    @Column(name="gridAreaCode"  )
    private String gridAreaCode;

    /**
    * 巡查人员
    */
    @Column(name="patrolManager"  )
    private String patrolManager;

    /**
    * 网格颜色
    */
    @Column(name="icon"  )
    private String icon;

    /**
    * 区域编号
    */
    @Column(name="number"  )
    private String number;

    /**
    * 网格中心坐标
    */
    @Column(name="coordinate"  )
    private String coordinate;

    /**
    * 删除标记
    */
    @Column(name="delTag"  , insertable = false, columnDefinition = "int default 1")
    private Integer delTag;

    /**
    * 父级id
    */
    @Column(name="pid"  )
    private String pid;

    /**
     * 网格区域坐标
     */
    @Column(name="gridPosition"  )
    private String gridPosition;


    public GridCommunity() {
    }

}
