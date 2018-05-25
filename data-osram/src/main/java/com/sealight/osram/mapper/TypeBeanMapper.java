package com.osram.osram.mapper;

import com.osram.osram.entity.TypeBean;

import java.util.List;

public interface TypeBeanMapper {
    int deleteByPrimaryKey(Integer typeId);

    int insert(TypeBean record);

    int insertSelective(TypeBean record);

    TypeBean selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(TypeBean record);

    int updateByPrimaryKey(TypeBean record);

    List<TypeBean> find();

}