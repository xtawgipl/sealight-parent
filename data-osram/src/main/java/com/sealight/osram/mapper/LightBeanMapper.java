package com.osram.osram.mapper;

import com.osram.osram.entity.LightBean;

public interface LightBeanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LightBean record);

    int insertSelective(LightBean record);

    LightBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LightBean record);

    int updateByPrimaryKey(LightBean record);
}