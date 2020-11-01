package com.plat.common.utils.idutils;


/**
 * ClassName: IdUtil <br/>
 * @Author: Cui.xx  <br/>
 * Date: 2019/4/12 17:46 <br/>
 * Version: 1.0 <br/>
 * Description:  <br/>
 */
public class IdUtil {
    private static IdGenerator idGenerator = new SnowflakeIdGenerator(1L);

    public static synchronized long getId() {
        return idGenerator.nextId();
    }

}
