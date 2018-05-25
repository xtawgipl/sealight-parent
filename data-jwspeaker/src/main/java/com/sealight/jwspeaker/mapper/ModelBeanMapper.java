package com.sealight.jwspeaker.mapper;

import com.sealight.jwspeaker.entity.ModelBean;

public interface ModelBeanMapper {
    int insert(ModelBean record);

    int insertSelective(ModelBean record);
}