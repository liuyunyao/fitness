package com.nidone.fitness.wx.controller.v1;

import com.nidone.fitness.wx.web.util.AbstractBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by dav on 7/13/17.
 */
@Controller
@RequestMapping(value = "/v1/gymequipment", method = {RequestMethod.POST, RequestMethod.GET})
public class GymEquipmentController extends AbstractBaseController{

    @RequestMapping(value = "",method = {RequestMethod.POST, RequestMethod.GET})
    public String Equipment(HttpServletRequest request,HttpServletResponse response) {
        //健身器材
        return "page/equipment";
    }
    @RequestMapping(value = "/Suggestions",method = {RequestMethod.POST, RequestMethod.GET})
    public String Suggestions(HttpServletRequest request,HttpServletResponse response) {
        //意见反馈
        return "";
    }
    @RequestMapping(value = "/TreadmillInfo",method = {RequestMethod.POST, RequestMethod.GET})
    public String QueryTreadmillInfo(HttpServletRequest request,HttpServletResponse response) {
        //跑步机信息
        return "";
    }
    @RequestMapping(value = "/operatorguide",method = {RequestMethod.POST, RequestMethod.GET})
    public String OperatorGuide(HttpServletRequest request,HttpServletResponse response) {
        //设备使用和维护指南
        return "";
    }
    @RequestMapping(value = "/Suggestions/submit",method = {RequestMethod.POST, RequestMethod.GET})
    public String SuggestionsUbmit(HttpServletRequest request,HttpServletResponse response) {
        //设备使用和维护指南
        return "";
    }
}