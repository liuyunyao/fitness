package com.nidone.fitness.wx.test;

import com.nidone.fitness.wx.utils.WXutils.WXCommonUtil;
import net.sf.json.JSONObject;

/**
 * Created by yuny on 2017/8/16.
 */
public class Test {
       public static void main(String[] args){
           String access_token = WXCommonUtil.Getaccess_token();
           JSONObject jsonObject=WXCommonUtil.CreateMenu(access_token);
           System.out.println(jsonObject);
       }
}
