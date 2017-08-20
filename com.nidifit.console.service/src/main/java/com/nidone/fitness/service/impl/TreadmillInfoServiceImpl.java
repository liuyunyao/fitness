package com.nidone.fitness.service.impl;

import com.common.exception.ApplicationException;
import com.common.exception.BizException;
import com.common.redis.RedisDbDao;
import com.common.util.AbstractBaseDao;
import com.common.util.BeanCoper;
import com.common.util.DefaultBaseService;
import com.common.util.StringUtils;
import com.common.web.url.JdUrl;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.nidone.fitness.dao.TreadmillInfoDao;
import com.nidone.fitness.domain.TreadmillInfo;
import com.nidone.fitness.domain.TreadmillStatus;
import com.nidone.fitness.domain.module.TreadmillStatusEnum;
import com.nidone.fitness.rpc.dto.Result;
import com.nidone.fitness.rpc.dto.UserFitnessDataDto;
import com.nidone.fitness.rpc.dto.UserInfoDto;
import com.nidone.fitness.rpc.module.FitnessDataTypeEnum;
import com.nidone.fitness.service.TreadmillInfoService;
import com.nidone.fitness.service.dto.controller.UserInputDto;
import com.nidone.fitness.service.module.CommonConstants;
import com.nidone.fitness.service.utils.MatrixToImageWriter;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dav on 7/13/17.
 */
@Service
public class TreadmillInfoServiceImpl extends DefaultBaseService<TreadmillInfo> implements TreadmillInfoService {
    private Logger logger = Logger.getLogger(TreadmillInfoServiceImpl.class);
    private static final String TOKEN_PREFIX = "treadmill.login.";
    // Thirty minutes
    private static final Integer DEFAULT_EXPIRE_SECONDS = 60 * 60;
    @Resource
    private TreadmillInfoDao treadmillInfoDao;
    @Resource
    private JdUrl homeModule;
    @Resource
    private RedisDbDao redisDbDao;

    @Override
    public AbstractBaseDao<TreadmillInfo> getBaseDao() {
        return this.treadmillInfoDao;
    }

