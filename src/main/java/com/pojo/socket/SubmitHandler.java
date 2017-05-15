package com.pojo.socket;

import com.controller.ExperimentController;
import com.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;

/**
 * Created by 钱逊 on 2017/5/15.
 */
public class SubmitHandler implements WebSocketHandler {
    private static final Logger logger = LoggerFactory.getLogger(ExperimentController.class);

    private static final ArrayList<WebSocketSession> user = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info(session.getAttributes().get(Constant.WEBSOCKET_USERNAME)+" success connected !");
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {

    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        user.remove(session.getAttributes().get(Constant.WEBSOCKET_USERNAME));
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
