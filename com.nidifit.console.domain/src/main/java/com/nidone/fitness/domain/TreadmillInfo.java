package com.nidone.fitness.domain;

import com.common.util.AbstractBaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by dav on 7/13/17.
 */
public class TreadmillInfo extends AbstractBaseEntity implements Serializable {

    /*** 跑步机类型*/
    private String type;

    /*** 软件版本*/
    private String softVersion;

    /*** 设备版本*/
    private String deviceVersion;

    /*** 系统版本*/
    private String sysVersion;

    /*** 序列号*/
    private String serialNumber;

    /*** MAC地址*/
    private String macAddress;

    /*** 跑步机等级*/
    private Integer level;

    /*** 跑步机归属人ID*/
    private Long ownerId;

    /*** 出厂时间*/
    private Date manufactureTime;

    /*** 产品投放时间*/
    private Date marketTime;

    /*** 上一次运行时间*/
    private Date lastRunningTime;

    /*** 总运行时长,单位秒*/
    private Long totalRunningTime;

    /*** 跑步机记录*/
    private String note;

    public String getSoftVersion() {
        return softVersion;
    }

    public void setSoftVersion(String softVersion) {
        this.softVersion = softVersion;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeviceVersion() {
        return deviceVersion;
    }

    public void setDeviceVersion(String deviceVersion) {
        this.deviceVersion = deviceVersion;
    }

    public String getSysVersion() {
        return sysVersion;
    }

    public void setSysVersion(String sysVersion) {
        this.sysVersion = sysVersion;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Date getManufactureTime() {
        return manufactureTime;
    }

    public void setManufactureTime(Date manufactureTime) {
        this.manufactureTime = manufactureTime;
    }

    public Date getMarketTime() {
        return marketTime;
    }

    public void setMarketTime(Date marketTime) {
        this.marketTime = marketTime;
    }

    public Date getLastRunningTime() {
        return lastRunningTime;
    }

    public void setLastRunningTime(Date lastRunningTime) {
        this.lastRunningTime = lastRunningTime;
    }

    public Long getTotalRunningTime() {
        return totalRunningTime;
    }

    public void setTotalRunningTime(Long totalRunningTime) {
        this.totalRunningTime = totalRunningTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
