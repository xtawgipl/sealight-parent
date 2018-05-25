package com.sealight.jwspeaker.mapper;

import com.sealight.jwspeaker.entity.LampBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LampBeanMapper {
    int insert(LampBean record);

    int insertSelective(LampBean record);

    List<LampBean> findByBrand(@Param("brand") String brand);
}