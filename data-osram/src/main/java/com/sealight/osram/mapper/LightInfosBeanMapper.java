package com.sealight.osram.mapper;

import com.sealight.osram.entity.LightInfosBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LightInfosBeanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LightInfosBean record);

    int insertSelective(LightInfosBean record);

    LightInfosBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LightInfosBean record);

    int updateByPrimaryKey(LightInfosBean record);

    LightInfosBean findByKeys(@Param("typeId") String typeId, @Param("useId") String useId, @Param("technologyId") String technologyId);

}