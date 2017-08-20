package com.nidone.fitness.rpc.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dav on 7/18/17.10:25 AM
 */
public class UserInfoDto implements Serializable{
    private String name;
    private Integer age;
    /*** 1:male 2:female */
    private Integer gender;
    private String nickName;
    private String phone;
    private List<UserFitnessDataDto> dataDtoList;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<UserFitnessDataDto> getDataDtoList() {
        return dataDtoList;
    }

    public void setDataDtoList(List<UserFitnessDataDto> dataDtoList) {
        this.dataDtoList = dataDtoList;
    }
}
