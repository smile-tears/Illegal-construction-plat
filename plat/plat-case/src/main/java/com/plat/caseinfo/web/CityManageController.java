package com.plat.caseinfo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by stone on 2020/6/13.
 */
@CrossOrigin(origins="*", maxAge=3600)
@RequestMapping("/cityManage")
@Controller
public class CityManageController {

    @RequestMapping("/index")
    public String index(Model model){
        System.out.println("ctrl index=============");
        model.addAttribute("msg","后台msg");
        return "index";
    }

}