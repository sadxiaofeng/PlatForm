package com.service.imp;

import com.dao.ILeaveMessageDao;
import com.pojo.LeaveMessage;
import com.service.ILeaveMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 钱逊 on 2017/5/30.
 */
@Service
public class LeaveMessageService implements ILeaveMessageService {
    @Resource
    ILeaveMessageDao leaveMessageDao;

    @Override
    public void create(LeaveMessage message) {
        leaveMessageDao.create(message);
    }

    @Override
    public void isRead(long id) {
        leaveMessageDao.isRead(id);
    }

    @Override
    public List<LeaveMessage> getLeaveMessage(Long sender, Long receiver) {
        return leaveMessageDao.getLeaveMessage(sender,receiver);
    }

    @Override
    public int getReceiverCount(long id) {
        return leaveMessageDao.getReceiverCount(id);
    }
}
