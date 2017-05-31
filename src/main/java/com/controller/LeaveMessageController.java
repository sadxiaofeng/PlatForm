package com.controller;

import com.alibaba.fastjson.JSON;
import com.pojo.LeaveMessage;
import com.pojo.User;
import com.pojo.ajax.LeaveMessageCount;
import com.service.ILeaveMessageService;
import com.service.IUserService;
import com.util.CookieUtil;
import com.util.UniversalResult;
import com.websocket.WebSocketConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 钱逊 on 2017/5/30.
 */
@RequestMapping("message")
@Controller
public class LeaveMessageController {

    @Resource
    IUserService userService;

    @Resource
    ILeaveMessageService leaveMessageService;

    @ResponseBody
    @RequestMapping("send")
    public UniversalResult sendMessage(HttpServletRequest request, String account, String message){
        User user = CookieUtil.getCurrentUser(request);
        User receiver = userService.getUserByAccount(account);
        LeaveMessage leaveMessage = new LeaveMessage(user.getId(),receiver.getId(),new Date(),message);
        leaveMessageService.create(leaveMessage);
        WebSocketConfig.getMsHandler().sendMessage(account);
        return UniversalResult.createSuccessResult(null);
    }

    @ResponseBody
    @RequestMapping("messageCount")
    public UniversalResult messageCount(HttpServletRequest request){
        User user = CookieUtil.getCurrentUser(request);
        List<LeaveMessage> listMessage = leaveMessageService.getLeaveMessage(null,user.getId());
        Map<String,LeaveMessageCount> map = new HashMap<>();
        listMessage.stream().forEach(ms->{
            if(map.containsKey(ms.getSender().getAccount())){
                LeaveMessageCount count = map.get(ms.getSender().getAccount());
                count.setCount(count.getCount()+1);
            }else{
                LeaveMessageCount count = new LeaveMessageCount(ms.getSender().getAccount(),ms.getSender().getName(),1);
                map.put(ms.getSender().getAccount(),count);
            }
        });
        List<LeaveMessageCount> result = new ArrayList<>();
        for(Object obj : map.keySet()){
            result.add(map.get(obj));
        }
        return UniversalResult.createSuccessResult(JSON.toJSONString(result));
    }

    @ResponseBody
    @RequestMapping("update")
    public UniversalResult update(String account){
        long id = userService.getUserByAccount(account).getId();
        leaveMessageService.isRead(id);
        return UniversalResult.createSuccessResult(null);
    }

    @ResponseBody
    @RequestMapping("selectAll")
    public UniversalResult selectAll(HttpServletRequest request,String account){
        User user = CookieUtil.getCurrentUser(request);
        User sender = userService.getUserByAccount(account);
        List<LeaveMessage> list = leaveMessageService.getLeaveMessage(sender.getId(),user.getId());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        list.stream().forEach(ms->{
            ms.setCurrent(formatter.format(ms.getTime()));
        });
        update(sender.getAccount());
        return UniversalResult.createSuccessResult(JSON.toJSONString(list));
    }
}
