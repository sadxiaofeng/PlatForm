package com.pojo.socket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.controller.ExperimentController;
import com.pojo.Submit;
import com.service.imp.SubmitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 钱逊 on 2017/5/15.
 */
public class SubmitHandler implements WebSocketHandler,ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);

    private static final Map<String,String> code = new HashMap<>();

    private static SubmitService submitService;

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {

    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> webSocketMessage) throws Exception {
        String currentCode = (String)webSocketMessage.getPayload();
        if(!currentCode.equals("#cancel")) {
            String id = (String) session.getAttributes().get("id");
            code.put(id, currentCode);
        }else{
            String id = (String) session.getAttributes().get("id");
            code.remove(id);
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        String id = (String)session.getAttributes().get("id");
        String str =  code.get(id);
        if(str!=null && !str.equals("")){
            Submit submit = submitService.getById(Long.parseLong(id));
            submit.setContent(str);
            submit.setSubmitTime(new Date());
            submit.setStatus(1l);
            submitService.update(submit);
            //移除
            code.remove(id);
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent() == null){//root application context 没有parent，他就是老大.
            //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
            submitService = event.getApplicationContext().getBean(SubmitService.class);
        }
    }
}
