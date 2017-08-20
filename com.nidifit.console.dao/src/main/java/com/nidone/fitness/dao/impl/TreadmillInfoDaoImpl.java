package com.nidone.fitness.dao.impl;

import com.common.util.AbstractBaseDao;
import com.common.util.DefaultBaseDao;
import com.common.util.DefaultBaseService;
import com.nidone.fitness.dao.TreadmillInfoDao;
import com.nidone.fitness.domain.TreadmillInfo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by dav on 7/13/17.
 */
@Component
public class TreadmillInfoDaoImpl extends DefaultBaseDao<TreadmillInfo> implements TreadmillInfoDao {

    @Override
    public String getNameSpace(String s) {
        return "";
    }
}
