package com.nidone.fitness.domain.module;

import java.io.Serializable;

/**
 * Created by dav on 7/14/17.4:39 PM
 */
public enum TreadmillStatusEnum implements Serializable{

    UNLOCKED("unlocked",0),
    LOCKED("locked",1),
    RUNNING("running", 3),
    IDLE("idle", 4),
    DISABLED("disabled", 5);

    private String type;
    private Integer value;

    TreadmillStatusEnum(String type, Integer value) {
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
