package com.osram.osram.mapper;

import com.osram.osram.entity.ManufacturerBean;

public interface ManufacturerBeanMapper {
    int deleteByPrimaryKey(Integer manufacturerId);

    int insert(ManufacturerBean record);

    int insertSelective(ManufacturerBean record);

    ManufacturerBean selectByPrimaryKey(Integer manufacturerId);

    int updateByPrimaryKeySelective(ManufacturerBean record);

    int updateByPrimaryKey(ManufacturerBean record);
}