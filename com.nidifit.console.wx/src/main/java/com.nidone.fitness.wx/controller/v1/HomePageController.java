package com.nidone.fitness.wx.controller.v1;

import com.nidone.fitness.service.ProfileInfoService;
import com.nidone.fitness.wx.web.util.AbstractBaseController;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by dav on 7/13/17.
 */
@Controller
@RequestMapping(value = "/v1/homepage", method = {RequestMethod.POST, RequestMethod.GET})
public class HomePageController extends AbstractBaseController{

    @Resource
    ProfileInfoService profileInfoService;
    @RequestMapping(value = "",method = {RequestMethod.POST, RequestMethod.GET})
    public String Homepage(HttpServletRequest request,String state) {
          //个人主页
//        String code=request.getParameter("code");
//        System.out.println(code+"    "+state);
        JSONObject jsonObject=profileInfoService.find();
        tovm("profile",jsonObject);
        return "page/homepage";
    }
    @RequestMapping(value = "/sportrecord",method = {RequestMethod.POST, RequestMethod.GET})
    public String Sportcord(HttpServletRequest request,HttpServletResponse response) {
        //个人运动记录
        return "page/sportrecord";
    }
}