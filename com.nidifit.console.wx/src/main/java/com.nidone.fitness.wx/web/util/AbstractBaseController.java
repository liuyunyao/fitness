package com.nidone.fitness.wx.web.util;

import com.common.cookie.CookieUtils;
import com.common.exception.BizException;
import com.common.util.Result;
import com.common.web.AbstractController;
import com.common.web.IExecute;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;

import javax.annotation.Resource;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by shilun on 2016/8/2.
 */
public class AbstractBaseController extends AbstractController {
    protected final static Logger LOGGER = Logger.getLogger(AbstractBaseController.class);


    protected String buildPage(IExecute execute) {
        Map<String, Object> map = new HashMap<>();
        try {
            Object data = execute.getData();
            if (data instanceof String) {
                return (String) data;
            }
        } catch (BizException e) {
            LOGGER.error(e.getMessage(), e);
            map.put("code", e.getCode());
            map.put("message", e.getMessage());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            map.put("code", "9999");
            map.put("message", e.getMessage());
        }
        tovm("value", map);
        return "/common/error";
    }
}
