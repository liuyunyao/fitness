package com.nidone.fitness.service.impl;

import com.common.util.AbstractBaseService;
import com.common.util.DefaultBaseService;
import com.nidone.fitness.domain.TreadmillInfo;
import com.nidone.fitness.domain.TreadmillStatus;
import com.nidone.fitness.service.ProfileInfoService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * Created by dav on 7/13/17.
 */
@Service
public class ProfileInfoServiceImpl implements  ProfileInfoService{


    @Override
    public void save(TreadmillStatus WxInfo) {

    }

    @Override
    public JSONObject find() {
        String json="{\n" +
                "    \"id\": 1,\n" +
                "    \"guid\": \"kngjdjbgjbjg\",\n" +
                "    \"user_guid\": \"yuhhvjkkjjj\",\n" +
                "    \"first_name\": \"姆\",\n" +
                "    \"family_name\": \"汤\",\n" +
                "    \"nickname\": \"tom\",\n" +
                "    \"head_img_url\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503034904690&di=16d5387ebecb1b898e6f1addc8813eb1&imgtype=0&src=http%3A%2F%2Fh.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2Fbf096b63f6246b605b0c760debf81a4c510fa24d.jpg\",\n" +
                "    \"sex\": \"男\",\n" +
                "    \"age\": \"22\",\n" +
                "    \"height\": 175,\n" +
                "    \"weight\": 56,\n" +
                "    \"activity_level\": 80,\n" +
                "    \"device_guid\": \"NidFit K21\",\n" +
                "    \"total_train_duration\": \"5676\",\n" +
                "    \"total_train_distance\": \"2345\",\n" +
                "    \"total_calories\": \"45677\",\n" +
                "    \"create_time\": 33455566,\n" +
                "    \"update_time\": 4456677\n" +
                "}";
        JSONObject jsonObject=JSONObject.fromObject(json);
        return jsonObject;
    }
}
