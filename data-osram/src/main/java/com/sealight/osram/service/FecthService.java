package com.sealight.osram.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sealight.osram.constants.Constant;
import com.sealight.osram.entity.*;
import com.sealight.osram.mapper.*;
import com.sealight.util.URLFetcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    @Resource(name = "modelBeanMapper")
    private ModelBeanMapper modelBeanMapper;

    @Resource(name = "urlanagerBeanMapper")
    private UrlanagerBeanMapper urlanagerBeanMapper;


    private ExecutorService executorService = Executors.newFixedThreadPool(50);


    public void fetchData(){
        if(urlanagerBeanMapper.findByUrl(Constant.ALL_MANUFACTURER_URL) == null){
            JSONObject manufacturerJson = URLFetcher.pickDataJSON(Constant.ALL_MANUFACTURER_URL);
            if(manufacturerJson.getInteger("error") == 0){
                JSONObject manufacturer = manufacturerJson.getJSONObject("result");
                for(Map.Entry<String, Object> manufacturerEntry :  manufacturer.entrySet()){
                    JSONObject manufacturerEntity = JSONObject.parseObject(manufacturerEntry.getValue().toString());
                    ManufacturerBean manufacturerBean = new ManufacturerBean(manufacturerEntity.getInteger("Manufacturer_id"), manufacturerEntity.getString("Manufacturer_name"));
                    manufacturerBeanMapper.insert(manufacturerBean);
                    logger.info("保存完成 ： " + manufacturerEntity);

                    String modelUrl = String.format(Constant.ALL_MODEL_URL, manufacturerBean.getManufacturerId());
                    if(urlanagerBeanMapper.findByUrl(modelUrl) == null){
                        JSONObject modelJson = URLFetcher.pickDataJSON(modelUrl);
                        if(modelJson.getInteger("error") == 0){
                            JSONArray modelArrayJson = modelJson.getJSONArray("result");
                            for(int i = 0; i < modelArrayJson.size(); ++i){
                                JSONObject modelOjbectJson = modelArrayJson.getJSONObject(i);
                                ModelBean modelBean = new ModelBean(manufacturerBean.getManufacturerId(),
                                        modelOjbectJson.getInteger("model_id"), modelOjbectJson.getString("model_name"));
                                modelBeanMapper.insert(modelBean);

                                String typeUrl = String.format(Constant.ALL_TYPE_URL, modelBean.getModelId());
                                if(urlanagerBeanMapper.findByUrl(typeUrl) == null){
                                    executorService.submit(new Runnable() {
                                        @Override
                                        public void run() {
                                            fetchLight(typeUrl);
                                        }
                                    });
                                }else{
                                    logger.info(String.format("typeUrl : %s 已经抓取过", typeUrl));
                                }
                            }
                        }else{
                            logger.error("model获取失败...");
                        }
                        urlanagerBeanMapper.insert(new UrlanagerBean("model", modelUrl));
                    }else{
                        logger.info(String.format("modelUrl : %s 已经抓取过", modelUrl));
                    }
                }
            }else{
                logger.error("品牌获取失败...");
            }
            urlanagerBeanMapper.insert(new UrlanagerBean("manufacturer", Constant.ALL_MANUFACTURER_URL));
        }else{
            logger.info(String.format("manufacturer_URL : %s 已经抓取过", Constant.ALL_MANUFACTURER_URL));
        }
    }


    private void fetchlamps(TechnologyBean technologyBean){
        technologyBeanMapper.insert(technologyBean);

        String lampsUrl = String.format(Constant.LAMPS_BY_USE_URL,
                technologyBean.getUseId(), technologyBean.getTypeId(), technologyBean.getTechnologyId());
        if(urlanagerBeanMapper.findByUrl(lampsUrl) == null) {
            JSONObject lampsJson = URLFetcher.pickDataJSON(lampsUrl);
            if(lampsJson.getInteger("error") == 0) {
                JSONArray lampsArrayJson = lampsJson.getJSONArray("result");
                for (int k = 0; k < lampsArrayJson.size(); ++k) {
                    int order = 0;
                    JSONObject lampsOjbectJson = lampsArrayJson.getJSONObject(k);
                    LightInfosBean lightInfosBean = new LightInfosBean(technologyBean.getUseId(),
                            technologyBean.getTypeId(),
                            technologyBean.getTechnologyId(),
                            order++,
                            lampsOjbectJson.getString("bullet_list"),
                            lampsOjbectJson.getString("lamp_info"),
                            lampsOjbectJson.getInteger("linecard_id"),
                            lampsOjbectJson.getString("linecard_name"),
                            lampsOjbectJson.getString("linksAutomotive"),
                            lampsOjbectJson.getString("osram_bestnr"),
                            lampsOjbectJson.getString("osram_ean"),
                            lampsOjbectJson.getString("osram_ece"),
                            lampsOjbectJson.getInteger("pillar_id"),
                            lampsOjbectJson.getString("pillar_image"),
                            lampsOjbectJson.getString("pillar_name"),
                            lampsOjbectJson.getString("prio"),
                            lampsOjbectJson.getString("product_image"),
                            lampsOjbectJson.getString("product_zmp"),
                            lampsOjbectJson.getString("usp")
                    );
                    lightInfosBeanMapper.insert(lightInfosBean);
                }
            }
        }else{
            logger.info(String.format("lampsUrl : %s 已经抓取过", lampsUrl));
        }
    }


    private void fetchLight(String typeUrl){
        JSONObject typeJson = URLFetcher.pickDataJSON(typeUrl);
        if(typeJson.getInteger("error") == 0){
            JSONArray typeArrayJson = typeJson.getJSONArray("result");
            for(int j = 0; j < typeArrayJson.size(); ++j){
                JSONObject typeOjbectJson = typeArrayJson.getJSONObject(j);
                String typeFrom = typeOjbectJson.getString("type_from");
                String typeFromYear = typeFrom.substring(0, 4);
                String typeFromMonth = typeFrom.substring(4, 6);
                String typeTo = typeOjbectJson.getString("type_to");
                String typeToYear = typeTo.substring(0, 4);
                String typeToMonth = typeTo.substring(4, 6);
                TypeBean typeBean = new TypeBean(typeOjbectJson.getInteger("type_id"),
                        typeOjbectJson.getInteger("model_id"),
                        typeFrom,
                        typeFromYear,
                        typeFromMonth,
                        typeTo,
                        typeToYear,
                        typeToMonth,
                        typeOjbectJson.getString("type_kw"),
                        typeOjbectJson.getString("type_axles"),
                        typeOjbectJson.getString("type_tonnage"),
                        typeOjbectJson.getInteger("variant_id"),
                        typeOjbectJson.getString("type_name"),
                        typeOjbectJson.getString("kba"));
                typeBeanMapper.insert(typeBean);

                String lightUrl = String.format(Constant.ALL_LIGHT_URL, typeBean.getTypeId());
                if(urlanagerBeanMapper.findByUrl(lightUrl) == null){
                    JSONObject lightJson = URLFetcher.pickDataJSON(lightUrl);
                    if(lightJson.getInteger("error") == 0) {
                        JSONObject light = lightJson.getJSONObject("result");
                        for (Map.Entry<String, Object> lightEntry : light.entrySet()) {
                            JSONObject lightEntity = JSONObject.parseObject(lightEntry.getValue().toString());
                            LightBean lightBean = new LightBean(typeBean.getTypeId(),
                                    lightEntity.getInteger("use_id"),
                                    lightEntity.getInteger("pos_id"),
                                    lightEntity.getString("use_name"));
                            lightBeanMapper.insert(lightBean);

                            String technologyUrl = String.format(Constant.ALL_TECHNOLOGY_URL, lightBean.getUseId(), lightBean.getTypeId());
                            if(urlanagerBeanMapper.findByUrl(technologyUrl) == null) {
                                JSONObject technologyJson = URLFetcher.pickDataJSON(technologyUrl);
                                if (technologyJson.getInteger("error") == 0) {
                                    Object result = technologyJson.get("result");
                                    if(result instanceof JSONObject){
                                        JSONObject technology = technologyJson.getJSONObject("result");
                                        for (Map.Entry<String, Object> technologyJsonEntry : technology.entrySet()) {
                                            JSONObject technologyEntity = JSONObject.parseObject(technologyJsonEntry.getValue().toString());
                                            TechnologyBean technologyBean = new TechnologyBean(lightBean.getUseId(),
                                                    lightBean.getTypeId(),
                                                    technologyEntity.getInteger("technology_id"),
                                                    technologyEntity.getString("technology_name"));

                                            fetchlamps(technologyBean);
                                        }
                                    }else{
                                        JSONArray technology = technologyJson.getJSONArray("result");
                                        for(int o = 0; o < technology.size(); ++o){
                                            JSONObject technologyObj = technology.getJSONObject(o);
                                            TechnologyBean technologyBean = new TechnologyBean(lightBean.getUseId(),
                                                    lightBean.getTypeId(),
                                                    technologyObj.getInteger("technology_id"),
                                                    technologyObj.getString("technology_name"));
                                            fetchlamps(technologyBean);
                                        }
                                    }
                                    urlanagerBeanMapper.insert(new UrlanagerBean("technology", technologyUrl));
                                }else{
                                    logger.error("technology获取失败...");
                                }
                            }else{
                                logger.info(String.format("technologyUrl : %s 已经抓取过", technologyUrl));
                            }
                        }
                        urlanagerBeanMapper.insert(new UrlanagerBean("light", lightUrl));
                    }else{
                        logger.error("light获取失败...");
                    }
                }else{
                    logger.info(String.format("lightUrl : %s 已经抓取过", lightUrl));
                }
            }
            urlanagerBeanMapper.insert(new UrlanagerBean("type", typeUrl));
        }else{
            logger.error("type获取失败...");
        }
    }
}
