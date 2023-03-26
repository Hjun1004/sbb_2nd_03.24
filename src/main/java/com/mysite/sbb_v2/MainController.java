package com.mysite.sbb_v2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/sbb2")
    @ResponseBody
    public String sbb_v2(){
        return "반갑고";
    }

    @GetMapping("/")
    public String root(){
        return "redirect:/2question/list";
    }
}
