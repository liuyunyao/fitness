package com.nidone.fitness.rpc.dto;

import java.io.Serializable;

/**
 * Created by dav on 7/18/17.10:25 AM
 */
public class UserFitnessDataDto implements Serializable{
    private Integer dataType;
    private String dataValue;
    private String unit;

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
