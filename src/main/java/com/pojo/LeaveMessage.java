package com.pojo;

import java.util.Date;

/**
 * Created by 钱逊 on 2017/5/30.
 */
public class LeaveMessage {
    Long id;
    Long senderId;
    User sender;
    Long receiverId;
    User receiver;
    Date time;
    String current;
    String content;
    Integer isRead;

    public LeaveMessage() {
    }

    public LeaveMessage(Long senderId, Long receiverId, Date time, String content) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.time = time;
        this.content = content;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }
}
