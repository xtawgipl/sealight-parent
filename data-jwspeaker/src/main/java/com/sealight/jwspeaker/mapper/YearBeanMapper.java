package com.sealight.jwspeaker.mapper;

import com.sealight.jwspeaker.entity.YearBean;

public interface YearBeanMapper {
    int insert(YearBean record);

    int insertSelective(YearBean record);
}