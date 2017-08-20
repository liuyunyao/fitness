package com.nidone.fitness.service;

import com.common.util.AbstractBaseService;
import com.nidone.fitness.domain.TreadmillInfo;
import com.nidone.fitness.domain.TreadmillStatus;
import com.nidone.fitness.rpc.dto.Result;
import com.nidone.fitness.rpc.dto.UserInfoDto;
import com.nidone.fitness.service.dto.controller.UserInputDto;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by dav on 7/13/17.
 */
@Service
public interface ProfileInfoService{

    /**
     *
     * @param WxInfo 微信用户信息
     */
    void save(TreadmillStatus WxInfo);

    JSONObject find();
}
