package com.osram.osram.service;

import com.osram.osram.mapper.LightInfosBeanMapper;
import com.osram.osram.entity.LightInfosBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("lightInfosService")
public class LightInfosService {

    @Resource(name = "lightInfosBeanMapper")
    private LightInfosBeanMapper lightInfosBeanMapper;

    public void safeAdd(LightInfosBean record){
        LightInfosBean lightInfosBean = lightInfosBeanMapper.findByKeys(record);
        if(lightInfosBean == null){
            lightInfosBeanMapper.insert(record);
        }
    }
}