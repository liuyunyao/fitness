package com.nidone.fitness.service.impl;


import com.common.exception.BizException;
import com.common.util.StringUtils;
import com.nidone.fitness.rpc.TreadmillInfoRPCService;
import com.nidone.fitness.rpc.dto.RequiredInfosDto;
import com.nidone.fitness.rpc.dto.Result;
import com.nidone.fitness.rpc.dto.UserFitnessDataDto;
import com.nidone.fitness.rpc.dto.UserInfoDto;
import com.nidone.fitness.rpc.module.FitnessDataTypeEnum;
import com.nidone.fitness.service.TreadmillInfoService;
import com.nidone.fitness.service.module.CommonConstants;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

/**
 * Created by dav 7/17/17 4:54 PM
 */
@WebService(endpointInterface = "com.nidone.fitness.rpc.TreadmillInfoRPCService", serviceName = "TreadmillInfoRPCService")
public class TreadmillInfoRPCServiceImpl implements TreadmillInfoRPCService {
    private Logger logger = Logger.getLogger(TreadmillInfoRPCServiceImpl.class);
    @Resource
    private String rpcAccessKey;
    @Resource
    private TreadmillInfoService treadmillInfoService;

    @Override
    public Result<RequiredInfosDto> queryRequiredInfos(@WebParam(name = "accessKey") String accessKey, String token, String phone) {
        Result<RequiredInfosDto> result = new Result<>();
        Result<Boolean> validateAccessKey = validateAccessKey(accessKey);
        if (!validateAccessKey.getSuccess()) {
            result.setSuccess(false);
            result.setCode(validateAccessKey.getCode());
            result.setMessage(validateAccessKey.getMessage());
            return result;
        }
        //判断phone是否为空
        if (StringUtils.isBlank(phone)) {
            result.setSuccess(false);
            result.setCode("error.phone.empty");
            result.setMessage("phone不能为空");
            return result;
        }
        // 判断token是否为空，token是跑步机与服务器的一个凭证，标识一个跑步机
        try {
            treadmillInfoService.validateToken(token);
        } catch (BizException e) {
            logger.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setCode(e.getCode());
            result.setMessage(e.getMessage());
            return result;
        }
        //成功
        result.setSuccess(true);
        RequiredInfosDto dto = new RequiredInfosDto();
        dto.setToken(token);
        dto.setPhone(phone);
        dto.setWeight(CommonConstants.DEFAULT_WEIGHT);
        dto.setHeight(CommonConstants.DEFAULT_HEIGHT);
        result.setModule(dto);
        return result;
    }

    private Result<Boolean> validateAccessKey(String accessKey) {
        Result<Boolean> result = new Result<>();
        // 判断accessKey 是否为空
        if (StringUtils.isBlank(accessKey)) {
            result.setSuccess(false);
            result.setCode("error.accessKey.empty");
            result.setMessage("accessKey不能为空");
            return result;
        }
        // 判断accessKey是否匹配
        if (!StringUtils.equals(rpcAccessKey, accessKey)) {
            result.setSuccess(false);
            result.setCode("error.accessKey.wrong");
            result.setMessage("accessKey不匹配");
            return result;
        }
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<Boolean> unlockTreadmill(String accessKey, String token, UserInfoDto userInfoDto) {
        Result<Boolean> result = new Result<>();
        Boolean flag = false;
        Result<Boolean> validateAccessKey = validateAccessKey(accessKey);
        // 判断accessKey 是否为空
        if (!validateAccessKey.getSuccess()) {
            result.setSuccess(false);
            result.setCode(validateAccessKey.getCode());
            result.setMessage(validateAccessKey.getMessage());
            result.setModule(flag);
            return result;
        }
        // Validate token
        // 判断token是否为空，token是跑步机与服务器的一个凭证，标识一个跑步机
        try {
            treadmillInfoService.validateToken(token);
        } catch (BizException e) {
            result.setSuccess(false);
            result.setCode(e.getCode());
            result.setMessage(e.getMessage());
            return result;
        }
        // 判断数据list是否为空
        List<UserFitnessDataDto> userFitnessDataDtoList = userInfoDto.getDataDtoList();
        if (userFitnessDataDtoList == null || userFitnessDataDtoList.isEmpty()) {
            result.setSuccess(false);
            result.setModule(flag);
            result.setCode("error.list.empty");
            result.setMessage("数据集合不能为空");
            return result;
        }
        // 判断身高体重是否为空
        UserFitnessDataDto height = null;
        UserFitnessDataDto weight = null;
        for (UserFitnessDataDto dataDto : userFitnessDataDtoList) {
            if (FitnessDataTypeEnum.WEIGHT.getDataType().equals(dataDto.getDataType())) {
                weight = dataDto;
                continue;
            }
            if (FitnessDataTypeEnum.HEIGHT.getDataType().equals(dataDto.getDataType())) {
                height = dataDto;
                continue;
            }
        }
        if (height == null || weight == null) {
            result.setSuccess(false);
            result.setModule(flag);
            result.setCode("error.height.empty");
            result.setMessage("身高数据不能为空");
            return result;
        }
        if(weight == null){
            result.setSuccess(false);
            result.setModule(flag);
            result.setCode("error.weight.empty");
            result.setMessage("体重数据不能为空");
            return result;
        }

        // Unlock successfully
        result = treadmillInfoService.unlockRPC(token, userInfoDto);
        return result;
    }

}