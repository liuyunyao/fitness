package com.nidone.fitness.service.module;

import com.nidone.fitness.domain.module.SexTypeEnum;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;

/**
 * Created by dav on 7/14/17.3:39 PM
 * <p>
 * This class concludes all constants which will be used in Service
 */
public class CommonConstants {
    /**
     * These fields statement below will be used in generating QRCode
     */
    // Default api organisation
    public static final String CRITERIA_ORG = "60living";
    public static final String CRITERIA_PROD = "nidfit";
    // Default api type
    public static final String CRITERIA_TYPE = "treadmill";
    // Default api version number
    public static final Integer CRITERIA_VNUM = 1;

    /**
     * These fields statement below will be used in initializing user's default info
     */
    public static final Integer DEFAULT_GENDER = SexTypeEnum.MALE.getValue();
    public static final Integer DEFAULT_AGE = 25;
    public static final String DEFAULT_NAME = "TestUser"+ new Random(10);
    public static final Integer DEFAULT_HEIGHT = 170;
    public static final Integer DEFAULT_WEIGHT = 65;
    public static final Integer DEFAULT_AUTH_TYPE = 0;
    // Default duration , unit is second
    public static final Integer DEFAULT_DURATION = 60 * 3;
    public static final Long DEFAULT_USERID = 10001l;

    /**
     * These fields statement below will be used in date format
     */
    public static final SimpleDateFormat UTC_FORMATTER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sssZ");
    public static final SimpleDateFormat CST_FORMATTER = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.CHINA);

}
