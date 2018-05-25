package com.osram.osram.mapper;

import com.osram.osram.entity.UrlanagerBean;
import org.apache.ibatis.annotations.Param;

public interface UrlanagerBeanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UrlanagerBean record);

    int insertSelective(UrlanagerBean record);

    UrlanagerBean selectByPrimaryKey(Integer id);

    UrlanagerBean findByUrl(@Param("url") String url);

    int updateByPrimaryKeySelective(UrlanagerBean record);

    int updateByPrimaryKey(UrlanagerBean record);
}