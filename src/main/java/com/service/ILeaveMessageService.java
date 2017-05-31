package com.service;

import com.pojo.LeaveMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 钱逊 on 2017/5/30.
 */
public interface ILeaveMessageService {
    void create(LeaveMessage message);

    void isRead(long id);

    List<LeaveMessage> getLeaveMessage(Long sender,Long receiver );

    int getReceiverCount(long id);
}
