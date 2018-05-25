package com.osram.osram.mapper;

import com.osram.osram.entity.TechnologyBean;

public interface TechnologyBeanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TechnologyBean record);

    int insertSelective(TechnologyBean record);

    TechnologyBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TechnologyBean record);

    int updateByPrimaryKey(TechnologyBean record);
}