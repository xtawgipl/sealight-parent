package com.sealight.osram.mapper;

import com.sealight.osram.entity.ModelBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ModelBeanMapper {
    int deleteByPrimaryKey(Integer modelId);

    int insert(ModelBean record);

    int insertSelective(ModelBean record);

    ModelBean selectByPrimaryKey(Integer modelId);

    int updateByPrimaryKeySelective(ModelBean record);

    int updateByPrimaryKey(ModelBean record);

    List<ModelBean> findByManufacturer(@Param("manufacturerId") Integer manufacturerId);
}