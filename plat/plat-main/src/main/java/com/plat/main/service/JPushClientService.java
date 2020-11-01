package com.plat.main.service;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.ServiceHelper;
import cn.jiguang.common.connection.NativeHttpClient;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jsms.api.SendSMSResult;
import cn.jsms.api.common.SMSClient;
import cn.jsms.api.common.model.BatchSMSPayload;
import cn.jsms.api.common.model.BatchSMSResult;
import cn.jsms.api.common.model.RecipientPayload;
import cn.jsms.api.common.model.SMSPayload;
import com.alibaba.fastjson.JSONObject;
import com.plat.main.config.JPushConfig;
import com.plat.main.config.JPushConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *  极光推送push消息Service
 */
@Service
public class JPushClientService {

    private final static Logger log = LoggerFactory.getLogger(JPushClientService.class);

    @Autowired
    private JPushConfig jPushConfig;

    /**
     * 推送push消息
     *
     * @param platform       指定推送平台
     * @param audience       指定推送范围
     * @param audienceValues 指定推送目标
     * @param title          通知标题
     * @param message        通知内容
     * @param extras         扩展字段
     * @return int
     */
    public int sendPush(String platform, String audience, List<String> audienceValues, String title, String message, String extras) {
        // 构建推送对象
        PushPayload payload = buildPushPayload(platform, audience, audienceValues, title, message, extras);
        // 推送push消息
        return sendPush(payload);
    }

