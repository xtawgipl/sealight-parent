package com.sealight.osram.mapper;

import com.sealight.osram.entity.UrlanagerBean;

public interface UrlanagerBeanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UrlanagerBean record);

    int insertSelective(UrlanagerBean record);

    UrlanagerBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UrlanagerBean record);

    int updateByPrimaryKey(UrlanagerBean record);
}