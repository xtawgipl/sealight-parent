package com.sealight.jwspeaker.mapper;

import com.sealight.jwspeaker.entity.UrlanagerBean;
import org.apache.ibatis.annotations.Param;

public interface UrlanagerBeanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UrlanagerBean record);

    int insertSelective(UrlanagerBean record);

    UrlanagerBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UrlanagerBean record);

    int updateByPrimaryKey(UrlanagerBean record);

    UrlanagerBean find(@Param("url") String url, @Param("typeName") String typeName);
}