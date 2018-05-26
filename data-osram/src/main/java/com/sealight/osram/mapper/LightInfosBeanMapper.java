package com.sealight.osram.mapper;

import com.sealight.osram.entity.LightInfosBean;

public interface LightInfosBeanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LightInfosBean record);

    int insertSelective(LightInfosBean record);

    LightInfosBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LightInfosBean record);

    int updateByPrimaryKey(LightInfosBean record);
}