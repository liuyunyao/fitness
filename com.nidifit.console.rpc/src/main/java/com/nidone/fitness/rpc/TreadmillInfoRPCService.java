package com.nidone.fitness.rpc;

import com.nidone.fitness.rpc.dto.RequiredInfosDto;
import com.nidone.fitness.rpc.dto.Result;
import com.nidone.fitness.rpc.dto.UserInfoDto;

import javax.jws.WebService;

/**
 * Created by dav 7/17/17 4:53 PM
 */
@WebService
public interface TreadmillInfoRPCService {

    /**
     * Query out user's required input values for unlocking treadmill
     *
     * @param accessKey --- Interface authentication key
     * @param token
     * @param phone
     * @return
     */
    Result<RequiredInfosDto> queryRequiredInfos(String accessKey, String token, String phone);

    /**
     * Unlock treadmill
     *
     * @param accessKey
     * @param token
     * @param userInfoDto
     * @return
     */
    Result<Boolean> unlockTreadmill(String accessKey, String token, UserInfoDto userInfoDto);
}
