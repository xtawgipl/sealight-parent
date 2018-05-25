package com.osram.osram.mapper;

import com.osram.osram.entity.LightInfosBean;

public interface LightInfosBeanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LightInfosBean record);

    int insertSelective(LightInfosBean record);

    LightInfosBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LightInfosBean record);

    int updateByPrimaryKeyWithBLOBs(LightInfosBean record);

    int updateByPrimaryKey(LightInfosBean record);

    LightInfosBean findByKeys(LightInfosBean record);

}