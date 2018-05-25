package com.sealight.jwspeaker.service;

import com.sealight.jwspeaker.entity.UrlanagerBean;
import com.sealight.jwspeaker.mapper.UrlanagerBeanMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhangjj
 * @create 2018-05-22 21:41
 **/
@Service("urlManagerService")
public class UrlManagerService {

    @Resource(name = "urlanagerBeanMapper")
    private UrlanagerBeanMapper urlanagerBeanMapper;


    public void add(String url, String typeName){
        UrlanagerBean urlanagerBean = new UrlanagerBean();
        urlanagerBean.setUrl(url);
        urlanagerBean.setTypeName(typeName);
        urlanagerBeanMapper.insert(urlanagerBean);
    }

    public boolean hasFetch(String url, String typeName){

        UrlanagerBean urlanagerBean = urlanagerBeanMapper.find(url, typeName);

        return urlanagerBean == null ? false : true;
    }
}
