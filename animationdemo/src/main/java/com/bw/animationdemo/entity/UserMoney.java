package com.bw.animationdemo.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * MineStudy
 * name: UserMoney
 * time: 2021/9/3 9:16.
 * author: 王益德
 * Describe:
 */
@Entity
public class UserMoney {
    @Id(autoincrement = true)
    private Long id;;

    private Double money;

    @Generated(hash = 2038308334)
    public UserMoney(Long id, Double money) {
        this.id = id;
        this.money = money;
    }

    @Generated(hash = 481630947)
    public UserMoney() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMoney() {
        return this.money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
