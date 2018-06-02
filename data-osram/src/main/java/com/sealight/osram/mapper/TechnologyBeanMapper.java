package com.sealight.osram.mapper;

import com.sealight.osram.entity.TechnologyBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TechnologyBeanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TechnologyBean record);

    int insertSelective(TechnologyBean record);

    TechnologyBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TechnologyBean record);

    int updateByPrimaryKey(TechnologyBean record);


    TechnologyBean findByKeys(@Param("typeId") String typeId, @Param("useId") String useId, @Param("technologyId") String technologyId);

    List<TechnologyBean> findByTypeUse(@Param("typeId") String typeId, @Param("useId") String useId);

    List<TechnologyBean> findByUseByType(@Param("typeId") Integer typeId, @Param("useId") Integer useId);
}