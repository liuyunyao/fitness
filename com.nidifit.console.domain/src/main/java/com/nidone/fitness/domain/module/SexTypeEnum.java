package com.nidone.fitness.domain.module;

import java.io.Serializable;

/**
 * Created by dav on 7/14/17.4:39 PM
 */
public enum SexTypeEnum implements Serializable{

    MALE("male",1),
    FEMALE("female",2);

    private String type;
    private Integer value;

    SexTypeEnum(String type, Integer value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public Integer getValue() {
        return value;
    }
}
