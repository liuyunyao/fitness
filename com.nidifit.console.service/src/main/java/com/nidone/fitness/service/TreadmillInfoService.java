package com.nidone.fitness.service;

import com.common.util.AbstractBaseService;
import com.nidone.fitness.domain.TreadmillInfo;
import com.nidone.fitness.domain.TreadmillStatus;
import com.nidone.fitness.rpc.dto.Result;
import com.nidone.fitness.rpc.dto.UserInfoDto;
import com.nidone.fitness.service.dto.controller.UserInputDto;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by dav on 7/13/17.
 */
@Service
public interface TreadmillInfoService extends AbstractBaseService<TreadmillInfo> {

    /**
     * Treadmill do login and return token and authentication page url
     *
     * @param treadmillInfo
     * @return
     */
    Map login(TreadmillStatus treadmillInfo);

    /**
     * Validate the token , and return current token binding treadmill
     *
     * @param token
     * @return
     */
    void validateToken(String token);

    /**
     * Generate QRCode byte[] stc
     *
     * @param token
     * @return
     */
    byte[] generateQRCode(String token);

    /**
     * This method used to register user from WX Created by dav 7/16/17 6:16 PM
     *
     * @return
     */
    void register(UserInputDto inputDto);

    /**
     * This method will validate param and parse out token from it
     *
     * @param param
     * @return
     */
    String validateParam(String param);

    /**
     *  Query out current related treadmill authentication status
     * @return
     */
    Map authStatusInfo(String token);

    /**
     * Unlock treadmill
     */
    Result<Boolean> unlockRPC(String token,UserInfoDto userInfoDto);
}
