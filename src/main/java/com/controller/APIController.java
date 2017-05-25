package com.controller;

import com.alibaba.fastjson.JSON;
import com.complie.DynamicEngine;
import com.pojo.Submit;
import com.pojo.User;
import com.service.imp.ExperimentService;
import com.service.imp.SubmitService;
import com.util.CookieUtil;
import com.util.DateUtil;
import com.util.UniversalResult;
import com.websocket.WebSocketConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by 钱逊 on 2017/5/12.
 */
@RequestMapping("api")
@Controller
public class APIController {

    @Resource
    ExperimentService experimentService;

    @Resource
    SubmitService submitService;

    @ResponseBody
    @RequestMapping("getNewExp")
    public UniversalResult getNewExp(HttpServletRequest request){
        User user = CookieUtil.getCurrentUser(request);
        return UniversalResult.createSuccessResult(JSON.toJSONString(submitService.getNewExp(user.getId())));
    }

    @ResponseBody
    @RequestMapping("runningCode")
    public UniversalResult runningCode(long id) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Submit submit = submitService.getById(id);
        String code = submit.getContent();
        String className = DateUtil.parseClassName(code);
        String result = (String) DynamicEngine.getInstance().javaCodeToObject(className,code);
        return UniversalResult.createSuccessResult(result);
    }

    @ResponseBody
    @RequestMapping("onlineRunning")
    public UniversalResult onlineRunning(String id) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String code = WebSocketConfig.getSubmitHandler().getCode(id);
        String className = DateUtil.parseClassName(code);
        String result = (String) DynamicEngine.getInstance().javaCodeToObject(className,code);
        return UniversalResult.createSuccessResult(result);
    }
}
