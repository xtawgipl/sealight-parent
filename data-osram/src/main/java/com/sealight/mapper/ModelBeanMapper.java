package com.sealight.mapper;

import com.sealight.entity.ModelBean;

public interface ModelBeanMapper {
    int deleteByPrimaryKey(Integer modelId);

    int insert(ModelBean record);

    int insertSelective(ModelBean record);

    ModelBean selectByPrimaryKey(Integer modelId);

    int updateByPrimaryKeySelective(ModelBean record);

    int updateByPrimaryKey(ModelBean record);
}