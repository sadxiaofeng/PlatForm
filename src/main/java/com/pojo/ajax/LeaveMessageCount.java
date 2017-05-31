package com.pojo.ajax;

/**
 * Created by 钱逊 on 2017/5/31.
 */
public class LeaveMessageCount {
    private String account;
    private String name;
    private int count;

    public LeaveMessageCount() {
    }

    public LeaveMessageCount(String account, String name, int count) {
        this.account = account;
        this.name = name;
        this.count = count;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
