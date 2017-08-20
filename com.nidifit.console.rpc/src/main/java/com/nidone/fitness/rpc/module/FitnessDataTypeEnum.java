package com.nidone.fitness.rpc.module;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by dav on 7/18/17.11:32 AM
 */
public enum  FitnessDataTypeEnum implements Serializable{

    HEIGHT(10,Integer.class,"身高","cm"),
    WEIGHT(11,Integer.class,"体重","kg"),
    SPEED(12,Integer.class,"速度","m/h"),
    SLOPE(13,Integer.class,"坡度","degree"),
    HR(14,Integer.class,"心律","bpm"),
    DURATION(15,Integer.class,"跑步时间","second"),
    DISTANCE(16,Integer.class,"跑步距离","meter"),
    CALORIE(17,Integer.class,"卡路里",""),
    STEPS(18,Integer.class,"跑步步数","step"),
    MODEL(19,String.class,"跑步机模式",""),
    SPEEDCHART(20, ArrayList.class,"速度变化趋势",""),
    SLOPECHART(21,ArrayList.class,"坡度变化趋势",""),
    HRCHART(22,ArrayList.class,"心律变化趋势","");



    private Integer dataType;
    private Class valueType;
    private String description;
    private String unit;

    public String getDescription() {
        return description;
    }

    public Integer getDataType() {
        return dataType;
    }

    public Class getValueType() {
        return valueType;
    }

    public String getUnit() {
        return unit;
    }

    FitnessDataTypeEnum(Integer dataType, Class valueType, String description, String unit) {
        this.dataType = dataType;
        this.valueType = valueType;
        this.description = description;
        this.unit = unit;
    }
}
