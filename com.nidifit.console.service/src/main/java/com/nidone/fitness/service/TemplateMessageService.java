package com.nidone.fitness.service;

import com.common.util.AbstractBaseService;
import com.nidone.fitness.domain.TreadmillInfo;
import com.nidone.fitness.domain.TreadmillStatus;
import org.springframework.stereotype.Service;

/**
 * Created by dav on 7/13/17.
 */
@Service
public interface TemplateMessageService extends AbstractBaseService<TreadmillInfo>{

    /**
     *
     *
     */
    void save(TreadmillStatus WxInfo);

}
