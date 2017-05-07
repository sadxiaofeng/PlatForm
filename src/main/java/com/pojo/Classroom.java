package com.pojo;

import java.util.List;

/**
 * Created by 钱逊 on 2017/4/18.
 */
public class Classroom {
    private Long id;
    private String name;
    private List<User> userList;

    public Classroom() {
    }

    public Classroom(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
