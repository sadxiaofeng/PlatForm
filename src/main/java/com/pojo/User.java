package com.pojo;

/**
 * Created by 钱逊 on 2017/4/16.
 */
public class User {
    private Long id;
    private String account;
    private String password;
    private Integer identity;
    private String name;
    private Long classId;
    private String telephone;

    public User() {
    }

    public User(String account, String password, Integer identity, String name, Long classId) {
        this.account = account;
        this.password = password;
        this.identity = identity;
        this.name = name;
        this.classId = classId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
