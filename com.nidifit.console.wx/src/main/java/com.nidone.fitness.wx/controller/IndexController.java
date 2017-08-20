package com.nidone.fitness.wx.controller;

import com.common.web.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by shilun on 2016/8/31.
 */
@Controller
@RequestMapping(value = "/")
public class IndexController extends AbstractController {

    @RequestMapping
    public String index() {
        return "/index";
    }
}
