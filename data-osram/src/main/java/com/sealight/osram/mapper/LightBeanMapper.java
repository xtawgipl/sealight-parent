package com.sealight.osram.mapper;

import com.sealight.osram.entity.LightBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LightBeanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LightBean record);

    int insertSelective(LightBean record);

    LightBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LightBean record);

    int updateByPrimaryKey(LightBean record);


    LightBean findByKeys(@Param("typeId") String typeId, @Param("useId") String useId, @Param("posId") String posId);

    List<LightBean> selectByType(@Param("typeId") String typeId);

    List<LightBean> findByType(@Param("typeId") Integer typeId);
}