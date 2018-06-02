package com.sealight.osram.mapper;

import com.sealight.osram.entity.ManufacturerBean;

import java.util.List;

public interface ManufacturerBeanMapper {
    int deleteByPrimaryKey(Integer manufacturerId);

    int insert(ManufacturerBean record);

    int insertSelective(ManufacturerBean record);

    ManufacturerBean selectByPrimaryKey(Integer manufacturerId);

    int updateByPrimaryKeySelective(ManufacturerBean record);

    int updateByPrimaryKey(ManufacturerBean record);

    List<ManufacturerBean> findAll();
}