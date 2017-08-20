package com.nidone.fitness.api.controller.v1;

import com.common.web.url.JdUrl;
import com.nidone.fitness.api.controller.dto.RequestTokenDto;
import com.nidone.fitness.api.web.util.AbstractBaseController;
import com.nidone.fitness.domain.TreadmillStatus;
import com.nidone.fitness.service.TreadmillInfoService;
import com.nidone.fitness.service.dto.controller.UserInputDto;
import com.nidone.fitness.service.module.CommonConstants;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Map;


/**
 * Created by dav on 7/13/17.
 */
@Controller
@RequestMapping(value = "/v1", method = RequestMethod.POST)
public class TreadmillInfoController extends AbstractBaseController {
    @Resource
    private TreadmillInfoService treadmillInfoService;
    @Resource
    private JdUrl homeModule;

    @RequestMapping(value = "getToken")
    @ResponseBody
    public Map buildToken(@RequestBody TreadmillStatus treadmillInfo) {
        return buildMessage(() -> treadmillInfoService.login(treadmillInfo));
    }

    /**
     * The combo package list of treadmill
     *
     * @param treadmillInfo
     * @return
     */
    @RequestMapping(value = "packageList")
    public String packageList(@RequestBody TreadmillStatus treadmillInfo) {
        return buildPage(() -> {
            treadmillInfoService.login(treadmillInfo);
            return "";
        });
    }

    /**
     * Generate the authentic QRCode page for treadmill component Created by dav 7/13/17 11:26 PM
     *
     * @return
     */
    @RequestMapping(value = "authPage", method = RequestMethod.GET)
    public String generateAuthPage(String token) {
        return buildPage(() -> {
            treadmillInfoService.validateToken(token);
            JdUrl qrCodeUrl = homeModule.getTarget("/v1/generateQRCode");
            qrCodeUrl.addQueryData("token", token);
            tovm("qrCodeUrl", qrCodeUrl.toString());
            return "/treadmill/auth";
        });
    }

    /**
     * Generate QRCode
     *
     * @param response
     * @param token
     */
    @RequestMapping(value = "generateQRCode", method = RequestMethod.GET)
    public void printQRCode(HttpServletResponse response, String token) {
        byte[] bs = treadmillInfoService.generateQRCode(token);
        response.setHeader("Content-Length", String.valueOf(bs.length));
        response.setHeader("Content-Type", "image/png");
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(response.getOutputStream());
            bos.write(bs, 0, bs.length);
            bos.flush();
        } catch (IOException e) {
            LOGGER.error("print ecg image error", e);
        } finally {
            IOUtils.closeQuietly(bos);
        }
    }

    /**
     * Return the input page for using treadmill with WeChat APP Created by dav 7/13/17 11:27 PM
     *
     * @return
     */
    @RequestMapping(value = "infos", method = {RequestMethod.POST, RequestMethod.GET})
    public String infos(String param) {
        return buildPage(() -> {
            String token = treadmillInfoService.validateParam(param);
            treadmillInfoService.validateToken(token);
            tovm("defaultAge",CommonConstants.DEFAULT_AGE);
            tovm("defaultHeight", CommonConstants.DEFAULT_HEIGHT);
            tovm("defaultWeight", CommonConstants.DEFAULT_WEIGHT);
            tovm("token", token);
            return "/treadmill/infos";
        });
    }

    /**
     * Save user's input information
     *
     * @return
     */
    @RequestMapping(value = "saveInfos")
    @ResponseBody
    public Map saveInfos(UserInputDto inputDto) {
        return buildMessage(() -> {
            treadmillInfoService.register(inputDto);
            return null;
        });
    }

    /**
     * Go to the unlock page
     *
     * @return
     */
    @RequestMapping(value = "unlock", method = RequestMethod.GET)
    public String unlock() {
        return "/treadmill/unlock";
    }

    /**
     * Query authentication status by token Created by dav 7/18/17 3:08 PM
     * This is designed for treadmill component
     *
     * @return
     */
    @RequestMapping(value = "auth")
    @ResponseBody
    public Map queryAuth(@RequestBody RequestTokenDto token) {
        return buildMessage(() -> treadmillInfoService.authStatusInfo(token.getToken()));
    }
}