    @Override
    public Map authStatusInfo(String token) {
        Map<String, Object> result = new HashMap<>();
        validateToken(token);

        // 查看跑步机是否解锁
        // Query authentication status
        boolean pass = false;
        TreadmillStatus treadmillStatus = checkUnlockedStatus(token);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 1);
        Date endFetch = calendar.getTime();
        while (endFetch.after(new Date())) {
            if (TreadmillStatusEnum.LOCKED.getValue().equals(treadmillStatus.getLockStatus())) {
                try {
                    Thread.sleep(1000);
                    treadmillStatus = checkUnlockedStatus(token);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
            if (TreadmillStatusEnum.UNLOCKED.getValue().equals(treadmillStatus.getLockStatus())) {
                pass = true;
                break;
            }
        }
        if (!pass) {
            // Build return data
            result.put("pass", pass);
            return result;
        }

        // Treadmill is unlocked now
        Map<String, Object> authInfo = new HashMap<>();
        Map<String, Object> userInfo = new HashMap<>();
        // authInfo
        authInfo.put("startTime", CommonConstants.UTC_FORMATTER.format(treadmillStatus.getStartTime()));
        authInfo.put("authType", treadmillStatus.getAuthType());
        authInfo.put("duration", treadmillStatus.getDuration());
        // TODO: 2017/7/27 authId is not cached right now !
        authInfo.put("authId", StringUtils.getUUID());// Authentic id
        authInfo.put("userInfo", userInfo);
        // userInfo
        userInfo.put("name", "dev01");
        userInfo.put("height", CommonConstants.DEFAULT_HEIGHT);
        userInfo.put("weight", CommonConstants.DEFAULT_WEIGHT);
        userInfo.put("gender", CommonConstants.DEFAULT_GENDER);// 1 male 2 female
        userInfo.put("age", CommonConstants.DEFAULT_AGE);


        // Build return data
        result.put("pass", pass);
        result.put("authInfo", authInfo);

        return result;
    }

    private TreadmillStatus checkUnlockedStatus(String token) {
        Object o = redisDbDao.getBySerialize(TOKEN_PREFIX + token);
        if (o == null) {
            throw new BizException("error.find.no.treadmill", "查无此跑步机信息");
        }
        return (TreadmillStatus) o;
    }

    @Override
    public Map login(TreadmillStatus treadmillStatus) {
        // Verify treadmill checked in or not
        TreadmillInfo treadmillInfo = new TreadmillInfo();
        BeanCoper.copyProperties(treadmillInfo, treadmillStatus);
        validateTreadmill(treadmillInfo);

        Map<String, Object> result = new HashMap<>();
        /*
         * Check this treadmill registered or not
         * If registered return token , else return nothing
         */
        // Set the default unlock status is locked
        treadmillStatus.setLockStatus(TreadmillStatusEnum.LOCKED.getValue());
        String token = StringUtils.getUUID();
        // Cache token to redis
        redisDbDao.setexBySerialize(TOKEN_PREFIX + token, DEFAULT_EXPIRE_SECONDS, treadmillStatus);
        // Token should be stored a copy in mysql in case of Server shutdown suddenly

        /*
         * Generate QRCode page url
         */
        JdUrl jdUrl = homeModule.getTarget("/v1/authPage");
        // Set query criteria
        Map<String, Object> query = new LinkedHashMap<>();
        query.put("token", token);

        jdUrl.setQuery(query);

        result.put("token", token);
        // Url is like http://www.baidu.com?...
        result.put("url", jdUrl.toString());

        return result;
    }

    @Override
    public void validateToken(String token) {
        if (StringUtils.isBlank(token)) {
            throw new BizException("error.token.empty", "您暂无权限，请确认您的权限并重试");
        }
        boolean exist = redisDbDao.exists(TOKEN_PREFIX + token);
        if (!exist) {
            throw new BizException("error.token.invalid", "没有权限");
        }
    }

    @Override
    public String validateParam(String param) {
//        try {
//            param = URLDecoder.decode(param,"utf-8");
//        } catch (UnsupportedEncodingException e) {
//            throw new ApplicationException(e);
//        }
        if (StringUtils.isBlank(param)) {
            throw new BizException("error.required.param.empty", "必要参数为空");
        }
        byte[] src = Base64.getDecoder().decode(param);
        String jsonStr = new String(src, 0, src.length);
        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        String token = jsonObject.getString("token");
        if (StringUtils.isBlank(token)) {
            throw new BizException("error.token.empty", "未获取到凭证信息");
        }

        return token;
    }

    @Override
    public byte[] generateQRCode(String token) {
        validateToken(token);

        JdUrl url = homeModule.getTarget("/v1/infos");
        url.addQueryData("org", CommonConstants.CRITERIA_ORG);
        url.addQueryData("prod", CommonConstants.CRITERIA_PROD);
        url.addQueryData("type", CommonConstants.CRITERIA_TYPE);
        url.addQueryData("vNum", CommonConstants.CRITERIA_VNUM);
        // Build param
        Map<String, Object> param = new HashMap<>();
        param.put("token", token);
        JSONObject jsonObject = JSONObject.fromObject(param);
        byte[] src;
        try {
            src = jsonObject.toString().getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new ApplicationException(e);
        }
        String base64JsonStr = Base64.getEncoder().encodeToString(src);
//        try {
//            base64JsonStr = URLEncoder.encode(base64JsonStr,"utf-8");
//        } catch (UnsupportedEncodingException e) {
//            throw new ApplicationException("error.unsupported.url.encode.param",e);
//        }
        url.addQueryData("param", base64JsonStr);

        // Build QRCode
        String qrCodeContent = url.toString();
        int qrcodeWidth = 232;
        int qrcodeHeight = 232;
        String qrcodeFormat = "png";
        HashMap<EncodeHintType, String> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(qrCodeContent, BarcodeFormat.QR_CODE, qrcodeWidth, qrcodeHeight, hints);
        } catch (WriterException e) {
            logger.error("Generate QRCode error.", e);
            return null;
        }

        byte[] bs = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            MatrixToImageWriter.writeToStream(bitMatrix, qrcodeFormat, out);
            bs = out.toByteArray();
        } catch (IOException e) {
            logger.error("BufferedImage transforms to byte array error", e);
        } finally {
            IOUtils.closeQuietly(out);
        }
        return bs;
    }

    @Override
    public void register(UserInputDto inputDto) {
        if (StringUtils.isBlank(inputDto.getToken())) {
            throw new BizException("error.input.token.empty", "未获取到您的凭证信息");
        }
        // Validate token
        validateToken(inputDto.getToken());

        if (inputDto.getHeight() == null || 0 == inputDto.getHeight()) {
            throw new BizException("error.input.height.empty", "请输入您的身高");
        }
        if (inputDto.getWeight() == null || 0 == inputDto.getWeight()) {
            throw new BizException("error.input.weight.empty", "请输入您的体重");
        }
//        Pattern number = Pattern.compile("^[0-9]+(\\.[0-9]{1,2})?$");
//        Matcher mHeight = number.matcher(inputDto.getHeight().toString());
//        Matcher mWeight = number.matcher(inputDto.getWeight().toString());
//        if (!mHeight.matches() || !mWeight.matches()) {
//            throw new BizException("error.input.wrong.format", "请输入正確的数字格式");
//        }

        // Phone is not null , then create user's record .
        String phone = inputDto.getPhone();
        if (StringUtils.isNotBlank(phone)) {
            // Verify phone's format
            Pattern pattern = Pattern.compile("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$");
            Matcher matcher = pattern.matcher(phone);
            if (!matcher.matches()) {
                throw new BizException("error.input.wrong.phone.format", "手机号码格式不正确");
            }
            // Register user

            unlock(inputDto);
            return;
        }

        // Mark the treadmill is unlocked with the token
        unlock(inputDto);
    }

    /**
     * Verify the treadmill is registered or not
     *
     * @param treadmillInfo
     */
    private void validateTreadmill(TreadmillInfo treadmillInfo) {
        if (StringUtils.isBlank(treadmillInfo.getSerialNumber())) {
            throw new BizException("error.treadmill.serial.number.empty", "请传入跑步机序列号");
        }
        if (StringUtils.isBlank(treadmillInfo.getMacAddress())) {
            throw new BizException("error.treadmill.mac.empty", "请传入跑步机MAC码");
        }
        if (StringUtils.isBlank(treadmillInfo.getType())) {
            throw new BizException("error.treadmill.type.empty", "请传入跑步机型号");
        }
        if (StringUtils.isBlank(treadmillInfo.getDeviceVersion())) {
            throw new BizException("error.treadmill.device.version.empty", "请传入跑步机版本号");
        }
        if (StringUtils.isBlank(treadmillInfo.getSysVersion())) {
            throw new BizException("error.treadmill.system.version.empty", "请传入跑步机系统版本号");
        }
        if (StringUtils.isBlank(treadmillInfo.getSoftVersion())) {
            throw new BizException("error.treadmill.soft.version.empty", "请传入跑步机软件版本号");
        }

        // Validate the treadmill is registered or not

    }

    /**
     * Start to unlock treadmill and save treadmill's current status
     *
     * @param inputDto
     */
    private void unlock(UserInputDto inputDto) {
        // Deserialize treadmill status from token
        TreadmillStatus treadmillStatus = deserializeByToken(inputDto.getToken());

        // Treadmill is unlocked already
        if (TreadmillStatusEnum.UNLOCKED.getValue().equals(treadmillStatus.getLockStatus())) {
            throw new BizException("error.unlocked.already", "该跑步机已解锁");
        }

        // Update treadmill status
        treadmillStatus.setAuthType(CommonConstants.DEFAULT_AUTH_TYPE);
        treadmillStatus.setLockStatus(TreadmillStatusEnum.UNLOCKED.getValue());
        treadmillStatus.setCurrentUsrId(CommonConstants.DEFAULT_USERID);
        // Unlock time
        treadmillStatus.setStartTime(new Date());
        treadmillStatus.setDuration(CommonConstants.DEFAULT_DURATION);

        // Update redis cache todo Not completed
        redisDbDao.setexBySerialize(TOKEN_PREFIX + inputDto.getToken(), CommonConstants.DEFAULT_DURATION, treadmillStatus);
    }

    private TreadmillStatus deserializeByToken(String token) {
        Object o = redisDbDao.getBySerialize(TOKEN_PREFIX + token);
        if (o == null) {
            throw new BizException("error.deserialize.object.null", "未获取到数据");
        }
        return (TreadmillStatus) o;
    }

    /**
     * This method procided for unlock treadmill from RPC
     *
     * @param token
     * @param userInfoDto
     * @return
     */
    @Override
    public Result<Boolean> unlockRPC(String token, UserInfoDto userInfoDto) {
        Result<Boolean> result = new Result<>();
        UserInputDto userInputDto = new UserInputDto();
        userInputDto.setToken(token);
        userInputDto.setPhone(userInfoDto.getPhone());
        UserFitnessDataDto height = null;
        UserFitnessDataDto weight = null;
        List<UserFitnessDataDto> userFitnessDataDtoList = userInfoDto.getDataDtoList();
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
        userInputDto.setHeight(Integer.valueOf(height.getDataValue()));
        userInputDto.setWeight(Integer.valueOf(weight.getDataValue()));

        try {
            unlock(userInputDto);
            result.setSuccess(true);
        }catch (BizException e){
            result.setSuccess(false);
            result.setCode(e.getCode());
            result.setMessage(e.getMessage());
        }

        return result;
    }
}
