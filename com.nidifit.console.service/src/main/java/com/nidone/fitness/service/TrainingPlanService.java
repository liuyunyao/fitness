package com.nidone.fitness.service;

import com.common.util.AbstractBaseService;
import com.nidone.fitness.domain.TreadmillInfo;
import com.nidone.fitness.domain.TreadmillStatus;
import org.springframework.stereotype.Service;

/**
 * Created by dav on 7/13/17.
 */
@Service
public interface TrainingPlanService extends AbstractBaseService<TreadmillInfo>{

    /**
     *
     *  训练计划
     */
    void save(TreadmillStatus WxInfo);

}
