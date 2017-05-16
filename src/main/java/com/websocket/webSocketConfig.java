package com.websocket;

import com.pojo.socket.MessageHandler;
import com.pojo.socket.SubmitHandler;
import com.websocket.interceptor.MsWebSocketHandlerInterceptor;
import com.websocket.interceptor.SubmitWebSocketHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Created by 钱逊 on 2017/5/15.
 */
@Configuration
@EnableWebMvc
@EnableWebSocket
public class webSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(getMsHandler(),"/ws/login").addInterceptors(new MsWebSocketHandlerInterceptor());
        registry.addHandler(getSubmitHandler(),"/ws/submit").addInterceptors(new SubmitWebSocketHandlerInterceptor());
    }


    @Bean
    public MessageHandler getMsHandler(){
        return new MessageHandler();
    }
    @Bean
    public SubmitHandler getSubmitHandler(){
        return new SubmitHandler();
    }
}
