package com.sealight.osram.mapper;

import com.sealight.osram.entity.TypeBean;

public interface TypeBeanMapper {
    int deleteByPrimaryKey(Integer typeId);

    int insert(TypeBean record);

    int insertSelective(TypeBean record);

    TypeBean selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(TypeBean record);

    int updateByPrimaryKey(TypeBean record);
}