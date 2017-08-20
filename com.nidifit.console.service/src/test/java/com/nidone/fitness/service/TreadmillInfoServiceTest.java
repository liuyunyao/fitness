package com.nidone.fitness.service;

import com.common.redis.RedisDbDao;
import com.common.util.DateUtil;
import com.common.util.StringUtils;
import com.common.web.url.JdUrl;
import com.nidone.fitness.domain.TreadmillInfo;
import com.nidone.fitness.domain.TreadmillStatus;
import com.nidone.fitness.service.module.CommonConstants;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by dav on 7/14/17.3:51 PM
 */
public class TreadmillInfoServiceTest extends BaseTestCase{
    @Autowired
    private JdUrl homeModule;
    @Autowired
    private TreadmillInfoService treadmillInfoService;
    @Autowired
    private RedisDbDao redisDbDao;


    @Test
    public void testJdUrl(){
        JdUrl url = homeModule.getTarget("/v1/authPage");
        url.addQueryData("org", CommonConstants.CRITERIA_ORG);
        url.addQueryData("prod", CommonConstants.CRITERIA_PROD);
        url.addQueryData("type",CommonConstants.CRITERIA_TYPE);
        url.addQueryData("vNum",CommonConstants.CRITERIA_VNUM);

        System.out.println(url.toString());
    }

    @Test
    public void testLogin(){
        treadmillInfoService.login(new TreadmillStatus());
    }

    @Test
    public void genAccessKsy(){
        System.out.println(StringUtils.getUUID());
    }

    @Test
    public void getJsonStr(){
        Map<String,Object> map = new HashMap<>();
        map.put("token","a2f4d0cc47204fa0a3b030ca6189d903");
        JSONObject jsonObject = JSONObject.fromObject(map);

        System.out.println(jsonObject.toString());
    }

    @Test
    public void  testDate() throws Exception{
        SimpleDateFormat smdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sssZ");

        System.out.println(new Date().toString());

        String utcTimeStr = smdf.format(new Date());
        System.out.println(utcTimeStr);

        System.out.println(utcTimeStr.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d{3}\\+\\d{4}"));

        Date date =  smdf.parse(utcTimeStr);

        System.out.println(date);
    }

    @Test
    public void testRedis(){
        boolean flag = redisDbDao.exists("xyz");
        System.out.println(flag);
    }
}