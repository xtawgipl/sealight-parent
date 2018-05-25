package com.sealight.jwspeaker.mapper;

import com.sealight.jwspeaker.entity.BrandBean;

import java.util.List;

public interface BrandBeanMapper {
    int insert(BrandBean record);

    int insertSelective(BrandBean record);

    List<BrandBean> findAll();
}