package com.nidone.fitness.domain;

import com.common.util.AbstractBaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by dav on 7/13/17.
 */
public class TreadmillStatus extends AbstractBaseEntity implements Serializable {

    /*** Type of treadmill*/
    private String type;

    /*** Software version of the treadmill*/
    private String softVersion;

    /*** Device version of the treadmill*/
    private String deviceVersion;

    /*** System version of the treadmill*/
    private String sysVersion;

    /*** Serial number of the treadmill*/
    private String serialNumber;

    /*** Mac address of the treadmill*/
    private String macAddress;

    /*** The current user who's using*/
    private Long currentUsrId;

    /*** The start time of this unlocking operation*/
    private Date startTime;

    /*** The duration of this unlocking operation*/
    private Integer duration ;

    /*** The authentic type of the treadmill */
    private Integer authType;

    /*** Status of treadmill Running/Disabled/Idle 3 Running 4 Idle 5 Disabled*/
    private Integer status;

    /*** Locking status of the treadmill 0 unlocked 1 locked*/
    private Integer lockStatus;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSoftVersion() {
        return softVersion;
    }

    public void setSoftVersion(String softVersion) {
        this.softVersion = softVersion;
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

    public Long getCurrentUsrId() {
        return currentUsrId;
    }

    public void setCurrentUsrId(Long currentUsrId) {
        this.currentUsrId = currentUsrId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getAuthType() {
        return authType;
    }

    public void setAuthType(Integer authType) {
        this.authType = authType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(Integer lockStatus) {
        this.lockStatus = lockStatus;
    }
}
