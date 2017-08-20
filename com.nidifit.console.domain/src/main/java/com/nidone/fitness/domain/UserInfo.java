package com.nidone.fitness.domain;

import com.common.annotation.QueryField;
import com.common.mongo.QueryType;
import com.common.util.AbstractBaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by dav 7/17/17 2:07 PM
 */
public class UserInfo extends AbstractBaseEntity implements Serializable {
    /*** Name*/
    private String name;

    /*** Age*/
    private Integer age;

    /*** Gender*/
    private Integer gender;

    /*** WeChat username*/
    private String wxName;

    /*** Phone*/
    private String phone;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getWxName() {
        return wxName;
    }

    public void setWxName(String wxName) {
        this.wxName = wxName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