    /**
     * 推送push消息
     *
     * @param payload 推送对象
     * @return int
     */
    public int sendPush(PushPayload payload) {
        log.info("开始推送push消息");
        ClientConfig clientConfig = ClientConfig.getInstance();
        ClientConfig.getInstance().setPushHostName("https://bjapi.push.jiguang.cn");

        String authCode = ServiceHelper.getBasicAuthorization(jPushConfig.getAppKey(), jPushConfig.getMasterSecret());

        NativeHttpClient httpClient = new NativeHttpClient(authCode, null, clientConfig);

        JPushClient jpushClient = new JPushClient(jPushConfig.getMasterSecret(), jPushConfig.getAppKey(), null, clientConfig);
        jpushClient.getPushClient().setHttpClient(httpClient);
        try {
            PushResult res = jpushClient.sendPush(payload);
            if (res.isResultOK()) {
                log.info("推送成功，PushResult：{}", res);
                return 2;
            } else {
                log.info("推送失败，PushResult：{}", res);
                return 3;
            }
        } catch (APIConnectionException e) {
            log.error("Connection error. Should retry later. ", e);
            log.error("Sendno: " + payload.getSendno());
            return 3;
        } catch (APIRequestException e) {
            log.error("Error response from JPush server. Should review and fix it. ", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Code: " + e.getErrorCode());
            log.info("Error Message: " + e.getErrorMessage());
            log.info("Msg ID: " + e.getMsgId());
            log.error("Sendno: " + payload.getSendno());
            return 3;
        }
    }

    /**
     * 构建推送对象
     *
     * @param platform       指定推送平台
     * @param audience       指定推送范围
     * @param audienceValues 指定推送目标
     * @param title          通知标题
     * @param message        通知内容
     * @param extras         扩展字段
     * @return PushPayload
     */
    public PushPayload buildPushPayload(String platform, String audience, List<String> audienceValues, String title, String message, String extras) {
        return PushPayload.newBuilder()
                .setPlatform(buildPlatform(platform)) // Platform指定推送平台
                .setAudience(buildAudience(audience, audienceValues)) // Audience指定推送目标
                .setNotification(buildNotification(title, message, extras)) // Notification推送通知内容体
                .setOptions(Options.newBuilder().setTimeToLive(jPushConfig.getLiveTime()).build()) // Options推送参数，设置离线消息保留时长
                .build();
    }

    /**
     * 构建推送平台
     *
     * @param platform 指定推送平台
     * @return Platform
     */
    private static Platform buildPlatform(String platform) {
        switch (platform) {
            case JPushConstants.PLATFORM_ALL:
                return Platform.all();
            case JPushConstants.PLATFORM_ANDROID:
                return Platform.android();
            case JPushConstants.PLATFORM_IOS:
                return Platform.ios();
            case JPushConstants.PLATFORM_WINPHONE:
                return Platform.winphone();
            case JPushConstants.PLATFORM_ANDROID_IOS:
                return Platform.android_ios();
            case JPushConstants.PLATFORM_ANDROID_WINPHONE:
                return Platform.android_winphone();
            case JPushConstants.PLATFORM_IOS_WINPHONE:
                return Platform.ios_winphone();
        }
        return Platform.all();
    }

    /**
     * 构建推送目标
     *
     * @param audience       指定推送范围
     * @param audienceValues 指定推送目标
     * @return Audience
     */
    private static Audience buildAudience(String audience, List<String> audienceValues) {
        switch (audience) {
            case JPushConstants.AUDIENCE_ALL:
                return Audience.all();
            case JPushConstants.AUDIENCE_TAG:
                return Audience.tag(audienceValues);
            case JPushConstants.AUDIENCE_TAG_AND:
                return Audience.tag_and(audienceValues);
            case JPushConstants.AUDIENCE_TAG_NOT:
                return Audience.tag_not(audienceValues);
            case JPushConstants.AUDIENCE_ALIAS:
                return Audience.alias(audienceValues);
            case JPushConstants.AUDIENCE_REGISTRATION_ID:
                return Audience.registrationId(audienceValues);
            case JPushConstants.AUDIENCE_SEGMENT:
                return Audience.segment(audienceValues);
            case JPushConstants.AUDIENCE_ABTEST:
                return Audience.abTest(audienceValues);
        }
        return Audience.all();
    }

    /**
     * 构建通知内容体
     *
     * @param title   通知标题
     * @param message 通知内容
     * @param extras  扩展字段
     * @return Notification
     */
    private static Notification buildNotification(String title, String message, String extras) {
        Notification.Builder notification = Notification.newBuilder()
                .setAlert(message) // alert通知，推送到Platform指定的多个平台
                .addPlatformNotification(AndroidNotification.newBuilder().setTitle(title).setBuilderId(2).addExtra("extras", extras).build()) // 构建Android平台上的通知结构
                .addPlatformNotification(IosNotification.newBuilder().incrBadge(1).addExtra("extras", extras).build()); // 构建iOS平台上的通知结构
        return notification.build();
    }

    /**
     * 发送模板短信
     *
     * @param phone  手机号
     * @param params 模板变量
     */
    public void sendTemplateSMS(String phone, Map<String, String> params) {
        sendTemplateSMS(phone, params, jPushConfig.getSmsTempId());
    }

    /**
     * 发送模板短信
     *
     * @param phone  手机号
     * @param params 模板变量
     * @param tempId 短信模板ID
     */
    public void sendTemplateSMS(String phone, Map<String, String> params, int tempId) {
        log.info("开始发送模板短信");
        Thread thread = new Thread(() -> {
            SMSPayload.Builder payload = SMSPayload.newBuilder();
            payload.setMobileNumber(phone);
            payload.setTempId(tempId);
            for (Map.Entry<String, String> entry : params.entrySet()) {
                payload.addTempPara(entry.getKey(), entry.getValue());
            }
            try {
                SMSClient client = new SMSClient(jPushConfig.getMasterSecret(), jPushConfig.getAppKey());
                SendSMSResult res = client.sendTemplateSMS(payload.build());
                log.info("SendSMSResult：{}", res);
                if (res.getResponseCode() == 200) {
                    log.info("发送成功 手机号:" + phone + " 短信消息");
                } else {
                    log.info("发送失败 手机号:" + phone + " 短信消息");
                }
            } catch (APIConnectionException e) {
                log.error("Connection error. Should retry later. ", e);
            } catch (APIRequestException e) {
                log.error("Error response from JPush server. Should review and fix it. ", e);
                log.info("HTTP Status: " + e.getStatus());
                log.info("Error Code: " + e.getErrorCode());
                log.info("Error Message: " + e.getMessage());
            }
        });
        thread.start();
    }

    /**
     * 批量发送模板短信
     *
     * @param phoneList 手机号
     * @param params    模板变量
     * @param tempId    短信模板ID
     */
    public void sendBatchTemplateSMS(List<String> phoneList, Map<String, String> params, int tempId) {
        log.info("开始批量发送模板短信");
        List<RecipientPayload> list = new ArrayList<>();
        for (String phone : phoneList) {
            RecipientPayload.Builder payload = RecipientPayload.newBuilder();
            payload.setMobile(phone);
            for (Map.Entry<String, String> entry : params.entrySet()) {
                payload.addTempPara(entry.getKey(), entry.getValue());
            }
            list.add(payload.build());
        }
        RecipientPayload[] recipientPayloads = new RecipientPayload[list.size()];
        BatchSMSPayload smsPayload = BatchSMSPayload.newBuilder()
                .setTempId(tempId)
                .setRecipients(list.toArray(recipientPayloads))
                .build();

        Thread thread = new Thread(() -> {
            long start = System.currentTimeMillis();
            try {
                SMSClient client = new SMSClient(jPushConfig.getMasterSecret(), jPushConfig.getAppKey());
                BatchSMSResult res = client.sendBatchTemplateSMS(smsPayload);
                log.info("BatchSMSResult：{}", res);
                for (String phone : phoneList) {
                    log.info("手机号:" + phone + " 短信消息");
                }
            } catch (APIConnectionException e) {
                log.error("Connection error. Should retry later. ", e);
            } catch (APIRequestException e) {
                log.error("Error response from JPush server. Should review and fix it. ", e);
                log.info("HTTP Status: " + e.getStatus());
                log.info("Error Code: " + e.getErrorCode());
                log.info("Error Message: " + e.getMessage());
            }
            log.info("批量发送模板短信，耗时" + (System.currentTimeMillis() - start) + "毫秒 ");
        });
        thread.start();
    }

}
