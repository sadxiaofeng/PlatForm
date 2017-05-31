package com.pojo;

/**
 * Created by 钱逊 on 2017/5/30.
 */
public class SocketMessage {
    int type;
    Object message;

    public SocketMessage() {
    }

    public SocketMessage(int type, Object message) {
        this.type = type;
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
