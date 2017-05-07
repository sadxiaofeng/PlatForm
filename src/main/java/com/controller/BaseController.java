package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 钱逊 on 2017/4/16.
 */
@RequestMapping("/")
@Controller
public class BaseController {

    @RequestMapping("home")
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView("main");
        mv.addObject("page","home");
        return mv;
    }

    @RequestMapping("/")
    public String defaultMain(){
        return "redirect:home";
    }
}
