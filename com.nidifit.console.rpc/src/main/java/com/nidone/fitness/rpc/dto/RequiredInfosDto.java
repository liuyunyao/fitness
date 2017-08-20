package com.nidone.fitness.rpc.dto;

import java.io.Serializable;

/**
 * Created by dav on 7/18/17.10:22 AM
 */
public class RequiredInfosDto implements Serializable{
    private String token;
    private String phone;
    private Integer height;
    private Integer weight;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
