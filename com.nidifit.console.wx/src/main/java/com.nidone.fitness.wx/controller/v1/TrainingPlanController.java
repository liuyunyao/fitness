package com.nidone.fitness.wx.controller.v1;

import com.common.util.StringUtils;
import com.nidone.fitness.wx.web.util.AbstractBaseController;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by dav on 7/13/17.
 */
@Controller
@RequestMapping(value = "/v1/trainingplan", method = {RequestMethod.POST, RequestMethod.GET})
public class TrainingPlanController extends AbstractBaseController{

    @RequestMapping(value = "",method = {RequestMethod.POST, RequestMethod.GET})
    public String TrainingPlan(HttpServletRequest request,HttpServletResponse response) {
           //训练计划
        String result=request.getParameter("result");
        if(!StringUtils.isBlank(result)) {
            JSONObject jsonObject = JSONObject.fromObject(result);
            System.out.println(jsonObject);
            System.out.println(jsonObject.get("sex"));
        }
        return "page/trainingplan";
    }
    @RequestMapping(value = "/course",method = {RequestMethod.POST, RequestMethod.GET})
    public String Querycourse(HttpServletRequest request,HttpServletResponse response) {
         //查询当前课程
        return "page/course";
    }
    @RequestMapping(value = "/replanning",method = {RequestMethod.POST, RequestMethod.GET})
    public String Replanning(HttpServletRequest request,HttpServletResponse response) {
         //重新规划页面
        return "page/replanning";
    }

}