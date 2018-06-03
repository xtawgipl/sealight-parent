package com.sealight.osram.service;

import com.sealight.osram.constants.Constant;
import com.sealight.osram.entity.*;
import com.sealight.osram.excel.LampExcelBean;
import com.sealight.osram.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author zhangjj
 * @create 2018-06-02 15:42
 **/
@Service
@Slf4j
public class ExcelServer {

    @Resource(name = "lightBeanMapper")
    private LightBeanMapper lightBeanMapper;

    @Resource(name = "lightInfosBeanMapper")
    private LightInfosBeanMapper lightInfosBeanMapper;

    @Resource(name = "technologyBeanMapper")
    private TechnologyBeanMapper technologyBeanMapper;

    @Resource(name = "typeBeanMapper")
    private TypeBeanMapper typeBeanMapper;

    @Resource(name = "modelBeanMapper")
    private ModelBeanMapper modelBeanMapper;


    private Map<String, Map<String, String>> getLampTitle(Integer manufacturerId){

        Map<String, String> headLampMap = new TreeMap<>();
        Map<String, String> innerLampmap = new TreeMap<>();
        Map<String, String> outerLampMap = new TreeMap<>();

        List<ModelBean> modelList = modelBeanMapper.findByManufacturer(manufacturerId);

        for(ModelBean model : modelList){
            List<TypeBean> typeList = typeBeanMapper.findByModel(model.getModelId());

            for(TypeBean type : typeList){
                List<LightBean> lightList = lightBeanMapper.findByType(type.getTypeId());
                for(LightBean light : lightList){
                    if(Constant.FRONT_POS_ID_LIST.contains(light.getPosId())){//前灯
                        headLampMap.put(light.getUseName(), "");
                    }else  if(Constant.REAR_POS_ID_LIST.contains(light.getPosId())){//后灯
                        outerLampMap.put(light.getUseName(), "");
                    }else  if(Constant.INTERNAL_POS_ID_LIST.contains(light.getPosId())){//内灯
                        innerLampmap.put(light.getUseName(), "");
                    }
                }
            }

        }

        Map<String, Map<String, String>> result = new HashMap<>();
        result.put("headLampMap", headLampMap);
        result.put("innerLampmap", innerLampmap);
        result.put("outerLampMap", outerLampMap);
        return result;
    }


    public List<LampExcelBean> getLampExcelList(ManufacturerBean manufacturerBean){

        List<LampExcelBean> lampExcelList = new ArrayList<>();

        Map<String, Map<String, String>> lampTitleData = getLampTitle(manufacturerBean.getManufacturerId());

        List<ModelBean> modelList = modelBeanMapper.findByManufacturer(manufacturerBean.getManufacturerId());

        for(ModelBean model : modelList){
            List<TypeBean> typeList = typeBeanMapper.findByModel(model.getModelId());

            if(typeList == null || typeList.isEmpty()){
                log.warn("model 没有数据： {}[{}] : {}[{}]", model.getManufacturerId(), manufacturerBean.getManufacturerName(),
                        model.getModelId(), model.getModelName());
            }

            for(TypeBean type : typeList){

                Map<String, String> headLampMap = new TreeMap<>();
                headLampMap.putAll(lampTitleData.get("headLampMap"));
                Map<String, String> innerLampmap = new TreeMap<>();
                innerLampmap.putAll(lampTitleData.get("innerLampmap"));
                Map<String, String> outerLampMap = new TreeMap<>();
                outerLampMap.putAll(lampTitleData.get("outerLampMap"));

                LampExcelBean lampExcelBean = new LampExcelBean();
                lampExcelBean.setManufacturer(manufacturerBean.getManufacturerName());
                lampExcelBean.setModel(model.getModelName());
                lampExcelBean.setType(String.format("%s %skW built %s.%s-%s.%s", type.getTypeName(), type.getTypeKw(),
                        type.getTypeFromMonth(), type.getTypeFromYear(),
                        type.getTypeToMonth(), type.getTypeToYear()));

                List<LightBean> lightList = lightBeanMapper.findByType(type.getTypeId());

                for(LightBean light : lightList){
                    List<TechnologyBean> technologyList = technologyBeanMapper.findByUseByType(type.getTypeId(), light.getUseId());

                    StringBuffer sb = new StringBuffer();
                    for(TechnologyBean technology : technologyList){
                        LightInfosBean lightInfos = lightInfosBeanMapper.findByKeys(String.valueOf(type.getTypeId()), String.valueOf(light.getUseId()), String.valueOf(technology.getTechnologyId()));

                        sb.append("[").append(technology.getTechnologyName()).append("]").
                                append(lightInfos.getOsramEce()).append("\r\n");
                    }

                    if(Constant.FRONT_POS_ID_LIST.contains(light.getPosId())){//前灯
                        headLampMap.put(light.getUseName(), sb.toString());
                    }else  if(Constant.REAR_POS_ID_LIST.contains(light.getPosId())){//后灯
                        outerLampMap.put(light.getUseName(), sb.toString());
                    }else  if(Constant.INTERNAL_POS_ID_LIST.contains(light.getPosId())){//内灯
                        innerLampmap.put(light.getUseName(), sb.toString());
                    }


                }

                lampExcelBean.setHeadLampMap(headLampMap);
                lampExcelBean.setInnerLampmap(innerLampmap);
                lampExcelBean.setOuterLampMap(outerLampMap);

                lampExcelList.add(lampExcelBean);
            }

        }

        return lampExcelList;
    }
}
