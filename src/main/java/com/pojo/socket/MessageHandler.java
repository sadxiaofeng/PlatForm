package com.pojo.socket;

import com.controller.ExperimentController;
import com.service.imp.SubmitService;
import com.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 钱逊 on 2017/5/15.
 */

public class MessageHandler implements  WebSocketHandler,ApplicationListener<ContextRefreshedEvent>{
    private static final Logger logger = LoggerFactory.getLogger(ExperimentController.class);

    private static final Map<String,WebSocketSession> user = new HashMap<>();

    private static SubmitService submitService;


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userId = (String)session.getAttributes().get(Constant.WEBSOCKET_USERID);
        logger.info(userId+" success connected !");
        user.put(userId,session);
        logger.info(user.keySet().size()+" has connected");
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {

    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        String userId = (String)session.getAttributes().get(Constant.WEBSOCKET_USERID);
        user.remove(userId);
        logger.info(userId +" close connected !");
        logger.info(user.keySet().size()+" has connected");
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