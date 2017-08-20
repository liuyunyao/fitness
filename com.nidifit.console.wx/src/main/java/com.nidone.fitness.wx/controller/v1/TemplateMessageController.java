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
@RequestMapping(value = "/v1/templatemessage", method = {RequestMethod.POST, RequestMethod.GET})
public class TemplateMessageController extends AbstractBaseController{

    @RequestMapping(value = "",method = {RequestMethod.POST, RequestMethod.GET})
    public String Homepage(HttpServletRequest request,HttpServletResponse response) {
          //个人主页
        return "";
    }
}