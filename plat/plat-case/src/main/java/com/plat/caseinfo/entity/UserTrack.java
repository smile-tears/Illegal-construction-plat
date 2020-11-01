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
 * @description 人员轨迹
 * @author admin
 * @date 2020-07-27 22:34:56
 */
@Entity
@Data
@Table(name="UserTrack")
public class UserTrack implements Serializable {

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
    * 用户id
    */
    @Column(name="userid"  )
    private String userid;

    /**
    * 经度
    */
    @Column(name="lng"  )
    private String lng;

    /**
    * 纬度
    */
    @Column(name="lat"  )
    private String lat;

    /**
    * 上传时间
    */
    @Column(name="uploadTime"  )
    private String uploadTime;


}
