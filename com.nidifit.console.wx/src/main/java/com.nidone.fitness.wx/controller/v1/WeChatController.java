package com.nidone.fitness.wx.controller.v1;

import com.common.redis.RedisDbDao;
import com.nidone.fitness.wx.utils.WXutils.HttpRequestUtil;
import com.nidone.fitness.wx.utils.WXutils.MessageUtil;
import com.nidone.fitness.wx.utils.WXutils.WXCommonUtil;
import com.nidone.fitness.wx.web.util.AbstractBaseController;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by dav on 7/13/17.
 */
@Controller
@RequestMapping(value = "/v1", method = {RequestMethod.POST, RequestMethod.GET})
public class WeChatController extends AbstractBaseController{
    @Resource
    private RedisDbDao redisDbDao;

    @RequestMapping(value = "weixinCore",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public void Coreservice(HttpServletRequest request,HttpServletResponse response) {
           System.out.println("进入chat");
        boolean isGet = request.getMethod().toLowerCase().equals("get");
        if(isGet) {
            System.out.println("enter get");
            WXCommonUtil.Validate(request,response);//验证服务器接口
        }else {
            System.out.println("enter post");
                // 接收消息并返回消息
            acceptMessage(request, response);
        }
    }


    //接收消息的方法
    private void acceptMessage(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("接收消息成功");
        try {
            //Map<String,String> map=MessageUtil.parseXMLCrypt(request);
            Map<String,String> map=MessageUtil.parseXML(request);
            System.out.println(map);
            Map<String,Object> respmap=new HashMap<>();
            // 自动回复规则
            if(map.containsKey("Event")&&map.get("Event").equals("subscribe")){
                MessageUtil.RespTextXmlMessage("欢迎关注NidFit智能健身",map,respmap,response);
            }else {
                MessageUtil.RespTextXmlMessage("你好",map,respmap,response);
            }
            String openid=map.get("FromUserName");
            // 获取access_token
            String access_token=null;
            if (redisDbDao.exists("access_token")){
                access_token=redisDbDao.get("access_token");
            }else{
                access_token= WXCommonUtil.Getaccess_token();
                redisDbDao.setex("access_token",6000,access_token);
            }
            //获取用户个人信息
            JSONObject jsonObject= WXCommonUtil.GetInfoMessage(access_token,openid);
            System.out.println(jsonObject);
        } catch (Exception e) {
            LOGGER.error("post request failed",e);
        }
    }
}