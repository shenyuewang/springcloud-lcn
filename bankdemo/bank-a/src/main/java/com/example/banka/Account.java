package com.example.banka;

/**
 * @Description TODO
 * @Author shen
 * @Date 2019/2/26 17:59
 **/
public class Account {

    private Integer id;
    private Integer money;
    private String user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
