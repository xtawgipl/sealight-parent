package com.osram.osram.service;

import com.osram.osram.mapper.UrlanagerBeanMapper;
import com.osram.osram.entity.UrlanagerBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("urlanagerService")
public class UrlanagerService {

    @Resource(name = "urlanagerBeanMapper")
    private UrlanagerBeanMapper urlanagerBeanMapper;

    public boolean hasFetch(String url){
        UrlanagerBean urlManager = urlanagerBeanMapper.findByUrl(url);
        if(urlManager == null){
            return false;
        }else{
            return true;
        }
    }

    public int insert(UrlanagerBean record){
       return urlanagerBeanMapper.insert(record);
    }
}