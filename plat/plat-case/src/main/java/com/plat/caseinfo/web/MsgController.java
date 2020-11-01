package com.plat.caseinfo.web;

import java.util.Map;
import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;


/**
 * Created by stone on 2020/6/13.
 */
@CrossOrigin(origins="*", maxAge=3600)
@RequestMapping("/testws")
@Controller
public class MsgController {

    @RequestMapping("/index")
    public String index(Model model){
        System.out.println("ctrl index=============");
        model.addAttribute("msg","后台msg");
        return "index";
    }

    @RequestMapping("/chat")
    public String chat(Model model){
        System.out.println("ctrl chat=============");
        model.addAttribute("msg","后台msg");
        return "chat";
    }

    /**
     * 回调接口,向客户端推送实时消息
     * @param params 消息数据结构
     * @param toUserId 发动到用户ID
     * @return
     * @throws IOException 异常
     */
    @Autowired
    public WebSocketServerConfig webSocketServerConfig;  

    @RequestMapping(value="/push/{toUserId}", method=RequestMethod.POST)
    @ResponseBody
    public Map pushToWeb(@RequestBody Map params,@PathVariable String toUserId) throws IOException {
        String message = params.get("message").toString();
        webSocketServerConfig.sendInfo(toUserId,message);
        Map returnMap = new HashMap();
        returnMap.put("code", 200);
        returnMap.put("msg", "成功发送消息");
        return returnMap;
    }


    @RequestMapping(value="/addCaseNum/{toUserId}", method=RequestMethod.POST)
    @ResponseBody
    public Map addCaseNum(@RequestBody Map params,@PathVariable String toUserId) throws IOException {
        Map returnMap = new HashMap();
        try {
//            String message = params.get("message").toString();

            JSONObject data = new JSONObject();
            data.put("nodeid", 1);
            data.put("destnodeid", 2);
            webSocketServerConfig.sendInfo(toUserId,JSONObject.toJSONString(data));
//            webSocketServerConfig.sendAllInfo(JSONObject.toJSONString(data));

            returnMap.put("code", 200);
            returnMap.put("msg", "成功发送消息");
        }catch (Exception e){
            e.printStackTrace();

            returnMap.put("code", 400);
            returnMap.put("msg", e.getMessage());
        }

        return returnMap;
    }
}