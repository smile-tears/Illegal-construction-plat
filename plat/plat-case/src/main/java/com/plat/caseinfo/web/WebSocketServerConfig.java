package com.plat.caseinfo.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.plat.caseinfo.entity.CaseInfoCity;
import com.plat.caseinfo.service.CaseInfoCityService;
import com.plat.caseinfo.service.CaseInfoCityServiceImpl;
import com.plat.common.entity.Page;

import net.bytebuddy.asm.Advice.This;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint("/websocket/{type}/{sid}")
@Component
public class WebSocketServerConfig {
	private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext context){
        applicationContext = context;
    }
    // 定义变量
    // private CaseInfoCityService caseInfoCityService;
	
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static AtomicInteger onlineNum = new AtomicInteger();
    private static AtomicInteger onlineNum2 = new AtomicInteger();

    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServer对象。
    private static ConcurrentHashMap<String, Session> sessionPools = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, Session> sessionPools2 = new ConcurrentHashMap<>();

    //发送消息
    public void sendMessage(Session session, String message) throws IOException {
        if(session != null){
            synchronized (session) {
//                System.out.println("发送数据：" + message);
                session.getBasicRemote().sendText(message);
            }
        }
    }
    //给指定用户发送信息
    public void sendInfo(String userName, String message){
        Session session = sessionPools.get(userName);
        try {
            sendMessage(session, message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    //给所有连接用户发送信息
    public void sendAllInfo(String message){
        // Session session = sessionPools.get(userName);
        Iterator<String> iterator = sessionPools.keySet().iterator();
        try {
        	while (iterator.hasNext()) {
        		String userid = iterator.next();
        		sendMessage(sessionPools.get(userid), message);
			}
        }catch (Exception e){
            e.printStackTrace();
        }
    }

  //给所有连接用户发送信息
    public void sendAllInfo2(String message){
        // Session session = sessionPools.get(userName);
        Iterator<String> iterator = sessionPools2.keySet().iterator();
        try {
        	while (iterator.hasNext()) {
        		String userid = iterator.next();
        		sendMessage(sessionPools2.get(userid), message);
			}
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    //建立连接成功调用
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "sid") String userName,
    		@PathParam(value = "type") String type){
    	if (type.equals("caseInfo")) {
    		sessionPools.put(userName, session);
            addOnlineCount();
    	} else if (type.equals("screen")) {
    		sessionPools2.put(userName, session);
            addOnlineCount2();
    	}
        
        //System.out.println(userName + "加入webSocket！当前人数为" + onlineNum);
//        try {
//            // sendMessage(session, "欢迎" + userName + "加入连接！");
//            // 发送案件信息
//        	// websocket注册bean
//        	caseInfoCityService = (CaseInfoCityService)applicationContext.getBean(CaseInfoCityService.class);
//        	Object object = caseInfoCityService.find2(2, new Page(1, 10), null);
//            sendMessage(session,  JSONObject.toJSONString(object));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    //关闭连接时调用
    @OnClose
    public void onClose(@PathParam(value = "sid") String userName,@PathParam(value = "type") String type){
    	if (type.equals("caseInfo")) {
    		sessionPools.remove(userName);
            subOnlineCount();
    	} else if (type.equals("screen")) {
    		sessionPools2.remove(userName);
            subOnlineCount2();
    	}
        
        // System.out.println(userName + "断开webSocket连接！当前人数为" + onlineNum);
    }

    //收到客户端信息
    @OnMessage
    public void onMessage(String message) throws IOException{
        message = "客户端：" + message + ",已收到";
        System.out.println(message);
//        for (Session session: sessionPools.values()) {
//            try {
//                sendMessage(session, message);
//            } catch(Exception e){
//                e.printStackTrace();
//                continue;
//            }
//        }
    }

    //错误时调用
    @OnError
    public void onError(Session session, Throwable throwable){
        System.out.println("发生错误");
        throwable.printStackTrace();
    }

    public static void addOnlineCount(){
        onlineNum.incrementAndGet();
    }
    
    public static void addOnlineCount2(){
        onlineNum2.incrementAndGet();
    }

    public static void subOnlineCount() {
        onlineNum.decrementAndGet();
    }
    
    public static void subOnlineCount2() {
        onlineNum2.decrementAndGet();
    }

}