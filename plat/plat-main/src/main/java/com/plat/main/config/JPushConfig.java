package com.plat.main.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jpush")
public class JPushConfig {

    /**
     * AppKey
     */
    private String appKey;

    /**
     * 密钥
     */
    private String masterSecret;

    private String groupKey;

    private String groupMasterSecert;

    /**
     * 短信模板ID
     */
    private int smsTempId;

    /**
     * 离线消息保留时长(秒)
     */
    private int liveTime;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getMasterSecret() {
        return masterSecret;
    }

    public void setMasterSecret(String masterSecret) {
        this.masterSecret = masterSecret;
    }

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public String getGroupMasterSecert() {
        return groupMasterSecert;
    }

    public void setGroupMasterSecert(String groupMasterSecert) {
        this.groupMasterSecert = groupMasterSecert;
    }

    public int getSmsTempId() {
        return smsTempId;
    }

    public void setSmsTempId(int smsTempId) {
        this.smsTempId = smsTempId;
    }

    public int getLiveTime() {
        return liveTime;
    }

    public void setLiveTime(int liveTime) {
        this.liveTime = liveTime;
    }

}