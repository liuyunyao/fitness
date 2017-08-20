package com.nidone.fitness.service;

import com.common.util.AbstractBaseService;
import com.nidone.fitness.domain.TreadmillInfo;

/**
 * Created by dav on 7/13/17.
 * <p>
 * This interface used for common services
 */
public interface CommonService {

    /**
     * This method used to verify the validity of the request
     *
     * @param apiAccessKey
     * @return
     */
    String accessApi(String apiAccessKey);
}
