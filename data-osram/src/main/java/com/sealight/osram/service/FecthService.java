package com.sealight.osram.service;

import com.alibaba.fastjson.JSONObject;
import com.sealight.osram.constants.Constant;
import com.sealight.osram.mapper.*;
import com.sealight.util.URLFetcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;

/**
 * @author zhangjj
 * @create 2018-05-26 17:27
 **/
@Service("fecthService")
public class FecthService {

    private static Logger logger = LoggerFactory.getLogger(FecthService.class);

    @Resource(name = "lightBeanMapper")
    private LightBeanMapper lightBeanMapper;

    @Resource(name = "lightInfosBeanMapper")
    private LightInfosBeanMapper lightInfosBeanMapper;

    @Resource(name = "manufacturerBeanMapper")
    private ManufacturerBeanMapper manufacturerBeanMapper;

    @Resource(name = "technologyBeanMapper")
    private TechnologyBeanMapper technologyBeanMapper;

    @Resource(name = "typeBeanMapper")
    private TypeBeanMapper typeBeanMapper;

    @Resource(name = "urlanagerBeanMapper")
    private UrlanagerBeanMapper urlanagerBeanMapper;


    public void fetchData(){
        for(String manufacturerUrl : Constant.ALL_MANUFACTURER_URL){
            if(urlanagerBeanMapper.findByUrl(manufacturerUrl) == null){
                JSONObject manufacturerJson = URLFetcher.pickDataJSON(manufacturerUrl);
                if(manufacturerJson.getInteger("error") == 0){
                    JSONObject manufacturer = manufacturerJson.getJSONObject("result");
                    for(Map.Entry<String, Object> manufacturerEntry :  manufacturer.entrySet()){

                    }
                }
            }else{
                logger.info(String.format("manufacturer_URL : %s 已经抓取过", manufacturerUrl));
            }
        }
    }
}
