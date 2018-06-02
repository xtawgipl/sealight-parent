package com.sealight.osram.mapper;

import com.sealight.osram.entity.TypeBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TypeBeanMapper {
    int deleteByPrimaryKey(Integer typeId);

    int insert(TypeBean record);

    int insertSelective(TypeBean record);

    TypeBean selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(TypeBean record);

    int updateByPrimaryKey(TypeBean record);


    int countAll();

    List<TypeBean> findByModel(@Param("modelId") Integer modelId);
}